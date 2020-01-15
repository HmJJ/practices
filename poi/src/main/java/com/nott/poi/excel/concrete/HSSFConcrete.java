package com.nott.poi.excel.concrete;

import com.alibaba.fastjson.JSONArray;
import com.nott.poi.excel.factory.ExcelFactoryImpl;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Workbook;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.io.InputStream;

/**
 * @Author: wangjun
 * @Description: xls
 * @Date: created in 2020/1/15 11:17
 * @Modified By:
 **/
@Component
public class HSSFConcrete extends ExcelFactoryImpl {

    @Override
    public JSONArray readFile(InputStream is) throws IOException {
        Workbook wb = new HSSFWorkbook(is);
        JSONArray array = parse(wb);
        return array;
    }
}
