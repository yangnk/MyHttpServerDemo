package com.example.demo.test;

import java.util.HashMap;
import java.util.Map;

/**
 * ${DESCRIPTION}
 *
 * @author yangningkai
 * @create 2022-03-30 21:52
 **/
public class StaticTest {
    public static Map<String, String> map = new HashMap<>();
    public static StaticTest instance = null;

    private  StaticTest() {}

    public static void setContext(String a, String b) {
        if (instance == null) {
            instance = new StaticTest();
            map.put(a, b);
            return;
        }
        map.put(a, b);
    }

    public static StaticTest getContext() {
        return instance;
    }
}
