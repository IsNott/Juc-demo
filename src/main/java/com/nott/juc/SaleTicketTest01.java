package com.nott.juc;

/**
 * @author Nott
 * @Date 2022/8/4
 */


public class SaleTicketTest01 {
    public static void main(String[] args) {
        //并发：多个线程操作一个资源，把资源类丢入线程
        Ticket ticket = new Ticket();

        //@FunctionalInterface 函数式接口 lambda表达式 (参数)->{代码}
        new Thread(() -> { for (int i = 0; i < 50; i++) ticket.sale(); }, "A").start();
        new Thread(() -> { for (int i = 0; i < 50; i++) ticket.sale(); }, "B").start();
        new Thread(() -> { for (int i = 0; i < 50; i++) ticket.sale(); }, "C").start();
    }
}

//synchronized
class Ticket {
    private int number = 50;

    // synchronized 本质：队列 锁
    public synchronized void sale() {
        if(number >0){
            System.out.println(Thread.currentThread().getName() + "卖出" + number-- + ",剩余：" + number);
        }
    }
}
