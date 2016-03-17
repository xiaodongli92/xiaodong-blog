package com.blog.test.thread;

/**
 * Created by xiaodong on 2016/3/17.
 */
public class StaticTest {
    public static void main(String[] args){
        staticFunction();
    }

    static StaticTest staticTest = new StaticTest();

    static  int b=112;

    static {
        System.out.println("1");
    }


    StaticTest() {
        System.out.println("3");
        System.out.println("a="+a+"b="+b);
    }

    {
        System.out.println("2");
    }

    public static void staticFunction(){
        System.out.println("4");
        System.out.println(b);
    }

    int a=110;
}
