package com.topscore.order.dispatcher;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;

@SpringBootApplication
public class DispatcherApplication {

    public static void main(String[] args) {
		ApplicationContext context = SpringApplication.run(DispatcherApplication.class, args);

//		Sender sender = context.getBean("sender", Sender.class);
//		Fetcher tmallFetcher = context.getBean("tmallFetcher", Fetcher.class);
//		sender.setFetcher(tmallFetcher);
//		sender.send();
	}
}
