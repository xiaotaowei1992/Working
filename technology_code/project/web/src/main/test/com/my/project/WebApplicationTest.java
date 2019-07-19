package com.my.project;

import com.my.project.utils.MqSender;
import com.my.project.utils.WechatUtils;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

/**
 * @Author: weixiaotao
 * @ClassName WebApplicationTest
 * @Date: 2018/12/19 18:13
 * @Description:
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = WebApplication.class)
@Rollback(value = false)
public class WebApplicationTest {
	@Autowired
	private MqSender mqSender;


	@Test
	public void test(){
		System.out.println("111");
		mqSender.sendMq("devDirect","routingKey","{'test':123}");
	}

	@Test
	public  void testWeChat() {
		String chatId = "666666";
		String msg11 = "aaa";
		WechatUtils.sendMessage(new WechatUtils.MarkDownMessage(msg11,chatId));
	}
}