package com.blog.test.thread;

/**
 * Created by xiaodong on 2016/3/17.
 */
public class StaticTest2 {
    static {
        System.out.println("父类--静态代码块");
    }

    public StaticTest2() {
        System.out.println("父类--构造函数");
    }

    {
        System.out.println("父类--非静态代码块");
    }

    public static void main(String[] args) {
        new ExB();
    }
}

class ExB extends StaticTest2 {
    static {
        System.out.println("子类--静态代码块");
    }
    {
        System.out.println("子类--非静态代码块");
    }

    public ExB() {
        System.out.println("子类--构造函数");
    }
}
