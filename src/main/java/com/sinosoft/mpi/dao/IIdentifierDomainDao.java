package com.sinosoft.mpi.dao;

import com.sinosoft.mpi.model.IdentifierDomain;

/**
* @Description: 居民标志域数据操作类接口
* 
* @Package com.sinosoft.mpi.dao 
* @author bysun
* @version v1.0,2012-3-12
* @see	
* @since	v1.0
 */
public interface IIdentifierDomainDao extends IBaseDao<IdentifierDomain> {
	/**
	 * 根据域唯一标识取得域信息
	 * @param sign
	 * @return nullable
	 */
	IdentifierDomain getByUniqueSign(String sign);
	
	/**
	 * 根据居民id取得身份域
	 * @param personId 
	 * @return nullable
	 */
	IdentifierDomain getByPersonId(String personId);
	
	
	IdentifierDomain findById(String domainid);
}
