package com.blog.test.thread;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

/**
 * @Description: 简单实现自己ThreadLocal
 * @Author: lixiaodong@souyidai.com
 * @CreateDate: 2016/6/15
 */
public class MyThreadLocal<T> {

    private Map<Thread, T> threadMap = Collections.synchronizedMap(new HashMap<Thread, T>());

    public void set(T value) {
        threadMap.put(Thread.currentThread(), value);
    }

    public T get() {
        Thread thread = Thread.currentThread();
        T value = threadMap.get(thread);
        if (null == value || !threadMap.containsKey(thread)) {
            value = initialValue();
            threadMap.put(Thread.currentThread(),value);
        }
        return value;
    }

    public void remove() {
        threadMap.remove(Thread.currentThread());
    }

    protected T initialValue(){
        return null;
    }
}
