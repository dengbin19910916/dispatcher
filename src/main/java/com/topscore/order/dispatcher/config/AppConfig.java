package com.topscore.order.dispatcher.config;

import org.springframework.amqp.core.Queue;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class AppConfig {

    @Bean
    public Queue vipQueue() {
        return new Queue("order-vip");
    }

    @Bean
    public Queue aliQueue() {
        return new Queue("order-ali");
    }
}
