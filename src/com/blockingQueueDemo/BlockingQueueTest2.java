package com.blockingQueueDemo;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;

/**
 * 线程通信
 */
public class BlockingQueueTest2 {
    public static void main(String[] args) {
        //创建一个容量为1 BlockingQueue
        BlockingQueue<String> bg = new ArrayBlockingQueue<>(1);
        new Producer("A1",bg).start();
        new Producer("A2",bg).start();
        new Producer("A3",bg).start();

        new Consumer("c1",bg).start();
    }
}

class Producer extends Thread {
    private BlockingQueue<String> bq;

    public Producer(String name,BlockingQueue<String> bq) {
        super(name);
        this.bq = bq;
    }

    @Override
    public void run() {
        String[] strArr = new String[]{"123","456","789"};
        for (int i = 0; i < 100; i++) {
            System.out.println(getName()+"生产者准备生产集合元素！");
            try {
                Thread.sleep(200);
                bq.put(strArr[i%3]);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println(getName()+"生产完成！"+bq);
        }

    }
}

class Consumer extends Thread {
    private BlockingQueue<String> bq;

    public Consumer(String name,BlockingQueue<String> bq) {
        super(name);
        this.bq = bq;
    }

    @Override
    public void run() {
        while (true) {
            System.out.println(getName() + "消费者准备消费集合元素");
            try {
                Thread.sleep(200);
                bq.take();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println(getName() + "消费完成"+bq);
        }
    }
}
