package com.nott.juc;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @author Nott
 * @Date 2022/8/4
 */


public class SaleTicketTest02 {
    public static void main(String[] args) {
        //并发：多个线程操作一个资源，把资源类丢入线程
        Ticket ticket = new Ticket();
        //@FunctionalInterface 函数式接口 lambda表达式 (参数)->{代码}
        new Thread(() -> { for (int i = 0; i < 50; i++) ticket.sale(); }, "A").start();
        new Thread(() -> { for (int i = 0; i < 50; i++) ticket.sale(); }, "B").start();
        new Thread(() -> { for (int i = 0; i < 50; i++) ticket.sale(); }, "C").start();

    }
}

//lock使用
// 1.new ReentrantLock()
// 2.lock.lock(); //加锁
// 3.lock.unlock(); //解锁
class Ticket01 {
    private int number = 50;

    Lock lock = new ReentrantLock();

    public void sale() {
        lock.lock(); //加锁
        try {
            if (number > 0) {
                System.out.println(Thread.currentThread().getName() + "卖出" + number-- + ",剩余：" + number);
            }
        } finally {
            lock.unlock(); //解锁
        }
    }
}
