package com.sinosoft.mpi.service;

import java.util.List;
import java.util.Map;

import com.sinosoft.mpi.model.SysRole;
import com.sinosoft.mpi.util.PageInfo;

/**
 * 系统角色操作接口
 */
public interface ISysRoleService extends IService<SysRole> {

	/**
	 * 取得某角色下的用户列表
	 * 
	 * @param role
	 *            角色
	 * @param page
	 *            分页信息
	 * @return
	 */
	List<Map<String, Object>> queryRoleUser(SysRole role, PageInfo page);

	/**
	 * 检查角色名是否可用
	 * 
	 * @param role
	 * @return
	 */
	boolean testRoleName(SysRole role);

}
