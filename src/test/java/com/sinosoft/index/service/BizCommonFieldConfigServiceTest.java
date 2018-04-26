package com.sinosoft.index.service;

import java.util.UUID;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.sinosoft.index.model.BizCommonFieldConfigModel;

@RunWith(SpringRunner.class)
@SpringBootTest
public class BizCommonFieldConfigServiceTest {

	ObjectMapper om = new ObjectMapper();

	@Autowired
	BizCommonFieldConfigService service;

	@Test
	public void contextLoads() throws Exception {
		BizCommonFieldConfigModel model = new BizCommonFieldConfigModel();
		model.setId("");
		model.setKey(UUID.randomUUID().toString());
		String id = service.save(model);
		System.out.println(id);
	}

}
