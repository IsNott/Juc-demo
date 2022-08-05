package com.nott.juc.lock8;

import java.util.concurrent.TimeUnit;

/**
 * @author Nott
 * @Date 2022/8/5
 */

/**
 * 一个静态同步方法一个普通同步方法，一个对象？ call
 * 一个静态同步方法一个普通同步方法，两个对象？ call
 */
public class Test04 {
    public static void main(String[] args) {
        // 两个调用者的Class类模板只有一个
        Phone04 phone = new Phone04();
        Phone04 phone01 = new Phone04();
        //Phone04 phone01 = new Phone04();
        new Thread(()->{phone.sendMsm();},"A").start();
        try {
            TimeUnit.SECONDS.sleep(1);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        new Thread(()->{phone01.call();},"B").start();
    }
}

class Phone04{

    // synchronized 锁的对象是方法的调用者
    // 锁的是class类模板
    public static synchronized void sendMsm(){
        try {
            TimeUnit.SECONDS.sleep(4);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("sendMsm...");
    }


    // 锁的是对象
    public synchronized void call(){
        System.out.println("call...");
    }

    public void hello(){
        System.out.println("hello...");
    }
}
