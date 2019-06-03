package com.nott.io.code.common.enums;

/**
 * @Author: allen
 * @Description:
 * @Date: created in 2018/11/15 18:32
 * @Modified By:
 */
public enum StateEnum {

    ENABLE("enabled","有效"),
    DISABLE("disabled", "失效");

    private String code;
    private String message;

    StateEnum(String code, String message){
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
