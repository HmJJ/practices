package com.nott.security.oauth2.user.repository;

import com.nott.security.code.domain.Login;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * @Author: wangjun
 * @Description:
 * @Date: created in 2020/7/3 16:58
 * @Modified By:
 **/
public interface LoginRepository extends JpaRepository<Login, Long> {
}
