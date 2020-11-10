package com.demo01;

public class FirstThread extends Thread {
    private int i;
    public void run(){
        for (; i < 100;i++) {
            System.out.println(getName()+" "+i);
        }
    }

    public static void main(String[] args) {
        for (int i = 0; i < 100; i++) {
            //currentThread()方法获取当前的线程
            System.out.println(Thread.currentThread().getName()+" "+i);

            if (i == 20) {
                //创建并启动第一个线程
                FirstThread f1 = new FirstThread();
                f1.setName("hahahah");
                f1.start();
                //创建并启动第二个线程
                new FirstThread().start();
            }
        }
    }
}
