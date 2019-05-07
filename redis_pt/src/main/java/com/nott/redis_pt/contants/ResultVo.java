package com.nott.redis_pt.contants;

import lombok.Data;

@Data
public class ResultVo {

    public static final Integer API_ERROR = 9000;
    public static final Integer API_SUCCESS = 200;

    private Integer code = API_SUCCESS;
    private String message;
    private Object data;
    private String version = "v1.0";

    public ResultVo(){}
    public ResultVo(Integer code) {
        this.code = code;
    }
    public ResultVo(Integer code, String message) {
        this.code = code;
        this.message = message;
    }
    public ResultVo(Integer code, Object data) {
        this.code = code;
        this.data = data;
    }
    public ResultVo(Integer code, String message, Object data) {
        this.code = code;
        this.message = message;
        this.data = data;
    }
}
