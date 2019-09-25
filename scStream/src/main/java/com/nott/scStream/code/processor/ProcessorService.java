package com.nott.scStream.code.processor;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.cloud.stream.annotation.StreamListener;
import org.springframework.cloud.stream.messaging.Processor;
import org.springframework.cloud.stream.messaging.Source;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.messaging.support.MessageBuilder;

/**
 * @Author: wangjun
 * @Description:
 * @Date: created in 2019/8/29 18:28
 * @Modified By:
 **/
@EnableBinding(Source.class)
public class ProcessorService {

    private static Logger log = LoggerFactory.getLogger(ProcessorService.class);

    @Autowired
    private Source source;

    public Boolean send(Object object) {
        Boolean flag = source.output().send(MessageBuilder.withPayload(object).build());
        return flag;
    }

    @StreamListener(Processor.OUTPUT)
    @SendTo(Processor.INPUT)
    public Object handle(Object object) {
        log.info("handle: ProcessorUtils: {}", object);
        return object;
    }

    /*@StreamListener(Processor.OUTPUT)
    @SendTo(Processor.INPUT)
    public Object handle(Object object) {
        log.info("handle: ProcessorUtils: {}", object);
        return object;
    }

    @StreamListener(Processor.INPUT)
    public void handle2(String value) {
        log.info("handle2: ProcessorUtils: {}", value);
    }*/

}
