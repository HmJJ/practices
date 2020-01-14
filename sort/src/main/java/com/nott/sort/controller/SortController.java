package com.nott.sort.controller;

import com.nott.sort.service.SortService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

/**
 * @Author: wangjun
 * @Description:
 * https://blog.csdn.net/weixin_41190227/article/details/86600821
 * @Date: created in 2020/1/14 15:13
 * @Modified By:
 **/
@RestController
public class SortController {

    private static final Logger log = LoggerFactory.getLogger(SortController.class);

    @Autowired
    private SortService service;

    @PostMapping("/sort")
    public String sort(@RequestBody Map<String, Object> map) {
        String message = service.sort(map);
        return message;
    }

}
