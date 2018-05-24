package com.sinosoft.mpi.ws;

import java.io.IOException;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.rabbitmq.client.AMQP;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;
import com.rabbitmq.client.DefaultConsumer;
import com.rabbitmq.client.Envelope;
import com.sinosoft.mpi.ws.domain.DataResult;

/**
 * 队列中数据处理结果测试
 */
public class MqIndexResultHandlerTests {

	public static ObjectMapper om = new ObjectMapper();
	public static final String MQ_HOST = "192.168.1.252";
	public static final int MQ_PORT = 5672;
	public static final String MQ_QUEUE_NAME = "mpi_queue_result";

	/**
	 * 处理一条数据
	 */
	public static void main(String[] args) {

		try {
			ConnectionFactory factory = new ConnectionFactory();
			factory.setHost(MQ_HOST);
			factory.setPort(MQ_PORT);
			Connection conn = factory.newConnection();

			final Channel channel = conn.createChannel();

			channel.basicConsume(MQ_QUEUE_NAME, true, new DefaultConsumer(channel) {
				@Override
				public void handleDelivery(String consumerTag, Envelope envelope, AMQP.BasicProperties properties,
						byte[] body) throws IOException {
					System.out.println(om.writeValueAsString(om.readValue(body, DataResult.class)));
				}
			});
			conn.close();
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

}
