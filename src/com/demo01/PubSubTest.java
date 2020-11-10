package com.demo01;

import java.util.List;
import java.util.concurrent.Flow;
import java.util.concurrent.SubmissionPublisher;

/**
 * java 新特性  发布订阅框架
 */
public class PubSubTest {
    public static void main(String[] args) {
        //创建一个SubmissionPublisher作为发布者
        SubmissionPublisher<String> publisher = new SubmissionPublisher<>();
        //创建订阅者
        MySubscriber<String> subscriber = new MySubscriber<>();
        //注册订阅者
        publisher.subscribe(subscriber);
        System.out.println("开发发布数据...");
        List.of("Java", "Kotlin", "Go", "Erlang", "Swift", "Lua")
                .forEach(im ->{
                    //发布数据
                    publisher.submit(im);
                    try {
                        Thread.sleep(500);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                });
        //发布结果
        publisher.close();
        synchronized ("fkJava") {
            try {
                //让现场进入等待
                "fkJava".wait();
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        }
    }
}

/**
 * 订阅者
 */
class MySubscriber<T> implements Flow.Subscriber<T> {

    //发布者与订阅者之间的纽带
    private Flow.Subscription subscription;

    //订阅式触发该方法
    @Override
    public void onSubscribe(Flow.Subscription subscription) {
        this.subscription = subscription;
        //开始请求数据
        subscription.request(1);
    }
    //接受数据时触发该方法
    @Override
    public void onNext(T item) {
        System.out.println("获取到数据："+item);
        //请求下一条数据
        subscription.request(1);
    }
    //订阅出错时触发该方法
    @Override
    public void onError(Throwable throwable) {
        throwable.printStackTrace();
        synchronized ("fkJava"){
            //唤醒同步器上等待的所有线程
            "fkJava".notifyAll();
        }
    }
    //订阅结束是触发该方法
    @Override
    public void onComplete() {
        System.out.println("订阅结束");
        synchronized ("fkJava"){
            "fkJava".notifyAll();
        }
    }
}