package com.example.demo.httpServer.handler;

import com.example.demo.httpServer.entity.MyEntity;
import com.example.demo.httpServer.handler.abs.MyAbstractHandler;
import com.example.demo.test.ClasspathTest;

/**
 * ${DESCRIPTION}
 *
 * @author yangningkaiR
 * @create 2022-03-29 20:00
 **/
public class LoginHandler extends MyAbstractHandler {

    @Override
    public void doGet(MyEntity context) {
        String path = new LoginHandler().getClass().getClassLoader().getResource("").getPath() + "/" + "login.html";
        System.out.println(path);
        context.getResponse().setHtmlFile(path);
    }
}
