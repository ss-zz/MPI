package com.sinosoft.mpi.dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import org.springframework.jdbc.core.PreparedStatementSetter;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import com.sinosoft.mpi.model.SysRole;
import com.sinosoft.mpi.util.IDUtil;

@Repository
public class SysRoleDao extends IBaseDao<SysRole> {

	@Override
	public void add(final SysRole entity) {
		entity.setSysRoleId(IDUtil.getUUID());
		String sql = "insert into mpi_sys_role(sys_role_id,role_name) values(?,?)";
		jdbcTemplate.update(sql, new PreparedStatementSetter() {
			public void setValues(PreparedStatement ps) throws SQLException {
				ps.setString(1, entity.getSysRoleId());
				ps.setString(2, entity.getRoleName());
			}
		});

	}

	@Override
	public void deleteById(final SysRole entity) {
		String sql = " delete from mpi_sys_role where sys_role_id = ?";
		jdbcTemplate.update(sql, new PreparedStatementSetter() {
			public void setValues(PreparedStatement ps) throws SQLException {
				ps.setString(1, entity.getSysRoleId());
			}
		});

	}

	@Override
	public void update(final SysRole entity) {
		if (entity == null || entity.getSysRoleId() == null) {
			return;
		}
		StringBuilder sql = new StringBuilder();
		sql.append(" update mpi_sys_role set role_name = ? where sys_role_id = ? ");
		jdbcTemplate.update(sql.toString(), new PreparedStatementSetter() {
			public void setValues(PreparedStatement ps) throws SQLException {
				ps.setString(1, entity.getRoleName());
				ps.setString(2, entity.getSysRoleId());
			}
		});
	}

	@Override
	public SysRole findById(SysRole entity) {
		String sql = " select sys_role_id,role_name from mpi_sys_role where sys_role_id = ? ";
		List<SysRole> datas = find(sql, new Object[] { entity.getSysRoleId() });
		SysRole result = null;
		if (datas != null && !datas.isEmpty()) {
			result = datas.get(0);
		}
		return result;
	}

	@Override
	public List<SysRole> find(String sql) {
		List<SysRole> datas = find(sql, new Object[] {});
		return datas;
	}

	@Override
	public List<SysRole> find(String sql, Object[] args) {
		List<SysRole> datas = jdbcTemplate.query(sql, args, new RowMapper<SysRole>() {
			@Override
			public SysRole mapRow(ResultSet rs, int row) throws SQLException {
				SysRole result = new SysRole();
				result.setSysRoleId(rs.getString("SYS_ROLE_ID"));
				result.setRoleName(rs.getString("ROLE_NAME"));
				return result;
			}
		});
		return datas;
	}

	@Override
	public List<SysRole> findAll() {
		String sql = " select sys_role_id,role_name from mpi_sys_role where 1=1 ";
		List<SysRole> datas = find(sql);
		return datas;
	}

}
