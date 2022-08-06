package com.nott.juc.unsafe;

import java.util.*;
import java.util.concurrent.CopyOnWriteArrayList;

/**
 * @author Nott
 * @Date 2022/8/5
 */

//java.util.ConcurrentModificationException 并发修改异常
public class ListTest01 {
    public static void main(String[] args) {
        // 并发下ArrayList 不安全
        /**
         * 解决方案：1、List<String> list = new Vector<>(); 里面的方法是synchronized修饰的
         * 2、List<String> list = Collections.synchronizedList(new ArrayList<>());
         * 3、List<String> list = new CopyOnWriteArrayList<>();
         */
        // CopyOnWrite 写入时复制 COW 计算机的优化策略
        // 多个线程调用时list读取固定，写入（覆盖）
        // 在写入时避免覆盖
        // 读写分离
        // CopyOnWriteArrayList比Vector效率高 ，因为使用ReentrantLock
        List<String> list = new CopyOnWriteArrayList<>();
        //List<String> list = new Vector<>();
        for (int i = 0; i < 10; i++) {
            new Thread(() -> {
                list.add(UUID.randomUUID().toString().substring(0, 5));
                System.out.println( list);
            }, String.valueOf(i)).start();
        }
    }
}
