package com.sinosoft.index.service;

import org.springframework.amqp.core.DirectExchange;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

/**
 * mq 服务
 * 
 * @author sinosoft
 *
 */
@Service
public class MqService {

	@Autowired
	RabbitTemplate rabbitTemplate;
	@Autowired()
	@Qualifier("queueIndex")
	Queue queueIndex;
	@Qualifier("queueResult")
	Queue queueResult;
	@Autowired
	DirectExchange directExchange;

	/**
	 * 发送消息到队列-index
	 * 
	 * @param obj
	 */
	public void sendMessageToIndex(Object obj) {
		if (obj == null)
			return;
		rabbitTemplate.convertAndSend(directExchange.getName(), queueIndex.getName(), obj);

	}

	/**
	 * 发送消息到队列-result
	 * 
	 * @param obj
	 */
	public void sendMessageToResult(Object obj) {
		if (obj == null)
			return;
		rabbitTemplate.convertAndSend(directExchange.getName(), queueResult.getName(), obj);

	}

}
