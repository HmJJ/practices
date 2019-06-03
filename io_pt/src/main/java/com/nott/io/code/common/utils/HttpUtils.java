package com.nott.io.code.common.utils;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.net.URLEncoder;

/**
 * Created by allen on 2016/4/29.
 */
public class HttpUtils {

    private static final Logger log = LoggerFactory.getLogger(HttpUtils.class);
    private static String[] IEBrowserSignals = {"MSIE", "Trident", "Edge"};


    public static String urlEncode(String value) {

        String result = "";
        try {
            result = URLEncoder.encode(value, "UTF-8").replace("+", "%20");

        } catch (UnsupportedEncodingException e) {
            log.error(e.getMessage());
        }
        return result;
    }


    public static String urlDecode(String value) {

        String result = "";
        try {
            result = URLDecoder.decode(value, "UTF-8");

        } catch (UnsupportedEncodingException e) {
            log.error(e.getMessage());
        }
        return result;
    }

//    public static boolean isMSBrowser(HttpServletRequest request) {
//        String userAgent = request.getHeader("User-Agent");
//        for (String signal : IEBrowserSignals) {
//            if (userAgent.contains(signal))
//                return true;
//        }
//        return false;
//    }

//    public static String convert(HttpServletRequest request, String fileName) {
//        String result = "";
//        boolean isMSIE = isMSBrowser(request);
//        if (isMSIE) {
//            result = urlEncode(fileName);
//        } else {
//            result = convert(fileName);
//        }
//        return result;
//    }

    public static String convertToUTF8(String str) {

        String result = null;
        try {
            result = new String(str.getBytes("ISO-8859-1"), "UTF-8");
        } catch (UnsupportedEncodingException e) {
            log.error("convertToUTF8 error:{}", e.getMessage());
        }
        return result;

    }

    private static String convert(String fileName) {
        String result = "";
        try {
            result = new String(fileName.getBytes("UTF-8"), "ISO-8859-1");
        } catch (UnsupportedEncodingException e) {
            log.error("convert encode error:{}", e.getMessage());
        }
        return result;
    }

    public static String getUTF8Str(String str){
        String result = "";
        try {
            if (str.equals(new String(str.getBytes("ISO-8859-1"), "ISO-8859-1"))) {      //判断是不是ISO-8859-1
                result = convertToUTF8(str);
            }else {
                result = str;
            }
        } catch (Exception e) {
            log.error("编码转换错误！");
        }
        return result;
    }

}
