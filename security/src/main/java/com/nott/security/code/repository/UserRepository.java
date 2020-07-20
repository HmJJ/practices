package com.nott.security.code.repository;

import com.nott.security.code.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * @Author: wangjun
 * @Description:
 * @Date: created in 2020/7/3 16:58
 * @Modified By:
 **/
public interface UserRepository extends JpaRepository<User, Long> {
}
