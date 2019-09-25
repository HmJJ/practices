package com.nott.scStream.code.processor;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Author: wangjun
 * @Description:
 * @Date: created in 2019/8/29 18:34
 * @Modified By:
 **/
@RestController
@RequestMapping(value = "/processor")
public class ProcessorController {

    private static Logger log = LoggerFactory.getLogger(ProcessorController.class);

    @Autowired
    private ProcessorService processorService;

    @RequestMapping(value = "send")
    public Boolean send(@RequestParam String value) {
        boolean flag = processorService.send(value);
        return flag;
    }

}
