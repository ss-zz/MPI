package com.sinosoft.mpi.dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementSetter;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import com.sinosoft.mpi.model.SysUser;
import com.sinosoft.mpi.util.IDUtil;

@Repository("sysUserDao")
public class SysUserDao implements ISysUserDao {

	@Resource
	private JdbcTemplate jdbcTemplate;

	@Override
	public void add(final SysUser entity) {
		entity.setUserId(IDUtil.getUUID());
		StringBuilder sql = new StringBuilder();
		sql.append(" insert into mpi_sys_user(").append(" user_id,sys_role_id,user_name,password,name,email, ")
				.append(" state,create_user_id,create_date,update_user_id,update_date ) ")
				.append(" values(?,?,?,?,?,?,?,?,sysdate,?,sysdate) ");
		jdbcTemplate.update(sql.toString(), new PreparedStatementSetter() {
			public void setValues(PreparedStatement ps) throws SQLException {
				ps.setString(1, entity.getUserId());
				ps.setString(2, entity.getSysRoleId());
				ps.setString(3, entity.getUserName());
				ps.setString(4, entity.getPassword());
				ps.setString(5, entity.getName());
				ps.setString(6, entity.getEmail());
				ps.setString(7, entity.getState());
				ps.setString(8, entity.getCreateUserId());
				ps.setString(9, entity.getUpdateUserId());
			}
		});

	}

	@Override
	public void deleteById(final SysUser entity) {
		String sql = " delete from mpi_sys_user where user_id = ?";
		jdbcTemplate.update(sql, new PreparedStatementSetter() {
			public void setValues(PreparedStatement ps) throws SQLException {
				ps.setString(1, entity.getUserId());
			}
		});

	}

	@Override
	public void update(final SysUser entity) {
		if (entity == null || entity.getUserId() == null) {
			return;
		}
		StringBuilder sql = new StringBuilder();
		sql.append(" update mpi_sys_user set ").append(" sys_role_id=?,user_name=?,password=?,name=?,email=?, ")
				.append(" state=?,update_user_id=?,update_date=sysdate ").append(" where user_id = ? ");
		jdbcTemplate.update(sql.toString(), new PreparedStatementSetter() {
			public void setValues(PreparedStatement ps) throws SQLException {
				ps.setString(1, entity.getSysRoleId());
				ps.setString(2, entity.getUserName());
				ps.setString(3, entity.getPassword());
				ps.setString(4, entity.getName());
				ps.setString(5, entity.getEmail());
				ps.setString(6, entity.getState());
				ps.setString(7, entity.getUpdateUserId());
				ps.setString(8, entity.getUserId());
			}
		});
	}

	@Override
	public SysUser findById(SysUser entity) {
		String sql = " select * from mpi_sys_user where user_id = ? ";
		List<SysUser> datas = find(sql, new Object[] { entity.getUserId() });
		SysUser result = null;
		if (datas != null && !datas.isEmpty()) {
			result = datas.get(0);
		}
		return result;
	}

	@Override
	public List<SysUser> find(String sql) {
		List<SysUser> datas = find(sql, new Object[] {});
		return datas;
	}

	@Override
	public List<SysUser> find(String sql, Object[] args) {
		List<SysUser> datas = jdbcTemplate.query(sql, args, new RowMapper<SysUser>() {
			@Override
			public SysUser mapRow(ResultSet rs, int row) throws SQLException {
				SysUser result = new SysUser();
				result.setUserId(rs.getString("USER_ID"));
				result.setSysRoleId(rs.getString("SYS_ROLE_ID"));
				result.setUserName(rs.getString("USER_NAME"));
				result.setPassword(rs.getString("PASSWORD"));
				result.setName(rs.getString("NAME"));
				result.setEmail(rs.getString("EMAIL"));
				result.setState(rs.getString("STATE"));
				result.setCreateUserId(rs.getString("CREATE_USER_ID"));
				result.setCreateDate(rs.getTimestamp("CREATE_DATE"));
				result.setUpdateUserId(rs.getString("UPDATE_USER_ID"));
				result.setUpdateDate(rs.getTimestamp("UPDATE_DATE"));
				return result;
			}
		});
		return datas;
	}

	@Override
	public List<SysUser> findAll() {
		String sql = " select * from mpi_sys_user where 1=1 ";
		List<SysUser> datas = find(sql);
		return datas;
	}

	@Override
	public int getCount(String sql) {
		return getCount(sql, new Object[] {});
	}

	@Override
	public int getCount(String sql, Object[] args) {
		return jdbcTemplate.queryForObject(sql, args, Integer.class);
	}

	public JdbcTemplate getJdbcTemplate() {
		return jdbcTemplate;
	}

	public void setJdbcTemplate(JdbcTemplate jdbcTemplate) {
		this.jdbcTemplate = jdbcTemplate;
	}

	@Override
	public List<Map<String, Object>> findForMap(String sql, Object[] args) {
		return jdbcTemplate.queryForList(sql, args);
	}

	@Override
	public List<Map<String, Object>> findForMap(String sql) {
		return jdbcTemplate.queryForList(sql);
	}
}
