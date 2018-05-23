package com.sinosoft.mpi.mq.handler.biz;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.sinosoft.mpi.model.biz.MpiBizInfoRegister;
import com.sinosoft.mpi.model.register.BizRegister;
import com.sinosoft.mpi.mq.handler.model.BizHandlerResult;

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
	public BizHandlerResult handleMessage(MpiBizInfoRegister bizInfo, String patientId, String mpiPk, BizRegister bizRegister ) {
		return commonBizHandlerService.saveBizInfo(bizInfo, patientId, mpiPk, bizRegister);
	}

}
