package com.nott.poi.excel.concrete;

import com.alibaba.fastjson.JSONArray;
import com.nott.poi.excel.factory.ExcelFactoryImpl;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

/**
 * @Author: wangjun
 * @Description: xlsx
 * @Date: created in 2020/1/15 11:16
 * @Modified By:
 **/
@Component(value = "xSSFConcrete")
public class XSSFConcrete<T> extends ExcelFactoryImpl<T> {

    @Override
    public JSONArray readFile(InputStream is) throws IOException {
        Workbook wb = new XSSFWorkbook(is);
        JSONArray array = parse(wb);
        return array;
    }

    @Override
    public byte[] write(List params) throws IOException {
        log.info("进入了实现方法XSSFConcrete...");
        Workbook wb = new XSSFWorkbook();
        byte[] bytes = writeData(wb, params);
        return bytes;
    }


}
