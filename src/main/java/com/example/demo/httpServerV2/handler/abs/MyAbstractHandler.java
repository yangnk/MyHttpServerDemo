package com.example.demo.httpServerV2.handler.abs;

import com.example.demo.httpServerV2.entity.MyEntity;
import com.example.demo.httpServerV2.process.MyResponseProcess;
import com.example.demo.httpServerV2.handler.inter.MyHandler;
import com.example.demo.httpServerV2.entity.MyRequest;

/**
 * ${DESCRIPTION}
 *
 * @author yangningkai
 * @create 2022-03-29 20:23
 **/
public abstract class MyAbstractHandler implements MyHandler{
    MyEntity entity;

    @Override
    public void init(MyEntity entity) {
        this.entity = entity;

    }

    @Override
    public void service(MyEntity entity) {
        //通过请求方式选择具体解决方法
        String method = entity.getRequest().getMethod();
        if(method.equals(MyRequest.GET)) {
            this.doGet(entity);
        } else if (method.equals(MyRequest.POST)) {
            this.doPost(entity);
        }
        sendResponse(entity);
    }

    private void sendResponse(MyEntity entity) {
        new MyResponseProcess().write(entity);
    }

    @Override
    public void doGet(MyEntity entity) { }

    @Override
    public void doPost(MyEntity entity) { }

    @Override
    public void destory(MyEntity entity) { }
}
