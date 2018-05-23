package com.sinosoft.mpi.ws;

import java.util.Date;
import java.util.UUID;

import javax.annotation.Resource;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.sinosoft.MpiApplication;
import com.sinosoft.mpi.model.MpiPersonInfoRegister;
import com.sinosoft.mpi.model.biz.MpiBizInfoRegister;
import com.sinosoft.mpi.model.register.BizRegister;
import com.sinosoft.mpi.service.MpiIndexRegisterService;
import com.sinosoft.mpi.ws.domain.DataResult;

/**
 * 业务数据注册测试
 * 
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = MpiApplication.class, webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class BizRegisterTests {

	private static final String SYSTEM_ID = "xdgykzs";
	private static final Short DATA_STATE = Short.valueOf("0");

	@Resource
	private MpiIndexRegisterService mpiIndexRegisterService;

	/**
	 * 注册一个人员
	 */
	@Test
	public void registerPerson() throws Exception {

		BizRegister biz = new BizRegister();

		biz.setType(DATA_STATE);
		biz.setSystemKey(SYSTEM_ID);

		biz.setMpiPersonInfoRegister(getDemoPerson());
		biz.setBizInfo(getDemoBiz());

		int max = 10;
		for (int i = 0; i < max; i++) {
			DataResult<Void> ret = mpiIndexRegisterService.registerBiz(biz);
			System.out.println(ret);
		}
	}

	/**
	 * 获取测试人员
	 * 
	 * @return
	 */
	private MpiPersonInfoRegister getDemoPerson() {

		Date now = new Date();

		MpiPersonInfoRegister person = new MpiPersonInfoRegister();

		// 必填项
		// 人员id
		person.setPatientId(UUID.randomUUID().toString());

		// 人员基本信息
		person.setNameCn("测试姓名");
		person.setGenderCd("01");
		person.setBirthDate(now);
		person.setArCd("410000");

		return person;
	}

	/**
	 * 获取测试业务数据
	 * 
	 * @return
	 */
	public MpiBizInfoRegister getDemoBiz() {
		MpiBizInfoRegister biz = new MpiBizInfoRegister();
		biz.setBizId(UUID.randomUUID().toString());
		biz.setBizClinicno("12345");
		biz.setBizClinicSerialno("45687");
		biz.setBizInpatientno("78545");
		biz.setBizInpatientSerialno("74521");
		return biz;
	}

}
