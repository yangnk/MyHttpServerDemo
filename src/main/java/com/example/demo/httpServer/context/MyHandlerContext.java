package com.example.demo.httpServer.context;

import com.example.demo.httpServer.handler.inter.MyHandler;

import java.util.HashMap;
import java.util.Map;

/**
 * ${handler映射容器，通过单例模式是实现}
 *
 * @author yangningkai
 * @create 2022-03-29 19:26
 **/
public class MyHandlerContext {
    private static Map<String, MyHandler> handlerMap = new HashMap<>();
    private static MyHandlerContext instance = null;

    private MyHandlerContext() {}

    //todo 将写死的classPath-uri映射写到xml中
    public static MyHandlerContext getContextInstance() {
        if (instance == null) {
            synchronized (MyHandler.class) {
                if (instance == null) {
                    instance = new MyHandlerContext();
                    String classPath = "com.example.demo.httpServer.handler.LoginHandler";
                    String uri = "/login.html";
                    Class<?> aClass = null;
                    MyHandler myHandler = null;
                    try {
                        aClass = Class.forName(classPath);
                        myHandler = (MyHandler) aClass.newInstance();
                    } catch (ClassNotFoundException e) {
                        e.printStackTrace();
                    } catch (IllegalAccessException e) {
                        e.printStackTrace();
                    } catch (InstantiationException e) {
                        e.printStackTrace();
                    }
                    handlerMap.put(uri, myHandler);
                }
            }
        }
        return instance;
    }
    public Map<String, MyHandler> getHandlerContext() {
        return handlerMap;
    }
}
