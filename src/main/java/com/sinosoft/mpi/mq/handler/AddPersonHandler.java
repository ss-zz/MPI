package com.sinosoft.mpi.mq.handler;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.sinosoft.mpi.model.PersonInfo;

/**
 * 增加主索引信息
 */
@Service
public class AddPersonHandler {

	@Resource
	CommonHandlerService commonHanlderService;

	/**
	 * 保存主索引信息
	 *
	 * @param personinfo
	 *            人员信息
	 * @return 主索引id
	 */
	public String handleMessage(PersonInfo personinfo) {
		return commonHanlderService.savePersonIndex(personinfo);
	}

}
