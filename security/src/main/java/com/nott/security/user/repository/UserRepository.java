package com.nott.security.user.repository;

import com.nott.security.user.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @Author: nott
 * @Description:
 * @Date: created in 2020/7/3 16:58
 * @Modified By:
 **/
public interface UserRepository extends JpaRepository<User, Long> {
}
