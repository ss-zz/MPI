package com.sinosoft.config;

import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.DirectExchange;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.rabbit.connection.CachingConnectionFactory;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitAdmin;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.rabbit.listener.SimpleMessageListenerContainer;
import org.springframework.amqp.rabbit.listener.adapter.MessageListenerAdapter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;

import com.sinosoft.mpi.mq.handler.PersonHandler;

/**
 * mq消息队列配置
 */
@Configuration
public class MqConfig {

	private static final String MQ_HOST = "mq.host";
	private static final String MQ_QUEUE_NAME = "mq.queue.name";
	private static final String MQ_EXCHANGE_NAME = "mq.exchange.name";

	@Autowired
	Environment env;

	/**
	 * rabbitAdmin 代理
	 * 
	 * @param connectionFactory
	 *            连接工厂
	 * @return
	 */
	@Bean
	RabbitAdmin rabbitAdmin(ConnectionFactory connectionFactory) {
		return new RabbitAdmin(connectionFactory);
	}

	/**
	 * 消息模版
	 * 
	 * @param connectionFactory
	 *            连接工厂
	 * @param exchange
	 *            交换器
	 * @return
	 */
	@Bean
	RabbitTemplate rabbitTemplate(ConnectionFactory connectionFactory, DirectExchange exchange) {
		RabbitTemplate rabbitTemplate = new RabbitTemplate(connectionFactory);
		rabbitTemplate.setExchange(env.getProperty(MQ_EXCHANGE_NAME));
		return rabbitTemplate;
	}

	/**
	 * 连接工厂
	 * 
	 * @return
	 */
	@Bean
	ConnectionFactory connectionFactory() {
		CachingConnectionFactory connectionFactory = new CachingConnectionFactory();
		connectionFactory.setHost(env.getProperty(MQ_HOST));
		return connectionFactory;
	}

	/**
	 * 队列
	 * 
	 * @return
	 */
	@Bean
	Queue queue() {
		return new Queue(env.getProperty(MQ_QUEUE_NAME), true);
	}

	/**
	 * 交换器
	 * 
	 * @return
	 */
	@Bean
	DirectExchange exchange() {
		DirectExchange exchange = new DirectExchange(env.getProperty(MQ_EXCHANGE_NAME), true, false);
		return exchange;
	}

	/**
	 * 绑定队列与交换器
	 * 
	 * @param queue
	 *            队列
	 * @param exchange
	 *            交换器
	 * @return
	 */
	@Bean
	Binding binding(Queue queue, DirectExchange exchange) {
		return BindingBuilder.bind(queue).to(exchange).with(env.getProperty(MQ_QUEUE_NAME));
	}

	/**
	 * 消息监听
	 * 
	 * @param connectionFactory
	 * @param listenerAdapter
	 * @return
	 */
	@Bean
	SimpleMessageListenerContainer container(ConnectionFactory connectionFactory,
			MessageListenerAdapter listenerAdapter) {
		SimpleMessageListenerContainer container = new SimpleMessageListenerContainer(connectionFactory);
		container.setDefaultRequeueRejected(false);
		container.setQueueNames(env.getProperty(MQ_QUEUE_NAME));
		container.setMessageListener(listenerAdapter);
		return container;
	}

	/**
	 * 消息处理-人员
	 * 
	 * @param personhandler
	 *            人员处理
	 * @return
	 */
	@Bean
	MessageListenerAdapter listenerAdapter(PersonHandler personhandler) {
		MessageListenerAdapter adapter = new MessageListenerAdapter(personhandler, "handleMessage");
		adapter.setResponseExchange(env.getProperty(MQ_EXCHANGE_NAME));
		return adapter;
	}

}
