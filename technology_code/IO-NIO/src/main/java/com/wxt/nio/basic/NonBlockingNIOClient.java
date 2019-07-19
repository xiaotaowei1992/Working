package com.wxt.nio.basic;

import com.sun.xml.internal.ws.policy.privateutil.PolicyUtils;
import org.junit.Test;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.BufferUnderflowException;
import java.nio.ByteBuffer;
import java.nio.channels.*;
import java.util.Date;
import java.util.Iterator;
import java.util.Scanner;

/**
 * @Author: weixiaotao
 * @ClassName BlockingNIOTest
 * @Date: 2018/11/2 10:00
 * @Description:
 * 一、使用 NIO 完成网络通信的三个核心：
 *
 * 1. 通道（Channel）：负责连接
 *
 * 	   java.nio.channels.Channel 接口：
 * 			|--SelectableChannel
 * 				|--SocketChannel
 * 				|--ServerSocketChannel
 * 				|--DatagramChannel
 *
 * 				|--Pipe.SinkChannel
 * 				|--Pipe.SourceChannel
 *
 * 2. 缓冲区（Buffer）：负责数据的存取
 *
 * 3. 选择器（Selector）：是 SelectableChannel 的多路复用器。用于监控 SelectableChannel 的 IO 状况
 *
 */
public class NonBlockingNIOClient {

	public static void main(String[] args) throws IOException {
		//client();
		sendMsg();
	}
	public static void client() throws IOException{
		//1.获取通道
		SocketChannel socketChannel = SocketChannel.open(new InetSocketAddress("10.100.19.86",9090));
		//2.切换非阻塞模式
		socketChannel.configureBlocking(false);
		//3.分配指定大小的缓冲区
		ByteBuffer buffer = ByteBuffer.allocate(1024);
		//4.发送数据给服务端
		Scanner scanner = new Scanner(System.in);
		while(scanner.hasNext()){
			String string = scanner.next();
			buffer.put((new Date().toString() + ":" + string).getBytes());
			buffer.flip();
			socketChannel.write(buffer);
			buffer.clear();
		}
		//5.关闭通道
		socketChannel.close();
	}

	/**
	 * DatagramChannel
	 */
	public static void sendMsg() throws  IOException{
		DatagramChannel channel = DatagramChannel.open();
		channel.configureBlocking(false);
		ByteBuffer buffer = ByteBuffer.allocate(1024);
		Scanner scanner = new Scanner(System.in);
		while(scanner.hasNext()){
			buffer.put(scanner.next().getBytes());
			buffer.flip();
			channel.send(buffer,new InetSocketAddress("10.100.19.86",9090));
			buffer.clear();
		}
		channel.close();
	}


}
