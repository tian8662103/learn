package com.demo01;

/**
 * 设置后台线程
 */
public class DaemonThread extends Thread {
    @Override
    public void run() {
        for (int i = 0; i < 100; i++) {
            System.out.println(this.getName() + ":" + i);
        }
    }

    public static void main(String[] args) {
        DaemonThread daemonThread = new DaemonThread();
//        daemonThread.start();
        //将此线程设置成后台线程
        daemonThread.setDaemon(true);
        for (int i = 0; i < 10; i++) {
            System.out.println(Thread.currentThread().getName() + ":" + i);
        }
    }
}
