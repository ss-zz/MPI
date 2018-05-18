package com.sinosoft.mpi.service;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.sinosoft.mpi.exception.ValidationException;
import com.sinosoft.mpi.model.PersonInfo;
import com.sinosoft.mpi.model.register.BizRegister;
import com.sinosoft.mpi.model.register.PersonRegister;
import com.sinosoft.mpi.ws.domain.DataResult;

/**
 * mpi 主索引注册服务
 */
@Service
public class MpiIndexRegisterService {

	@Resource
	PersonInfoService personInfoService;
	@Resource
	MqService mqService;

	/**
	 * 注册人员
	 * 
	 * @param personInfo
	 * @return
	 */
	public DataResult<Void> registerPerson(PersonRegister personReg) {
		DataResult<Void> result = null;
		try {
			// 注册人员
			PersonInfo person = personReg.getPersonInfo();
			// 保存数据到本地
			personInfoService.save(person);
			// 发送到mq
			mqService.sendPersonInfo(person);

			result = new DataResult<Void>(true,
					"人员注册成功：pname = " + person.getNameCn() + "，personId = " + person.getFieldPk());
		} catch (ValidationException e) { // 验证异常
			result = new DataResult<Void>(false, e.getMessage());
		} catch (Throwable e) { // 其他错误
			e.printStackTrace();
			result = new DataResult<Void>(false, "系统错误,无法完成注册人员操作");
		}
		return result;
	}

	/**
	 * 注册人员
	 * 
	 * @param personInfo
	 * @return
	 */
	public DataResult<Void> registeBiz(BizRegister bizReg) {
		DataResult<Void> result = null;
		try {
			// 注册人员
			PersonInfo person = bizReg.getPersonInfo();
			personInfoService.save(person);
			// 业务数据不处理直接发送到mq

			// 发送到mq
			mqService.sendBizRegister(bizReg);

			result = new DataResult<Void>(true,
					"业务注册成功：pname = " + person.getNameCn() + "，personId = " + person.getFieldPk());
		} catch (ValidationException e) { // 验证异常
			result = new DataResult<Void>(false, e.getMessage());
		} catch (Throwable e) { // 其他错误
			e.printStackTrace();
			result = new DataResult<Void>(false, "系统错误,无法完成注册业务操作");
		}
		return result;
	}

}
