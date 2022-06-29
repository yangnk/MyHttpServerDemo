package com.example.demo.test;

/**
 * ${DESCRIPTION}
 *
 * @author yangningkai
 * @create 2022-03-30 21:54
 **/
public class StaticTestMain {
    public static void main(String[] args) {
        StaticTest.setContext("a", "b");
        StaticTest.setContext("aa", "bb");
        System.out.println(StaticTest.getContext().map.toString());
    }
}
