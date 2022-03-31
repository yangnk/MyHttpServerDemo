package com.example.demo.test;

import java.io.*;
import org.dom4j.*;
import org.dom4j.io.*;

public class Dom4jTest {

    public static void main(String arge[]) {
        try {
            File f = new File("/Users/yangnk/IdeaProjects/myHttpServerDemo/src/main/java/com/example/demo/test/test.xml");
            SAXReader reader = new SAXReader();
            Document doc = reader.read(f);
            Element root = doc.getRootElement();
            System.out.println("port" + root.element("port").getData().toString());
            System.out.println("serverName:" + root.element("serverName").getData().toString());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}