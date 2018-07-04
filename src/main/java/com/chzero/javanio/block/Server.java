package com.chzero.javanio.block;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;

/**
 * @author CHZERO
 * @version 1.0
 * @date 2018-07-04 22:54
 * @email 827348260@qq.com
 * @description  NIO 服务器端 阻塞模式
 */
public class Server{

    public static void main(String[] args) throws IOException{
        // 1. 获取服务器端通道
        ServerSocketChannel serverSocketChannel = ServerSocketChannel.open();

        //2. 绑定连接
        serverSocketChannel.bind(new InetSocketAddress(9999));
        System.out.println("服务器启动...");


        //3. 获取客户端连接
        SocketChannel accept = serverSocketChannel.accept();
        System.out.println("获取到客户端连接 -> " + accept.getLocalAddress());

        ByteBuffer byteBuffer = ByteBuffer.allocate(1024);

        //4. 接收客户端数据并输出
        accept.read(byteBuffer);
        byteBuffer.flip();

        System.out.println(new String(byteBuffer.array()));

        //5. 关闭通道
        //serverSocketChannel.close();
    }

}
