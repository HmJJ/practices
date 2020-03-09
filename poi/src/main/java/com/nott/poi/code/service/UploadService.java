package com.nott.poi.code.service;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.nott.poi.excel.factory.ExcelFactory;
import com.nott.poi.excel.factory.ExcelFactoryConcrete;
import com.nott.poi.code.vo.UploadVo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.math.BigDecimal;
import java.util.Iterator;

/**
 * @Author: wangjun
 * @Description:
 * @Date: created in 2020/1/15 10:42
 * @Modified By:
 **/
@Service
public class UploadService {

    private static final Logger log = LoggerFactory.getLogger(UploadService.class);
    @Autowired
    private ExcelFactoryConcrete excelFactoryConcrete;

    public void upload(UploadVo vo) {
//        validate(vo);
//        Long orderId = vo.getOrderId();
//        log.info("orderId: {}", orderId);
        for (MultipartFile file : vo.getFiles()) {
            JSONArray array = parseExcel(file);
            if (array == null)
                continue;
            /*Iterator iterator = array.iterator();
            while (iterator.hasNext()) {
                JSONObject object = (JSONObject) iterator.next();
                writeFile(object);
            }*/
            writeFile(array, "D:\\data\\shopbox\\updateAccountDetail.txt");
//            writeFile(array, "D:\\data\\shopbox\\updateDealerEmail.txt");
        }
    }

    private void validate(UploadVo vo) {
        if (vo.getOrderId() == null) {
            log.error("orderId为必传项!");
        }
        if (vo.getFiles() == null || vo.getFiles().isEmpty()) {
            log.error("请选择商品明细的excel!");
        }
    }

    private JSONArray parseExcel(MultipartFile file) {
        String[] params = file.getOriginalFilename().split("\\.");
        ExcelFactory xmlFactory = excelFactoryConcrete.getService(params[1]);
        JSONArray array = null;
        try {
            array = xmlFactory.readFile(file.getInputStream());
        } catch (IOException e) {
            log.error(e.getCause().getMessage());
            log.error("读取文件失败！");
        }
        return array;
    }

    private void writeFile(JSONArray array, String pathname) {
        FileWriter  fw = null;
        try {
            File file = new File(pathname);
            if (file.exists()) {
                file.delete();
            }
            fw = new FileWriter(file, true);
        } catch (IOException e) {
            e.printStackTrace();
        }
        PrintWriter pw = new PrintWriter(fw);
        Iterator iterator = array.iterator();
        while (iterator.hasNext()) {
            JSONObject jsonObject = (JSONObject) iterator.next();
//            updateDealerEmail(jsonObject, pw);
            updateAccountDetail(jsonObject, pw);
        }

        pw.flush();
        try {
            fw.flush();
            pw.close();
            fw.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void updateDealerEmail(JSONObject jsonObject, PrintWriter pw) {
        String name = jsonObject.getString("门店名称");
        if (name == null) {
            return;
        }
        String email1 = jsonObject.getString("对账单Email");
        String email2 = jsonObject.getString("发货单Email");
        pw.println("set @name='"+ name +"', @email1='"+ email1 +"', @email2="+email2+";\n" +
                "update pty_dealer set email1 = @email1 where name = @name and ISNULL(email1);\n" +
                "update pty_dealer set email2 = @email2 where name = @name and ISNULL(email2);\n");
    }

    private void updateAccountDetail(JSONObject jsonObject, PrintWriter pw) {
        String name = jsonObject.getString("门店名称");
        if (name == null) {
            return;
        }
        String operateTime = jsonObject.getString("操作时间");
        String remark = jsonObject.getString("备注");
        BigDecimal oldAmount = new BigDecimal(jsonObject.getString("调整前货款金额"));
        BigDecimal amount = new BigDecimal(jsonObject.getString("调整后货款金额"));
        BigDecimal balance = new BigDecimal(jsonObject.getString("差值"));
        // 调整前货款为0时，用insert语句，否则用update
        if (BigDecimal.ZERO.compareTo(oldAmount) == 0) {
            pw.println("set @name='"+ name +"', @operateTime='"+ operateTime +"', @oldAmount="+oldAmount+", @amount="+amount+", @balance="+balance+", @remark='"+remark+"';\n" +
                    "set @partyId=(select party_id from pty_dealer where name = @name);\n" +
                    "set @tradeUser=(select id from idm_user idm left join pty_staff pty on pty.party_id = idm.party_id where pty.mobile='15121126805');\n" +
                    "insert into fin_account_detail (`party_id`, `fee_type`, `credit_account`, `credit_amount`, `trade_user_id`, `trade_time`, `remark`) VALUES (@partyId, '300', 'advance', @amount, @tradeUser, @operateTime, @remark);\n" +
                    "update fin_account t1, (select MAX(balance)+@balance as 'balance' from fin_account where party_id = @partyId and account_code = 'advance') t2 set t1.balance = t2.balance where t1.party_id = @partyId and t1.account_code = 'advance';\n");
        } else {
            pw.println("set @name='"+ name +"', @operateTime='"+ operateTime +"', @oldAmount="+oldAmount+", @amount="+amount+", @balance="+balance+", @remark='"+remark+"';\n" +
                    "set @partyId=(select party_id from pty_dealer where name = @name);\n" +
                    "update fin_account_detail set credit_amount = @amount, remark = @remark where party_id = @partyId and trade_time = @operateTime and credit_amount = @oldAmount;\n" +
                    "update fin_account t1, (select MAX(balance)+@balance as 'balance' from fin_account where party_id = @partyId and account_code = 'advance') t2, (select credit_amount from fin_account_detail where party_id = @partyId and trade_time = @operateTime and credit_amount = @amount) t3 set t1.balance = t2.balance where t1.party_id = @partyId and t1.account_code = 'advance' and t3.credit_amount = @amount;\n");
        }
    }

}
