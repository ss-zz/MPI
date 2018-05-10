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
import org.springframework.beans.factory.annotation.Qualifier;
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
	private static final String MQ_PORT = "mq.port";
	private static final String MQ_EXCHANGE_NAME = "mq.exchange.name";
	private static final String MQ_QUEUE_NAME_INDEX = "mq.queue.index.name";
	private static final String MQ_QUEUE_NAME_RESULT = "mq.queue.result.name";

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
		connectionFactory.setPort(env.getProperty(MQ_PORT, Integer.class));
		return connectionFactory;
	}

	/**
	 * 交换器
	 * 
	 * @return
	 */
	@Bean
	DirectExchange exchange() {
		return new DirectExchange(env.getProperty(MQ_EXCHANGE_NAME), true, false);
	}

	/**
	 * 队列-主索引
	 * 
	 * @return
	 */
	@Bean("queueIndex")
	Queue queueIndex() {
		return new Queue(env.getProperty(MQ_QUEUE_NAME_INDEX), true);
	}

	/**
	 * 队列-结果
	 * 
	 * @return
	 */
	@Bean("queueResult")
	Queue queueResult() {
		return new Queue(env.getProperty(MQ_QUEUE_NAME_RESULT), true);
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
	Binding bindingIndex(@Qualifier("queueIndex") Queue queue, DirectExchange exchange) {
		return BindingBuilder.bind(queue).to(exchange).with(env.getProperty(MQ_QUEUE_NAME_INDEX));
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
	Binding bindingResult(@Qualifier("queueResult") Queue queue, DirectExchange exchange) {
		return BindingBuilder.bind(queue).to(exchange).with(env.getProperty(MQ_QUEUE_NAME_RESULT));
	}

	/**
	 * 消息监听
	 * 
	 * @param connectionFactory
	 * @param listenerAdapterIndex
	 *            队列-待处理数据
	 * @return
	 */
	@Bean
	SimpleMessageListenerContainer container(ConnectionFactory connectionFactory,
			@Qualifier("listenerAdapterIndex") MessageListenerAdapter listenerAdapterIndex) {
		SimpleMessageListenerContainer container = new SimpleMessageListenerContainer(connectionFactory);
		container.setDefaultRequeueRejected(false);
		container.setQueueNames(env.getProperty(MQ_QUEUE_NAME_INDEX));
		container.setMessageListener(listenerAdapterIndex);
		return container;
	}

	/**
	 * 队列消费者-待处理数据
	 * 
	 * @param personhandler
	 *            人员处理
	 * @return
	 */
	@Bean("listenerAdapterIndex")
	MessageListenerAdapter listenerAdapterIndex(PersonHandler handler) {
		MessageListenerAdapter adapter = new MessageListenerAdapter(handler, "handleMessage");
		adapter.setResponseExchange(env.getProperty(MQ_EXCHANGE_NAME));
		return adapter;
	}

}
