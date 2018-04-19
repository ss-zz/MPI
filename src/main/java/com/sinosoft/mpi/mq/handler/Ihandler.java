package com.sinosoft.mpi.mq.handler;

import com.sinosoft.mpi.model.PersonInfo;

/**
 * 处理接口
 */
public interface Ihandler {

	/**
	 * 处理对象消息
	 * 
	 * @param obj
	 */
	public void handleMessage(PersonInfo obj);

}
