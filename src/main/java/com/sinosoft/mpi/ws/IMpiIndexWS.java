package com.sinosoft.mpi.ws;

import java.util.List;

import javax.jws.WebParam;
import javax.jws.WebService;
import javax.jws.soap.SOAPBinding;
import javax.jws.soap.SOAPBinding.Style;

import com.sinosoft.mpi.model.PersonIndex;
import com.sinosoft.mpi.model.register.BizRegister;
import com.sinosoft.mpi.model.register.PersonRegister;
import com.sinosoft.mpi.ws.domain.DataResult;
import com.sinosoft.mpi.ws.domain.MpiIndexSearchParams;

/**
 * 主索引服务
 */
@WebService
@SOAPBinding(style = Style.DOCUMENT)
public interface IMpiIndexWS {

	/**
	 * 注册人员数据
	 * 
	 * @param personInfo
	 *            人员信息
	 */
	DataResult<Void> registerPerson(@WebParam(name = "personInfo") PersonRegister personInfo);

	/**
	 * 批量注册人员数据
	 * 
	 * @param personInfos
	 *            人员信息
	 */
	DataResult<Void> registerPersons(@WebParam(name = "personInfos") PersonRegister[] personInfos);

	/**
	 * 注册业务数据
	 * 
	 * @param bizInfo
	 *            业务信息
	 */

	DataResult<Void> registerBiz(@WebParam(name = "bizInfo") BizRegister bizInfo);

	/**
	 * 批量注册业务数据
	 * 
	 * @param bizInfos
	 *            业务信息
	 */

	DataResult<Void> registerBizs(@WebParam(name = "bizInfos") BizRegister[] bizInfos);

	/**
	 * 根据主索引id查询主索引信息
	 * 
	 * @param mpiId
	 *            主索引id
	 * @return 主索引信息
	 */
	DataResult<PersonIndex> getPersonIndexByMpiId(@WebParam(name = "mpiId") String mpiId);

	/**
	 * 根据搜索条件搜索主索引
	 * 
	 * @param mpiId
	 *            主索引id
	 * @return 主索引信息
	 */
	DataResult<List<PersonIndex>> searchPersonIndex(@WebParam(name = "params") MpiIndexSearchParams params);

	/**
	 * 根据主索引id获取所有相关业务id
	 * 
	 * @param mpiId
	 *            主索引id
	 * @return 业务id、业务系统id、次id列表
	 */
	DataResult<String[][]> searchBizsByMpiId(@WebParam(name = "mpiId") String mpiId);

	/**
	 * 根据主索引id获取所有相关业务id
	 * 
	 * @param mpiId
	 *            主索引id
	 * @param systemId
	 *            业务系统id
	 * @return 业务id、次id列表
	 */
	DataResult<String[][]> searchBizsByMpiIdAndSystemId(@WebParam(name = "mpiId") String mpiId,
			@WebParam(name = "systemId") String systemId);

}
