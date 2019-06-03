package com.nott.io.code.common.domain;

/**
 * Created by allen on 2016/4/13.
 */
public class CodeInfo {

    private Object code;
    private String description;
    private String name;

    public CodeInfo(Object code, String description) {
        this.code = code;
        this.description = description;
    }

    public CodeInfo(Object code, String name, String description) {
        this.code = code;
        this.description = description;
        this.name = name;
    }

    @SuppressWarnings("unchecked")
    public <T> T getCode() {
        return (T)code;
    }

    public String getDescription() {
        return description;
    }
    public String getName() {return name;}
}
