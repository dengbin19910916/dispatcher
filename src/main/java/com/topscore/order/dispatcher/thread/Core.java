package com.topscore.order.dispatcher.thread;

import java.util.List;
import java.util.concurrent.Exchanger;

public class Core {
    public static void main(String[] args) {
        Exchanger<List<String>> exchanger = new Exchanger<>();

        Producer producer = new Producer(exchanger);
        Consumer consumer = new Consumer(exchanger);

        Thread threadProducer = new Thread(producer);
        Thread threadConsumer = new Thread(consumer);
        threadProducer.start();
        threadConsumer.start();
    }
}
