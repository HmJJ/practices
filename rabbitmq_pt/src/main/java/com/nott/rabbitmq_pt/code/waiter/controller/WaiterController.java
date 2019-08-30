package com.nott.rabbitmq_pt.code.waiter.controller;

import com.nott.rabbitmq_pt.code.config.ConnectionUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Author: wangjun
 * @Description:
 * @Date: created in 2019/8/29 10:31
 * @Modified By:
 **/
@RestController
@RequestMapping(value = "waiter")
public class WaiterController {

    public static Logger logger = LoggerFactory.getLogger(WaiterController.class);

    @RequestMapping(value = "record")
    public void record() {
        ConnectionUtil.get();
    }

}
