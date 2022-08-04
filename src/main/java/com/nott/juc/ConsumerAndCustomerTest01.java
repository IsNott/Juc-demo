package com.nott.juc;

/**
 * @author Nott
 * @Date 2022/8/4
 */

/**
 * 线程之间的通信问题：生产者和消费者问题 等待唤醒 通知唤醒
 * 线程之间交替执行 A B操作同一个变量 num
 * A num ++
 * B num --
 */
public class ConsumerAndCustomerTest01 {
    public static void main(String[] args) {
        Data data = new Data();

        new Thread(()->{
            for (int i = 0; i < 10; i++) {
                try {
                    data.increment();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        },"A").start();
        new Thread(()->{
            for (int i = 0; i < 10; i++) {
                try {
                    data.decrement();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        },"B").start();
        new Thread(()->{
            for (int i = 0; i < 10; i++) {
                try {
                    data.increment();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        },"C").start();
        new Thread(()->{
            for (int i = 0; i < 10; i++) {
                try {
                    data.decrement();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        },"D").start();
    }
}

// 判断是否需要等待 业务 通知
class Data {
    private int num = 0;

    //+1
    public synchronized void increment() throws InterruptedException {
        while (num != 0) {
            //等待
            this.wait();
        }
        num++; //通知其他线程 +1结束
        System.out.println(Thread.currentThread().getName()+"=>"+num);
        this.notifyAll();
    }

    //-1
    public synchronized void decrement() throws InterruptedException {
        while (num == 0) {
            //等待
            this.wait();
        }
        num--; //通知其他线程 -1结束
        System.out.println(Thread.currentThread().getName()+"=>"+num);
        this.notifyAll();
    }
}