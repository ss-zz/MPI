package com.sinosoft.config.ws;

import javax.xml.ws.Endpoint;

import org.apache.cxf.bus.spring.SpringBus;
import org.apache.cxf.jaxws.EndpointImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.sinosoft.pixpdqv3.iti44.Iti44PixPortType;
import com.sinosoft.pixpdqv3.iti44.Iti44PixPortTypeImpl;
import com.sinosoft.pixpdqv3.iti45.Iti45PortType;
import com.sinosoft.pixpdqv3.iti45.Iti45PortTypeImpl;
import com.sinosoft.pixpdqv3.iti47.Iti47PortType;
import com.sinosoft.pixpdqv3.iti47.Iti47PortTypeImpl;

/**
 * cxf webservice 配置-hl7 v3相关
 */
@Configuration
public class WsHl7v3Config {

	@Autowired
	public SpringBus bus;

	@Bean
	public Iti44PixPortType iti44PixPortType() {
		return new Iti44PixPortTypeImpl();
	}
	
	@Bean
	public Iti45PortType iti45PixPortType() {
		return new Iti45PortTypeImpl();
	}
	
	@Bean
	public Iti47PortType iti47PixPortType() {
		return new Iti47PortTypeImpl();
	}

	@Bean
	public Endpoint iti44() {
		EndpointImpl endpoint = new EndpointImpl(bus, iti44PixPortType());
		endpoint.publish("/iti44");
		return endpoint;
	}

	@Bean
	public Endpoint iti45() {
		EndpointImpl endpoint = new EndpointImpl(bus, iti45PixPortType());
		endpoint.publish("/iti45");
		return endpoint;
	}

	@Bean
	public Endpoint iti47() {
		EndpointImpl endpoint = new EndpointImpl(bus, iti47PixPortType());
		endpoint.publish("/iti47");
		return endpoint;
	}

}
