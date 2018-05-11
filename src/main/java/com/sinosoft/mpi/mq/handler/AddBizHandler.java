package com.sinosoft.mpi.mq.handler;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.sinosoft.mpi.model.biz.BizInfo;

/**
 * 增加业务信息
 */
@Service
public class AddBizHandler {

	@Resource
	CommonBizHandlerService commonBizHandlerService;

	/**
	 * 保存业务信息
	 * 
	 * @param bizInfo
	 *            业务信息
	 * @param patientId
	 *            人员id
	 * @param mpiPk
	 *            主索引id
	 * @return
	 */
	public String handleMessage(BizInfo bizInfo, String patientId, String mpiPk) {
		return commonBizHandlerService.saveBizInfo(bizInfo, patientId, mpiPk);
	}

}
