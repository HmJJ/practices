package com.nott.postgis.code.vo;

import java.math.BigDecimal;

/**
 * @Author: wangjun
 * @Description:
 * @Date: created in 2020/7/21 17:07
 * @Modified By:
 **/
public class GisQuery {

    protected Integer distance;
    protected BigDecimal longitude;
    protected BigDecimal latitude;
    protected String geoStr;
    protected Long tenantId;

    public Integer getDistance() {
        return distance;
    }

    public void setDistance(Integer distance) {
        this.distance = distance;
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

    public String getGeoStr() {
        return geoStr;
    }

    public void setGeoStr(String geoStr) {
        this.geoStr = geoStr;
    }

    public Long getTenantId() {
        return tenantId;
    }

    public void setTenantId(Long tenantId) {
        this.tenantId = tenantId;
    }
}
