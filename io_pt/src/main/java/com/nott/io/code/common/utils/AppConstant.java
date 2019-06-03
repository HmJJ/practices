package com.nott.io.code.common.utils;


import com.wayonsys.common.domain.CodeInfo;

/**
 * Created by allen on 2016/4/14.
 */
public class AppConstant {

    public static final  Integer ZERO = 0;
    public static final  Integer ONE = 1;
    public static final String YES = "yes";
    public static final String NO = "no";

    public static class AppCode {
        public static final CodeInfo IOS = new CodeInfo(100,"苹果手机");
        public static final CodeInfo ANDROID = new CodeInfo(200,"安卓手机");
        public static final CodeInfo PC = new CodeInfo(300,"电脑");
        public static final CodeInfo WEIXIN = new CodeInfo(400,"微信");
    }

    public static class Deleted {
        public static final CodeInfo UN_DELETED		= new CodeInfo(0, "未删除");
        public static final CodeInfo DELETED 		= new CodeInfo(1, "已删除");
    }
}
