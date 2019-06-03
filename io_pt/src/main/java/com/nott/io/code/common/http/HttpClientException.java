package com.nott.io.code.common.http;

/**
 * Created by allen on 2017/8/11.
 */
public class HttpClientException extends RuntimeException{

    private String statusCode;

    public static void throwException(String message) {
        throw new HttpClientException(message);
    }

    private HttpClientException(String message) {
        super(message);
    }

    private HttpClientException(String message, String statusCode) {
        super(message);
        this.statusCode = statusCode;
    }

    public String getStatusCode() {
        return statusCode;
    }
}
