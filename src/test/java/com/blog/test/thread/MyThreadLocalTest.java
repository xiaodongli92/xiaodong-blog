package com.blog.test.thread;

/**
 * @Description:
 * @Author: lixiaodong@souyidai.com
 * @CreateDate: 2016/6/15
 */
public class MyThreadLocalTest {

    private static MyThreadLocal<Integer> numberLocal = new MyThreadLocal<Integer>(){
        @Override
        protected Integer initialValue() {
            return 0;
        }
    };

    private static int getNumber(){
        numberLocal.set(numberLocal.get()+1);
        return numberLocal.get();
    }

    public static void main(String[] args) {
        MyThread myThread1 = new MyThread();
        MyThread myThread2 = new MyThread();
        MyThread myThread3 = new MyThread();
        myThread1.start();
        myThread2.start();
        myThread3.start();
    }

    private static class MyThread extends Thread{
        @Override
        public void run() {
            System.out.println(Thread.currentThread().getName()+"》"+getNumber());
            System.out.println(Thread.currentThread().getName()+"》"+getNumber());
            System.out.println(Thread.currentThread().getName()+"》"+getNumber());
        }
    }
}
