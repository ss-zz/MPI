package com.sinosoft.mpi.service;

import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sinosoft.config.MqConfig;
import com.sinosoft.mpi.model.register.BizRegister;
import com.sinosoft.mpi.model.register.PersonRegister;
import com.sinosoft.mpi.ws.domain.DataResult;

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
	public void sendPersonInfo(PersonRegister personRegister) {
		BizRegister reg = new BizRegister();
		reg.setMpiPersonInfoRegister(personRegister.getMpiPersonInfoRegister());
		reg.setSystemKey(personRegister.getSystemKey());
		reg.setType(personRegister.getType());
		sendBizRegister(reg);
	}

	/**
	 * 发送数据处理结果
	 * 
	 * @param result
	 */
	public <T> void sendDataResult(DataResult<T> result) {
		mqTemplate.convertAndSend(MqConfig.MQ_EXCHANGE_NAME, MqConfig.MQ_QUEUE_NAME_RESULT, result);
	}

}
