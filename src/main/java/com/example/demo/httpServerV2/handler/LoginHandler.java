package com.example.demo.httpServerV2.handler;

import com.example.demo.httpServerV2.entity.MyEntity;
import com.example.demo.httpServerV2.handler.abs.MyAbstractHandler;

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
