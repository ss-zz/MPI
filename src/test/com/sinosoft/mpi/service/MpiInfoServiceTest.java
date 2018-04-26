package com.sinosoft.mpi.service;

import java.io.IOException;
import java.util.List;
import java.util.Map;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.test.context.junit4.SpringRunner;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.sinosoft.mpi.service.index.MpiIndexService;

@RunWith(SpringRunner.class)
@SpringBootTest
public class MpiInfoServiceTest {

	ObjectMapper om = new ObjectMapper();

	@Autowired
	MpiIndexService service;

	@Test
	public void contextLoads() throws Exception {
		Query q = new Query();
		Criteria titleCriteria = new Criteria();
		titleCriteria.andOperator(Criteria.where("title").regex("ceshi-"));
		q.addCriteria(titleCriteria);
		List<Object> items = service.findIndex(q);
		for (Object obj : items) {
			System.out.println(transObjToJson(obj));
		}
	}

	/**
	 * 对象转json
	 * 
	 * @param obj
	 *            对象
	 * @return json字符串
	 * @throws JsonProcessingException
	 */
	private String transObjToJson(Object obj) throws JsonProcessingException {
		return om.writeValueAsString(obj);
	}

	/**
	 * json 转 map
	 * 
	 * @param json
	 *            json字符串
	 * @return map对象
	 */
	private Map<String, Object> transJsonToMap(String json)
			throws JsonParseException, JsonMappingException, IOException {
		return om.readValue(json, new TypeReference<Map<String, Object>>() {
		});
	}

}
