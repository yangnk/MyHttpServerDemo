package com.example.demo.httpServerV2.process;

import com.example.demo.httpServerV2.entity.MyEntity;
import com.example.demo.httpServerV2.entity.MyRequest;
import com.example.demo.httpServerV2.entity.MyResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.*;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.SocketChannel;
import java.util.Date;

/**
 * ${DESCRIPTION}
 *
 * @author yangningkai
 * @create 2022-03-29 20:04
 **/
public class MyResponseProcess {
    private MyRequest request;
    private MyResponse response;
    private String protocol;
    private int statuCode;
    private String statuCodeStr;
    private ByteBuffer buffer;
    private String serverName;
    private String contentType;
    private SocketChannel channel;
    private Selector selector;
    private SelectionKey key;
    private final static Logger logger = LoggerFactory.getLogger("MyResponseProcess.class");
    private BufferedReader reader;
    private String htmlFile;

    public void write(MyEntity context) {
        //从context中得到相应的参数
        request = context.getRequest();
        response = context.getResponse();
        buffer = ByteBuffer.allocate(1024);
        protocol = request.getProtocol();
        statuCode = response.getStatuCode();
        statuCodeStr = response.getStatuCodeStr();
        serverName = MyResponse.SERVER_NAME;
        contentType = response.getContentType();
        key = response.getKey();
        selector = key.selector();
        channel = (SocketChannel) key.channel();
        htmlFile = response.getHtmlFile();

        //得到响应正文内容
        String html = setHtml(context);

        StringBuilder sb = new StringBuilder();
        //状态行
        sb.append(protocol + " " + statuCode + " " + statuCodeStr + "\r\n");
        //响应头
        sb.append("Server: " + serverName + "\r\n");
        sb.append("Content-Type: " + contentType + "\r\n");
        sb.append("Date: " + new Date() + "\r\n");
        if (reader != null) {
            sb.append("Content-Length: " + html.getBytes().length + "\r\n");
        }

        //响应内容
        sb.append("\r\n");
        sb.append(html);

        buffer.put(sb.toString().getBytes());
        //从写模式，切换到读模式
        buffer.flip();
        try {
            logger.info("生成相应\r\n" + sb.toString());
            channel.register(selector, SelectionKey.OP_WRITE);
            channel.write(buffer);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private String setHtml(MyEntity context) {
        StringBuilder html = null;
        if (htmlFile != null && htmlFile.length() > 0) {

            html = new StringBuilder();

            try {
                reader = new BufferedReader(new FileReader(new File(htmlFile)));
                String htmlStr;
                htmlStr = reader.readLine();
                while (htmlStr != null) {
                    html.append(htmlStr + "\r\n");
                    htmlStr = reader.readLine();
                }

            } catch (FileNotFoundException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        return html.toString();
    }
}
