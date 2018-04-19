package com.sinosoft.mpi.notification.service;

import com.sinosoft.mpi.notification.event.EventType;

/**
 * 添加事件
 */
public interface IEventService {

	/**
	 * 触发事件
	 * 
	 * @param eventType
	 *            事件类型
	 * @param value
	 */
	void fireEvent(EventType eventType, Object value);
}
