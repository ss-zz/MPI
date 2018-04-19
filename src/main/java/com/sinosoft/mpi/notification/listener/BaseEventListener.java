package com.sinosoft.mpi.notification.listener;

import java.util.Map;

import javax.jms.Message;
import javax.jms.MessageListener;
import javax.jms.ObjectMessage;

import com.sinosoft.mpi.notification.event.IEvent;

public class BaseEventListener implements MessageListener {

	private Map<String, IEventHandler> handlerMap;

	@Override
	public void onMessage(Message message) {

		if (message instanceof ObjectMessage) {
			ObjectMessage om = (ObjectMessage) message;
			try {
				IEvent event = (IEvent) om.getObject();
				IEventHandler handler = handlerMap.get(event.getEventType().getName());
				handler.processEvent(event);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}

	}

	public void setHandlerMap(Map<String, IEventHandler> handlerMap) {
		this.handlerMap = handlerMap;
	}

}
