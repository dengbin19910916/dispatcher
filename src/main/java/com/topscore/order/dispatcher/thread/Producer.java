package com.topscore.order.dispatcher.thread;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Exchanger;

public class Producer implements Runnable {

    private List<String> buffer = new ArrayList<>();
    private final Exchanger<List<String>> exchanger;

    public Producer(Exchanger<List<String>> exchanger) {
        this.exchanger = exchanger;
    }

    @Override
    public void run() {
//        int cycle = 1;
//        for (int i = 0; i < 10; i++) {
//            System.out.printf("Producer: Cycle %d\n", cycle);
//            for (int j = 0; j < 10; j++) {
//                String message = "Event " + ((i * 10) + j);
//                System.out.printf("Producer: %s\n", message);
//                buffer.add(message);
//            }
//
//            try {
//                Thread.sleep(1000);
//                buffer = exchanger.exchange(buffer);
//            } catch (InterruptedException e) {
//                e.printStackTrace();
//            }
//
//            System.out.println("Producer: " + buffer.size());
//            cycle++;
//        }

        // 实现10次交换
        for (int i = 0; i < 10; i++) {
            buffer.add("第" + i + "次生产者的数据" + i);
            try {
                // 调用exchange方法来与consumer交换数据
                System.out.println("第" + i + "次生产者在等待.....");
                buffer = exchanger.exchange(buffer);
                System.out.println("第" + i + "次生产者交换后的数据：" + buffer.get(i));
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
