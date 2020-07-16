package com.nott.reflect.code.controller;


import com.nott.reflect.code.service.RiskService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Author: wangjun
 * @Description:
 * @Date: created in 2020/7/16 16:30
 * @Modified By:
 **/
@RequestMapping("/risk")
@RestController
public class RiskController {

    public static final Logger logger = LoggerFactory.getLogger(RiskController.class);

    @Autowired
    private RiskService riskService;

    @PostMapping("/search")
    public void search() {
        riskService.getPermissionQuery();
    }

}
