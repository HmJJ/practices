package com.nott.poi.excel.factory;

import com.alibaba.fastjson.JSONArray;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

/**
 * @Author: wangjun
 * @Description:
 * @Date: created in 2020/1/15 11:18
 * @Modified By:
 **/
public interface ExcelFactory<T> {

    JSONArray readFile(InputStream is) throws IOException;
    byte[] write(List<T> params) throws IOException;

}
