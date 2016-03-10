package com.blog.test.thread;

/**
 * Created by xiaodong on 2016/3/10.
 */
public class Thread1 {
    public static void main(String args[]){
        System.out.println(Thread.currentThread().getName()+" 线程开始执行");
        new MitiSay("A").start();
        new MitiSay("B").start();
//        new MitiSay("A").run();
//        new MitiSay("B").run();
        System.out.println(Thread.currentThread().getName()+" 线程结束执行");
    }
}

class MitiSay extends Thread {

    public MitiSay(String threadName){
        super(threadName);
    }

    public void run(){
        System.out.println(getName()+" 线程开始");
        try {
            for (int i=0;i<10;i++){
                System.out.println("第"+i+"次"+getName());
                sleep((long) (Math.random()*10));
            }
        }catch (InterruptedException e){
            e.printStackTrace();
        }
        System.out.println(getName()+"线程结束！");
    }
}
