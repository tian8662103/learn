package com.demo01;

import java.util.Random;
import java.util.concurrent.Callable;
import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.Future;
import java.util.concurrent.RecursiveTask;

/**
 * 有返回值任务拆分
 */
public class Sum {
    public static void main(String[] args) throws Exception{
        int[] arr = new int[100];
        Random random = new Random();
        int total = 0;
        //初始化100个数字元素
        for (int i = 0, len = arr.length; i < len; i++) {
            int tmp = random.nextInt(20);
            //对数组元素赋值，并将元素的值添加到sum总和中
            total += (arr[i] = tmp);
        }
        System.out.println(total);
        //创建一个通用线程池
        ForkJoinPool pool = ForkJoinPool.commonPool();
        //提交可分解的CalTask任务
        Future<Integer> future = pool.submit(new CalTask(arr,0,arr.length));
        System.out.println(future.get());
        //关闭线程
        pool.shutdown();
    }
}

class CalTask extends RecursiveTask<Integer> {

    //每个 小任务 最多只累加20个数
    private static final int THRESHOLD = 20;
    private int arr[];
    private int start;
    private int end;

    public CalTask(int[] arr, int start, int end) {
        this.arr = arr;
        this.start = start;
        this.end = end;
    }

    @Override
    protected Integer compute() {
        int sum = 0;
        if (end - start < THRESHOLD) {
            for (int i = start; i < end; i++) {
                sum += arr[i];
            }
            return sum;
        } else {
            //当end与start之间的差大于THRESHOLD 任务拆分
            int middle = (start + end) / 2;
            CalTask left = new CalTask(arr, start, middle);
            CalTask right = new CalTask(arr, middle, end);
            left.fork();
            right.fork();
            //把两个任务累加的结果合并起来
            return left.join() + right.join();
        }

    }
}