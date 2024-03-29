package com.nott.external.code.external.connect.vo;

/**
 * @Author: wangjun
 * @Description:
 * @Date: created in 2020/4/30 17:34
 * @Modified By:
 **/
public class ParamVo {

    private String key;
    private String value;

    public ParamVo() {}
    public ParamVo(String key, String value) {
        this.key = key;
        this.value = value;
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }
}
