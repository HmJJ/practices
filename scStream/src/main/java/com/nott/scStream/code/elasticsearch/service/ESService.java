package com.nott.scStream.code.elasticsearch.service;

import com.alibaba.fastjson.JSONObject;
import com.nott.scStream.code.Utils.HttpUtils;
import com.nott.scStream.code.demo.domain.Person;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.cloud.stream.annotation.StreamListener;
import org.springframework.cloud.stream.messaging.Processor;
import org.springframework.cloud.stream.messaging.Sink;
import org.springframework.cloud.stream.messaging.Source;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashMap;
import java.util.Map;

/**
 * @Author: wangjun
 * @Description:
 * @Date: created in 2019/9/17 10:46
 * @Modified By:
 **/
@Service
@Transactional(rollbackFor = Exception.class)
@EnableBinding(Sink.class)
public class ESService {

    private static final Logger log = LoggerFactory.getLogger(ESService.class);

    private static final String esURL = "http://localhost:9200/nott/";

    @StreamListener(Processor.INPUT)
    public void put(Person person) {
        HttpUtils httpUtils = new HttpUtils();
        String url = esURL + "person/" + person.getId();
        Map map = new HashMap();
        map.put("id", person.getId());
        map.put("name", person.getName());
        map.put("sex", person.getSex());
        map.put("age", person.getAge());
        map.put("remark", person.getRemark());
        JSONObject result = httpUtils.getJSONByUrl(url, new JSONObject(map));
        log.info("ESService: put(建索引) -> {}", result);
    }

    public void get() {

    }

}
