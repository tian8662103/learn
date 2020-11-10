package com.demo01;

/**
 * 线程优先级的设置
 * setPriority()来设置
 */
public class PriorityTest extends  Thread {
    public PriorityTest(String name) {
        super(name);
    }

    @Override
    public void run() {
        for (int i = 0; i < 50; i++) {
            System.out.println(this.getName()+",优先级"+getPriority()+",循环变量:"+i);
        }
    }

    public static void main(String[] args) {
        for (int i = 0; i < 30; i++) {
            if (i == 10) {
                PriorityTest p1 = new PriorityTest("p1");
                p1.start();
                System.out.println("优先级:"+p1.getPriority());
                p1.setPriority(MIN_PRIORITY);
            }
            if (i == 20) {
                PriorityTest p2 = new PriorityTest("p2");
                p2.start();
                System.out.println("优先级:"+p2.getPriority());
                p2.setPriority(MAX_PRIORITY);
            }
        }
    }
}
