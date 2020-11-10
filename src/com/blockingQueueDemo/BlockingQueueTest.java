package com.blockingQueueDemo;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;

public class BlockingQueueTest {
    public static void main(String[] args) throws Exception{
        BlockingQueue<String> bq = new ArrayBlockingQueue<>(2);
        bq.offer("1123");
        bq.offer("1123");
        bq.offer("1123");
    }
}
