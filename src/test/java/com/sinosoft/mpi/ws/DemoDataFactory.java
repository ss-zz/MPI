package com.sinosoft.mpi.ws;

import java.util.Date;
import java.util.UUID;

import com.sinosoft.mpi.model.MpiPersonInfoRegister;
import com.sinosoft.mpi.model.biz.MpiBizInfoRegister;
import com.sinosoft.mpi.model.register.BizRegister;
import com.sinosoft.mpi.model.register.PersonRegister;

/**
 * 测试数据构造
 */
public class DemoDataFactory {

	private static final String SYSTEM_ID = "xdgykzs";
	private static final Short DATA_STATE = Short.valueOf("0");

	/**
	 * 获取测试业务注册数据
	 * 
	 * @return
	 */
	public static BizRegister getBizRegister() {
		BizRegister bizRegister = new BizRegister();
		bizRegister.setType(DATA_STATE);
		bizRegister.setSystemKey(SYSTEM_ID);
		bizRegister.setMpiPersonInfoRegister(getPerson());
		bizRegister.setBizInfo(getBiz());
		return bizRegister;
	}

	/**
	 * 获取测试业务注册数据
	 * 
	 * @return
	 */
	public static PersonRegister getPersonRegister() {
		PersonRegister personRegister = new PersonRegister();
		personRegister.setType(DATA_STATE);
		personRegister.setSystemKey(SYSTEM_ID);
		personRegister.setMpiPersonInfoRegister(getPerson());
		return personRegister;
	}

	/**
	 * 获取测试人员
	 */
	public static MpiPersonInfoRegister getPerson() {

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
		person.setIdNo("410182451452145412");

		return person;
	}

	/**
	 * 获取测试业务数据
	 * 
	 * @return
	 */
	public static MpiBizInfoRegister getBiz() {
		MpiBizInfoRegister biz = new MpiBizInfoRegister();
		biz.setBizId(UUID.randomUUID().toString());
		biz.setBizClinicno("12345");
		biz.setBizClinicSerialno("45687");
		biz.setBizInpatientno("78545");
		biz.setBizInpatientSerialno("74521");
		return biz;
	}

}
