package com.sinosoft.config;

import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.DirectExchange;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.rabbit.config.SimpleRabbitListenerContainerFactory;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitAdmin;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.support.converter.ContentTypeDelegatingMessageConverter;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.amqp.support.converter.MessageConverter;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * mq消息队列配置
 */
@Configuration
public class MqConfig {

	public static final String MQ_EXCHANGE_NAME = "mpi_exchange_index";
	public static final String MQ_QUEUE_NAME_INDEX = "mpi_queue_index";
	public static final String MQ_QUEUE_NAME_RESULT = "mpi_queue_result";

	/**
	 * 交换器
	 * 
	 * @return
	 */
	@Bean
	DirectExchange exchange() {
		return new DirectExchange(MQ_EXCHANGE_NAME, true, false);
	}

	/**
	 * 队列-主索引
	 * 
	 * @return
	 */
	@Bean("queueIndex")
	Queue queueIndex() {
		return new Queue(MQ_QUEUE_NAME_INDEX, true);
	}

	/**
	 * 队列-结果
	 * 
	 * @return
	 */
	@Bean("queueResult")
	Queue queueResult() {
		return new Queue(MQ_QUEUE_NAME_RESULT, true);
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
		return BindingBuilder.bind(queue).to(exchange).with(MQ_QUEUE_NAME_INDEX);
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
		return BindingBuilder.bind(queue).to(exchange).with(MQ_QUEUE_NAME_RESULT);
	}

	@Bean
	public RabbitAdmin rabbitTemplate(ConnectionFactory connectionFactory) {
		return new RabbitAdmin(connectionFactory);
	}

	@Bean
	public RabbitTemplate rabbitTemplate(ConnectionFactory connectionFactory, MessageConverter messageConverter) {
		RabbitTemplate template = new RabbitTemplate(connectionFactory);
		template.setMessageConverter(messageConverter);
		return template;
	}

	@Bean
	public SimpleRabbitListenerContainerFactory rabbitListenerContainerFactory(ConnectionFactory connectionFactory,
			MessageConverter messageConverter) {
		SimpleRabbitListenerContainerFactory factory = new SimpleRabbitListenerContainerFactory();
		factory.setConnectionFactory(connectionFactory);
		factory.setMessageConverter(messageConverter);
		return factory;
	}

	@Bean
	public MessageConverter messageConverter() {
		return new ContentTypeDelegatingMessageConverter(new Jackson2JsonMessageConverter());
	}

}
