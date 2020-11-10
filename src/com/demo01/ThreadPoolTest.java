package com.demo01;


import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;


/***
 * 线程池
 */
public class ThreadPoolTest {
    public static void main(String[] args) throws Exception {
        //创建一个线程池
        ExecutorService executorService = Executors.newFixedThreadPool(6);
        //Lambda表达式创建Runnable
        Runnable target =() ->{
            for (int i = 0; i < 100; i++) {
                System.out.println(Thread.currentThread().getName() + "的i值为：" + i);
            }
        };
        //创建两个线程池
        executorService.submit(target);
        executorService.submit(target);
        //关闭线程池
        executorService.shutdown();

    }
}
