package com.nott.rabbitmq_pt.code.customer.controller;

import com.nott.rabbitmq_pt.code.config.ConnectionUtil;
import com.nott.rabbitmq_pt.code.customer.vo.CustomerVo;
import com.nott.rabbitmq_pt.code.customer.vo.GemVo;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.*;

/**
 * @Author: wangjun
 * @Description:
 * @Date: created in 2019/8/28 15:21
 * @Modified By:
 **/
@RestController
@RequestMapping(value = "customer")
public class CustomerController {

    public static Logger logger = LoggerFactory.getLogger(CustomerController.class);

    @PostMapping("order")
    public void send(@RequestBody CustomerVo vo) {

        ConnectionUtil.send(vo.getMessage());

    }

    public void pay() {

    }

    @RequestMapping(value = "leet")
    public String calcGemNum(@RequestBody GemVo vo) {
        String address = vo.getTemp();
        address = address.replaceAll("[\\.]", ".");
        return address;
    }

}
