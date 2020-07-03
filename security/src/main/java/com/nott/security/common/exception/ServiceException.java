package com.nott.security.common.exception;

import java.util.HashMap;
import java.util.Map;

/**
 * @Author: wangjun
 * @Description:
 * @Date: created in 2020/7/3 11:12
 * @Modified By:
 **/
public class ServiceException extends RuntimeException {

    public static final String GENERAL_ERROR = "9000";
    private String statusCode;
    private Map<String, Object> data = new HashMap<>();

    public ServiceException(String message) {
        super(message);
        this.setStatusCode("9000");
    }

    public ServiceException(String statusCode, String message) {
        super(message);
        this.setStatusCode(statusCode);
    }

    public ServiceException(String message, Throwable cause) {
        super(message, cause);
    }

    public ServiceException(String statusCode, String message, Throwable cause) {
        super(message, cause);
        this.setStatusCode(statusCode);
    }

    public void add(String key, Object value) {
        this.data.put(key, value);
    }

    public String getStatusCode() {
        return this.statusCode;
    }

    public void setStatusCode(String statusCode) {
        this.statusCode = statusCode;
    }

    public Map<String, Object> getData() {
        return this.data;
    }

}
