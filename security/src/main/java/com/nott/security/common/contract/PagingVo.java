package com.nott.security.common.contract;

import com.fasterxml.jackson.annotation.JsonIgnore;

import java.io.Serializable;
import java.util.List;

/**
 * @Author: wangjun
 * @Description:
 * @Date: created in 2020/7/3 14:35
 * @Modified By:
 **/
public class PagingVo implements Serializable {
    protected int page;
    protected int size;
    protected Long userId;

    public PagingVo() {
    }

    @JsonIgnore
    public int getOffset() {
        return (this.page - 1) * this.size;
    }

    public int getPage() {
        return this.page;
    }

    public void setPage(int page) {
        this.page = page;
    }

    public int getSize() {
        return this.size;
    }

    public void setSize(int size) {
        this.size = size;
    }

    @JsonIgnore
    public Long getUserId() {
        return this.userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }
}
