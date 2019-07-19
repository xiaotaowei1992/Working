package com.wxt.rabbitmq.routing;

import com.rabbitmq.client.*;

import java.io.IOException;

/**
 * @Author: weixiaotao
 * @ClassName ReceiveDirect
 * @Date: 2018/11/30 11:02
 * @Description:
 */
public class ReceiveDirect3 {
	private static final String EXCHANGE_NAME = "direct_logs";

	public static void main(String[] argv) throws Exception {
		//建立连接和通道
		ConnectionFactory factory = new ConnectionFactory();
		factory.setHost("192.168.24.128");
		factory.setUsername("admin");
		factory.setPassword("admin");
		Connection connection = factory.newConnection();
		Channel channel = connection.createChannel();
		//声明路由器和类型
		channel.exchangeDeclare(EXCHANGE_NAME, BuiltinExchangeType.DIRECT);
		//声明队列
		String queueName = channel.queueDeclare().getQueue();
		//定义要监听的级别
		String[] severities = {"error"};
		//根据绑定键绑定
		for (String severity : severities) {
			channel.queueBind(queueName, EXCHANGE_NAME, severity);
		}
		System.out.println(" [ReceiveDirect3] Waiting for messages. To exit press CTRL+C");

		Consumer consumer = new DefaultConsumer(channel) {
			@Override
			public void handleDelivery(String consumerTag, Envelope envelope,
					AMQP.BasicProperties properties, byte[] body) throws IOException {
				String message = new String(body, "UTF-8");
				System.out.println(" [ReceiveDirect3] Received '" + envelope.getRoutingKey() + "':'" + message + "'");
			}
		};
		channel.basicConsume(queueName, true, consumer);
	}
}
