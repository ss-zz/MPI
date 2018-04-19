package com.sinosoft.config.ws;

import javax.xml.ws.Endpoint;

import org.apache.cxf.Bus;
import org.apache.cxf.bus.spring.SpringBus;
import org.apache.cxf.jaxws.EndpointImpl;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.sinosoft.book.pull.BookPullService;
import com.sinosoft.mpi.ws.PersonQueryWS;
import com.sinosoft.mpi.ws.PersonWS;

/**
 * cxf webservice 配置-居民相关
 * 
 * @author sinosoft
 *
 */
@Configuration
public class WsPersonConfig {

	@Bean(name = Bus.DEFAULT_BUS_ID)
	public SpringBus springBus() {
		return new SpringBus();
	}

	@Bean
	public PersonWS personWS() {
		return new PersonWS();
	}

	@Bean
	public PersonQueryWS personQueryWS() {
		return new PersonQueryWS();
	}

	@Bean
	public BookPullService bookPullService() {
		return new BookPullService();
	}

	@Bean
	public Endpoint endpointPersonWs() {
		EndpointImpl endpoint = new EndpointImpl(springBus(), personWS());
		endpoint.publish("/personWS");
		return endpoint;
	}

	@Bean
	public Endpoint endpointPersonQueryWS() {
		EndpointImpl endpoint = new EndpointImpl(springBus(), personQueryWS());
		endpoint.publish("/personQueryWS");
		return endpoint;
	}

	@Bean
	public Endpoint endpointBookPullWS() {
		EndpointImpl endpoint = new EndpointImpl(springBus(), bookPullService());
		endpoint.publish("/bookPullWS");
		return endpoint;
	}

}
