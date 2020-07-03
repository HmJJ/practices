package com.nott.security.common.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.nott.security.common.enums.StateEnum;

import javax.persistence.EntityListeners;
import javax.persistence.MappedSuperclass;

/**
 * @Author: wangjun
 * @Description:
 * @Date: created in 2020/7/3 10:19
 * @Modified By:
 **/
@MappedSuperclass
@EntityListeners({DomainEntityListener.class})
public abstract class DomainEntity extends AbstractAuditable {

    private static final long serialVersionUID = 8164192584839368686L;

    @JsonIgnore
    protected String objectState;
    @JsonIgnore
    protected Long ownerId;
    @JsonIgnore
    protected Long ownerGroupId;

    public DomainEntity() {
        this.objectState = StateEnum.ENABLE.getCode();
        this.ownerId = 0L;
    }

    public String getObjectState() {
        return objectState;
    }

    public void setObjectState(String objectState) {
        this.objectState = objectState;
    }

    public Long getOwnerId() {
        return ownerId;
    }

    public void setOwnerId(Long ownerId) {
        this.ownerId = ownerId;
    }

    public Long getOwnerGroupId() {
        return ownerGroupId;
    }

    public void setOwnerGroupId(Long ownerGroupId) {
        this.ownerGroupId = ownerGroupId;
    }
}
