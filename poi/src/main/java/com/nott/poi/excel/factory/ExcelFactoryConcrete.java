package com.nott.poi.excel.factory;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @Author: wangjun
 * @Description:
 * @Date: created in 2020/1/15 11:22
 * @Modified By:
 **/
@Service
public class ExcelFactoryConcrete {

    private static final String suffix = "SSFConcrete";

    @Autowired
    Map<String, ExcelFactory> map = new ConcurrentHashMap<>(2);

    public ExcelFactory getService(String type) {
        type = type.toLowerCase();
        String head;
        if ("xls".equals(type)) {
            head = "h";
        } else {
            head = "x";
        }
        ExcelFactory factory = map.get(head + suffix);
        return factory;
    }

}
