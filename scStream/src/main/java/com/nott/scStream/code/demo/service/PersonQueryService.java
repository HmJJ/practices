package com.nott.scStream.code.demo.service;

import com.nott.scStream.code.demo.domain.Person;
import com.nott.scStream.code.demo.repository.PersonRepository;
import org.hibernate.service.spi.ServiceException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @Author: wangjun
 * @Description:
 * @Date: created in 2019/9/17 17:22
 * @Modified By:
 **/
@Service
public class PersonQueryService {

    @Autowired
    private PersonRepository personRepository;

    public Person find(Long id) {
        if (id == null) {
            throw new ServiceException("id不能为空");
        }
        Person person = personRepository.findById(id).get();
        if (person == null) {
            throw new ServiceException("id无效");
        }
        return person;
    }

}
