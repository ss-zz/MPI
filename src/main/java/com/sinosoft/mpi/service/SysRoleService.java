package com.sinosoft.mpi.service;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.commons.lang.StringUtils;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import com.sinosoft.mpi.dao.mpi.SysRoleDao;
import com.sinosoft.mpi.exception.ValidationException;
import com.sinosoft.mpi.model.SysRole;
import com.sinosoft.mpi.util.PageInfo;

/**
 * 系统角色服务
 */
@Service
public class SysRoleService {

	@Resource
	private SysRoleDao sysRoleDao;
	@Resource
	JdbcTemplate jdbcTemplate;

	/**
	 * 保存
	 * 
	 * @param t
	 */
	public void save(SysRole t) {
		// 验证是否可用
		if (testRoleName(t)) {
			sysRoleDao.save(t);
		} else {
			throw new ValidationException("角色添加失败，角色名:[" + t.getRoleName() + "]已存在");
		}
	}

	/**
	 * 更新角色
	 * 
	 * @param t
	 */
	public void update(SysRole t) {
		// 验证是否可用
		if (testRoleName(t)) {
			sysRoleDao.save(t);
		} else {
			throw new ValidationException("角色更新失败，角色名:[" + t.getRoleName() + "]已存在");
		}

	}

	/**
	 * 删除
	 * 
	 * @param t
	 */
	public void delete(SysRole t) {
		sysRoleDao.delete(t);
	}

	/**
	 * 根据id获取详情
	 * 
	 * @param id
	 * @return
	 */
	public SysRole getObject(String id) {
		return sysRoleDao.findOne(id);
	}

	/**
	 * 分页查询
	 * 
	 * @param t
	 * @param page
	 * @return
	 */
	public List<SysRole> queryForPage(SysRole t, PageInfo page) {
		return sysRoleDao.findAll(page).getContent();
	}

	/**
	 * 查询角色用户列表
	 * 
	 * @param role
	 * @param page
	 * @return
	 */
	public List<Map<String, Object>> queryRoleUser(SysRole role, PageInfo page) {
		String sql = "select u.user_id,u.name,u.user_name,u.email,r.role_name from mpi_sys_user u "
				+ " left join mpi_sys_role r on u.sys_role_id = r.sys_role_id where u.sys_role_id = ? ";
		// 总数查询sql
		String countSql = page.buildCountSql(sql);
		// 设置总数
		page.setTotal(jdbcTemplate.queryForObject(countSql, new Object[] { role.getSysRoleId() }, Integer.class));
		String querySql = page.buildPageSql(sql);
		return jdbcTemplate.queryForList(querySql, new Object[] { role.getSysRoleId() });
	}

	/**
	 * 检测角色名是否存在
	 * 
	 * @param role
	 * @return
	 */
	public boolean testRoleName(SysRole role) {
		if (StringUtils.isBlank(role.getRoleName())) {
			return false;
		} else {
			int count = 0;
			if (StringUtils.isNotBlank(role.getSysRoleId())) {
				count = sysRoleDao.countByRoleNameAndSysRoleIdNot(role.getRoleName(), role.getSysRoleId());
			} else {
				count = sysRoleDao.countByRoleName(role.getRoleName());
			}
			return count == 0;
		}
	}

	/**
	 * 获取所有系统角色
	 */
	public List<SysRole> findAll() {
		return sysRoleDao.findAll();
	}

}
