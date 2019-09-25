package com.nott.scStream.code.elasticsearch.service;

import com.nott.scStream.code.demo.domain.Person;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.cloud.stream.annotation.StreamListener;
import org.springframework.cloud.stream.messaging.Processor;
import org.springframework.cloud.stream.messaging.Sink;

/**
 * @Author: wangjun
 * @Description:
 * @Date: created in 2019/9/20 9:37
 * @Modified By:
 **/
@EnableBinding(Sink.class)
public class ListenerService {

    private static final Logger log = LoggerFactory.getLogger(ESService.class);

    @Autowired
    private ESService esService;

    @StreamListener(Processor.INPUT)
    public void singleSave(Person person) {
        boolean flag = esService.singleSave(person);
        log.info("ESService: put(建索引) -> {}", flag);
    }

}
