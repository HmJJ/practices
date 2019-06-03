package com.nott.io.code.common.json;


import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;


public final class JacksonUtils {

    private static final Logger log = LoggerFactory.getLogger(JacksonUtils.class);
    private static final ObjectMapper objectMapper;

    static {
        objectMapper = new ObjectMapper();

    }

    public static <T> String toJSON(T obj) {
        String result;
        try {
            result = objectMapper.writeValueAsString(obj);
        } catch (Exception e) {
            log.error("Java toJSON error", e);
            throw new RuntimeException(e);
        }
        return result;
    }

    public static <T> T fromJSON(String json, Class<T> type) {
        T obj;
        try {
            obj = objectMapper.readValue(json, type);
        } catch (Exception e) {
            log.error("JSON to Java object error", e);
            throw new RuntimeException(e);
        }
        return obj;
    }

    /**
     * 把json字符串转化为集合对象
     * @param json json字符串，数组，必须带有[]
     * @param parameterClass  具体的CLASS（元数据）对象
     * @return 返回ArrayList<parameterClass>
     */
    public static <T> List<T> jsonToList(String json, Class<?> parameterClass) {
        JavaType javaType = objectMapper.getTypeFactory().constructParametricType(ArrayList.class, parameterClass);
        try {
            return objectMapper.readValue(json,javaType);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public static List<LinkedHashMap> jsonToList(String json) {
        try {
            return objectMapper.readValue(json, ArrayList.class);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public static ObjectMapper getObjectMapper() {
        return objectMapper;
    }

}
