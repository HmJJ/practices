package com.nott.postgis.code.vo;

import java.math.BigDecimal;

/**
 * @Author: wangjun
 * @Description:
 * @Date: created in 2020/7/21 17:16
 * @Modified By:
 **/
public class GisRecord {

    protected Long id;
    protected String name;
    protected Long businessKey;
    protected String businessType;
    protected BigDecimal longitude;
    protected BigDecimal latitude;
    protected String geom;
    protected Integer distance;
    protected Long tenantId;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Long getBusinessKey() {
        return businessKey;
    }

    public void setBusinessKey(Long businessKey) {
        this.businessKey = businessKey;
    }

    public String getBusinessType() {
        return businessType;
    }

    public void setBusinessType(String businessType) {
        this.businessType = businessType;
    }

    public BigDecimal getLongitude() {
        return longitude;
    }

    public void setLongitude(BigDecimal longitude) {
        this.longitude = longitude;
    }

    public BigDecimal getLatitude() {
        return latitude;
    }

    public void setLatitude(BigDecimal latitude) {
        this.latitude = latitude;
    }

    public String getGeom() {
        return geom;
    }

    public void setGeom(String geom) {
        this.geom = geom;
    }

    public Integer getDistance() {
        return distance;
    }

    public void setDistance(Integer distance) {
        this.distance = distance;
    }

    public Long getTenantId() {
        return tenantId;
    }

    public void setTenantId(Long tenantId) {
        this.tenantId = tenantId;
    }

    @Override
    public String toString() {
        return "GisRecord{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", businessKey=" + businessKey +
                ", businessType='" + businessType + '\'' +
                ", longitude=" + longitude +
                ", latitude=" + latitude +
                ", geom='" + geom + '\'' +
                ", distance=" + distance +
                ", tenantId=" + tenantId +
                '}';
    }
}
