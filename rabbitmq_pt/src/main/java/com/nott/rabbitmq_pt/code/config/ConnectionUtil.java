package com.nott.rabbitmq_pt.code.config;

import com.rabbitmq.client.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @Author: wangjun
 * @Description:
 * @Date: created in 2019/8/28 15:17
 * @Modified By:
 **/
public class ConnectionUtil {

    public static Logger logger = LoggerFactory.getLogger(ConnectionUtil.class);
    public static final String QUEUE_NAME = "yearning_life";

    public static Connection getConnection() throws Exception {
        //定义连接工厂
        ConnectionFactory factory = new ConnectionFactory();
        //设置服务地址
        factory.setHost("localhost");
        //端口
        factory.setPort(5672);
        //设置账号信息，用户名、密码、vhost
        factory.setVirtualHost("nott_virtual");
        factory.setUsername("nott");
        factory.setPassword("123456");
        //通过工程获取连接
        Connection connection = factory.newConnection();
        return connection;
    }

    public static Channel getChannel() {
        try {
            //获取到连接以及mq通道
            Connection connection = ConnectionUtil.getConnection();
            //从连接中创建通道
            Channel channel = connection.createChannel();
            return channel;
        } catch (Exception e) {
            logger.info(e.getCause().getMessage());
        }
        return null;
    }

    public static void send(String message) {

        try {
            Channel channel = getChannel();

            //创建队列
            channel.queueDeclare(QUEUE_NAME, false, false, false, null);
            //消息内容
            channel.basicPublish("", QUEUE_NAME, null, message.getBytes());
            logger.info("[x] Sent {}", message);
            //关闭通道和连接
            channel.close();
            channel.getConnection().close();
        } catch (Exception e) {
            logger.info(e.getCause().getMessage());
        }

    }

    public static void get() {

        try {
            Channel channel = getChannel();

            //创建队列
            channel.queueDeclare(QUEUE_NAME, false, false, false, null);
            //定义队列的消费者
            Consumer consumer = channel.getDefaultConsumer();
            //监听队列
            channel.basicConsume(QUEUE_NAME, true, consumer);
            //获取消息

        } catch (Exception e) {
            logger.info(e.getCause().getMessage());
        }

    }

}
