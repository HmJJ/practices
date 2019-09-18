package com.nott.scStream.code.demo.repository;

import com.nott.scStream.code.demo.domain.Person;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @Author: wangjun
 * @Description:
 * @Date: created in 2019/9/17 11:01
 * @Modified By:
 **/
public interface PersonRepository extends JpaRepository<Person, Long> {
}
