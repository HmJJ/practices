package com.nott.scStream.code.demo.service;

import com.nott.scStream.code.demo.domain.Person;
import com.nott.scStream.code.demo.repository.PersonRepository;
import com.nott.scStream.code.processor.ProcessorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @Author: wangjun
 * @Description:
 * @Date: created in 2019/9/17 17:22
 * @Modified By:
 **/
@Service
public class PersonService {

    @Autowired
    private PersonRepository personRepository;
    @Autowired
    private PersonQueryService personQueryService;
    @Autowired
    private ProcessorService processorService;

    public void create(String name, String sex, Integer age, String remark) {
        Person entity = new Person();
        entity.setName(name);
        entity.setSex(sex);
        entity.setAge(age);
        entity.setRemark(remark);
        personRepository.save(entity);
        processorService.send(entity);
    }

    public void modify(Long id, String name, String sex, Integer age, String remark) {
        Person entity = personQueryService.find(id);
        entity.setName(name);
        entity.setSex(sex);
        entity.setAge(age);
        entity.setRemark(remark);
        personRepository.save(entity);
    }

    public void delete(Long id) {
        Person entity = personQueryService.find(id);
        personRepository.delete(entity);
    }

}
