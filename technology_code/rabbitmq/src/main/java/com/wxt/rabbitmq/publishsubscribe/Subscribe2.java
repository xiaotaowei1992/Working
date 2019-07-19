package com.wxt.rabbitmq.publishsubscribe;

import com.rabbitmq.client.*;

import java.io.IOException;

/**
 * @Author: weixiaotao
 * @ClassName Subscribe
 * @Date: 2018/11/29 20:23
 * @Description:
 */
public class Subscribe2 {
	private static final String EXCHANGE_NAME = "logs";

	public static void main(String[] argv) throws Exception {
		//建立连接和通道
		ConnectionFactory factory = new ConnectionFactory();
		factory.setHost("192.168.24.128");
		factory.setUsername("admin");
		factory.setPassword("admin");
		Connection connection = factory.newConnection();
		Channel channel = connection.createChannel();

		//声明路由器及类型
		channel.exchangeDeclare(EXCHANGE_NAME, BuiltinExchangeType.FANOUT);
		//声明一个随机名字的队列
		String queueName = channel.queueDeclare().getQueue();
		//绑定队列到路由器上
		channel.queueBind(queueName, EXCHANGE_NAME, "");

		System.out.println(" [Subscribe2] Waiting for messages. To exit press CTRL+C");

		//开始监听消息
		Consumer consumer = new DefaultConsumer(channel) {
			@Override
			public void handleDelivery(String consumerTag, Envelope envelope,
					AMQP.BasicProperties properties, byte[] body) throws IOException {
				String message = new String(body, "UTF-8");
				System.out.println(" [Subscribe2] Received '" + message + "'");
			}
		};
		channel.basicConsume(queueName, true, consumer);
	}
}
