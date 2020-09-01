package com.nott.poi.poiexcel.factory;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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
public class ExcelFactoryConcrete<T> {

    private static final Logger log = LoggerFactory.getLogger(ExcelFactoryConcrete.class);

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
        ExcelFactory<T> factory = map.get(head + suffix);
        return factory;
    }

}
