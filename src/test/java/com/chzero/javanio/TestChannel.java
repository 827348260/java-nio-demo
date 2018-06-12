package com.chzero.javanio;

import org.junit.Test;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.RandomAccessFile;
import java.nio.ByteBuffer;
import java.nio.MappedByteBuffer;
import java.nio.channels.FileChannel;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;

/**
 * @author CHZERO
 * @version 1.0
 * @date 2018-06-07 17:18
 * @email 827348260@qq.com
 * @description
 */
public class TestChannel{

    //Channel 通道,  用于源节点与目标节点的连接, 在javaNIO中用于缓冲区数据的传输, 本身不存储数据;
    //通道的主要实现类: java.nio.channels.Channel接口:
    // FileChannel          用于读取、写入、映射和操作文件的通道。
    // SocketChannel        通过 UDP 读写网络中的数据通道。
    // ServerSocketChannel  通过 TCP 读写网络中的数据。
    // DatagramChannel      可以监听新进来的 TCP 连接，对每一个新进来的连接都会创建一个 SocketChannel

    //获取通道: java针对支持通道的类提供了 getChannel() 方法;
    //本地IO  FileInputStream / FileOutputStream  RandomAccessFile
    //网络IO  Socket / ServerSocket DatagramSocket

    //在 jdk1.7中  针对NIO.2 提供了open()方法;
    //在 jdk1.7中  中队NIO.2 Files 工具类中提供了newByteChannel()方法;

    //通道之间的数据传输
    //transferFrom()
    //transferTo()


    //分散读取（Scattering Reads）是指从 Channel 中读取的数据“分散”到多个 Buffer 中
    //聚集写入（Gathering Writes）是指将多个 Buffer 中的数据“聚集”到 Channel

    @Test
    public void testFileChannel4() throws Exception{
        //分散读取（Scattering Reads）是指从 Channel 中读取的数据“分散”到多个 Buffer 中
        //聚集写入（Gathering Writes）是指将多个 Buffer 中的数据“聚集”到 Channel
        RandomAccessFile randomAccessFile = new RandomAccessFile("1.txt", "rw");

        //获取通道
        FileChannel channel = randomAccessFile.getChannel();
        //分配缓冲区
        ByteBuffer bufferOne = ByteBuffer.allocate(100);

    }


    @Test
    public void testFileChannel3() throws Exception{
        //通道之间的数据传输
        //transferFrom()
        //transferTo()
        FileChannel inFileChannel = FileChannel.open(Paths.get("ChromeSetup.exe"), StandardOpenOption.READ);
        FileChannel outFileChannel = FileChannel.open(Paths.get("test3.exe"), StandardOpenOption.WRITE, StandardOpenOption.READ, StandardOpenOption.CREATE);

        //inFileChannel.transferTo(0, inFileChannel.size(), outFileChannel);
        outFileChannel.transferFrom(inFileChannel, 0, inFileChannel.size());
    }


    @Test
    public void testFileChannel2() throws Exception{
        //直接缓冲区 完成文件复制 (内存映射文件)
        //建议将直接缓冲区主要分配给那些易受基础系统的本机 I/O 操作影响的大型、持久的缓冲区
        FileChannel inFileChannel = FileChannel.open(Paths.get("ChromeSetup.exe"), StandardOpenOption.READ);
        FileChannel outFileChannel = FileChannel.open(Paths.get("test2.exe"), StandardOpenOption.WRITE, StandardOpenOption.READ, StandardOpenOption.CREATE);

        //内存映射文件
        MappedByteBuffer inMappedByteBuffer = inFileChannel.map(FileChannel.MapMode.READ_ONLY, 0, inFileChannel.size());
        MappedByteBuffer outMappedByteBuffer = outFileChannel.map(FileChannel.MapMode.READ_WRITE, 0, inFileChannel.size());

        //直接对缓冲区进行数据的读写
        byte[] bytes = new byte[inMappedByteBuffer.limit()];
        inMappedByteBuffer.get(bytes);
        outMappedByteBuffer.put(bytes);

        inFileChannel.close();
        outFileChannel.close();
    }


    @Test
    public void testFileChannel() throws Exception{

        //非直接缓冲区 完成文件复制

        FileInputStream inputStream = new FileInputStream("ChromeSetup.exe");
        FileOutputStream outputStream = new FileOutputStream("test.exe");

        //获取通道
        FileChannel inputStreamChannel = inputStream.getChannel();
        FileChannel outputStreamChannel = outputStream.getChannel();

        //分配缓冲区
        ByteBuffer buffer = ByteBuffer.allocate(10240);

        //将 通道中的数据存入缓冲区
        while (inputStreamChannel.read(buffer) != -1){
            buffer.flip();  //改变通道为读模式
            outputStreamChannel.write(buffer); //将缓冲区中的数据写到通道中
            buffer.clear(); //清空(重置) 缓冲区状态
        }

        //关闭资源
        inputStreamChannel.close();
        outputStreamChannel.close();
        inputStreamChannel.close();
        outputStreamChannel.close();
    }


}
