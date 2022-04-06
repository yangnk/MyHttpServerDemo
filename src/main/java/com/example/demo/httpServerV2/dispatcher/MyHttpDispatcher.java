package com.example.demo.httpServerV2.dispatcher;

import com.example.demo.httpServerV2.entity.MyEntity;
import com.example.demo.httpServerV2.context.MyHandlerContext;
import com.example.demo.httpServerV2.handler.inter.MyHandler;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.nio.channels.SelectionKey;

/**
 * ${DESCRIPTION}
 *
 * @author yangningkai
 * @create 2022-03-28 21:57
 **/
public class MyHttpDispatcher implements Runnable {
    private SelectionKey key;
    private String requestHeader;
    private MyEntity entity = new MyEntity();
    private MyHandler myHandler;
    private final static Logger logger = LoggerFactory.getLogger("MyHttpDispatcher.class");

    public MyHttpDispatcher(SelectionKey key, String requestHeader) {
        this.key = key;
        this.requestHeader = requestHeader;
    }


    @Override
    public void run() {
        //初始化context
        entity.setContext(requestHeader, key);
        //获取uri
        String uri = entity.getRequest().getUri();
        //通过uri得到handler
        myHandler = MyHandlerContext.getContextInstance().getHandlerContext().get(uri);
        //初始化handler
        if (myHandler != null) {
            myHandler.init(entity);
            myHandler.service(entity);
        }
    }
}
