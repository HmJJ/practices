package com.nott.security.oauth2.user.domain;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.List;

/**
 * @Author: wangjun
 * @Description:
 * @Date: created in 2020/7/17 10:52
 * @Modified By:
 **/
public class LoginUser implements UserDetails {

    private static final long serialVersionUID = 4010607324380894976L;
    private Long loginId;
    private String password;
    private String username;
    private String mobile;
    private Collection<? extends GrantedAuthority> authorities;

    public LoginUser() {
    }

    public LoginUser(Long loginId, String username) {
        this.loginId = loginId;
        this.username = username;
    }

    public LoginUser(String username, List<GrantedAuthority> grantedAuthorities) {
        this.username=username;
        this.authorities=grantedAuthorities;
    }

    public LoginUser( Long loginId, String login, String password, List<GrantedAuthority> grantedAuthorities) {
        this.username =login;
        this.loginId=loginId;
        this.password=password;
        this.authorities=grantedAuthorities;

    }

    public LoginUser(Long loginId, String username, String password, String mobile, Collection<? extends GrantedAuthority> authorities) {
        this.username = username;
        this.password = password;
        this.mobile = mobile;
        this.authorities = authorities;
        this.loginId = loginId;
    }

    public LoginUser(String username, String password, String mobile, Collection<? extends GrantedAuthority> authorities) {
        if (username != null && !"".equals(username) && password != null) {
            this.username = username;
            this.password = password;
            this.mobile = mobile;
            this.authorities = authorities;
        } else {
            throw new IllegalArgumentException("Cannot pass null or empty values to constructor");
        }
    }


    public Long getLoginId() {
        return loginId;
    }

    public void setLoginId(Long loginId) {
        this.loginId = loginId;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public void setAuthorities(Collection<? extends GrantedAuthority> authorities) {
        this.authorities = authorities;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return null;
    }

    @Override
    public String getPassword() {
        return null;
    }

    @Override
    public String getUsername() {
        return null;
    }

    @Override
    public boolean isAccountNonExpired() {
        return false;
    }

    @Override
    public boolean isAccountNonLocked() {
        return false;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return false;
    }

    @Override
    public boolean isEnabled() {
        return false;
    }

    @Override
    public boolean equals(Object rhs) {
        return rhs instanceof LoginUser ? this.username.equals(((LoginUser)rhs).username) : false;
    }

    @Override
    public int hashCode() {
        return this.username.hashCode();
    }
}
