package com.sinosoft.mpi.notification.event;

import java.io.Serializable;

public interface IEvent extends Serializable {
	/**
	 * 取得事件类型
	 * 
	 * @return
	 */
	EventType getEventType();

	/**
	 * 设置事件类型
	 * 
	 * @param eventType
	 */
	void setEventType(EventType eventType);

	/**
	 * 取得操作值
	 * 
	 * @return
	 */
	Object getValue();

	/**
	 * 设置操作值
	 * 
	 * @param value
	 */
	void setValue(Object value);
}
