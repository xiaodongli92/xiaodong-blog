package com.blog.test.thread;

/**
 * Created by xiaodong on 2016/3/17.
 */
class SSClass
{
    static
    {
        System.out.println("SSClass");
    }
}
class SuperClass extends SSClass
{
    static
    {
        System.out.println("SuperClass init!");
    }

    public static int value = 123;

    public SuperClass()
    {
        System.out.println("init SuperClass");
    }
}
class SubClass extends SuperClass
{
    static
    {
        System.out.println("SubClass init");
    }

    static int value=2;

    public SubClass()
    {
        System.out.println("init SubClass");
    }
}
class NotInitialization
{
    public static void main(String[] args)
    {
        System.out.println(SubClass.value);
    }
}
