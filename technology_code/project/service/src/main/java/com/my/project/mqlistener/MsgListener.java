package com.my.project.mqlistener;

import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.stereotype.Component;

import java.io.UnsupportedEncodingException;

/**
 * @Author: weixiaotao
 * @ClassName MsgListener
 * @Date: 2018/12/14 16:03
 * @Description:
 */
@Slf4j
@Component
@ConditionalOnProperty(value = "mq.dev.enabled", havingValue = "true")
public class MsgListener {

	//@RabbitListener(queues = "${mq.dev.test_queue}" , containerFactory = "devRabbitListenerContainerFactory")
	public void testQueueMessage(Message message) {
		try {
			String body = null;
			body = new String(message.getBody(), "UTF-8");
			log.info("从mq同步出xxx信息：{}", body);
		}
		catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
	}
}
