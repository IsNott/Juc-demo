package com.nott.juc.add;

import java.util.concurrent.CountDownLatch;

/**
 * @author Nott
 * @Date 2022/8/6
 */

// 计数器
public class CountDownLatchDemo {
    public static void main(String[] args) throws InterruptedException {
        // 等待线程总数是6
        // 减少锁存器的计数，如果计数达到零则释放所有等待的线程
        CountDownLatch countDownLatch = new CountDownLatch(6);
        for (int i = 0; i <= 6; i++) {
            new Thread(() -> {
                System.out.println(Thread.currentThread().getName() + " Go out");
                countDownLatch.countDown(); //计算器减一
            }, String.valueOf(i)).start();
        }
        // 当前线程等到锁存器倒计数到零，除非线程是 interrupted
        countDownLatch.await(); // 计算器等待归零，然后程序向下运行
        System.out.println("process end..");

    }
}
