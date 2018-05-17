package com.sinosoft.mpi.ws;

import javax.annotation.Resource;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.sinosoft.MpiApplication;
import com.sinosoft.mpi.model.PersonInfo;
import com.sinosoft.mpi.service.PersonInfoService;

/**
 * webservice 测试
 * 
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = MpiApplication.class, webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class WSTests {

	@Resource
	private PersonInfoService personInfoService;

	@Test
	public void registerPerson() throws Exception {
		PersonInfo person = getDemoPerson();
		personInfoService.save(person);
	}

	private PersonInfo getDemoPerson() {
		PersonInfo person = new PersonInfo();
		person.setNameCn("测试姓名");
		person.setNameEn("ceshixingming");
		person.setGenderCd("01");
		person.setDomainId("1");
		person.setUniqueSign("005471944");
		person.setRegisterOrgCode("005471944");
		person.setRegisterOrgName("测试医院");
		return person;
	}

}
