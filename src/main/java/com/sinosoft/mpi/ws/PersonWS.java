package com.sinosoft.mpi.ws;

import javax.annotation.Resource;
import javax.jws.WebService;

import org.apache.log4j.Logger;

import com.sinosoft.mpi.exception.ValidationException;
import com.sinosoft.mpi.model.PersonInfo;
import com.sinosoft.mpi.model.PersonInfoSimple;
import com.sinosoft.mpi.service.PersonInfoService;

/**
 * 居民服务
 */
@WebService(endpointInterface = "com.sinosoft.mpi.ws.IPersonWS", serviceName = "PersonWS")
public class PersonWS implements IPersonWS {
	private final static int MAX_DATA_LENGTH = 100;

	private Logger logger = Logger.getLogger(PersonWS.class);

	@Resource
	private PersonInfoService personInfoService;

	/**
	 * 添加居民信息
	 */
	@Override
	public DataResult addPerson(PersonInfo person) {
		DataResult result = null;
		try {
			personInfoService.save(person);
			result = new DataResult(true,
					"人员添加成功 pname = " + person.getNAME_CN() + " personId = " + person.getFIELD_PK());
		} catch (ValidationException e) { // 验证异常

			result = new DataResult(false, e.getMessage());
		} catch (Throwable e) { // 其他错误
			logger.error("系统错误,无法完成添加居民信息操作", e);
			result = new DataResult(false, "系统错误,无法完成添加居民信息操作");
		}
		return result;
	}

	/**
	 * 批量添加居民信息
	 */
	@Override
	public DataResult addPersonBatch(PersonInfo[] persons) {

		DataResult result = null;
		try {
			validDatasLength(persons.length);
			personInfoService.addPersonBatch(persons);
			result = new DataResult();
		} catch (ValidationException e) { // 验证异常
			result = new DataResult(false, e.getMessage());
		} catch (Throwable e) { // 其他错误
			logger.error("系统错误,无法完成添加居民信息操作", e);
			result = new DataResult(false, "系统错误,无法完成添加居民信息操作");
		}
		return result;
	}

	/**
	 * 合并两个居民信息
	 */
	@Override
	public DataResult mergePerson(PersonInfoSimple retired, PersonInfoSimple surviving) {
		PersonInfo retiredPerson = retired.toPersonInfo();
		PersonInfo survivingPerson = surviving.toPersonInfo();
		DataResult result = null;
		try {
			personInfoService.mergePerson(retiredPerson, survivingPerson);
			result = new DataResult();
		} catch (ValidationException e) {// 验证异常
			result = new DataResult(false, e.getMessage());
		} catch (Exception e) {
			logger.error("系统错误,无法完成合并居民信息操作", e);
			result = new DataResult(false, "系统错误,无法完成合并居民信息操作");
		}
		return result;
	}

	/**
	 * 匹配合并居民信息
	 */
	@Override
	public DataResult mergePersonBatch(PersonInfoSimple[] retireds, PersonInfoSimple surviving) {
		DataResult result = null;
		try {
			validDatasLength(retireds.length);
			personInfoService.mergePersonBatch(retireds, surviving);
			result = new DataResult();
		} catch (ValidationException e) { // 验证异常
			result = new DataResult(false, e.getMessage());
		} catch (Throwable e) { // 其他错误
			logger.error("系统错误,无法完成添加居民信息操作", e);
			result = new DataResult(false, "系统错误,无法完成添加居民信息操作");
		}
		return result;
	}

	/**
	 * 更新居民信息
	 */
	@Override
	public DataResult updatePerson(PersonInfo person) {
		DataResult result = null;
		try {
			personInfoService.update(person);
			result = new DataResult();
		} catch (ValidationException e) {// 验证异常
			result = new DataResult(false, e.getMessage());
		} catch (Throwable e) { // 其他错误
			logger.error("系统错误,无法完成更新居民信息操作", e);
			result = new DataResult(false, "系统错误,无法完成更新居民信息操作");
		}
		return result;
	}

	/**
	 * 匹配更新居民信息
	 */
	@Override
	public DataResult updatePersonBatch(PersonInfo[] persons) {
		DataResult result = null;
		try {
			// 校验数据数量
			validDatasLength(persons.length);
			personInfoService.updatePersonBatch(persons);
			result = new DataResult();
		} catch (ValidationException e) { // 验证异常
			result = new DataResult(false, e.getMessage());
		} catch (Throwable e) { // 其他错误
			logger.error("系统错误,无法完成添加居民信息操作", e);
			result = new DataResult(false, "系统错误,无法完成添加居民信息操作");
		}
		return result;
	}

	/**
	 * 校验数据数量
	 * 
	 * @param length
	 *            数据长度
	 */
	private void validDatasLength(int length) {
		if (length > MAX_DATA_LENGTH) {
			throw new ValidationException("数据量过大无法完成批量操作,每次最多处理" + MAX_DATA_LENGTH + "条数据.");
		}
	}

}
