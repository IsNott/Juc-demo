package com.nott.juc.callable;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.FutureTask;

/**
 * @author Nott
 * @Date 2022/8/6
 */


public class CallableTest01 {
    public static void main(String[] args) throws ExecutionException, InterruptedException {
        // new Thread(new Runnable()).start();
        // new Thread(new FutureTask<V>()).start();
        // new Thread(new FutureTask<V>(Callable())).start();
        MyThread myThread = new MyThread();
        FutureTask task = new FutureTask(myThread);
        new Thread(task, "A").start();
        new Thread(task, "B").start(); // call只打印一次，结果会被缓存
        System.out.println(task.get()); // 获取FutureTask的callable结果

        // 使用lambda定义callable
        Callable<String> stringCallable = () -> {
            System.out.println("lambda..");
            return "null";
        };
        FutureTask<String> futureTask = new FutureTask<>(stringCallable);
        new Thread(futureTask).start();
        System.out.println(futureTask.get()); // get方法可能会产生阻塞，一般放到程序最后或者使用异步通信

    }
}

class MyThread implements Callable<String> {

    @Override
    public String call() throws Exception {
        System.out.println("CAll...");
        return "call";
    }
}
