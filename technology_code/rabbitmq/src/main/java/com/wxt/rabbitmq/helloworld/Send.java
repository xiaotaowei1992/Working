package com.wxt.rabbitmq.helloworld;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;

/**
 * @Author: weixiaotao
 * @ClassName Send
 * @Date: 2018/11/29 17:50
 * @Description:
 */
public class Send {
	//定义队列名字
	private final static String QUEUE_NAME = "weixiaotao";
	public static void main(String[] argv) throws Exception {
		//创建连接和通道 创建一个连接到Rabbit服务器的连接
		ConnectionFactory factory = new ConnectionFactory();
		factory.setHost("192.168.24.128");
		factory.setUsername("admin");
		factory.setPassword("admin");
		Connection connection = factory.newConnection();
		//创建了一个通道（channel），大部分的API操作均在这里完成
		Channel channel = connection.createChannel();
		//为通道指明队列 对于Send来说，必须指明消息要发到哪个队列：
		channel.queueDeclare(QUEUE_NAME, false, false, false, null);
		String message = "Hello World bbb...!";
		//发布消息
		channel.basicPublish("", QUEUE_NAME, null, message.getBytes("UTF-8"));
		System.out.println(" [x] Sent '" + message + "'");
		//关闭连接
		channel.close();
		connection.close();
	}
}

