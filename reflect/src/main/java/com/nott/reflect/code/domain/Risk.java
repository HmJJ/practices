package com.nott.reflect.code.domain;

import javax.persistence.Entity;
import javax.persistence.Table;

/**
 * @Author: wangjun
 * @Description:
 * @Date: created in 2020/7/16 16:17
 * @Modified By:
 **/
@Entity
@Table(name = "risk")
public class Risk {

    private Long id;
    private String name;
    private Long groupPartyId;

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
