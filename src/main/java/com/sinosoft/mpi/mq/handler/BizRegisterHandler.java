package com.sinosoft.mpi.mq.handler;

import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.sinosoft.config.MqConfig;
import com.sinosoft.mpi.model.MpiPersonInfoRegister;
import com.sinosoft.mpi.model.PersonInfo;
import com.sinosoft.mpi.model.biz.MpiBizInfoRegister;
import com.sinosoft.mpi.model.register.BizRegister;
import com.sinosoft.mpi.model.register.BizRegisterMq;
import com.sinosoft.mpi.mq.handler.biz.AddBizHandler;
import com.sinosoft.mpi.mq.handler.model.BizHandlerResult;
import com.sinosoft.mpi.mq.handler.model.HandlerResult;
import com.sinosoft.mpi.mq.handler.person.AddPersonHandler;
import com.sinosoft.mpi.mq.handler.person.SplitPersonHandler;
import com.sinosoft.mpi.mq.handler.person.UpdatePersonHandler;
import com.sinosoft.mpi.service.MqService;
import com.sinosoft.mpi.ws.domain.DataResult;

/**
 * 消息队列处理-待处理注册数据
 */
@Component
public class BizRegisterHandler {

	@Autowired
	AddPersonHandler addPersonHandler;
	@Autowired
	UpdatePersonHandler updatePersonHandler;
	@Autowired
	SplitPersonHandler splitPersonandler;
	@Autowired
	AddBizHandler addBizHandler;
	@Autowired
	MqService mqService;

	@RabbitListener(queues = MqConfig.MQ_QUEUE_NAME_INDEX)
	public void handleMessage(BizRegisterMq bizRegisterMq) {
		DataResult<HandlerResult> result = new DataResult<>();
		try {
			HandlerResult handlerResult = new HandlerResult();

			BizRegister bizRegister = bizRegisterMq.getBizRegister();

			// 注册类型
			Short type = bizRegister.getType();

			// 人员信息
			MpiPersonInfoRegister personInfoRegister = bizRegister.getMpiPersonInfoRegister();
			PersonInfo personInfo = personInfoRegister.toPersonInfo(bizRegisterMq.getBizRegister());
			personInfo.setFieldPk(bizRegisterMq.getPersonFieldPk());

			String srcPersonId = personInfoRegister.getPatientId();
			// 人员原始id
			handlerResult.setSrcPersonId(srcPersonId);

			// 数据默认状态
			if (type == null) {
				type = 0;
			}

			// 处理类型
			handlerResult.setType(String.valueOf(type));

			// 主索引id
			String mpiPk = null;
			if (type == 0) {// 新增
				// 处理人员
				mpiPk = addPersonHandler.handleMessage(personInfo);

				// 处理业务
				MpiBizInfoRegister bizInfo = bizRegister.getBizInfo();
				if (bizInfo != null) {
					// 业务数据默认状态

					BizHandlerResult bizHandlerResult = addBizHandler.handleMessage(bizInfo, srcPersonId, mpiPk,
							bizRegister);

					handlerResult.setBizIdxId(bizHandlerResult.getNewBizId());
					handlerResult.setBizTimeId(bizHandlerResult.getTimeId());
					handlerResult.setSrcBizId(bizHandlerResult.getSrcBizId());
				}

			} else if (type == 1) {// 更新
				// 处理人员
				mpiPk = updatePersonHandler.handleMessage(personInfo);

				// TODO 处理业务

			} else if (type == 2) {// 拆分
				// 处理人员
				mpiPk = splitPersonandler.handleMessage(personInfo);

				// TODO 处理业务
			} else {
				result.setSuccess(false);
				result.setMsg("数据注册类型错误，应为[0|1|2]，实际为-" + type);
			}
			handlerResult.setPersonIdxId(mpiPk);

			if (result.isSuccess()) {
				result.setData(handlerResult);
			}

			// TODO 将结果发送到订阅地址

		} catch (Exception e) {
			e.printStackTrace();
			result.setSuccess(false);
			result.setMsg("数据处理发生异常：" + e.getMessage());
		} finally {
			// 处理结果发送到mq
			mqService.sendDataResult(result);
		}

	}

}
