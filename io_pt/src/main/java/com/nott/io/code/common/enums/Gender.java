package com.nott.io.code.common.enums;

/**
 * @Author: allen
 * @Description:
 * @Date: created in 2018/11/26 17:25
 * @Modified By:
 */
public enum Gender {
    FEMALE("female","女性"),
    MALE("male","男性");

    private String code;
    private String message;

    Gender(String code, String message){
        this.code = code;
        this.message = message;
    }

    public String getCode() {
        return code;
    }

    public String getMessage() {
        return message;
    }
}
