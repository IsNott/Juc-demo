package com.nott.juc.add;

import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

/**
 * @author Nott
 * @Date 2022/8/6
 */


public class CyclicBarrierDemo {

    //集齐七颗龙珠
    public static void main(String[] args) {
        // CyclicBarrier(int parties, Runnable barrierAction)
        // 创建一个新的 CyclicBarrier ，当给定数量的参与方（线程）等待它时将跳闸，并且当屏障被触发时执行给定的屏障操作，由进入屏障的最后一个线程执行。
        CyclicBarrier barrier = new CyclicBarrier(7, () -> {
            System.out.println("召唤神龙");
        }); // 传递需要到达的线程数量和一个Runnable线程

        for (int i = 0; i <= 2; i++) {
            final int temp = i;
            new Thread(() -> {
                System.out.println(Thread.currentThread().getName() + "获得第" + temp + "龙珠");
                try {
                    // 等待所有 parties在此障碍上调用 await 或者指定的等待时间过去
                    barrier.await(1, TimeUnit.SECONDS); // 如果线程达不到数量，会抛出异常信息
                } catch (InterruptedException e) {
                    e.printStackTrace();
                } catch (BrokenBarrierException e) {
                    e.printStackTrace();
                } catch (TimeoutException e) {
                    e.printStackTrace();
                }
            }, String.valueOf(i)).start();
        }
    }
}
