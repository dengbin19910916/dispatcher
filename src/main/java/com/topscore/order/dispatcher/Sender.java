package com.topscore.order.dispatcher;

import com.google.common.util.concurrent.RateLimiter;
import com.google.gson.Gson;
import com.topscore.order.dispatcher.domain.OrderMessage;
import com.topscore.order.dispatcher.domain.OrderMessageRepository;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * 消息发送接口。
 */
@Slf4j
@Component
public class Sender {

    @Autowired
    protected RabbitTemplate rabbitTemplate;
    @Autowired
    private OrderMessageRepository repository;
    @Autowired
    private Gson gson;
    @Autowired
    private RateLimiter limiter;

    @Setter
    private Fetcher fetcher;

    public void send() {
        List<OrderMessage> messages = fetcher.fetch();
        messages.forEach(message -> {
            limiter.acquire();  // 限流，默认每秒100条
            repository.save(message);
            rabbitTemplate.convertAndSend(gson.toJson(message));
            log.info("sent message: " + gson.toJson(message));
        });
    }
}
