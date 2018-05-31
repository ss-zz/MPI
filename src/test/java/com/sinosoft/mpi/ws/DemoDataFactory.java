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
		bizRegister.setState(DATA_STATE);
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
		personRegister.setState(DATA_STATE);
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
		person.setNameCn("测试姓名" + getRandomStr());
		person.setGenderCd("01");// 性别
		person.setGenderDn("男");
		person.setBirthDate(now);// 出生日期
		person.setArCd("410000");// 地区编码
		// person.setIdNo("410182451452145412");// 身份证号
		person.setMedicalserviceNo(getRandomStr());// 医疗服务号
		person.setHrId(getRandomStr());// 居民健康档案号
		person.setNhCard(getRandomStr());// 新农合卡号
		person.setSscid(getRandomStr());// 社保卡号
		
		person.setLivingAddr("居住地址");
		person.setProvinceName("省");
		person.setCityName("城市");
		person.setAreaName("区域");
		person.setVillageName(getRandomStr());

		return person;
	}

	/**
	 * 获取随机字符串
	 * 
	 * @return
	 */
	private static String getRandomStr() {
		// 1-10随机数
		return (int) (1 + Math.random() * (10 - 1 + 1)) + "";
	}

	/**
	 * 获取测试业务数据
	 * 
	 * @return
	 */
	public static MpiBizInfoRegister getBiz() {
		MpiBizInfoRegister biz = new MpiBizInfoRegister();
		biz.setBizId(UUID.randomUUID().toString());// 业务id-每次都随机
		biz.setBizClinicno(getRandomStr());
		biz.setBizClinicSerialno(getRandomStr());
		biz.setBizInpatientno(getRandomStr());
		biz.setBizInpatientSerialno(getRandomStr());
		return biz;
	}

}
