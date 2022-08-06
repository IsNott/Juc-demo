package com.nott.juc.unsafe;

import java.util.Collections;
import java.util.HashSet;
import java.util.Set;
import java.util.UUID;
import java.util.concurrent.CopyOnWriteArraySet;

/**
 * @author Nott
 * @Date 2022/8/5
 */

//java.util.ConcurrentModificationException 并发修改异常
// 可以看出set和list都是线程不安全的

/**
 * 1、Set<String> set = Collections.synchronizedSet(new HashSet<String>());
 * 2、CopyOnWriteArraySet<String> set = new CopyOnWriteArraySet<>();
 */
public class SetTest {
    public static void main(String[] args) {
        HashSet<String> set = new HashSet<>();
        //Set<String> set = Collections.synchronizedSet(new HashSet<String>());
        //CopyOnWriteArraySet<String> set = new CopyOnWriteArraySet<>();
        for (int i = 0; i < 10; i++) {
            new Thread(()->{
                set.add(UUID.randomUUID().toString().substring(0,5));
                System.out.println(set);
            },String.valueOf(i)).start();
        }
    }
}
