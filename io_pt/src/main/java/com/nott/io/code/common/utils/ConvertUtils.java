package com.nott.io.code.common.utils;

/**
 * @Author: allen
 * @Description:
 * @Date: created in 2018/9/4 9:48
 * @Modified By:
 */
public class ConvertUtils {

    public static Long convertToLong(Object obj) {
        Long result = Long.parseLong(String.valueOf(obj));
        return result;
    }
}
