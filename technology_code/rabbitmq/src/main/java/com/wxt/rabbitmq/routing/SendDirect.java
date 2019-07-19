package com.wxt.rabbitmq.routing;

import com.rabbitmq.client.BuiltinExchangeType;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;

/**
 * @Author: weixiaotao
 * @ClassName SendDirect
 * @Date: 2018/11/30 11:01
 * @Description:
 */
public class SendDirect {
	private static final String EXCHANGE_NAME = "direct_logs";

	public static void main(String[] argv) throws Exception {
		//创建连接
		ConnectionFactory factory = new ConnectionFactory();
		factory.setHost("192.168.24.128");
		factory.setUsername("admin");
		factory.setPassword("admin");
		Connection connection = factory.newConnection();
		Channel channel = connection.createChannel();
		//声明路由器和路由器的类型
		channel.exchangeDeclare(EXCHANGE_NAME, BuiltinExchangeType.DIRECT);

		//"info", "warning", "error"
		String severity = "error";
		String message = ".........i am msg.haha........";

		//发布消息
		channel.basicPublish(EXCHANGE_NAME, severity, null, message.getBytes("UTF-8"));
		System.out.println(" [x] Sent '" + severity + "':'" + message + "'");

		channel.close();
		connection.close();
	}
}

