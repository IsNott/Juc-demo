package com.nott.juc.lock8;

/**
 * @author Nott
 * @Date 2022/8/5
 */


import java.util.concurrent.TimeUnit;

/**
 * 3、一个普通方法，一个同步方法先输出hello还是sendMsm？ hello
 * 4、两个对象，两个同步方法，先执行哪个？
 */
public class Test02 {
    public static void main(String[] args) {
        // 两个调用者，两个锁
        Phone02 phone = new Phone02();
        Phone02 phone01 = new Phone02();
        new Thread(()->{phone.sendMsm();},"A").start();
        try {
            TimeUnit.SECONDS.sleep(1);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        new Thread(()->{phone01.call();},"B").start();
    }
}

class Phone02{

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
    //这里没有锁，不受锁的影响，调用者不会被锁
    public void hello(){
        System.out.println("hello...");
    }
}
