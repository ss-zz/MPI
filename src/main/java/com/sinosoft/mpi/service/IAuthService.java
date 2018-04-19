package com.sinosoft.mpi.service;

/**
 * 认证接口
 */
public interface IAuthService {

	/**
	 * 认证账户
	 * 
	 * @param userName
	 * @param password
	 * @return
	 */
	public boolean auth(String userName, String password);
}
