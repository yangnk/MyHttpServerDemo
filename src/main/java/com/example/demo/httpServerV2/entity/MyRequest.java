package com.example.demo.httpServerV2.entity;

import java.util.HashMap;
import java.util.Map;

/**
 * ${DESCRIPTION}
 *
 * @author yangningkai
 * @create 2022-03-28 22:17
 **/
public class MyRequest {

    public static final String POST = "POST";
    public static final String GET = "GET";
    Map<String, Object> query = new HashMap<>();
    Map<String, Object> header = new HashMap<>();
    String method;
    String uri;
    String protocol;


    public MyRequest(String requestHeader) {
        init(requestHeader);

    }

    /**
     * 初始化
     *
     * @param requestHeader 请求体requestHeader
     */
    private void init(String requestHeader) {
        //将请求分行
        String[] headers = requestHeader.split("\r\n");
        //设置请求方式
        initMethod(headers[0]);
        //设置URI
        initURI(headers[0]);
        //设置版本协议
        initProtocol(headers[0]);
        //设置请求头
        initRequestHeaders(headers);
    }

    private void initRequestHeaders(String[] headers) {
        //去掉第一行
        for (int i = 1; i < headers.length; i++) {
            String key = headers[i].substring(0, headers[i].indexOf(":"));
            String value = headers[i].substring(headers[i].indexOf(":") + 1);
            header.put(key, value);
        }
    }

    private void initProtocol(String header) {
        protocol = header.substring(header.lastIndexOf(" ") + 1, header.length());
    }

    private void initURI(String header) {
        uri = header.substring(header.indexOf(" ") + 1, header.indexOf(" ", header.indexOf(" ") + 1));
        //如果是get方法，则后面跟着参数   /index?a=1&b=2
        if (method.toUpperCase().equals("GET")) {
            //有问号表示后面跟有参数
            if (uri.contains("?")) {
                String attr = uri.substring(uri.indexOf("?") + 1, uri.length());
                uri = uri.substring(0, uri.indexOf("?"));
                initAttribute(attr);
            }
        }
    }

    private void initAttribute(String attr) {
        String[] attrs = attr.split("&");
        for (String string : attrs) {
            String key = string.substring(0, string.indexOf("="));
            String value = string.substring(string.indexOf("=") + 1);
            query.put(key, value);
        }
    }

    private void initMethod(String header) {
        method = header.substring(0, header.indexOf(" "));
    }

    public Map<String, Object> getQuery() {
        return query;
    }

    public Map<String, Object> getHeader() {
        return header;
    }

    public String getMethod() {
        return method;
    }

    public String getUri() {
        return uri;
    }

    public String getProtocol() {
        return protocol;
    }

    public void setQuery(Map<String, Object> query) {
        this.query = query;
    }

    public void setHeader(Map<String, Object> header) {
        this.header = header;
    }

    public void setMethod(String method) {
        this.method = method;
    }

    public void setUri(String uri) {
        this.uri = uri;
    }

    public void setProtocol(String protocol) {
        this.protocol = protocol;
    }
}
