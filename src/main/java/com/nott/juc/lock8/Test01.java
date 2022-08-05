package com.nott.juc.lock8;

/**
 * @author Nott
 * @Date 2022/8/5
 */

import java.util.concurrent.TimeUnit;

/**
 * 8锁现象
 * 1、标准情况下，两个线程先打印 发短信还是打电话？ 发短信
 * 2、sendMsm延迟4秒情况下，两个线程先打印 发短信还是打电话？ 发短信
 */
public class Test01 {
    public static void main(String[] args) {
        Phone phone = new Phone();
        new Thread(()->{phone.sendMsm();},"A").start();
        try {
            TimeUnit.SECONDS.sleep(1);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        new Thread(()->{phone.call();},"B").start();
    }
}

class Phone{

    // synchronized 锁的对象是方法的调用者
    // 两个方法用的是同一把锁，因为操作同一个对象，谁先拿到谁先执行
    public synchronized void sendMsm(){
        try {
            TimeUnit.SECONDS.sleep(4);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("sendMsm...");
    }

    public synchronized void call(){
        System.out.println("call...");
    }
}
