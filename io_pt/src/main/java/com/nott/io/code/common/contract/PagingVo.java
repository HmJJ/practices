package com.nott.io.code.common.contract;

import com.fasterxml.jackson.annotation.JsonIgnore;

import java.io.Serializable;

/**
 * @Author: allen
 * @Description:
 * @Date: created in 2018/11/12 16:31
 * @Modified By:
 */
public class PagingVo implements Serializable {

    protected int page;
    protected int size;
    protected Long userId;
    protected Long tenantId;


    @JsonIgnore
    public int getOffset() {
        return (page - 1) * size;
    }

    public int getPage() {
        return page;
    }

    public void setPage(int page) {
        this.page = page;
    }

    public int getSize() {
        return size;
    }

    public void setSize(int size) {
        this.size = size;
    }

    @JsonIgnore
    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    @JsonIgnore
    public Long getTenantId() {
        return tenantId;
    }

    public void setTenantId(Long tenantId) {
        this.tenantId = tenantId;
    }
}
