package com.wxt.rabbitmq.publishsubscribe;

import com.rabbitmq.client.BuiltinExchangeType;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;

/**
 * @Author: weixiaotao
 * @ClassName Publish
 * @Date: 2018/11/29 20:23
 * @Description:
 */
public class Publish {
	private static final String EXCHANGE_NAME = "logs";

	public static void main(String[] argv) throws Exception {
		//建立连接和通道
		ConnectionFactory factory = new ConnectionFactory();
		factory.setHost("192.168.24.128");
		factory.setUsername("admin");
		factory.setPassword("admin");
		Connection connection = factory.newConnection();
		Channel channel = connection.createChannel();

		//声明路由以及路由的类型
		channel.exchangeDeclare(EXCHANGE_NAME, BuiltinExchangeType.FANOUT);

		String message = "msg...";

		//发布消息
		channel.basicPublish(EXCHANGE_NAME, "", null, message.getBytes("UTF-8"));
		System.out.println(" [x] Sent '" + message + "'");

		//关闭连接和通道
		channel.close();
		connection.close();
	}
}
