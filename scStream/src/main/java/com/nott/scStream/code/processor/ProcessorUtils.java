package com.nott.scStream.code.processor;

import com.nott.scStream.code.domain.Person;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.cloud.stream.annotation.StreamListener;
import org.springframework.cloud.stream.messaging.Processor;
import org.springframework.messaging.handler.annotation.SendTo;

/**
 * @Author: wangjun
 * @Description:
 * @Date: created in 2019/8/29 18:28
 * @Modified By:
 **/
@EnableBinding(Processor.class)
public class ProcessorUtils {

    private static Logger log = LoggerFactory.getLogger(ProcessorUtils.class);

    @StreamListener(Processor.OUTPUT)
    @SendTo(Processor.INPUT)
    public String handle(String value) {
        log.info("handle: ProcessorUtils: {}", value);
        return value.toUpperCase();
    }

    @StreamListener(Processor.INPUT)
    public void handle2(String value) {
        log.info("handle2: ProcessorUtils: {}", value);
    }

}
