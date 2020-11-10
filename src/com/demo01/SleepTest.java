package com.demo01;

import java.util.Date;

/**
 * sleep是线程处于阻塞状态
 */
public class SleepTest {
    public static void main(String[] args) throws InterruptedException {
        for (int i = 0; i < 10; i++) {
            System.out.println("当前时间："+ new Date());
            Thread.sleep(1000);

        }
    }
}
