package com.sinosoft.mpi.dao.mpi;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import com.sinosoft.mpi.model.SysUser;

/**
 * 系统用户dao
 */
public interface SysUserDao extends JpaRepository<SysUser, String>, JpaSpecificationExecutor<SysUser> {

	/**
	 * 查询用户名数量-忽略大小写
	 * 
	 * @param userName
	 *            用户名
	 * @return
	 */
	int countByUserNameIgnoreCase(String userName);

	/**
	 * 查询用户数量-根据用户名、密码、状态
	 * 
	 * @param userName
	 *            用户名
	 * @param password
	 *            密码
	 * @param state
	 *            状态
	 * @return
	 */
	int countByUserNameIgnoreCaseAndPasswordAndState(String userName, String password, String state);

}
