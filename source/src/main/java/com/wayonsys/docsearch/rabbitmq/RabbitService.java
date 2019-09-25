package com.wayonsys.docsearch.rabbitmq;

import com.alibaba.fastjson.JSON;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.core.MessageProperties;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.UnsupportedEncodingException;
import java.util.Date;
import java.util.HashMap;

@Service
public class RabbitService {

    Logger log = LoggerFactory.getLogger(RabbitService.class);

    @Autowired
    RabbitTemplate rabbitTemplate;

    public void sendToTest() {
        MessageProperties props = new MessageProperties();
        props.setContentType(MessageProperties.CONTENT_TYPE_JSON);
        org.springframework.amqp.core.Message m=null;
        HashMap<String, String> vo = new HashMap<String, String>();
        String d = "test msg" + new Date();
        vo.put("value", d);
        try {
            m = new org.springframework.amqp.core.Message(JSON.toJSONString(vo).getBytes("utf-8"),props);
            m.getMessageProperties().setExpiration("60000");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        log.info("send ...");
        rabbitTemplate.send("exc.test", "test", m);

    }

    @RabbitListener(queues = "que.test")
    public void testQueueListener(Message message) {
        try {
            log.info(new String(message.getBody(), "utf8"));
        }
        catch (Exception e) {
            e.printStackTrace();
        }
    }

}
