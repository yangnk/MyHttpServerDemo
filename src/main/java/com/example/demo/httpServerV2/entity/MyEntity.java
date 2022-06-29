package com.example.demo.httpServerV2.entity;

import java.nio.channels.SelectionKey; /**
 * ${DESCRIPTION}
 *
 * @author yangningkai
 * @create 2022-03-28 22:10
 **/
public class MyEntity {
    private MyRequest request;
    private MyResponse response;

    public void setContext(String requestHeader, SelectionKey key) {
        MyRequest myRequest = new MyRequest(requestHeader);
        MyResponse myResponse = new MyResponse(key);
        this.request = myRequest;
        this.response = myResponse;
    }

    public void setRequest(MyRequest request) {
        this.request = request;
    }

    public void setResponse(MyResponse response) {
        this.response = response;
    }
    public MyRequest getRequest() {
        return request;
    }

    public MyResponse getResponse() {
        return response;
    }

}
