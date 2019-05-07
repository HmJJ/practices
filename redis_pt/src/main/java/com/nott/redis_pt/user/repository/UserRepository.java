package com.nott.redis_pt.user.repository;

import com.nott.redis_pt.user.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {
}
