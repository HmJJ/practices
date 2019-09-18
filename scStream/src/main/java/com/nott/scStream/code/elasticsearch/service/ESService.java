package com.nott.scStream.code.elasticsearch.service;

import com.nott.scStream.code.demo.domain.Person;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * @Author: wangjun
 * @Description:
 * @Date: created in 2019/9/17 10:46
 * @Modified By:
 **/
@Service
@Transactional(rollbackFor = Exception.class)
public class ESService {

    private static final String esURL = "http://localhost:9200/nott/";

    public void put(Person person) {

    }

    public void get() {

    }

}
