package com.chzero.javanio;

import org.junit.Test;

import java.nio.ByteBuffer;

/**
 * @author CHZERO
 * @version 1.0
 * @date 2018-06-07 14:29
 * @email 827348260@qq.com
 * @description
 */
public class TestBuffer{

    @Test
    public void testByteBuffer(){
        //新建缓冲区, 给缓冲区分配空间
        ByteBuffer byteBuffer = ByteBuffer.allocate(1024);

        //非直接缓冲区: allocate() 在JVM内存中分配缓冲区
        //直接缓冲区 :  allocateDirect() 在操作系统物理内存中分配缓冲区, 可以提高效率

        //put, get  从缓冲区 存或者取数据
        //缓冲区的四个属性:
        //1. capacity: 容量 声明后不可修改;
        //2. limit   : 界限 缓冲区中可操作数据的大小, 大于等于limit位置的数据不能进行读写;
        //3. position: 位置 表示缓冲区中正在操作数据的位置; 0 <= mark <= position <= limit <= capacity
        //4. mark    : 标记 表示记录当前position 的位置, 可以通过reset() 恢复到mark 的位置
        System.out.println("----------------------allocate(1024)--------------------");

        System.out.println("capacity : " + byteBuffer.capacity()); //缓冲区的容量
        System.out.println("position : " + byteBuffer.position()); //正在操作的位置
        System.out.println("limit : " + byteBuffer.limit()); //缓冲区可操作数据界限

        String str = "abcde";
        byteBuffer.put(str.getBytes()); //写数据

        System.out.println("---------------------put()---------------------");

        System.out.println("capacity : " + byteBuffer.capacity()); //缓冲区的容量
        System.out.println("position : " + byteBuffer.position()); //正在操作的位置
        System.out.println("limit : " + byteBuffer.limit()); //缓冲区可操作数据界限, 不包括这个值(小于limit值)

        byteBuffer.flip(); //改变缓冲区读写模式

        System.out.println("----------------------flip()--------------------");

        System.out.println("capacity : " + byteBuffer.capacity()); //缓冲区的容量
        System.out.println("position : " + byteBuffer.position()); //正在操作的位置
        System.out.println("limit : " + byteBuffer.limit()); //缓冲区可操作数据界限

        byte[] bytes = new byte[byteBuffer.limit()];
        byteBuffer.get(bytes); //读数据

        System.out.println("--------------------get()----------------------");
        System.out.println(new String(bytes, 0, bytes.length));
        System.out.println("capacity : " + byteBuffer.capacity()); //缓冲区的容量
        System.out.println("position : " + byteBuffer.position()); //正在操作的位置
        System.out.println("limit : " + byteBuffer.limit()); //缓冲区可操作数据界限

        byteBuffer.rewind(); //重置当前位置实现重读
        byteBuffer.get(bytes);
        System.out.println("--------------------rewind()----------------------");
        System.out.println(new String(bytes, 0, bytes.length));
        System.out.println("capacity : " + byteBuffer.capacity()); //缓冲区的容量
        System.out.println("position : " + byteBuffer.position()); //正在操作的位置
        System.out.println("limit : " + byteBuffer.limit()); //缓冲区可操作数据界限

        byteBuffer.clear(); //重置position和limit  数据并没有被清空
        System.out.println("--------------------clear()----------------------");
        System.out.println("capacity : " + byteBuffer.capacity()); //缓冲区的容量
        System.out.println("position : " + byteBuffer.position()); //正在操作的位置
        System.out.println("limit : " + byteBuffer.limit()); //缓冲区可操作数据界限

    }

}
