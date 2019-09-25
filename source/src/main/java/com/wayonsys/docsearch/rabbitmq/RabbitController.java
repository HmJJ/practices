package com.wayonsys.docsearch.rabbitmq;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;


@Controller
@RequestMapping("/api/rabbit")
public class RabbitController {

    Logger log = LoggerFactory.getLogger(RabbitController.class);

    @Autowired
    RabbitService rabbitService;

    @RequestMapping("/send")
    @ResponseBody
    public String send() {

        rabbitService.sendToTest();
        return "OK";

    }



}
