package com.demo01;

public class SecondThread implements Runnable {
    private int i ;
    public void run() {
        for (; i < 100; i++) {
            System.out.println(Thread.currentThread().getName()+" "+i);
        }
    }

    public static void main(String[] args) {
        for (int i = 0; i < 100; i++) {
            System.out.println(Thread.currentThread().getName() + "" + i);
            if (i == 20) {
                SecondThread sc = new SecondThread();
                //通过new Thread(target,nema)方法创建新的线程
                new Thread(sc, "线程1").start();
                new Thread(sc, "线程2").start();
            }
        }
    }
}
