package com.sinosoft.mpi.ws;

import java.io.PrintStream;

import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.web.client.RestTemplate;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.sinosoft.mpi.ws.domain.DataResult;

public class BaseTest {

	public static final String BASEURL = "http://localhost:9201/";

	public static ObjectMapper om = new ObjectMapper();
	public static RestTemplate rest = new RestTemplate();

	static {
		rest.getMessageConverters().add(new MappingJackson2HttpMessageConverter());
	}

	/**
	 * 打印请求结果
	 * 
	 * @param ret
	 */
	public static void print(DataResult<?> ret) {
		PrintStream p = null;
		if (ret.isSuccess()) {
			p = System.out;
		} else {
			p = System.err;
		}
		p.println("===请求结果>>>====");
		p.println("成功：" + ret.isSuccess());
		p.println("消息：" + ret.getMsg());
		try {
			p.println("内容：" + om.writeValueAsString(ret.getData()));
		} catch (JsonProcessingException e) {
			e.printStackTrace();
		}
		p.println("===<<<请求结果====");
	}

	/**
	 * 发送get请求
	 * 
	 * @param url
	 * @param params
	 */
	public static <T> void get(String url, Object params) {
		print(rest.getForObject(BASEURL + url, DataResult.class, params));
	}

	/**
	 * 发送post请求
	 * 
	 * @param url
	 * @param params
	 */
	public static <T> void post(String url, Object params) {
		print(rest.postForObject(BASEURL + url, params, DataResult.class));
	}

}
