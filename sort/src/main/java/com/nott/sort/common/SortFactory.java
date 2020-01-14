package com.nott.sort.common;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @Author: wangjun
 * @Description:
 * @Date: created in 2020/1/14 15:05
 * @Modified By:
 **/
@Service
public class SortFactory {

    private static final String prefix = "Sort";

    @Autowired
    Map<String, Sort> map = new ConcurrentHashMap<>(10);

    public Sort getService(String typeName) {
        String type = typeName + prefix;
        return map.get(type);
    }

}
