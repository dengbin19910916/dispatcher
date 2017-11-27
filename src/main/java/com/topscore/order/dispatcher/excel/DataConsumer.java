package com.topscore.order.dispatcher.excel;

import java.util.ArrayList;
import java.util.Collection;
import java.util.concurrent.Exchanger;

public class DataConsumer implements Runnable {

    private Collection<Object> buffer = new ArrayList<>();

    private Exchanger<Collection<Object>> exchanger;

    public DataConsumer(Exchanger<Collection<Object>> exchanger) {
        this.exchanger = exchanger;
        new Thread(this).start();
    }

    @Override
    public void run() {
        try {
            for (int i = 0; i < 2; i++) {
                buffer = exchanger.exchange(buffer);
                System.out.println(buffer.size());
                System.out.println(buffer);
                buffer.clear();
            }

        } catch (InterruptedException ignored) {
        }
    }

    public static void main(String[] args) {
        Exchanger<Collection<Object>> exchanger = new Exchanger<>();
        Thread producer = new Thread(new DataProducer(exchanger));
        Thread consumer = new Thread(new DataConsumer(exchanger));

//        producer.start();
//        consumer.start();
    }
}
