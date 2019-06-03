package com.nott.io.code.common.exception;


/**
 * @Author: allen
 * @Description:
 * @Date: created in 2016/4/5 16:31
 * @Modified By:
 */
public class ServiceException extends RuntimeException {

    /** 服务器内部错误 */
    public static final String GENERAL_ERROR		= "9000";


    private String statusCode;

    public ServiceException(String message) {
        super(message);
        setStatusCode(GENERAL_ERROR);
    }

    public ServiceException(String statusCode, String message) {
        super(message);
        setStatusCode(statusCode);
    }


    public ServiceException(String message, Throwable cause) {
        super(message, cause);
    }

    public ServiceException(String statusCode, String message, Throwable cause) {
        super(message, cause);
        setStatusCode(statusCode);
    }

    public String getStatusCode() {
        return statusCode;
    }

    public void setStatusCode(String statusCode) {
        this.statusCode = statusCode;
    }


}
