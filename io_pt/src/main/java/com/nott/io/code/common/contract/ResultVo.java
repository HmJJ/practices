package com.nott.io.code.common.contract;

/**
 * @Author: allen
 * @Description:
 * @Date: created in 2018/11/8 19:03
 * @Modified By:
 */
public class ResultVo {

    public static final String API_OK = "1000";
    public static final String API_ERROR = "9000";

    private String code	=  API_OK;
    private String message	= "ok";
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

    public long getCostTime() {
        return costTime;
    }

    public void setCostTime(long costTime) {
        this.costTime = costTime;
    }

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
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
