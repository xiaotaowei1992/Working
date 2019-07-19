package com.wxt.rabbitmq.workerqueues;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;
import com.rabbitmq.client.MessageProperties;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @Author: weixiaotao
 * @ClassName NewTask
 * @Date: 2018/11/29 18:23
 * @Description:
 */
public class NewTask {
	private static final String TASK_QUEUE_NAME = "task_queue";
	public static void main(String[] argv) throws Exception {
		ConnectionFactory factory = new ConnectionFactory();
		factory.setHost("192.168.24.128");
		factory.setUsername("admin");
		factory.setPassword("admin");
		Connection connection = factory.newConnection();
		Channel channel = connection.createChannel();
		boolean durable = true;
		channel.queueDeclare(TASK_QUEUE_NAME, durable, false, false, null);
		//String message = "test...";
		List<String> msgs = new ArrayList<String>();
		msgs.add("1.");
		msgs.add("2..");
		msgs.add("3...");
		msgs.add("4....");
		msgs.add("5.....");
		msgs.add("6......");
		msgs.add("7.......");
		msgs.add("8........");
		msgs.add("9.........");
		for (String message:msgs) {
			channel.basicPublish( "", TASK_QUEUE_NAME, MessageProperties.PERSISTENT_TEXT_PLAIN, message.getBytes());
			System.out.println(" [x] Sent '" + message + "'");
		}
		channel.close();
		connection.close();
	}
}
