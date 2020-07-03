package com.nott.security.common.exception;

import org.springframework.security.authentication.AuthenticationServiceException;

/**
 * @Author: wangjun
 * @Description:
 * @Date: created in 2020/7/3 14:42
 * @Modified By:
 **/
public class AuthException extends AuthenticationServiceException {
    private String code = "9002";

    public AuthException(String code, String message) {
        super(message);
        this.code = code;
    }

    public AuthException(String message) {
        super(message);
    }

    public String getCode() {
        return this.code;
    }

    public String toString() {
        String result = "code " + this.code + " message " + this.getMessage();
        return result;
    }
}
