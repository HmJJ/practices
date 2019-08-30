package com.nott.scStream.code.processor;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.cloud.stream.messaging.Processor;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.messaging.support.MessageBuilder;
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
@EnableBinding(value = {Processor.class})
public class ProcessorController {

    @Autowired
    private Processor processor;

    @RequestMapping(value = "send")
    public Boolean send(@RequestParam String value) {
        boolean flag = processor.output().send(MessageBuilder.withPayload(value).build());
        return flag;
    }

}
