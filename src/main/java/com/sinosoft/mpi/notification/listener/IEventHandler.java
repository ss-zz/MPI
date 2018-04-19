package com.sinosoft.mpi.notification.listener;

import com.sinosoft.mpi.notification.event.IEvent;

/**
 * 事件处理程序
 */
public interface IEventHandler {

	/**
	 * 处理事件
	 * 
	 * @param event
	 */
	void processEvent(IEvent event);
}
