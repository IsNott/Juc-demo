package com.nott.juc.readwrite;

/**
 * @author Nott
 * @Date 2022/8/6
 */

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

/**
 * 独占锁、互斥锁（写锁） 一次只能被一个线程占有
 * 共享锁（读锁） 多个线程同时占有
 * ReadWriteLock
 * 1、读-读 可以共存
 * 2、读-写 不能共存
 * 3、写-写 不能共存
 */
public class ReadWriteLockDemo {
    public static void main(String[] args) {
        MyCacheLock cache = new MyCacheLock();
        // 写入
        for (int i = 0; i < 10; i++) {
            final String temp = String.valueOf(i);
            final int tempInt = i;
            new Thread(() -> {
                cache.put(temp, tempInt);
            }, String.valueOf(i)).start();
        }

        // 读取
        for (int i = 0; i < 10; i++) {
            final String temp = String.valueOf(i);
            final int tempInt = i;
            new Thread(() -> {
                cache.get(temp);
            }, String.valueOf(i)).start();
        }
    }
}

/**
 * 自定义缓存
 */
class MyCache {
    private volatile Map<String, Object> map = new HashMap<>();


    // 存，写
    public void put(String k, Object v) {
        System.out.println(Thread.currentThread().getName() + "put cache: " + v);
        map.put(k, v);
        System.out.println(Thread.currentThread().getName() + "put cache end...");
        System.out.println(map);
    }


    // 取，读
    public void get(String k) {
        Object o = map.get(k);
        System.out.println(Thread.currentThread().getName() + "get cache: " + o);
        System.out.println(Thread.currentThread().getName() + "get cache end...");
    }


}

/**
 * 自定义缓存加读写锁
 */
class MyCacheLock {
    private volatile Map<String, Object> map = new HashMap<>();
    // 读写锁，ReadWriteLock接口的唯一实现类
    // 更加细粒度的控制
    private ReentrantReadWriteLock lock = new ReentrantReadWriteLock();


    // 存，写
    // 写入时应同时只有一个线程进行写
    public void put(String k, Object v) {
        lock.writeLock().lock();
        try {
            System.out.println(Thread.currentThread().getName() + "put cache: " + v);
            map.put(k, v);
            System.out.println(Thread.currentThread().getName() + "put cache end...");
            System.out.println(map);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            lock.writeLock().unlock();
        }
    }


    // 取，读
    // 读的时候可以多个线程同时进
    public void get(String k) {
        lock.readLock().lock();
        try {
            Object o = map.get(k);
            System.out.println(Thread.currentThread().getName() + "get cache: " + o);
            System.out.println(Thread.currentThread().getName() + "get cache end...");
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            lock.readLock().unlock();
        }
    }
}