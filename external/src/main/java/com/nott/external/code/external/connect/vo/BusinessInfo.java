package com.nott.external.code.external.connect.vo;

import org.apache.commons.lang3.StringUtils;

import javax.persistence.Embeddable;
import javax.persistence.Transient;
import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

/**
 * @Author: wangjun
 * @Description:
 * @Date: created in 2020/7/27 15:50
 * @Modified By:
 **/
@Embeddable
public class BusinessInfo implements Serializable {
    private Long businessKey;
    private String businessType;

    public static BusinessInfo create(Long key, String type) {
        BusinessInfo info = new BusinessInfo(key, type);
        return info;
    }

    public BusinessInfo() {
    }

    public BusinessInfo(Long key, String type) {
        this.businessKey = key;
        this.businessType = type;
    }

    @Transient
    public boolean isValid() {
        return this.businessKey != null && StringUtils.isNotBlank(this.businessType);
    }

    @Transient
    public Map<String, Object> getMap() {
        Map<String, Object> map = new HashMap();
        map.put("businessKey", this.businessKey);
        map.put("businessType", this.businessType);
        return map;
    }

    public Long getBusinessKey() {
        return this.businessKey;
    }

    public void setBusinessKey(Long businessKey) {
        this.businessKey = businessKey;
    }

    public String getBusinessType() {
        return this.businessType;
    }

    public void setBusinessType(String businessType) {
        this.businessType = businessType;
    }
}
