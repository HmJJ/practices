package com.nott.security.code.domain;

import com.nott.security.common.entity.DomainEntity;

import javax.persistence.Entity;
import javax.persistence.Table;

/**
 * @Author: wangjun
 * @Description:
 * @Date: created in 2020/7/3 16:56
 * @Modified By:
 **/
@Entity
@Table(name = "ser_login_client")
public class LoginClient extends DomainEntity {

    private Long loginId;
    private String clientId;
    private String clientSecret;
    private String status;

    public Long getLoginId() {
        return loginId;
    }

    public void setLoginId(Long loginId) {
        this.loginId = loginId;
    }

    public String getClientId() {
        return clientId;
    }

    public void setClientId(String clientId) {
        this.clientId = clientId;
    }

    public String getClientSecret() {
        return clientSecret;
    }

    public void setClientSecret(String clientSecret) {
        this.clientSecret = clientSecret;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
