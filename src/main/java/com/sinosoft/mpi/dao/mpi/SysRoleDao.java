package com.sinosoft.mpi.dao.mpi;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import com.sinosoft.mpi.model.SysRole;

/**
 * 系统角色dao
 */
public interface SysRoleDao extends JpaRepository<SysRole, String>, JpaSpecificationExecutor<SysRole> {

	/**
	 * 查询指定角色名的数据量
	 * 
	 * @param roleName
	 *            角色名
	 * @return
	 */
	int countByRoleName(String roleName);

	/**
	 * 查询非指定角色id的角色名数量
	 * 
	 * @param roleName
	 *            角色名
	 * @param sysRoleId
	 *            角色id
	 * @return
	 */
	int countByRoleNameAndSysRoleIdNot(String roleName, String sysRoleId);

}
