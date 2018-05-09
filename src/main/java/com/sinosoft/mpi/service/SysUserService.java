package com.sinosoft.mpi.service;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Service;

import com.sinosoft.mpi.dao.SysRoleDao;
import com.sinosoft.mpi.dao.SysUserDao;
import com.sinosoft.mpi.exception.ValidationException;
import com.sinosoft.mpi.model.SysRole;
import com.sinosoft.mpi.model.SysUser;
import com.sinosoft.mpi.util.PageInfo;

@Service("sysUserService")
public class SysUserService implements ISysUserService {

	private Logger logger = Logger.getLogger(SysUserService.class);
	@Resource
	private SysUserDao sysUserDao;
	@Resource
	private SysRoleDao sysRoleDao;

	@Override
	public void save(SysUser t) {
		if (!testUserName(t.getUserName())) {
			logger.debug("用户名:" + t.getUserName() + "已存在");
			throw new ValidationException("用户名:" + t.getUserName() + "已存在");
		}
		sysUserDao.add(t);
	}

	/**
	 * 检查用户名是否存在
	 * 
	 * @param userName
	 * @return 不存在-true 存在-false
	 */
	public boolean testUserName(String userName) {
		if (StringUtils.isBlank(userName))
			return false;
		int i = sysUserDao.getCount(" select count(*) from mpi_sys_user where upper(user_name) = ?",
				new Object[] { userName.toUpperCase() });

		return i == 0;
	}

	@Override
	public void update(SysUser t) {
		SysUser tmp = sysUserDao.findById(t);
		tmp.setName(t.getName());
		tmp.setEmail(t.getEmail());
		tmp.setSysRoleId(t.getSysRoleId());
		sysUserDao.update(tmp);
	}

	@Override
	public void delete(SysUser t) {
		sysUserDao.deleteById(t);
		logger.debug("Delete SysUser,userId=" + t);
	}

	@Override
	public SysUser getObject(String id) {
		SysUser entity = new SysUser();
		entity.setUserId(id);
		entity = sysUserDao.findById(entity);
		return entity;
	}

	@Override
	public boolean auth(String userName, String password) {
		boolean result = false;
		// 用户名&密码不为空的时候才去数据库查询验证
		if (StringUtils.isNotBlank(userName) && StringUtils.isNotBlank(password)) {
			// 根据用户名和密码作为条件查询用户数量
			String sql = " select count(1) from mpi_sys_user where upper(user_name)=? and password=? and state=1 ";
			int count = sysUserDao.getCount(sql, new Object[] { userName.toUpperCase(), password });
			result = count > 0;
		}
		return result;
	}

	@Override
	public List<SysUser> queryForPage(SysUser t, PageInfo page) {
		// XXX ben 实际应用的时候这里需要添加查询条件
		String sql = " select * from mpi_sys_user where 1=1 ";
		sql = page.buildPageSql(sql);
		return sysUserDao.find(sql, new Object[] {});
	}

	/**
	 * 根据用户获取该用户的系统角色
	 * 
	 * @param user
	 *            系统用户(主键值必要属性)
	 * @return
	 */
	@Override
	public SysRole getSysRoleByUser(SysUser user) {
		String sql = " select * from mpi_sys_role where sys_role_id in ( select "
				+ " sys_role_id from mpi_sys_user where user_id = ? ) ";
		List<SysRole> list = sysRoleDao.find(sql, new Object[] { user.getUserId() });
		SysRole result = null;
		if (list != null && !list.isEmpty()) {
			result = list.get(0);
		}
		return result;
	}

	/**
	 * 列表页面查询结果
	 */
	@Override
	public List<Map<String, Object>> findForList() {
		String sql = "select u.user_id,u.name,u.user_name,u.email,r.role_name from mpi_sys_user u "
				+ " left join mpi_sys_role r on u.sys_role_id = r.sys_role_id where 1=1 ";
		return sysRoleDao.findForMap(sql);
	}

	/**
	 * 列出所有系统角色
	 */
	@Override
	public List<SysRole> findRoles() {
		return sysRoleDao.find(" select * from mpi_sys_role where 1=1 ");
	}

}
