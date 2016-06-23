package com.blog.test.reflect;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;

/**
 * @Description:
 * @Author: lixiaodong@souyidai.com
 * @CreateDate: 2016/6/23
 */
public class ReflectTest {

    public static void main(String[] args) throws IllegalAccessException, NoSuchMethodException, InvocationTargetException {
        Person person = new Person();
        person.setName("test");
        person.setAge(1);
        //利用反射获取对象私有方法
        Method method = person.getClass().getDeclaredMethod("getNameAndAge");
        method.setAccessible(true);
        System.out.println(method.invoke(person));
        //利用反射获取对象属性
        Map<String,String> map = new HashMap<>();
        Class clazz = person.getClass();
        Field[] fields = clazz.getDeclaredFields();
        for (Field field:fields) {
            String name = field.getName();
            field.setAccessible(true);
            if (field.get(person) != null) {
                String value = field.get(person).toString();
                if (!"".equals(value)) {
                    map.put(name,value);
                }
            }
        }
        System.out.println(map);
    }

    private static class Person{
        public Person() {}

        private String name;

        private int age;

        private String getNameAndAge(){
            return "name = " + name + ",age = " + age;
        }

        @Override
        public String toString() {
            return "Person{" +
                    "name='" + name + '\'' +
                    ", age=" + age +
                    '}';
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public int getAge() {
            return age;
        }

        public void setAge(int age) {
            this.age = age;
        }
    }
}
