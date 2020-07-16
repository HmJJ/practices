package com.nott.reflect.code.vo;

import java.util.List;

/**
 * @Author: wangjun
 * @Description:
 * @Date: created in 2020/7/16 16:21
 * @Modified By:
 **/
public class RiskQuery {

    protected Long id;
    protected String name;
    protected Long groupPartyId;
    protected List<Long> groupIdList;

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

    public List<Long> getGroupIdList() {
        return groupIdList;
    }

    public void setGroupIdList(List<Long> groupIdList) {
        this.groupIdList = groupIdList;
    }
}
