package com.sinosoft.mpi.mq.handler;

import org.apache.log4j.Logger;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.sinosoft.config.MqConfig;
import com.sinosoft.mpi.model.PersonInfo;
import com.sinosoft.mpi.model.register.PersonRegister;

/**
 * 消息队列人员数据处理
 */
@Component
public class PersonHandler {

	private static Logger logger = Logger.getLogger(PersonHandler.class);

	@Autowired
	AddPersonHandler addPersonHandler;
	@Autowired
	UpdatePersonHandler updatePersonHandler;
	@Autowired
	SplitPersonHandler splitPersonandler;

	@RabbitListener(queues = MqConfig.MQ_QUEUE_NAME_INDEX)
	public void handleMessage(Object obj) {
		try {
			logger.debug("处理人员对象=>" + obj.toString());
			if (obj instanceof PersonRegister) {
				// 人员信息
				PersonInfo personinfo = ((PersonRegister) obj).getPersonInfo();
				// 数据状态
				int state = personinfo.getSTATE();
				String mpiPk = null;
				if (state == 0) {// 新增
					mpiPk = addPersonHandler.handleMessage(personinfo);
				} else if (state == 1) {// 更新
					mpiPk = updatePersonHandler.handleMessage(personinfo);
				} else if (state == 2) {// 拆分
					mpiPk = splitPersonandler.handleMessage(personinfo);
				}

				if (mpiPk != null) {
					// TODO 业务信息

				}

			} else {
				throw new RuntimeException("错误的事件参数,参数应为PersonRegister对象,但实际为" + obj);
			}
		} catch (Exception e) {
			String fieldPk = null;
			if (obj != null && obj instanceof PersonInfo) {
				fieldPk = ((PersonInfo) obj).getFIELD_PK();
			}
			throw new RuntimeException("处理人员发生未知异常[" + fieldPk + "]:" + obj, e);
		}
	}

}
