package com.nott.security.oauth2.user.repository;

import com.nott.security.oauth2.user.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @Author: nott
 * @Description:
 * @Date: created in 2020/7/3 16:58
 * @Modified By:
 **/
public interface UserRepository extends JpaRepository<User, Long> {
}
