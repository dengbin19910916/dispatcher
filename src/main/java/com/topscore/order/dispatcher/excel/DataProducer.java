package com.topscore.order.dispatcher.excel;

import org.apache.commons.lang3.RandomUtils;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collection;
import java.util.concurrent.Exchanger;

public class DataProducer implements Runnable {

    private Collection<Object> buffer = new ArrayList<>();

    private Exchanger<Collection<Object>> exchanger;

    public DataProducer(Exchanger<Collection<Object>> exchanger) {
        this.exchanger = exchanger;
        new Thread(this).start();
    }

    @Override
    public void run() {
        // 实现10次交换
        for (int i = 0; i < 2; i++) {
            for (int j = 0; j++ < 3_0; ) {
                buffer.add(new Student("邓斌", RandomUtils.nextInt(16, 40), LocalDateTime.now(),
                        RandomUtils.nextInt(50, 100), new BigDecimal(RandomUtils.nextInt(3000, 500000))));
            }
            try {
                // 调用exchange方法来与consumer交换数据
               buffer = exchanger.exchange(buffer);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
