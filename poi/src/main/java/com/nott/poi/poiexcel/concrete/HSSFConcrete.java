package com.nott.poi.poiexcel.concrete;

import com.alibaba.fastjson.JSONArray;
import com.nott.poi.poiexcel.factory.ExcelFactoryImpl;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Workbook;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

/**
 * @Author: wangjun
 * @Description: xls
 * @Date: created in 2020/1/15 11:17
 * @Modified By:
 **/
@Component(value = "hSSFConcrete")
public class HSSFConcrete<T> extends ExcelFactoryImpl {

    @Override
    public JSONArray readFile(InputStream is) throws IOException {
        Workbook wb = new HSSFWorkbook(is);
        JSONArray array = parse(wb);
        return array;
    }

    @Override
    public byte[] write(List params) throws IOException {
        log.info("进入了实现方法HSSFConcrete...");
        return new byte[0];
    }
}
