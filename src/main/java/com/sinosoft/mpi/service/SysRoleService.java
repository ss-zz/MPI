package com.sinosoft.mpi.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Service;

import com.sinosoft.mpi.dao.ISysRoleDao;
import com.sinosoft.mpi.exception.ValidationException;
import com.sinosoft.mpi.model.SysRole;
import com.sinosoft.mpi.util.PageInfo;

@Service("sysRoleService")
public class SysRoleService implements ISysRoleService {
	private Logger logger = Logger.getLogger(SysRoleService.class);

	@Resource
	private ISysRoleDao sysRoleDao;

	@Override
	public void save(SysRole t) {
		// 验证是否可用
		if (testRoleName(t)) {
			sysRoleDao.add(t);
			logger.debug("Add SysRole:" + t);
		} else {
			logger.debug("添加系统角色时验证失败,角色名:[" + t.getRoleName() + "]已存在!");
			throw new ValidationException("添加系统角色时验证失败,角色名:[" + t.getRoleName() + "]已存在!");
		}
	}

	@Override
	public void update(SysRole t) {
		// 验证是否可用
		if (testRoleName(t)) {
			sysRoleDao.update(t);
			logger.debug("Edit SysRole:" + t);
		} else {
			throw new ValidationException("修改系统角色时验证失败,角色名:[" + t.getRoleName() + "]已存在!");
		}

	}

	@Override
	public void delete(SysRole t) {
		sysRoleDao.deleteById(t);
		logger.debug("Del SysRole:sysRoleId=" + t.getSysRoleId());
	}

	@Override
	public SysRole getObject(String id) {
		SysRole role = new SysRole();
		role.setSysRoleId(id);
		role = sysRoleDao.findById(role);
		logger.debug("Load SysRole:sysRoleId=" + id + ",result=" + role);
		return role;
	}

	@Override
	public List<SysRole> queryForPage(SysRole t, PageInfo page) {
		// XXX ben 实际应用的时候这里需要添加查询条件
		String sql = " select * from mpi_sys_role where 1=1 ";
		// 取得总条数sql
		String countSql = page.buildCountSql(sql);
		logger.debug("Execute sql:[" + countSql + "],params[]");
		// 设置总条数
		page.setTotal(sysRoleDao.getCount(countSql, new Object[] {}));
		// 取得分页查询sql
		String querySql = page.buildPageSql(sql);
		logger.debug("Execute sql:[" + querySql + "],params[]");
		return sysRoleDao.find(querySql, new Object[] {});
	}

	@Override
	public List<Map<String, Object>> queryRoleUser(SysRole role, PageInfo page) {
		String sql = "select u.user_id,u.name,u.user_name,u.email,r.role_name from mpi_sys_user u "
				+ " left join mpi_sys_role r on u.sys_role_id = r.sys_role_id where u.sys_role_id = ? ";
		// 总数查询sql
		String countSql = page.buildCountSql(sql);
		logger.debug("Execute sql:[" + countSql + "],params[" + role.getSysRoleId() + "]");
		// 设置总数
		page.setTotal(sysRoleDao.getCount(countSql, new Object[] { role.getSysRoleId() }));
		String querySql = page.buildPageSql(sql);
		logger.debug("Execute sql:[" + querySql + "],params[" + role.getSysRoleId() + "]");
		return sysRoleDao.findForMap(querySql, new Object[] { role.getSysRoleId() });
	}

	@Override
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

	public ISysRoleDao getSysRoleDao() {
		return sysRoleDao;
	}

	public void setSysRoleDao(ISysRoleDao sysRoleDao) {
		this.sysRoleDao = sysRoleDao;
	}

}
