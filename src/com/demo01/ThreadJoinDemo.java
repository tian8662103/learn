package com.demo01;

/***
 * join()是当前方法进入就绪状态，等待调用join方法的线程运行结束
 */
public class ThreadJoinDemo extends Thread {
    //提供一个有参构造，用于设置线程名字
    public ThreadJoinDemo(String name) {
        super(name);
    }

    @Override
    public void run() {
        for (int i = 0; i < 100; i++) {
            System.out.println(this.getName()+":"+i);
        }
    }

    public static void main(String[] args) throws Exception {
        new ThreadJoinDemo("新线程").start();
        for (int i = 0; i < 100; i++) {
            if (i == 20) {
                ThreadJoinDemo tj = new ThreadJoinDemo("被Join的线程");
                tj.start();
                //main线程被调用tj线程join方法，main进入就绪状态必须等待tj线程执行完成
                tj.join();
            }
            System.out.println(Thread.currentThread().getName()+":"+i);
        }

    }

}


