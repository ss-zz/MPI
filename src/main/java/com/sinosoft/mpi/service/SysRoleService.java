package com.sinosoft.mpi.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Service;

import com.sinosoft.mpi.dao.SysRoleDao;
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

	/**
	 * 保存
	 * 
	 * @param t
	 */
	public void save(SysRole t) {
		// 验证是否可用
		if (testRoleName(t)) {
			sysRoleDao.add(t);
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
			sysRoleDao.update(t);
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
		sysRoleDao.deleteById(t);
	}

	/**
	 * 根据id获取详情
	 * 
	 * @param id
	 * @return
	 */
	public SysRole getObject(String id) {
		SysRole role = new SysRole();
		role.setSysRoleId(id);
		role = sysRoleDao.findById(role);
		return role;
	}

	/**
	 * 分页查询
	 * 
	 * @param t
	 * @param page
	 * @return
	 */
	public List<SysRole> queryForPage(SysRole t, PageInfo page) {
		String sql = " select * from mpi_sys_role where 1=1 ";
		// 取得总条数sql
		String countSql = page.buildCountSql(sql);
		// 设置总条数
		page.setTotal(sysRoleDao.getCount(countSql, new Object[] {}));
		// 取得分页查询sql
		String querySql = page.buildPageSql(sql);
		return sysRoleDao.find(querySql, new Object[] {});
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
		page.setTotal(sysRoleDao.getCount(countSql, new Object[] { role.getSysRoleId() }));
		String querySql = page.buildPageSql(sql);
		return sysRoleDao.findForMap(querySql, new Object[] { role.getSysRoleId() });
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
			List<String> args = new ArrayList<String>(2);
			StringBuilder sql = new StringBuilder("select count(1) from mpi_sys_role where role_name = ? ");
			args.add(role.getRoleName());
			if (StringUtils.isNotBlank(role.getSysRoleId())) {
				sql.append(" and sys_role_id != ? ");
				args.add(role.getSysRoleId());
			}

			int count = sysRoleDao.getCount(sql.toString(), args.toArray());
			return count == 0;
		}
	}

}
