package com.nott.juc.lock8;

import java.util.concurrent.TimeUnit;

/**
 * @author Nott
 * @Date 2022/8/5
 */

/**
 * 5.增加两个静态同步方法，只有一个对象，先输出？ sendMsm
 * 6.两个对象两个静态同步？ sendMsm
 */
public class Test03 {
    public static void main(String[] args) {
        // 两个调用者的Class类模板只有一个
        Phone03 phone = new Phone03();
        Phone03 phone01 = new Phone03();
        new Thread(()->{phone.sendMsm();},"A").start();
        try {
            TimeUnit.SECONDS.sleep(1);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        new Thread(()->{phone01.call();},"B").start();
    }
}

class Phone03{

    // synchronized 锁的对象是方法的调用者
    // static 静态方法
    // 类一加载就有了，锁的是class
    // 因为Phone03.class全局唯一，所以全局只有一个调用者
    public static synchronized void sendMsm(){
        try {
            TimeUnit.SECONDS.sleep(4);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("sendMsm...");
    }


    public static synchronized void call(){
        System.out.println("call...");
    }

    public void hello(){
        System.out.println("hello...");
    }
}
