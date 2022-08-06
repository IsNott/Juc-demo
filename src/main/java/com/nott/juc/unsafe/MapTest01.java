package com.nott.juc.unsafe;

import java.util.HashMap;
import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @author Nott
 * @Date 2022/8/6
 */


public class MapTest01 {
    public static void main(String[] args) {
        // 默认等价于什么？new HashMap<>(16,0.75f)
        //HashMap<String, String> map = new HashMap<>(); // 并发修改异常java.util.ConcurrentModificationException
        // 使用ConcurrentHashMap，需要理解ConcurrentHashMap的原理
        ConcurrentHashMap<String, String> map = new ConcurrentHashMap<>();
        // 加载因子，初始化容量
        for (int i = 0; i < 30 ; i++) {
            new Thread(()->{
                map.put(Thread.currentThread().getName(), UUID.randomUUID().toString().substring(0,5));
                System.out.println(map);
            },String.valueOf(i)).start();

        }
    }
}
