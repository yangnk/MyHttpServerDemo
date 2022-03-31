package com.example.demo.test;

import java.io.IOException;

/**
 * ${DESCRIPTION}
 *
 * @author yangningkai
 * @create 2022-03-31 08:39
 **/
public class ClasspathTest {
    public static void main(String[] args) throws IOException {
        System.out.println(new ClasspathTest().getClass().getClassLoader().getResource("").getPath());
        System.out.println(new ClasspathTest().getClass().getResource("").getContent().toString());
        System.out.println(new ClasspathTest().getClass().getResource("").getPath());
        System.out.println("Users yangnk".replace("\\s", ""));
    }
}
