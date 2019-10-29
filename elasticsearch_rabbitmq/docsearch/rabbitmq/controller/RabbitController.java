package com.wayonsys.ecm.docsearch.rabbitmq.controller;

import com.wayonsys.ecm.docsearch.rabbitmq.service.RabbitService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Author: wangjun
 * @Description:
 * @Date: created in 2019/9/26 13:52
 * @Modified By:
 **/
@RestController
@RequestMapping("/api/processor")
public class RabbitController {

    private static final Logger log = LoggerFactory.getLogger(RabbitController.class);

    @Autowired
    private RabbitService processorService;

    @RequestMapping("send")
    public void send(@RequestParam String value) {
        processorService.send(value);
    }

}
