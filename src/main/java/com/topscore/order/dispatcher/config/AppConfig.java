package com.topscore.order.dispatcher.config;

import com.google.common.util.concurrent.RateLimiter;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import org.springframework.amqp.core.Queue;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.web.servlet.ViewResolver;
import org.springframework.web.servlet.view.BeanNameViewResolver;

@Configuration
public class AppConfig {

    @Bean
    public RateLimiter limiter() {
        return RateLimiter.create(100);
    }

    @Bean
    public Gson gson() {
        return new GsonBuilder().create();
    }

    // prod: start
    @Bean
    @Profile("prod")
    public Queue prodTmallQueue() {
        return new Queue("order-tmall");
    }

    @Bean
    @Profile("prod")
    public Queue prodTaobaoQueue() {
        return new Queue("order-taobao");
    }

    @Bean
    @Profile("prod")
    public Queue prodJdQueue() {
        return new Queue("order-jd");
    }

    @Bean
    @Profile("prod")
    public Queue prodVipQueue() {
        return new Queue("order-vip");
    }
    // prod: end

    // test: start
    @Bean
    @Profile("test")
    public Queue testTmallQueue() {
        return new Queue("order-tmall-test");
    }

    @Bean
    @Profile("test")
    public Queue testTaobaoQueue() {
        return new Queue("order-taobao-test");
    }

    @Bean
    @Profile("test")
    public Queue testJdQueue() {
        return new Queue("order-jd-test");
    }

    @Bean
    @Profile("test")
    public Queue testVipQueue() {
        return new Queue("order-vip-test");
    }
    // test: end

    // dev: start
    @Bean
    @Profile("dev")
    public Queue devTmallQueue() {
        return new Queue("order-tmall-dev");
    }

    @Bean
    @Profile("dev")
    public Queue devTaobaoQueue() {
        return new Queue("order-taobao-dev");
    }

    @Bean
    @Profile("dev")
    public Queue devJdQueue() {
        return new Queue("order-jd-dev");
    }

    @Bean
    @Profile("dev")
    public Queue devVipQueue() {
        return new Queue("order-vip-dev");
    }
    // dev: end

    @Bean
    public ViewResolver viewResolver() {
        return new BeanNameViewResolver();
    }

}
