package com.example.demo.httpServer.server;

import com.example.demo.httpServer.dispatcher.MyHttpDispatcher;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.ServerSocket;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.util.Iterator;
import java.util.Set;

/**
 * ${DESCRIPTION}
 *
 * @author yangningkai
 * @create 2022-03-28 21:09
 **/
public class MyServer implements Runnable {
    private final static Logger logger = LoggerFactory.getLogger("MyServer.class");
    private final static Integer PORT = 8001;
    private boolean interrupted = true;

    public void setInterrupted(boolean interrupted) {
        this.interrupted = interrupted;
    }

    @Override
    public void run() {


        try {
            //打开selector
            Selector selector = Selector.open();
            //打开ServerSocketChannel
            ServerSocketChannel socketChannel = ServerSocketChannel.open();
            //得到serverSocket对象
            ServerSocket socket = socketChannel.socket();
            //bind、设置、注册
            socket.setReuseAddress(true);
            try {
                socket.bind(new InetSocketAddress(PORT));
            } catch (IOException e) {
                logger.error("绑定端口失败");
            }
            logger.info("成功绑定端口:{}", PORT);
            socketChannel.configureBlocking(false);
            socketChannel.register(selector, SelectionKey.OP_ACCEPT);
            logger.info("服务成功启动");

            while (interrupted) {
                int selectCount = selector.select();
                if (selectCount == 0) {
                    continue;
                }
                Set<SelectionKey> selectionKeys = selector.selectedKeys();
                Iterator<SelectionKey> iterator = selectionKeys.iterator();
                while (iterator.hasNext()) {
                    SelectionKey key = iterator.next();
                    if (key.isAcceptable()) {
                        //获取channel
                        ServerSocketChannel serverSocketChannel = (ServerSocketChannel) key.channel();
                        //将其转化为serversocketchannel
                        SocketChannel socketChannel1 = serverSocketChannel.accept();
                        //将其注册到selector上
                        if (socketChannel1 != null) {
                            socketChannel1.configureBlocking(false);
                            socketChannel1.register(selector, SelectionKey.OP_READ);
                            logger.info("收到了来自:{} 的请求", ((InetSocketAddress) socketChannel1.getRemoteAddress()).getHostString());
                        }
                    }
                    if (key.isReadable()) {
                        //获取socketchannel
                        SocketChannel socketChannel1 = (SocketChannel) key.channel();
                        //读取socketChannel中内容
                        String requestHeader = receive(socketChannel1);
                        if (requestHeader.length() > 0) {
                            new Thread(new MyHttpDispatcher(key, requestHeader)).start();
                            logger.info("开始处理httpHeader数据");
                        }
                    }else if (key.isWritable()) {
                        //该key有Write事件
                        logger.info("有流写出!");
                        SocketChannel socketChannel1 = (SocketChannel) key.channel();
                        socketChannel1.shutdownInput();
                        socketChannel1.close();
                    }
                    //todo 拓展writeable事件
                    iterator.remove();
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }


    }

    //socketchannel--->bytebuffer--->字节输出流
    private String receive(SocketChannel socketChannel1) {
        ByteBuffer byteBuffer = ByteBuffer.allocate(1024);
        int size = 0;
        byte[] bytes;
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        try {
            while ((size = socketChannel1.read(byteBuffer)) > 0) {
                byteBuffer.flip();
                bytes = new byte[size];
                byteBuffer.get(bytes);
                byteArrayOutputStream.write(bytes);
                byteBuffer.clear();
            }
        } catch (IOException e) {
            e.printStackTrace();
            logger.error("读取requestHeader失败");
        }
        return new String(byteArrayOutputStream.toByteArray());
    }
}
