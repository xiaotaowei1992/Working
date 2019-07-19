package com.wxt.nio.basic;

import org.junit.Test;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;

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
public class BlockingNIOTest {
	/**
	 * 客户端
	 * @throws IOException
	 */
	@Test
	public void client() throws IOException{
		//1.获取通道 Paths.get("1.png")  Paths.get("e:/", "movie/100.avi")
		SocketChannel socketChannel = SocketChannel.open(new InetSocketAddress("10.100.19.86",9090));
		FileChannel inChannel = FileChannel.open(Paths.get("e:/", "movie/100.avi"), StandardOpenOption.READ);

		//2.分配指定大小的缓冲区
		ByteBuffer buffer = ByteBuffer.allocate(1024);
		//3.读取本地文件，并发送到服务器
		while(inChannel.read(buffer) != -1){
			buffer.flip();
			socketChannel.write(buffer);
			buffer.clear();
		}
		//5.优化
		socketChannel.shutdownOutput();
		//接受服务端的反馈
		int length = 0;
		while ((length = socketChannel.read(buffer)) != -1){
			buffer.flip();
			System.out.println(new String(buffer.array(), 0, length));
			buffer.clear();
		}

		//4.关闭通道
		inChannel.close();
		socketChannel.close();
	}

	/**
	 * 服务端
	 */
	@Test
	public void server() throws IOException{
		//1.获取通道 Paths.get("2.png")  Paths.get("e:/", "movie/2.avi")
		ServerSocketChannel serverSocketChannel = ServerSocketChannel.open();
		FileChannel outChannel = FileChannel.open(Paths.get("e:/", "movie/2.avi"),StandardOpenOption.WRITE,StandardOpenOption.CREATE);
		//2.绑定连接
		serverSocketChannel.bind(new InetSocketAddress("127.0.0.1",9090));
		//3.获取客户端连接的通道
		SocketChannel socketChannel = serverSocketChannel.accept();
		//4.分配指定大小的缓冲区
		ByteBuffer buffer = ByteBuffer.allocate(1024);
		//5.接受客户端数据，并保存本地
		while(socketChannel.read(buffer) != -1){
			buffer.flip();
			outChannel.write(buffer);
			buffer.clear();
		}
		//优化
		//发送反馈客户端
		buffer.put("server received data success...".getBytes());
		buffer.flip();
		socketChannel.write(buffer);

		//6.关闭通道
		socketChannel.close();
		outChannel.close();
		serverSocketChannel.close();
	}
}
