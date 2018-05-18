package com.sinosoft.mpi.service;

import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sinosoft.config.MqConfig;
import com.sinosoft.mpi.model.PersonInfo;
import com.sinosoft.mpi.model.register.BizRegister;

/**
 * mq相关服务
 */
@Service
public class MqService {

	@Autowired
	private RabbitTemplate mqTemplate;

	/**
	 * 发送bizRegister
	 * 
	 * @param bizRegister
	 */
	public void sendBizRegister(BizRegister bizRegister) {
		mqTemplate.convertAndSend(MqConfig.MQ_EXCHANGE_NAME, MqConfig.MQ_QUEUE_NAME_INDEX, bizRegister);
	}

	/**
	 * 发送personInfo
	 * 
	 * @param personInfo
	 */
	public void sendPersonInfo(PersonInfo personInfo) {
		BizRegister reg = new BizRegister();
		reg.setPersonInfo(personInfo);
		reg.setSystemKey(personInfo.getDomainId());
		sendBizRegister(reg);
	}

}
