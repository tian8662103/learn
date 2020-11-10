package com.demo01;

/**
 * 实现Runnable接口开启多线程
 */
public class RunnableDemo implements Runnable {
    private int i;

    public void run() {
        for (; i < 100; i++) {
            System.out.println(Thread.currentThread().getName()+" "+i);
        }
    }

    public static void main(String[] args) {
        for (int i  = 0; i < 100; i++) {
            System.out.println(Thread.currentThread().getName()+" "+i);
            if (i == 20) {
                RunnableDemo runnableDemo = new RunnableDemo();
                new Thread(runnableDemo, "线程一").start();
                new Thread(runnableDemo, "线程二").start();
            }
        }
    }
}
