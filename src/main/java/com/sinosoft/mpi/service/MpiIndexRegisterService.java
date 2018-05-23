package com.sinosoft.mpi.service;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.sinosoft.mpi.exception.ValidationException;
import com.sinosoft.mpi.model.MpiPersonInfoRegister;
import com.sinosoft.mpi.model.PersonInfo;
import com.sinosoft.mpi.model.biz.MpiBizInfoRegister;
import com.sinosoft.mpi.model.register.BizRegister;
import com.sinosoft.mpi.model.register.BizRegisterMq;
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
	 * @param personRegister
	 * @return
	 */
	public DataResult<Void> registerPerson(PersonRegister personRegister) {

		if (personRegister == null) {
			return new DataResult<Void>(false, "注册信息不能为空");
		}
		// 注册人员
		MpiPersonInfoRegister mpiPersonInfoRegister = personRegister.getMpiPersonInfoRegister();
		if (mpiPersonInfoRegister == null) {
			return new DataResult<Void>(false, "注册的人员信息不能为空");
		}
		try {
			PersonInfo person = mpiPersonInfoRegister.toPersonInfo(personRegister);
			// 保存数据到本地
			personInfoService.save(person);
			String personFieldPk = person.getFieldPk();
			// 发送到mq
			mqService.sendPersonInfo(personRegister, personFieldPk);

			return new DataResult<Void>(true, "【人员注册成功】中文名： " + mpiPersonInfoRegister.getNameCn() + "，原始人员id"
					+ mpiPersonInfoRegister.getPatientId());
		} catch (ValidationException e) { // 验证异常
			return new DataResult<Void>(false, "【校验失败】" + e.getMessage());
		} catch (Throwable e) { // 其他错误
			e.printStackTrace();
			return new DataResult<Void>(false, "【注册失败】");
		}
	}

	/**
	 * 批量注册人员
	 * 
	 * @param personRegisters
	 * @return
	 */
	public DataResult<Void> registerPersons(PersonRegister[] personRegisters) {
		DataResult<Void> ret = new DataResult<Void>();
		ret.setSuccess(true);
		StringBuilder sb = new StringBuilder();
		for (PersonRegister personRegister : personRegisters) {
			DataResult<Void> personRegisterResult = registerPerson(personRegister);
			boolean success = personRegisterResult.isSuccess();
			String message = personRegisterResult.getMsg();
			if (ret.isSuccess()) {
				ret.setSuccess(success);
			}
			sb.append("【" + message + "】");
		}
		ret.setMsg(sb.toString());
		return ret;
	}

	/**
	 * 注册业务
	 * 
	 * @param bizReg
	 * @return
	 */
	public DataResult<Void> registerBiz(BizRegister bizReg) {
		if (bizReg == null) {
			return new DataResult<Void>(false, "注册信息不能为空");
		}
		// 注册人员
		MpiPersonInfoRegister personRegister = bizReg.getMpiPersonInfoRegister();
		if (personRegister == null) {
			return new DataResult<Void>(false, "注册的人员信息不能为空");
		}
		try {
			// 注册人员
			PersonInfo person = personRegister.toPersonInfo(bizReg);
			personInfoService.save(person);
			// 业务数据不处理直接发送到mq

			// 业务数据校验
			MpiBizInfoRegister bizInfo = bizReg.getBizInfo();
			if (bizInfo == null) {
				return new DataResult<Void>(false, "注册的业务信息不能为空");
			}

			// 发送到mq
			BizRegisterMq bizRegMq = new BizRegisterMq();
			bizRegMq.setBizRegister(bizReg);
			bizRegMq.setPersonFieldPk(person.getFieldPk());
			mqService.sendBizRegisterMq(bizRegMq);

			return new DataResult<Void>(true, "【业务注册成功】中文名：" + person.getNameCn() + "，原始人员id："
					+ personRegister.getPatientId() + ", 原始业务id：" + bizInfo.getBizId());
		} catch (ValidationException e) { // 验证异常
			return new DataResult<Void>(false, "【校验失败】" + e.getMessage());
		} catch (Throwable e) { // 其他错误
			e.printStackTrace();
			return new DataResult<Void>(false, "【注册失败】");
		}
	}

	/**
	 * 批量注册业务
	 * 
	 * @param bizInfos
	 * @return
	 */
	public DataResult<Void> registerBizs(BizRegister[] bizInfos) {
		DataResult<Void> ret = new DataResult<Void>();
		ret.setSuccess(true);
		StringBuilder sb = new StringBuilder();
		for (BizRegister bizRegister : bizInfos) {
			DataResult<Void> bizRegisterResult = registerBiz(bizRegister);
			boolean success = bizRegisterResult.isSuccess();
			String message = bizRegisterResult.getMsg();
			if (ret.isSuccess()) {
				ret.setSuccess(success);
			}
			sb.append("【" + message + "】");
		}
		ret.setMsg(sb.toString());
		return ret;
	}

}
