package com.demo01;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

/**
 * 线程变量
 */
public class ThreadLocalTest {
    public static void main(String[] args) {
        Account at = new Account("初始名");
        /**
         * 虽然两个线程共享一个账号，即只有一个账号
         * 但由于两个ThreadLocal类型的，所以每个线程
         * 都完全拥有各自的账号名副本，因此在i==6之后，将看到两个线程
         * 访问同一个账号时出现不同的账号名
         */
        new MyTest( "A1",at).start();
        new MyTest( "A2",at).start();
        /**
         * 通过Collections类的synchronizedMap方法 在多线程中使用线程安全的HashMap对象
         */
        Map<Object, Object> objectObjectMap = Collections.synchronizedMap(new HashMap<>());
    }
}

class Account {
    //定义一个线程变量
    private ThreadLocal<String> name = new ThreadLocal<>();

    public Account(String str) {
        this.name.set(str);
        //访问当前线程name副本值
        System.out.println("-----"+this.name.get());
    }

    public String getName() {
        return name.get();
    }

    public void setName(String str) {
        this.name.set(str);
    }
}

class MyTest extends Thread {

    private Account account;

    public MyTest(String name, Account account) {
        super(name);
        this.account = account;
    }

    @Override
    public void run() {
        for (int i = 0; i < 10; i++) {
            if (i == 6) {
                account.setName(getName());
            }
            System.out.println(account.getName()+"账号的i值："+i);
        }
    }
}