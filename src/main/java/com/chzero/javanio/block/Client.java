package com.chzero.javanio.block;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SocketChannel;

/**
 * @author CHZERO
 * @version 1.0
 * @date 2018-07-04 22:54
 * @email 827348260@qq.com
 * @description   NIO 客户端 阻塞模式
 */
public class Client{

    public static void main(String[] args) throws IOException{

        //1. 获取通道
        SocketChannel socketChannel = SocketChannel.open(new InetSocketAddress( "localhost",9999));

        //2. 分配缓冲区
        ByteBuffer byteBuffer = ByteBuffer.allocate(1024);

        String message = "Hello World.";

        //3. 发送数据到服务器
        byteBuffer.put(message.getBytes()); //添加数据到缓冲区
        byteBuffer.flip();
        socketChannel.write(byteBuffer); //把数据从缓冲区写入到通道中去

        //4. 接收服务器返回的数据
        byteBuffer.clear().flip();
        socketChannel.read(byteBuffer);
        byteBuffer.flip();
        System.out.println(new String(byteBuffer.array()));

        //5. 关闭通道
        //socketChannel.close();
    }

}
