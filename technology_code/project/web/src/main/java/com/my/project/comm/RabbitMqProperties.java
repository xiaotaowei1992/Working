package com.my.project.comm;

import lombok.Data;
import lombok.EqualsAndHashCode;
import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * @Author: weixiaotao
 * @ClassName RabbitMqProperties
 * @Date: 2018/12/14 14:08
 * @Description:
 */
@Data
@ConfigurationProperties(prefix = "mq")
public class RabbitMqProperties {
	private Dev dev;

	@Data
	@EqualsAndHashCode(callSuper = true)
	static class Dev extends BaseProps {
        private String devDirect;
        private String devFanout;
        private String[] directQueues;
        private String routingKey;
        private String testQueue;
	}

	@Data
	static class BaseProps {
		/**
		 * 是否启用，默认true
		 */
		private boolean enabled;

		private String host;

		private int port;

		private String username;

		private String password;

		private String virtualhost;
	}
}
