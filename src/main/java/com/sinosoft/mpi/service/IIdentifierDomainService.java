package com.sinosoft.mpi.service;

import java.util.List;

import com.sinosoft.mpi.model.IdentifierDomain;

/**
 * 标志域操作
 */
public interface IIdentifierDomainService extends IService<IdentifierDomain> {
	
	/**
	 * 查询所有身份域,列表显示使用
	 */
	List<IdentifierDomain> queryAll();

	/**
	 * 检查添加或修改的身份域唯一标识是否存在
	 * 
	 * @param domain
	 * @return true-不存在 false-存在
	 */
	boolean testDomain(IdentifierDomain domain);

	/**
	 * 查询所有需要信息推送身份域
	 */
	List<IdentifierDomain> queryPushDomain();

	List<IdentifierDomain> queryByDomianId(String unique_sign);
}
