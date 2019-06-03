package com.nott.io.code.common.domain;

/**
 * Created by allen on 2016/12/6.
 */
public class Data {

    private Object code;
    private String name;




    public static Data create(Object code, String name) {

        Data result = new Data(code,name);
        return result;
    }

    public Data(Object code, String name){
        this.code = code;
        this.name = name;
    }
    public Data() {}

    public <T> T getCode() {
        return (T)code;
    }

    public String getName() {
        return name;
    }
}
