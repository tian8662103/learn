package com.demo01;

public class ThreadDemo1 extends Thread {
    private int i;

    public void run() {
        //线程执行体
        for (; i < 100; i++) {
            System.out.println(this.getName()+""+i);
        }
    }

    public static void main(String[] args) {
        for (int i = 0; i < 100; i++) {
            //Thread.currentThread()获取当前线程
            System.out.println(Thread.currentThread().getName()+" "+i);
            if (i == 20) {
//                new ThreadDemo1().start();
                ThreadDemo1 threadDemo1 = new ThreadDemo1();
                threadDemo1.setName("线程");
                threadDemo1.start();
                ThreadDemo1 threadDemo2 = new ThreadDemo1();
                threadDemo2.setName("线");
                threadDemo2.start();

            }

        }
    }
}
