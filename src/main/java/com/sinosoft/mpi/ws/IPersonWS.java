package com.sinosoft.mpi.ws;

import javax.jws.WebParam;
import javax.jws.WebService;
import javax.jws.soap.SOAPBinding;
import javax.jws.soap.SOAPBinding.Style;

import com.sinosoft.mpi.model.PersonInfo;
import com.sinosoft.mpi.model.PersonInfoSimple;
import com.sinosoft.mpi.ws.domain.DataResult;

/**
 * 居民信息服务
 */
@WebService
@SOAPBinding(style = Style.DOCUMENT)
public interface IPersonWS {

	/**
	 * 添加居民信息
	 * 
	 * @param person
	 */
	DataResult<Void> addPerson(@WebParam(name = "person") PersonInfo person);

	/**
	 * 批量添加居民信息接口
	 * 
	 * @param persons
	 *            一次传输最大数量100,大于此数量将返回错误信息.
	 */
	DataResult<Void> addPersonBatch(@WebParam(name = "persons") PersonInfo[] persons);

	/**
	 * 更新居民信息
	 * 
	 * @param person
	 */
	DataResult<Void> updatePerson(@WebParam(name = "person") PersonInfo person);

	/**
	 * 批量更新居民信息接口
	 * 
	 * @param persons
	 *            一次传输最大数量100,大于此数量将返回错误信息.
	 */
	DataResult<Void> updatePersonBatch(@WebParam(name = "persons") PersonInfo[] persons);

	/**
	 * 合并两个居民信息
	 * 
	 * @param retired
	 *            要删除的居民信息
	 * @param surviving
	 *            要保留的居民信息
	 */
	DataResult<Void> mergePerson(@WebParam(name = "retired") PersonInfoSimple retired,
			@WebParam(name = "surviving") PersonInfoSimple surviving);

	/**
	 * 批量合并居民信息
	 * 
	 * @param retireds
	 *            一次传输最大数量100,大于此数量将返回错误信息.
	 * @param surviving
	 *            要保留的居民信息
	 * @return
	 */
	DataResult<Void> mergePersonBatch(@WebParam(name = "retireds") PersonInfoSimple[] retireds,
			@WebParam(name = "surviving") PersonInfoSimple surviving);
}
