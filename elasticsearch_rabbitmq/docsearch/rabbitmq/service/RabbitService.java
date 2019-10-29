package com.wayonsys.ecm.docsearch.rabbitmq.service;

import com.alibaba.fastjson.JSON;
import com.wayonsys.ecm.document.vo.RabbitVo;
import com.wayonsys.ecm.utils.FileUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.core.*;
import org.springframework.amqp.core.MessageProperties;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitAdmin;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.UnsupportedEncodingException;
import java.util.HashMap;
import java.util.Map;

/**
 * @Author: wangjun
 * @Description:
 * @Date: created in 2019/9/26 13:53
 * @Modified By:
 **/
@Service
public class RabbitService {

    private static final Logger log = LoggerFactory.getLogger(RabbitService.class);

    public static final String EXCHANGE_NAME = "direct.es.exchange";
    public static final String QUEUE_NAME = "direct.es.queue";
    public static final String ROUTING_KEY = "direct.es.queue";

    @Autowired
    RabbitTemplate rabbitTemplate;

    public void send(Object object) {
        MessageProperties props = new MessageProperties();
        props.setContentType(MessageProperties.CONTENT_TYPE_BYTES);
        RabbitVo info = (RabbitVo) object;
        log.info("rabbit message: {}", info.toString());
        Message m = new Message(FileUtils.objectToByte(info), props);
        m.getMessageProperties().setExpiration("60000");
        log.info("sending ...");

        ConnectionFactory factory = rabbitTemplate.getConnectionFactory();
        RabbitAdmin admin = new RabbitAdmin(factory);
        DirectExchange exchange = new DirectExchange(EXCHANGE_NAME);
        admin.declareExchange(exchange);
        Queue queue = new Queue(QUEUE_NAME, true);
        admin.declareQueue(queue);
        admin.declareBinding(BindingBuilder.bind(queue).to(exchange).with(ROUTING_KEY));
        rabbitTemplate = new RabbitTemplate(factory);
        rabbitTemplate.send(EXCHANGE_NAME, ROUTING_KEY, m);

    }

}
