package com.my.project.comm;

import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.core.*;
import org.springframework.amqp.rabbit.config.SimpleRabbitListenerContainerFactory;
import org.springframework.amqp.rabbit.connection.CachingConnectionFactory;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitAdmin;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.amqp.SimpleRabbitListenerContainerFactoryConfigurer;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;

import java.util.Arrays;
import java.util.Objects;

/**
 * @Author: weixiaotao
 * @ClassName MQConfig
 * @Date: 2018/12/14 14:40
 * @Description:
 */
@Configuration
@ConditionalOnClass({ ConnectionFactory.class, RabbitTemplate.class})
@EnableConfigurationProperties(RabbitMqProperties.class)
@Slf4j
public class MqConfig {
	/**
	 * dev MQ配置
	 */
	@Configuration
	@ConditionalOnProperty(value = "mq.dev.enabled", havingValue = "true")
	static class DevMqConfiguration {

		@Autowired
		RabbitMqProperties config;

		/*@Bean
		@Primary*/
		public CachingConnectionFactory devRabbitConnectionFactory() {
			RabbitMqProperties.Dev dev = config.getDev();
			CachingConnectionFactory ccf = new CachingConnectionFactory();
			ccf.setHost(dev.getHost());
			ccf.setPort(dev.getPort());
			ccf.setUsername(dev.getUsername());
			ccf.setPassword(dev.getPassword());
			ccf.setVirtualHost(dev.getVirtualhost());
			ccf.setPublisherReturns(true);
			return ccf;
		}

		//@Bean
		public RabbitAdmin devRabbitAdmin() {
			RabbitAdmin rabbitAdmin = new RabbitAdmin(devRabbitConnectionFactory());
			RabbitMqProperties.Dev dev = config.getDev();
			// 申明 direct 交换机
			DirectExchange commonDirectExchange = (DirectExchange)ExchangeBuilder.directExchange(dev.getDevDirect()).durable(true).build();
			rabbitAdmin.declareExchange(commonDirectExchange);
			// 申明并绑定对账单检查结果队列
			Arrays.stream(dev.getDirectQueues()).filter(Objects::nonNull).forEach(
					queueName->{
						Queue queue = new Queue(queueName,true);
						rabbitAdmin.declareQueue(queue);
						rabbitAdmin.declareBinding(BindingBuilder.bind(queue).to(commonDirectExchange).with(dev.getRoutingKey()));
					}
			);

			// 声明 Fanout 交换机
			FanoutExchange outinFanoutExchangeName = (FanoutExchange) ExchangeBuilder.fanoutExchange(dev.getDevFanout()).durable(true).build();
			rabbitAdmin.declareExchange(outinFanoutExchangeName);
			// 声明并绑定队列
			Queue outinQueue = new Queue(dev.getTestQueue(), true);
			rabbitAdmin.declareQueue(outinQueue);
			rabbitAdmin.declareBinding(BindingBuilder.bind(outinQueue).to(outinFanoutExchangeName));
			return rabbitAdmin;
		}

		//@Bean
		public RabbitTemplate devRabbitTemplate() {
			RabbitTemplate rabbitTemplate = new RabbitTemplate(devRabbitConnectionFactory());
			// 默认使用事务模式提交
			rabbitTemplate.setChannelTransacted(true);
			rabbitTemplate.setReturnCallback((message, replyCode, replyText, tmpExchange, tmpRoutingKey) ->
					log.error("dev mq queue receive message error,message= " + message + " replyCode" + replyCode + " replyText " + replyText + " tmpExchange " + tmpExchange + " tmpRoutingKey " + tmpRoutingKey));
			return rabbitTemplate;
		}

		//@Bean
		public SimpleRabbitListenerContainerFactory devRabbitListenerContainerFactory(
				SimpleRabbitListenerContainerFactoryConfigurer configurer) {
			SimpleRabbitListenerContainerFactory factory = new SimpleRabbitListenerContainerFactory();
			factory.setConcurrentConsumers(1);
			factory.setMaxConcurrentConsumers(1);
			configurer.configure(factory, devRabbitConnectionFactory());
			return factory;
		}
	}
}
