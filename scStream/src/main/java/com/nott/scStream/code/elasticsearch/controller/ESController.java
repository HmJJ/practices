package com.nott.scStream.code.elasticsearch.controller;

import com.nott.scStream.code.demo.domain.Person;
import com.nott.scStream.code.elasticsearch.service.ESService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Author: wangjun
 * @Description:
 * @Date: created in 2019/9/17 10:43
 * @Modified By:
 **/
@RequestMapping("es")
@RestController
public class ESController {

    @Autowired
    private ESService esService;

    @RequestMapping(value = "put", method = RequestMethod.PUT)
    public void putData(@RequestBody Person person) {
        esService.put(person);
    }

}
