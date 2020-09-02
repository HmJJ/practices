/**
 * @Author: nott
 * @Description:
 * @Date: created in 2020/9/2 18:30
 * @Modified By:
 */
package com.nott.poi.poiexcel.service;

import com.alibaba.fastjson.JSONArray;
import com.nott.poi.eaxyexcel.vo.TemplateVo;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@Service
public class FormConvert {

    public TemplateVo convert(MultipartFile file) {
        TemplateVo vo = new TemplateVo();
        Workbook wb = null;
        try {
            wb = new XSSFWorkbook(file.getInputStream());
        } catch (IOException e) {
            e.printStackTrace();
        }
        if (wb != null) {
            JSONArray array = parse(wb);
        }

        return vo;
    }

    private JSONArray parse(Workbook wb) {
        return new JSONArray();
    }

}
