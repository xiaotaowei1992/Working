package com.wxt.rabbitmq.topic;

import com.rabbitmq.client.BuiltinExchangeType;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;

/**
 * @Author: weixiaotao
 * @ClassName SendTopic
 * @Date: 2018/11/30 11:20
 * @Description:
 */
public class SendTopic {
	private static final String EXCHANGE_NAME = "topic_logs";

	public static void main(String[] argv) {
		Connection connection = null;
		Channel channel = null;
		try {
			//建立连接和通道
			ConnectionFactory factory = new ConnectionFactory();
			factory.setHost("192.168.24.128");
			factory.setUsername("admin");
			factory.setPassword("admin");
			connection = factory.newConnection();
			channel = connection.createChannel();

			//声明路由器和路由器类型
			channel.exchangeDeclare(EXCHANGE_NAME, BuiltinExchangeType.TOPIC);

			//定义路由键和消息
			String routingKey = "kern.critical";
			String message = "A critical kernel error";

			//发布消息
			channel.basicPublish(EXCHANGE_NAME, routingKey, null, message.getBytes("UTF-8"));
			System.out.println(" [x] Sent '" + routingKey + "':'" + message + "'");

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (connection != null) {
				try {
					connection.close();
				} catch (Exception ignore) {
				}
			}
		}
	}
}
