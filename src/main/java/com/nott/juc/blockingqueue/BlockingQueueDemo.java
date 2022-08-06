package com.nott.juc.blockingqueue;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.TimeUnit;

/**
 * @author Nott
 * @Date 2022/8/6
 */


public class BlockingQueueDemo {
    public static void main(String[] args) throws InterruptedException {
        test03();
    }

    // 抛出异常
    public static void test01() {
        //  public ArrayBlockingQueue(int capacity) {
        ArrayBlockingQueue<Object> queue = new ArrayBlockingQueue<>(3);
        System.out.println(queue.add("a"));
        System.out.println(queue.add("a"));
        System.out.println(queue.add("a"));

        System.out.println(queue.element()); // 查看队首
        // java.lang.IllegalStateException: Queue full
        //System.out.println(queue.add("a"));
        System.out.println("-------------------------");
        System.out.println(queue.remove());
        System.out.println(queue.remove());
        System.out.println(queue.remove());

        System.out.println(queue.element()); // 查看队首 java.util.NoSuchElementException

        // java.util.NoSuchElementException
        System.out.println(queue.remove());
    }

    // 不抛出异常，有返回值
    public static void test02() {
        //  public ArrayBlockingQueue(int capacity) {
        ArrayBlockingQueue<Object> queue = new ArrayBlockingQueue<>(3);
        System.out.println(queue.offer("a"));
        System.out.println(queue.offer("a"));
        System.out.println(queue.offer("a"));
        System.out.println(queue.offer("a")); // false
        System.out.println(queue.peek());
        System.out.println("-------------------------");
        System.out.println(queue.poll());
        System.out.println(queue.poll());
        System.out.println(queue.poll());
        System.out.println(queue.poll()); // null
    }

    // 不抛出异常，阻塞
    public static void test03() throws InterruptedException {
        //  public ArrayBlockingQueue(int capacity) {
        ArrayBlockingQueue<Object> queue = new ArrayBlockingQueue<>(3);
        queue.put("a");
        queue.put("b");
        queue.put("c");
        queue.put("d"); // 一直阻塞
        System.out.println("-------------------------");
        System.out.println(queue.take());
        System.out.println(queue.take());
        System.out.println(queue.take());
        System.out.println(queue.take()); // 一直阻塞
    }

    // 超时等待
    public static void test04() throws InterruptedException {
        //  public ArrayBlockingQueue(int capacity) {
        ArrayBlockingQueue<Object> queue = new ArrayBlockingQueue<>(3);
        System.out.println(queue.offer("a", 1, TimeUnit.SECONDS));
        System.out.println(queue.offer("a", 1, TimeUnit.SECONDS));
        System.out.println(queue.offer("a", 1, TimeUnit.SECONDS));
        System.out.println(queue.offer("a", 1, TimeUnit.SECONDS)); // false
        System.out.println("-------------------------");
        System.out.println(queue.poll());
        System.out.println(queue.poll());
        System.out.println(queue.poll());
        System.out.println(queue.poll()); // null
    }
}
