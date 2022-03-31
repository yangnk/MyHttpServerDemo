package com.example.demo.httpServer.entity;

import java.nio.channels.SelectionKey; /**
 * ${DESCRIPTION}
 *
 * @author yangningkai
 * @create 2022-03-28 22:17
 **/
public class MyResponse {
    public static final String SERVER_NAME = "myHttpServer";
    private SelectionKey key;
    //内容类型  defalut 为text/html
    private String contentType = "text/html";
    //响应码  defalut 为200
    private int StatuCode = 200;
    private String statuCodeStr = "OK";
    private String htmlFile = "";

    public MyResponse(SelectionKey key) {
        this.key = key;
    }

    public String getContentType() {
        return contentType;
    }

    public int getStatuCode() {
        return StatuCode;
    }

    public SelectionKey getKey() {
        return key;
    }

    public String getStatuCodeStr() {
        return statuCodeStr;
    }

    public String getHtmlFile() {
        return htmlFile;
    }

    public void setHtmlFile(String htmlFile) {
        this.htmlFile = htmlFile;
    }

    public void setContentType(String contentType) {
        this.contentType = contentType;
    }

    public void setStatuCode(int statuCode) {
        StatuCode = statuCode;
    }

    public void setStatuCodeStr(String statuCodeStr) {
        this.statuCodeStr = statuCodeStr;
    }
}
