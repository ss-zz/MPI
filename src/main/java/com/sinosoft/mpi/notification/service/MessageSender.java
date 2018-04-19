package com.sinosoft.mpi.notification.service;

import javax.jms.Destination;
import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.Session;

import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.jms.core.MessageCreator;

import com.sinosoft.mpi.notification.event.IEvent;

/**
 * 消息发送器，主要发送消息到消息中间件里
 */
public class MessageSender {

	// 发送到发送队列里，这个发送队列（有上级的接受）
	private Destination sendDestination;

	private JmsTemplate jmsTemplate;
	private AmqpTemplate amqpTemplate;

	/**
	 * 发送到发送队列里
	 * 
	 * @param message
	 */
	public void send(final IEvent event) {
		jmsTemplate.send(sendDestination, new MessageCreator() {

			@Override
			public Message createMessage(Session session) throws JMSException {
				return session.createObjectMessage(event);
			}

		});
	}

	/**
	 * 发送到指定队列（主要是医院的接受队列和本地市接收队列）
	 * 
	 * @param message
	 */
	public void send(String queueName, final IEvent event) {
		jmsTemplate.send(queueName, new MessageCreator() {
			@Override
			public Message createMessage(Session session) throws JMSException {
				return session.createObjectMessage(event);
			}
		});
	}

	/**
	 * 发送到指定队列（主要用在上级队列在本级的映射的队列）
	 * 
	 * @param message
	 */
	public void send(Destination destination, final IEvent event) {
		jmsTemplate.send(destination, new MessageCreator() {
			@Override
			public Message createMessage(Session session) throws JMSException {
				return session.createObjectMessage(event);
			}
		});
	}

	public void setSendDestination(Destination sendDestination) {
		this.sendDestination = sendDestination;
	}

	public void setJmsTemplate(JmsTemplate jmsTemplate) {
		this.jmsTemplate = jmsTemplate;
	}

}
