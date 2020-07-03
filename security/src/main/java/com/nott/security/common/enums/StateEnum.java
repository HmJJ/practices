package com.nott.security.common.enums;

/**
 * @Author: wangjun
 * @Description:
 * @Date: created in 2020/7/3 11:08
 * @Modified By:
 **/
public enum StateEnum {

    ENABLE("enabled", "有效"),
    DISABLE("disabled", "失效");

    private String code;
    private String message;

    StateEnum(String code, String message) {
        this.code = code;
        this.message = message;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
