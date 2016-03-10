package com.blog.test.thread;

import java.util.Random;
import java.util.concurrent.Callable;
import java.util.concurrent.Executors;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.FutureTask;

/**
 * Created by xiaodong on 2016/3/10.
 */
public class Executor1 {


    public static void main(String[] args) throws InterruptedException{
        System.out.println("线程"+Thread.currentThread().getName()+" 开始");
        Task task = new Task();
        FutureTask futureTask = new FutureTask(task);
        ExecutorService executorService = Executors.newFixedThreadPool(5);
        executorService.submit(futureTask);
        System.out.println("线程完成没有"+futureTask.isDone());
        Thread.sleep(500);
        System.out.println("线程完成没有"+futureTask.isDone());
        Thread.sleep(2000);
        System.out.println("线程完成没有"+futureTask.isDone());
        System.out.println("线程"+Thread.currentThread().getName()+" 结束");
        executorService.shutdown();
    }
}
class Task implements Callable<Boolean> {

    public Task (){

    }
    @Override
    public Boolean call() throws Exception {
        try {
            System.out.print(Thread.currentThread().getName()+" 线程开始");
            for (int i=0;i<10;i++){
                System.out.println("第"+i+"个"+Thread.currentThread().getName());
                Thread.sleep(new Random().nextInt(500));
            }
            System.out.print(Thread.currentThread().getName()+" 线程结束");
            return Boolean.TRUE;
        } catch (Exception e){
            e.printStackTrace();
            return Boolean.FALSE;
        }
    }
}
