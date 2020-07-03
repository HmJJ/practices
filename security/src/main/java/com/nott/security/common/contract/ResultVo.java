package com.nott.security.common.contract;

/**
 * @Author: wangjun
 * @Description:
 * @Date: created in 2020/7/3 14:36
 * @Modified By:
 **/
public class ResultVo {
    public static final String API_OK = "1000";
    public static final String API_ERROR = "9000";
    private String code = "1000";
    private String message = "ok";
    private Object data;
    private long costTime;

    public ResultVo() {
    }

    public ResultVo(Object data) {
        this.data = data;
    }

    public ResultVo(String code, String message) {
        this.code = code;
        this.message = message;
    }

    public ResultVo(String code, String message, Object data) {
        this.code = code;
        this.message = message;
        this.data = data;
    }

    public String getCode() {
        return this.code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getMessage() {
        return this.message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Object getData() {
        return this.data;
    }

    public void setData(Object data) {
        this.data = data;
    }

    public long getCostTime() {
        return this.costTime;
    }

    public void setCostTime(long costTime) {
        this.costTime = costTime;
    }
}
