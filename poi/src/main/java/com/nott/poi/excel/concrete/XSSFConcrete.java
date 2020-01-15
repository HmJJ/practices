package com.nott.poi.excel.concrete;

import com.alibaba.fastjson.JSONArray;
import com.nott.poi.excel.factory.ExcelFactoryImpl;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.io.InputStream;

/**
 * @Author: wangjun
 * @Description: xlsx
 * @Date: created in 2020/1/15 11:16
 * @Modified By:
 **/
@Component
public class XSSFConcrete extends ExcelFactoryImpl {

    @Override
    public JSONArray readFile(InputStream is) throws IOException {
        Workbook wb = new XSSFWorkbook(is);
        JSONArray array = parse(wb);
        return array;
    }
}
