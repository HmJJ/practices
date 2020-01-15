package com.nott.poi.excel.factory;

import com.alibaba.fastjson.JSONArray;

import java.io.IOException;
import java.io.InputStream;

/**
 * @Author: wangjun
 * @Description:
 * @Date: created in 2020/1/15 11:18
 * @Modified By:
 **/
public interface ExcelFactory {

    JSONArray readFile(InputStream is) throws IOException;

}
