package com.blog.test.thread;

/**
 * Created by xiaodong on 2016/3/10.
 */
public class Runnable1 implements Runnable {

    public static void main(String[] args){
        System.out.println(Thread.currentThread().getName()+" 线程开始执行！");
        Runnable1 runnable1 = new Runnable1();
        Thread thread1 = new Thread(runnable1);
        Thread thread2 = new Thread(runnable1);
        thread1.start();
        thread2.start();
        System.out.println(Thread.currentThread().getName()+" 线程结束执行！");
    }

    @Override
    public void run() {
        System.out.println(Thread.currentThread().getName()+" 线程开始执行");
        try {
            for (int i=0;i<10;i++){
                System.out.println("第"+i+"个"+Thread.currentThread().getName());
                Thread.sleep((long) (Math.random()*10));
            }
        } catch (InterruptedException e){
            e.printStackTrace();
        }
        System.out.println(Thread.currentThread().getName()+" 线程结束执行");
    }
}
