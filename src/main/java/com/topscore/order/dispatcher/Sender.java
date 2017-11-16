package com.topscore.order.dispatcher;

import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * 消息发送接口。
 */
@Slf4j
public abstract class Sender {

    @Autowired
    protected RabbitTemplate rabbitTemplate;

    public void send(String message) {
        rabbitTemplate.convertAndSend(message);
    }
}
