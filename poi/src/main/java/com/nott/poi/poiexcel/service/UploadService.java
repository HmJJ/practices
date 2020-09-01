package com.nott.poi.poiexcel.service;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.nott.poi.poiexcel.factory.ExcelFactory;
import com.nott.poi.poiexcel.factory.ExcelFactoryConcrete;
import com.nott.poi.code.vo.UploadVo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
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
@Service(value = "poiUploadService")
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
            writeFile(array, "D:\\data\\shopbox\\updateAccountDetail_20200310.txt");
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
        BigDecimal amount = new BigDecimal(jsonObject.getString("调整后货款金额"));
        BigDecimal balance = new BigDecimal(jsonObject.getString("差值"));
        String opt = jsonObject.getString("操作");
        // 调整前货款为0时，用insert语句，否则用update
        if ("删去".equals(opt)) {
            pw.println("set @name='"+ name +"', @operateTime='"+ operateTime +"', @amount="+amount+", @balance="+balance+";\n" +
                    "set @partyId=(select party_id from pty_dealer where name = @name);\n" +
                    "delete from fin_account_detail where party_id = @partyId and trade_time = @operateTime and credit_amount = @amount;\n" +
                    "update fin_account t1, (select MAX(balance)+@balance as 'balance' from fin_account where party_id = @partyId and account_code = 'advance') t2 set t1.balance = t2.balance where t1.party_id = @partyId and t1.account_code = 'advance';\n");
        } else {
            pw.println("set @name='"+ name +"', @operateTime='"+ operateTime +"', @amount="+amount+", @balance="+balance+";\n" +
                    "set @partyId=(select party_id from pty_dealer where name = @name);\n" +
                    "update fin_account t1, (select MAX(balance)+@balance as 'balance' from fin_account where party_id = @partyId and account_code = 'payable') t2 set t1.balance = t2.balance where t1.party_id = @partyId and t1.account_code = 'payable';\n");
        }
    }

}
