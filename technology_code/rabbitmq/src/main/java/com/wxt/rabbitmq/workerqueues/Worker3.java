package com.wxt.rabbitmq.workerqueues;

/**
 * @Author: weixiaotao
 * @ClassName Worker
 * @Date: 2018/11/29 18:23
 * @Description:
 */
import com.rabbitmq.client.*;

import java.io.IOException;

public class Worker3 {
	private static final String TASK_QUEUE_NAME = "task_queue";

	public static void main(String[] argv) throws Exception {
		ConnectionFactory factory = new ConnectionFactory();
		factory.setHost("192.168.24.128");
		factory.setUsername("admin");
		factory.setPassword("admin");
		final Connection connection = factory.newConnection();
		final Channel channel = connection.createChannel();
		boolean durable = true;
		channel.queueDeclare(TASK_QUEUE_NAME, durable, false, false, null);
		System.out.println(" [*] Waiting for messages. To exit press CTRL+C");

		channel.basicQos(1);

		final Consumer consumer = new DefaultConsumer(channel) {
			@Override
			public void handleDelivery(String consumerTag, Envelope envelope, AMQP.BasicProperties properties, byte[] body) throws IOException {
				String message = new String(body, "UTF-8");

				System.out.println(" [Worker3] Received '" + message + "'");
				try {
					doWork(message);
				} finally {
					System.out.println(" [Worker3] Done");
					channel.basicAck(envelope.getDeliveryTag(), false);
				}
			}
		};
		boolean autoAck = false;
		channel.basicConsume(TASK_QUEUE_NAME, autoAck, consumer);
	}

	private static void doWork(String task) {
		for (char ch : task.toCharArray()) {
			if (ch == '.') {
				try {
					Thread.sleep(1000);
				} catch (InterruptedException ignored) {
					Thread.currentThread().interrupt();
				}
			}
		}
	}
}

