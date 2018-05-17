package com.sinosoft.mpi.service;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.commons.lang.StringUtils;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import com.sinosoft.mpi.dao.mpi.SysUserDao;
import com.sinosoft.mpi.exception.ValidationException;
import com.sinosoft.mpi.model.SysRole;
import com.sinosoft.mpi.model.SysUser;
import com.sinosoft.mpi.util.PageInfo;

/**
 * 系统用户服务
 */
@Service
public class SysUserService {

	@Resource
	SysUserDao sysUserDao;
	@Resource
	SysRoleService sysRoleService;
	@Resource
	JdbcTemplate jdbcTemplate;

	/**
	 * 保存
	 * 
	 * @param t
	 */
	public void save(SysUser t) {
		if (!testUserName(t.getUserName())) {
			throw new ValidationException("用户名:" + t.getUserName() + "已存在");
		}
		sysUserDao.save(t);
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
		return sysUserDao.countByUserNameIgnoreCase(userName) == 0;
	}

	/**
	 * 更新用户
	 * 
	 * @param t
	 */
	public void update(SysUser t) {
		SysUser tmp = sysUserDao.findOne(t.getUserId());
		tmp.setName(t.getName());
		tmp.setEmail(t.getEmail());
		tmp.setSysRoleId(t.getSysRoleId());
		sysUserDao.save(tmp);
	}

	/**
	 * 删除用户
	 * 
	 * @param t
	 */
	public void delete(SysUser t) {
		sysUserDao.delete(t);
	}

	/**
	 * 根据id获取详情
	 * 
	 * @param id
	 * @return
	 */
	public SysUser getObject(String id) {
		return sysUserDao.findOne(id);
	}

	/**
	 * 验证用户名密码
	 * 
	 * @param userName
	 * @param password
	 * @return
	 */
	public boolean auth(String userName, String password) {
		boolean result = false;
		// 用户名&密码不为空的时候才去数据库查询验证
		if (StringUtils.isNotBlank(userName) && StringUtils.isNotBlank(password)) {
			return sysUserDao.countByUserNameIgnoreCaseAndPasswordAndState(userName, password, "1") > 0;
		}
		return result;
	}

	/**
	 * 分页查询用户
	 * 
	 * @param t
	 * @param page
	 * @return
	 */
	public List<SysUser> queryForPage(SysUser t, PageInfo page) {
		return sysUserDao.findAll(page).getContent();
	}

	/**
	 * 根据用户获取该用户的系统角色
	 * 
	 * @param user
	 *            系统用户(主键值必要属性)
	 * @return
	 */
	public SysRole getSysRoleByUser(SysUser user) {
		String sql = " select * from mpi_sys_role where sys_role_id in ( select "
				+ " sys_role_id from mpi_sys_user where user_id = ? ) ";
		List<SysRole> list = jdbcTemplate.queryForList(sql, new Object[] { user.getUserId() }, SysRole.class);
		return list.size() > 0 ? list.get(0) : null;
	}

	/**
	 * 列表页面查询结果
	 */
	public List<Map<String, Object>> findForList() {
		String sql = "select u.user_id,u.name,u.user_name,u.email,r.role_name from mpi_sys_user u "
				+ " left join mpi_sys_role r on u.sys_role_id = r.sys_role_id where 1=1 ";
		return jdbcTemplate.queryForList(sql);
	}

}
