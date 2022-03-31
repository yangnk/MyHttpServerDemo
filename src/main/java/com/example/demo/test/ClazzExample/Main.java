package com.example.demo.test.ClazzExample;

import java.lang.reflect.Method;

/**
 * ${DESCRIPTION}
 *
 * @author yangningkai
 * @create 2022-03-31 08:51
 **/
public class Main {
    public static void main(String[] args) throws ClassNotFoundException, IllegalAccessException, InstantiationException {
        Class<?> aClass = Class.forName("com.example.demo.test.ClazzExample.Clazz");
        Clazz aClass1 = (Clazz) aClass.newInstance();
        aClass1.getTest();
//        Object.class.getMethods();
//        Method[] methods = aClass1.getTest();
//        for (Method method : methods) {
//            System.out.println(method.toString());
//
//        }
    }
}
