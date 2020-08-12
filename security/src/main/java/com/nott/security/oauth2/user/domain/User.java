package com.nott.security.oauth2.user.domain;

import javax.persistence.Entity;
import javax.persistence.Table;

/**
 * @Author: nott
 * @Description:
 * @Date: created in 2020/7/3 10:12
 * @Modified By:
 **/
@Entity
@Table(name = "ser_user")
public class User {

    private Long loginId;
    private Long personId;
    private String name;
    private String mobile;
    private String status;

    public Long getLoginId() {
        return loginId;
    }

    public void setLoginId(Long loginId) {
        this.loginId = loginId;
    }

    public Long getPersonId() {
        return personId;
    }

    public void setPersonId(Long personId) {
        this.personId = personId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
