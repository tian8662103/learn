package com.threadgroup;

public class ThreadGroupTest {
    public static void main(String[] args) {
        //获取主线程所在的线程组
        ThreadGroup mainGroup = Thread.currentThread().getThreadGroup();
        System.out.println("主线程组的线程组名称" + mainGroup.getName());
        System.out.println("主线程是否是后台线程" + mainGroup.isDaemon());
        new MyThread("主线程组的线程").start();
        ThreadGroup tg = new ThreadGroup("新线程足");
        tg.setDaemon(true);
        System.out.println("新线程组是否为后台线程" + tg.isDaemon());
        MyThread mtg = new MyThread(tg, "tg组的线程甲");
        mtg.start();
        new MyThread(tg,"Tg组的线程乙").start();
    }
}

class MyThread extends Thread {
    public MyThread(String name) {
        super(name);
    }

    public MyThread(ThreadGroup group, String name) {
        super(group, name);
    }

    @Override
    public void run() {
        for (int i = 0; i < 20; i++) {
            System.out.println(getName()+"线程的i变量:"+i);
        }
    }
}
