package com.nott.juc;

/**
 * @author Nott
 * @Date 2022/8/4
 */

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * A 执行完 调用B B执行完调用C C执行调用A
 */
public class ConditionTest01 {
    public static void main(String[] args) {
        Data3 data3 = new Data3();
        new Thread(()->{
            for (int i = 0; i < 10; i++) {
                data3.printA();;
            }
        },"a").start();
        new Thread(()->{
            for (int i = 0; i < 10; i++) {
                data3.printB();;
            }
        },"b").start();
        new Thread(()->{
            for (int i = 0; i < 10; i++) {
                data3.printC();
            }
        },"c").start();
    }
}

class Data3{
    private Lock lock = new ReentrantLock();
    private Condition conditionA = lock.newCondition();
    private Condition conditionB = lock.newCondition();
    private Condition conditionC = lock.newCondition();
    private int num = 1;

    public void printA(){
        lock.lock();
        try {
        //   业务、判断->执行->通知
            while (num !=1){
                conditionA.await();
            }
            num = 2;
            System.out.println(Thread.currentThread().getName() + "->AAAAA");
            conditionB.signal();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            lock.unlock();
        }

    }
    public void printB(){
        lock.lock();
        try {
            while (num != 2){
                conditionB.await();
            }
            num = 3;
            System.out.println(Thread.currentThread().getName() + "->BBBBB");
            conditionC.signal();
        } catch (Exception e) {

            e.printStackTrace();
        } finally {
            lock.unlock();
        }

    }
    public void printC(){
        lock.lock();
        try {
            while (num != 3){
                conditionC.await();
            }
            num = 1;
            System.out.println(Thread.currentThread().getName() + "->CCCCCC");
            conditionA.signal();
        } catch (Exception e) {

            e.printStackTrace();
        } finally {
            lock.unlock();
        }
    }
}
