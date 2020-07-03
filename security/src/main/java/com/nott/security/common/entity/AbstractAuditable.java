package com.nott.security.common.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import org.hibernate.envers.Audited;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.annotation.LastModifiedDate;

import javax.persistence.Column;
import javax.persistence.EntityListeners;
import javax.persistence.MappedSuperclass;
import java.time.LocalDateTime;

/**
 * @Author: wangjun
 * @Description:
 * @Date: created in 2020/7/3 10:20
 * @Modified By:
 **/
@MappedSuperclass
@Audited
@EntityListeners({DomainEntityListener.class})
public class AbstractAuditable extends IdEntity {
    @CreatedBy
    @JsonIgnore
    @Column(
            name = "created_by",
            nullable = false,
            updatable = false
    )
    private Long createdBy;
    @CreatedDate
    @JsonIgnore
    @Column(
            name = "created_time",
            nullable = false
    )
    private LocalDateTime createdTime;
    @LastModifiedBy
    @JsonIgnore
    @Column(
            name = "last_updated_by",
            nullable = false,
            updatable = false
    )
    private Long lastUpdatedBy;
    @LastModifiedDate
    @JsonIgnore
    @Column(
            name = "last_update_time",
            nullable = false
    )
    private LocalDateTime lastUpdatedTime;

    public AbstractAuditable() {}

    public Long getCreatedBy() {
        return createdBy;
    }

    public void setCreatedBy(Long createdBy) {
        this.createdBy = createdBy;
    }

    public LocalDateTime getCreatedTime() {
        return createdTime;
    }

    public void setCreatedTime(LocalDateTime createdTime) {
        this.createdTime = createdTime;
    }

    public Long getLastUpdatedBy() {
        return lastUpdatedBy;
    }

    public void setLastUpdatedBy(Long lastUpdatedBy) {
        this.lastUpdatedBy = lastUpdatedBy;
    }

    public LocalDateTime getLastUpdatedTime() {
        return lastUpdatedTime;
    }

    public void setLastUpdatedTime(LocalDateTime lastUpdatedTime) {
        this.lastUpdatedTime = lastUpdatedTime;
    }
}
