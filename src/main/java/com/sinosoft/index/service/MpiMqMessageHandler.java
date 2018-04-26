package com.sinosoft.index.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * mpi 队列数据处理
 * 
 * @author sinosoft
 *
 */
@Service
public class MpiMqMessageHandler {

	@Autowired
	MpiIndexService mpiIndexService;
	@Autowired
	BizConfigService bizConfigService;
	@Autowired
	BizFieldConfigService bizFieldConfigService;
	@Autowired
	BizCommonFieldConfigService bizCommonFieldConfigService;
	@Autowired
	MqService mqService;

	/**
	 * 队列数据处理
	 * 
	 * @param message
	 *            消息内容
	 */
	public void handleMessage(Object message) {
		System.out.println("从队列中获取消息：" + message.toString());

		// TODO 检索待合并数据

		// TODO 计算并合并数据

		// TODO 保存主索引index数据

		// TODO 保存操作记录

	}

}
