package com.sinosoft.mpi.mq.handler;

import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.sinosoft.config.MqConfig;
import com.sinosoft.mpi.model.PersonInfo;
import com.sinosoft.mpi.model.biz.MpiBizInfo;
import com.sinosoft.mpi.model.register.BizRegister;

/**
 * 消息队列人员数据处理
 */
@Component
public class PersonHandler {

	@Autowired
	AddPersonHandler addPersonHandler;
	@Autowired
	UpdatePersonHandler updatePersonHandler;
	@Autowired
	SplitPersonHandler splitPersonandler;

	@Autowired
	AddBizHandler addBizHandler;

	@RabbitListener(queues = MqConfig.MQ_QUEUE_NAME_INDEX)
	public void handleMessage(Object obj) {
		try {
			if (obj instanceof BizRegister) {
				BizRegister bizRegister = (BizRegister) obj;
				// 人员信息
				PersonInfo personInfo = bizRegister.getPersonInfo();
				// 数据状态
				int state = personInfo.getState();
				// 主索引id
				String mpiPk = null;
				if (state == 0) {// 新增
					mpiPk = addPersonHandler.handleMessage(personInfo);
				} else if (state == 1) {// 更新
					mpiPk = updatePersonHandler.handleMessage(personInfo);
				} else if (state == 2) {// 拆分
					mpiPk = splitPersonandler.handleMessage(personInfo);
				}

				if (state == 0) {// 新增
					// 患者id
					String patientId = personInfo.getFieldPk();
					// 业务信息
					MpiBizInfo bizInfo = bizRegister.getBizInfo();
					String ret = addBizHandler.handleMessage(bizInfo, patientId, mpiPk);
					System.out.println("业务处理结果：" + ret);
				}

			} else {
				throw new RuntimeException("错误的事件参数,参数应为PersonRegister对象,但实际为" + obj);
			}
		} catch (Exception e) {
			String fieldPk = null;
			if (obj != null && obj instanceof PersonInfo) {
				fieldPk = ((PersonInfo) obj).getFieldPk();
			}
			throw new RuntimeException("处理人员发生未知异常[" + fieldPk + "]:" + obj, e);
		}
	}

}
