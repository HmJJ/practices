package com.nott.security.common.exception;

/**
 * @Author: wangjun
 * @Description:
 * @Date: created in 2020/7/3 14:47
 * @Modified By:
 **/
public class LoginException extends RuntimeException {
    public static final String LOGIN_ERROR = "9001";
    private String statusCode = "9001";

    public LoginException(String msg) {
        super(msg);
    }

    public String getStatusCode() {
        return this.statusCode;
    }
}
