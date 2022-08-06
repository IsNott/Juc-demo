package com.nott.juc.add;

import java.util.concurrent.Semaphore;
import java.util.concurrent.TimeUnit;

/**
 * @author Nott
 * @Date 2022/8/6
 */


public class SemaphoreDemo {
    public static void main(String[] args) {
        /**
         * 停车位问题：有三个车位，六辆车，限流
         */
        // 允许的线程数量，使用给定数量的许可和非公平公平设置创建 Semaphore 。
        Semaphore semaphore = new Semaphore(3);
        for (int i = 0; i < 6; i++) {
            new Thread(() -> {
                try {
                    semaphore.acquire(); //获取许可，从此信号量获取许可，阻止直到一个可用，或者线程为interrupted ：抢到车位
                    System.out.println(Thread.currentThread().getName() + "抢到车位");
                    TimeUnit.SECONDS.sleep(3);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                semaphore.release(); // 释放，发布许可证，将其返回到信号量。
            }, String.valueOf(i)).start();
        }
    }
}
