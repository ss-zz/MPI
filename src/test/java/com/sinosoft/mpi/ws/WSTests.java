package com.sinosoft.mpi.ws;

import java.util.Date;
import java.util.UUID;

import javax.annotation.Resource;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.sinosoft.MpiApplication;
import com.sinosoft.mpi.model.PersonInfo;
import com.sinosoft.mpi.model.register.PersonRegister;
import com.sinosoft.mpi.service.MpiIndexRegisterService;
import com.sinosoft.mpi.ws.domain.DataResult;

/**
 * webservice 测试
 * 
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = MpiApplication.class, webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class WSTests {

	private static final String SYSTEM_ID = "005471944";
	private static final String SYSTEM_DESC = "测试系统";

	@Resource
	private MpiIndexRegisterService mpiIndexRegisterService;

	/**
	 * 注册一个人员
	 */
	@Test
	public void registerPerson() throws Exception {

		PersonRegister person = new PersonRegister();
		person.setPersonInfo(getDemoPerson());
		person.setSystemKey(SYSTEM_ID);

		DataResult<Void> ret = mpiIndexRegisterService.registerPerson(person);
		System.out.println(ret);
	}

	/**
	 * 获取测试人员
	 * 
	 * @return
	 */
	private PersonInfo getDemoPerson() {

		String systemId = SYSTEM_ID;
		String systemDesc = SYSTEM_DESC;
		Date now = new Date();

		PersonInfo person = new PersonInfo();

		// 必填项

		// 人员id
		person.setPatientId(UUID.randomUUID().toString());

		// 人员基本信息
		person.setNameCn("测试姓名");
		person.setGenderCd("01");
		person.setBirthDate(now);
		person.setArCd("410000");

		// 域信息
		person.setDomainId("1");
		person.setUniqueSign(systemId);

		// 数据状态
		person.setState((short) 0);

		// 发送信息
		person.setSendOrgCode(systemId);
		person.setSendSystem(systemDesc);
		person.setSendTime(now);

		// 注册信息
		person.setRegisterDate(now);
		person.setRegisterOrgCode(systemId);
		person.setRegisterOrgName(systemDesc);

		// 系统开发商信息
		person.setProviderName("系统开发商");
		person.setProviderOrgCode("000");

		return person;
	}

}
