package com.chzero.javanio.block;

/**
 * @author CHZERO
 * @version 1.0
 * @date 2018-07-04 22:43
 * @email 827348260@qq.com
 * @description NIO  文件IO都是阻塞IO, 只有网络IO可以是非阻塞IO
 * 一. 使用NIO进行网路通信的三个核心:
 * 1. 通道(Channel): 通道
 *  java.nio.channels.Channel接口:
 *      |-- SelectableChannel
 *          |-- SocketChanel
 *          |-- ServerSocketChanel
 *          |-- DatagramChannel
 *
 *          |-- Pipe.SinkChannel
 *          |-- Pipe.SourceChannel
 *
 * 2. 缓冲区(Buffer): 负责数据的存取
 * 3. 选择器(Selector): 是SelectableChannel的多路复用器. 用于监控SelectableChannel的IO状况.
 */
public class TestBlockingNIO{}


