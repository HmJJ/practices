package com.nott.io.code.common.utils;

import org.apache.commons.io.IOUtils;

import java.io.PrintWriter;
import java.io.StringWriter;

public class ExceptionUtils {

    public static String getErrorInfo(Exception e) {
        if(e == null) {
            return "";
        }
        PrintWriter pw = null;
        try {
            StringWriter sw = new StringWriter();
            pw = new PrintWriter(sw);
            e.printStackTrace(pw);
            String result = sw.toString();
            return result;
        }finally {
            IOUtils.closeQuietly(pw);
        }
    }
}
