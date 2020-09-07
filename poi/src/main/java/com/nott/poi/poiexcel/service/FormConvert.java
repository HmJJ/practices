/**
 * @Author: nott
 * @Description:
 * @Date: created in 2020/9/2 18:30
 * @Modified By:
 */
package com.nott.poi.poiexcel.service;

import com.alibaba.fastjson.JSONArray;
import com.nott.poi.eaxyexcel.vo.TemplateVo;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.ArrayList;

@Service
public class FormConvert {

    public TemplateVo convert(MultipartFile file) {
        TemplateVo vo = new TemplateVo();
        Workbook wb = null;
        try {
            String originalFilename = file.getOriginalFilename();
            System.out.println(originalFilename);
            String name = originalFilename.substring(0, originalFilename.lastIndexOf("."));
            String suffix = originalFilename.substring(originalFilename.lastIndexOf(".") + 1);
            System.out.println(name+"  "+suffix);
            wb = new XSSFWorkbook(file.getInputStream());
        } catch (IOException e) {
            e.printStackTrace();
        }
        if (wb != null) {
            FormulaEvaluator evaluator = wb.getCreationHelper().createFormulaEvaluator();
            int sheetNum = wb.getNumberOfSheets();
            for (int i=0; i<sheetNum; i++) {
                Sheet sheet = wb.getSheetAt(i);
                int rowNum = sheet.getLastRowNum();
                for (int r=0; r<rowNum; r++) {
                    Row row = sheet.getRow(r);
                    if (row == null) continue;
                    int columnNum = row.getLastCellNum();
                    for (int c=0; c<columnNum; c++) {
                        Cell cell = row.getCell(c);
                        CellValue cellValue = evaluator.evaluate(cell);
                        if (cellValue == null) continue;
                        String flagCode = cellValue.formatAsString().replaceAll("\"", "");
                        if (flagCode.contains("${")) {
                            System.out.println(r + " "+c+" "+flagCode);
                        }
                    }
                }
            }
        }

        return vo;
    }

}
