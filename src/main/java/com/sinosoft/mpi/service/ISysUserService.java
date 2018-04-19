package com.sinosoft.mpi.service;


import java.util.List;
import java.util.Map;

import com.sinosoft.mpi.model.SysRole;
import com.sinosoft.mpi.model.SysUser;

/**
 * 
*    
* @Description  系统用户操作 
* 
* 
*
* 
* @Package com.sinosoft.mpi.service 
* @author <a href="mailto:qinshouxin@sinosoft.com.cn">Qin Shouxin </a> 
* @version v1.0,2012-3-16
 * @param <T>
* @see	
* @since	（可选）	
*
 */
public interface ISysUserService extends IService<SysUser>,IAuthService {	
	
	/**
	 * 根据用户获取该用户的系统角色
	 * @param user 系统用户(主键值必要属性)
	 * @return
	 */
	SysRole getSysRoleByUser(SysUser user);
	
	/**
	 * 列表页面查询结果
	 */
	List<Map<String,Object>> findForList();
	
	/**
	 * 列出所有系统角色
	 */
	List<SysRole> findRoles();
	
	/**
	 * 检查用户名是否存在
	 * @param userName
	 * @return 不存在-true 存在-false
	 */
	boolean testUserName(String userName);
}
