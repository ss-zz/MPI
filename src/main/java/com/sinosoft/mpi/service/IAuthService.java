package com.sinosoft.mpi.service;

/**
 * 
*    
* @Description  认证接口   
* 
* 
*
* 
* @Package com.sinosoft.mpi.service 
* @author <a href="mailto:qinshouxin@sinosoft.com.cn">Qin Shouxin </a> 
* @version v1.0,2012-3-16
* @see	
* @since	（可选）	
*
 */
public interface IAuthService {

	/**
	 * 认证账户
	 * @param userName
	 * @param password
	 * @return
	 */
	public boolean auth(String userName,String password);
}
