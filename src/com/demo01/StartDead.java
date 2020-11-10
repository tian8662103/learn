package com.demo01;

/**
 * start 运行线程
 */
public class StartDead extends Thread {
    private int i;

    @Override
    public void run() {
        for (; i < 100; i++) {
            System.out.println(this.getName()+":"+i);
        }
    }

    public static void main(String[] args) {
        StartDead startDead = new StartDead();
        for (int i = 0; i < 100; i++) {
            System.out.println(Thread.currentThread().getName()+":"+i);
            if (i == 20) {
                startDead.start();
                //判断线程是否启动 true 启动 ，false 没有启动
                System.out.println(startDead.isAlive());
            }
//            if (i > 20 && !startDead.isAlive() ) {
//                startDead.start();
//            }
        }
    }
}
