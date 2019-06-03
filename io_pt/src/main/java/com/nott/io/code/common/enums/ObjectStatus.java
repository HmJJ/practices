package com.nott.io.code.common.enums;

/**
 * Created by allen on 2017/4/27.
 */
public enum ObjectStatus {
    VALID(1,"valid"),
    INVALID(0, "invalid");

    private int code;
    private String message;

    ObjectStatus(int code, String message){
        this.code = code;
        this.message = message;
    }

    public int getCode() {
        return code;
    }



    public String getMessage() {
        return message;
    }

}
