package com.nott.security.common.security;

import java.io.Serializable;

/**
 * @Author: wangjun
 * @Description:
 * @Date: created in 2020/7/3 11:17
 * @Modified By:
 **/
public class MyAccountInfo implements Serializable {

    private static final long serialVersionUID = 2512585893098630799L;

    private String loginName;
    private String releTypeName;
    private String name;
    private Long userId;
    private Long personId;
    private String mobile;

    public String getLoginName() {
        return loginName;
    }

    public void setLoginName(String loginName) {
        this.loginName = loginName;
    }

    public String getReleTypeName() {
        return releTypeName;
    }

    public void setReleTypeName(String releTypeName) {
        this.releTypeName = releTypeName;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public Long getPersonId() {
        return personId;
    }

    public void setPersonId(Long personId) {
        this.personId = personId;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }
}
