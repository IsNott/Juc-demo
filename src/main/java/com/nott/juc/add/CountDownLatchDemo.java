package com.nott.juc.add;

import java.util.concurrent.CountDownLatch;

/**
 * @author Nott
 * @Date 2022/8/6
 */

// 计数器
public class CountDownLatchDemo {
    public static void main(String[] args) throws InterruptedException {
        // 总数是6
        CountDownLatch countDownLatch = new CountDownLatch(6);
        for (int i = 0; i <= 6; i++) {
            new Thread(() -> {
                System.out.println(Thread.currentThread().getName() + " Go out");
                countDownLatch.countDown(); //计算器减一
            }, String.valueOf(i)).start();
        }
        countDownLatch.await(); // 计算器等待归零，然后程序向下运行
        System.out.println("process end..");

    }
}
