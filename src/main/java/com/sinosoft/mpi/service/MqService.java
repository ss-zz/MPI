package com.sinosoft.mpi.service;

import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sinosoft.config.MqConfig;
import com.sinosoft.mpi.model.register.BizRegister;
import com.sinosoft.mpi.model.register.BizRegisterMq;
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
	 * 发送bizRegisterMq
	 * 
	 * @param bizRegisterMq
	 */
	public void sendBizRegisterMq(BizRegisterMq bizRegisterMq) {
		mqTemplate.convertAndSend(MqConfig.MQ_EXCHANGE_NAME, MqConfig.MQ_QUEUE_NAME_INDEX, bizRegisterMq);
	}

	/**
	 * 发送personRegister
	 * 
	 * @param personRegister
	 *            人员注册信息
	 * @param personFieldPk
	 *            新生成的人员主键
	 */
	public void sendPersonInfo(PersonRegister personRegister, String personFieldPk) {
		BizRegisterMq regMq = new BizRegisterMq();
		BizRegister reg = new BizRegister();
		reg.setMpiPersonInfoRegister(personRegister.getMpiPersonInfoRegister());
		reg.setSystemKey(personRegister.getSystemKey());
		reg.setType(personRegister.getType());
		regMq.setBizRegister(reg);
		regMq.setPersonFieldPk(personFieldPk);
		sendBizRegisterMq(regMq);
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
