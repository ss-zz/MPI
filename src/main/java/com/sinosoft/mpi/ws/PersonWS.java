package com.sinosoft.mpi.ws;

import javax.annotation.Resource;
import javax.jws.WebService;

import org.apache.log4j.Logger;

import com.sinosoft.mpi.dics.LogOpStyle;
import com.sinosoft.mpi.dics.LogOpType;
import com.sinosoft.mpi.exception.ValidationException;
import com.sinosoft.mpi.model.PersonInfo;
import com.sinosoft.mpi.model.PersonInfoSimple;
import com.sinosoft.mpi.service.PersonInfoService;
import com.sinosoft.mpi.ws.domain.DataResult;

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
	public DataResult<Void> addPerson(PersonInfo person) {
		DataResult<Void> result = null;
		try {
			personInfoService.save(person);
			result = new DataResult<Void>(true,
					"人员添加成功 pname = " + person.getNameCn() + " personId = " + person.getFieldPk());
		} catch (ValidationException e) { // 验证异常

			result = new DataResult<Void>(false, e.getMessage());
		} catch (Throwable e) { // 其他错误
			logger.error("系统错误,无法完成添加居民信息操作", e);
			result = new DataResult<Void>(false, "系统错误,无法完成添加居民信息操作");
		}
		return result;
	}

	/**
	 * 批量添加居民信息
	 */
	@Override
	public DataResult<Void> addPersonBatch(PersonInfo[] persons) {

		DataResult<Void> result = null;
		try {
			validDatasLength(persons.length);
			personInfoService.addPersonBatch(persons);
			result = new DataResult<Void>();
		} catch (ValidationException e) { // 验证异常
			result = new DataResult<Void>(false, e.getMessage());
		} catch (Throwable e) { // 其他错误
			logger.error("系统错误,无法完成添加居民信息操作", e);
			result = new DataResult<Void>(false, "系统错误,无法完成添加居民信息操作");
		}
		return result;
	}

	/**
	 * 合并两个居民信息
	 */
	@Override
	public DataResult<Void> mergePerson(PersonInfoSimple retired, PersonInfoSimple surviving) {
		PersonInfo retiredPerson = retired.toPersonInfo();
		PersonInfo survivingPerson = surviving.toPersonInfo();
		DataResult<Void> result = null;
		try {
			personInfoService.mergePerson(retiredPerson, survivingPerson, LogOpType.MODIFY, LogOpStyle.AUTO_MERGE);
			result = new DataResult<Void>();
		} catch (ValidationException e) {// 验证异常
			result = new DataResult<Void>(false, e.getMessage());
		} catch (Exception e) {
			logger.error("系统错误,无法完成合并居民信息操作", e);
			result = new DataResult<Void>(false, "系统错误,无法完成合并居民信息操作");
		}
		return result;
	}

	/**
	 * 匹配合并居民信息
	 */
	@Override
	public DataResult<Void> mergePersonBatch(PersonInfoSimple[] retireds, PersonInfoSimple surviving) {
		DataResult<Void> result = null;
		try {
			validDatasLength(retireds.length);
			personInfoService.mergePersonBatch(retireds, surviving, LogOpType.MODIFY, LogOpStyle.AUTO_MERGE);
			result = new DataResult<Void>();
		} catch (ValidationException e) { // 验证异常
			result = new DataResult<Void>(false, e.getMessage());
		} catch (Throwable e) { // 其他错误
			logger.error("系统错误,无法完成添加居民信息操作", e);
			result = new DataResult<Void>(false, "系统错误,无法完成添加居民信息操作");
		}
		return result;
	}

	/**
	 * 更新居民信息
	 */
	@Override
	public DataResult<Void> updatePerson(PersonInfo person) {
		DataResult<Void> result = null;
		try {
			personInfoService.update(person);
			result = new DataResult<Void>();
		} catch (ValidationException e) {// 验证异常
			result = new DataResult<Void>(false, e.getMessage());
		} catch (Throwable e) { // 其他错误
			logger.error("系统错误,无法完成更新居民信息操作", e);
			result = new DataResult<Void>(false, "系统错误,无法完成更新居民信息操作");
		}
		return result;
	}

	/**
	 * 匹配更新居民信息
	 */
	@Override
	public DataResult<Void> updatePersonBatch(PersonInfo[] persons) {
		DataResult<Void> result = null;
		try {
			// 校验数据数量
			validDatasLength(persons.length);
			personInfoService.updatePersonBatch(persons);
			result = new DataResult<Void>();
		} catch (ValidationException e) { // 验证异常
			result = new DataResult<Void>(false, e.getMessage());
		} catch (Throwable e) { // 其他错误
			logger.error("系统错误,无法完成添加居民信息操作", e);
			result = new DataResult<Void>(false, "系统错误,无法完成添加居民信息操作");
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
