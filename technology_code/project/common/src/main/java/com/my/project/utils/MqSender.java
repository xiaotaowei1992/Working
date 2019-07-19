package com.my.project.utils;

import com.my.project.exception.BizException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

/**
 * @Author: weixiaotao
 * @ClassName MqSender
 * @Date: 2018/12/14 15:47
 * @Description:
 */
@Slf4j
@Component
public class MqSender {

	@Value("${mq.dev.enabled:false}")
	private boolean enable;

	@Autowired(required = false)
	private RabbitTemplate devRabbitTemplate;

	/**
	 * 推送消息到mq
	 *
	 * @param exchange
	 * @param routingKey
	 * @param msg
	 */
	public void sendMq(String exchange, String routingKey, Object msg) {
		if (!enable) {
			throw new BizException("MQ未启用，不能推送消息");
		}
		try {
			devRabbitTemplate.convertAndSend(exchange, routingKey, msg);
			log.info("推送消息{}成功", msg);
		} catch (Exception e) {
			log.error("推送消息{}失败", msg);
			throw new BizException("MQ消息推送失败", e);
		}
	}
}