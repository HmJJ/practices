package com.nott.security.user.domain;

import javax.persistence.Entity;
import javax.persistence.Table;

/**
 * @Author: nott
 * @Description:
 * @Date: created in 2020/7/3 16:55
 * @Modified By:
 **/
@Entity
@Table(name = "ser_login")
public class Login {

    private String username;
    private String password;
    private String email;
    private String mobile;
    private String status;

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
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
