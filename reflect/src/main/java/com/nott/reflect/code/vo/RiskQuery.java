package com.nott.reflect.code.vo;

import java.util.List;

/**
 * @Author: wangjun
 * @Description:
 * @Date: created in 2020/7/16 16:21
 * @Modified By:
 **/
public class RiskQuery extends PagingVo {

    protected Long id;
    protected String name;
    protected Long groupPartyId;

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

    public Long getGroupPartyId() {
        return groupPartyId;
    }

    public void setGroupPartyId(Long groupPartyId) {
        this.groupPartyId = groupPartyId;
    }

}
