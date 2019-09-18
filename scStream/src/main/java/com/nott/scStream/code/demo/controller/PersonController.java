package com.nott.scStream.code.demo.controller;

import com.nott.scStream.code.demo.domain.Person;
import com.nott.scStream.code.demo.service.PersonService;
import org.hibernate.service.spi.ServiceException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * @Author: wangjun
 * @Description:
 * @Date: created in 2019/9/18 10:38
 * @Modified By:
 **/
@RestController
@RequestMapping("person")
public class PersonController {

    private static final Logger log = LoggerFactory.getLogger(PersonController.class);

    @Autowired
    private PersonService personService;

    @PostMapping("create")
    public void create(@RequestBody Person person) {
        try {
            personService.create(person.getName(), person.getSex(), person.getAge(), person.getRemark());
        } catch (Exception e) {
            e.printStackTrace();
            throw new ServiceException("PersonController, create, Exception: {}", e);
        }
    }

}
