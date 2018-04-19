package com.sinosoft.mpi.notification.event;

/**
 * 基础事件
 */
public class BaseEvent implements IEvent {

	private static final long serialVersionUID = -888805225309900106L;

	/**
	 * 事件类型
	 */
	private EventType eventType;

	/**
	 * 值
	 */
	private Object value;

	public BaseEvent() {
		super();
	}

	public BaseEvent(EventType eventType, Object value) {
		super();
		this.eventType = eventType;
		this.value = value;
	}

	public EventType getEventType() {
		return eventType;
	}

	public void setEventType(EventType eventType) {
		this.eventType = eventType;
	}

	public Object getValue() {
		return value;
	}

	public void setValue(Object value) {
		this.value = value;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((eventType == null) ? 0 : eventType.hashCode());
		result = prime * result + ((value == null) ? 0 : value.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		BaseEvent other = (BaseEvent) obj;
		if (eventType != other.eventType)
			return false;
		if (value == null) {
			if (other.value != null)
				return false;
		} else if (!value.equals(other.value))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "BaseEvent [eventType=" + eventType + ", value=" + value + "]";
	}
}
