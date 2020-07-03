package com.nott.security.code.repository;

import com.nott.security.code.domain.LoginClient;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @Author: wangjun
 * @Description:
 * @Date: created in 2020/7/3 16:58
 * @Modified By:
 **/
public interface LoginClientRepository extends JpaRepository<LoginClient, Long> {
}
