package com.nott.io.code.common.tree;


import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;

/**
 * Created by allen on 2016/10/28.
 */
public class PickerData {
    private Long id;
    private String parentCode;
    private String code;
    private String name;
    private String description;
    private Boolean checked;
    private Integer level = 0;

    public PickerData() {}

    public PickerData(String code, String parentCode, String name) {
        this.code = code;
        this.parentCode = parentCode;
        this.name = name;
    }

    public PickerData(String code, String parentCode, String name, Integer level) {

        this.code = code;
        this.parentCode = parentCode;
        this.name = name;
        this.level = level;
    }

    public PickerData(Long id,String code, String parentCode, String name, Integer level) {
        this.id = id;
        this.code = code;
        this.parentCode = parentCode;
        this.name = name;
        this.level = level;
    }



    @Override
    public int hashCode() {
        return code.hashCode();
    }

    @Override
    public boolean equals(Object anObject) {

        if(anObject == null) {
            return false;
        }
        if(this == anObject) {
            return true;
        }
        if(!(anObject instanceof PickerData)) {
            return false;
        }
        PickerData o = (PickerData)anObject;
        if(code.equals(o.getCode())) {
            return true;
        }
        return false;
    }

//    @JsonSerialize(include=JsonSerialize.Inclusion.NON_NULL)
    @JsonInclude( JsonInclude.Include.NON_NULL)
    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    @JsonIgnore
    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @JsonIgnore
    public String getParentCode() {
        return parentCode;
    }

    public void setParentCode(String parentCode) {
        this.parentCode = parentCode;
    }

    @JsonIgnore
    public Boolean getChecked() {
        return checked;
    }

    public void setChecked(Boolean checked) {
        this.checked = checked;
    }

    public Integer getLevel() {
        return level;
    }

    public void setLevel(Integer level) {
        this.level = level;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
}
