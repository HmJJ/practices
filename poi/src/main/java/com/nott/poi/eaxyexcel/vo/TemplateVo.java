/**
 * @Author: nott
 * @Description:
 * @Date: created in 2020/9/2 16:56
 * @Modified By:
 */
package com.nott.poi.eaxyexcel.vo;

public class TemplateVo {

    private String name;
    private String suffix;
    private String code;
    private Integer row;
    private Integer column;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSuffix() {
        return suffix;
    }

    public void setSuffix(String suffix) {
        this.suffix = suffix;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public Integer getRow() {
        return row;
    }

    public void setRow(Integer row) {
        this.row = row;
    }

    public Integer getColumn() {
        return column;
    }

    public void setColumn(Integer column) {
        this.column = column;
    }
}
