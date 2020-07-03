package com.nott.security.common.entity;

import com.nott.security.common.exception.ServiceException;
import com.nott.security.common.security.MyAccountInfo;
import com.nott.security.common.security.SecurityUtils;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import javax.persistence.PostLoad;
import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;
import java.time.LocalDateTime;
import java.time.ZoneId;

/**
 * @Author: wangjun
 * @Description:
 * @Date: created in 2020/7/3 10:20
 * @Modified By:
 **/
@Component("domainEntityListener")
public class DomainEntityListener {

    private static final Logger log = LoggerFactory.getLogger(DomainEntityListener.class);
    private static final ZoneId ZONE = ZoneId.of("Asia/Shanghai");
    
    public DomainEntityListener() {}

    @PrePersist
    public void touchForCreate(Object target) {
        this.create(target);
    }

    @PreUpdate
    public void touchForUpdate(Object target) {
        this.update(target);
    }

    @PostLoad
    public void touchForLoad(Object target) {
        if (target instanceof DomainEntity) {
            DomainEntity var2 = (DomainEntity)target;
        }

    }

    private void create(Object target) {
        MyAccountInfo myAccountInfo = SecurityUtils.getMyAccountInfo();
        if (target instanceof DomainEntity) {
            DomainEntity entity = (DomainEntity)target;
            this.validateTenant();
            if (myAccountInfo != null) {
                entity.setCreatedBy(myAccountInfo.getUserId());
                entity.setOwnerId(myAccountInfo.getUserId());
            }

            entity.setCreatedTime(LocalDateTime.now(ZONE));
        }

    }

    private void update(Object target) {
        this.validateTenant();

        if (target instanceof AbstractAuditable) {
            AbstractAuditable entity = (AbstractAuditable)target;
            MyAccountInfo myAccountInfo = SecurityUtils.getMyAccountInfo();
            if (myAccountInfo != null) {
                entity.setLastUpdatedBy(myAccountInfo.getUserId());
            }

            entity.setLastUpdatedTime(LocalDateTime.now(ZONE));
        }

    }

    private void validateTenant() {
        MyAccountInfo myAccountInfo = SecurityUtils.getMyAccountInfo();
        if (myAccountInfo != null) {
            if (StringUtils.isBlank(myAccountInfo.getLoginName()))
                throw new ServiceException("请先登录");
        }
    }
}
