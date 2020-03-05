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
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
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
            writeFile(array);
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

    private void writeFile(JSONArray array) {
        FileWriter  fw = null;
        try {
            File txt = new File("D:\\data\\shopbox\\updateDealereEmail.txt");
            fw = new FileWriter(txt, true);
        } catch (IOException e) {
            e.printStackTrace();
        }
        PrintWriter pw = new PrintWriter(fw);
        Iterator iterator = array.iterator();
        while (iterator.hasNext()) {
            JSONObject jsonObject = (JSONObject) iterator.next();
            String name = jsonObject.getString("系统名称");
            if (name == null) {
                return;
            }
            String email1 = jsonObject.getString("对账单Email");
            String email2 = jsonObject.getString("发货单Email");
            pw.println("set @name = '"+ name +"', @email1 = '"+ email1 +"', @email2 = '"+email2+"';\n" +
                    "update pty_dealer set email1 = @email1 where name = @name and ISNULL(email1);\n" +
                    "update pty_dealer set email2 = @email2 where name = @name and ISNULL(email2);\n");
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

}
