package com.sinosoft.mpi.ws;

import javax.annotation.Resource;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.sinosoft.MpiApplication;
import com.sinosoft.config.MqConfig;
import com.sinosoft.mpi.mq.handler.BizRegisterHandler;

/**
 * 队列中数据处理结果测试
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = MpiApplication.class, webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class MqIndexResultHandlerTests {

	@Resource
	BizRegisterHandler bizRegisterHandler;
	@Autowired
	RabbitTemplate mqTemplate;

	ObjectMapper om = new ObjectMapper();

	/**
	 * 处理一条数据
	 */
	@Test
	public void handleOne() throws Exception {

		// 从处理结果队列中获取结果内容
		Object result = null;
		int count = 0;
		while ((result = mqTemplate.receiveAndConvert(MqConfig.MQ_QUEUE_NAME_RESULT)) != null) {
			count++;
			System.out.println(om.writeValueAsString(result));
		}
		System.out.println(count == 0 ? "结果队列无数据" : "结果队列中返回数据" + count + "条");

	}

}
