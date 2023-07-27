package com.example.demo.test;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import sun.jvm.hotspot.HelloWorld;

/**
 * ${DESCRIPTION}
 *
 * @author yangningkai
 * @create 2022-03-27 17:14
 **/
public class HelloLogback {
    private final static Logger logger = LoggerFactory.getLogger("HelloLogback.class");

    public static void main(String[] args) {
        logger.error("info test");

    }
}
