package com.nott.security.common.utils;

import org.apache.commons.io.IOUtils;

import java.io.PrintWriter;
import java.io.StringWriter;

/**
 * @Author: wangjun
 * @Description:
 * @Date: created in 2020/7/3 14:45
 * @Modified By:
 **/
public class ExceptionUtils {

    public ExceptionUtils() {}

    public static String getErrorInfo(Exception e) {
        if (e == null) {
            return "";
        } else {
            PrintWriter pw = null;

            String var4;
            try {
                StringWriter sw = new StringWriter();
                pw = new PrintWriter(sw);
                e.printStackTrace(pw);
                String result = sw.toString();
                var4 = result;
            } finally {
                IOUtils.closeQuietly(pw);
            }

            return var4;
        }
    }
}
