package com.threadgroup;

public class ExHandler {
    public static void main(String[] args) {
        Thread.currentThread().setUncaughtExceptionHandler(new MyExHandle());
        int a = 1 / 0;
        System.out.println("程序正常结束");
    }
}

class MyExHandle implements Thread.UncaughtExceptionHandler {
    /**
     * 处理线程未处理的异常
     * @param t
     * @param e
     */
    @Override
    public void uncaughtException(Thread t, Throwable e) {
        System.out.println(t + "线程出现了异常：" + e);
    }
}
