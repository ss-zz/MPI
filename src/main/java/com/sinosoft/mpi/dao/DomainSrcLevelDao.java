package com.sinosoft.mpi.dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementSetter;
import org.springframework.jdbc.core.RowCallbackHandler;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import com.sinosoft.mpi.model.DomainSrcLevel;
import com.sinosoft.mpi.util.IDUtil;

@Repository("domainSrcLevelDao")
public class DomainSrcLevelDao implements IDomainSrcLevelDao {
	@Resource
	private JdbcTemplate jdbcTemplate;

	@Override
	public List<DomainSrcLevel> find(String sql) {
		return find(sql, new Object[] {});
	}

	@Override
	public List<Map<String, Object>> findForMap(String sql, Object[] args) {
		return null;
	}

	@Override
	public List<Map<String, Object>> findForMap(String sql) {
		final List<Map<String, Object>> result = new ArrayList<Map<String, Object>>();
		jdbcTemplate.query(sql, new RowCallbackHandler() {
			@Override
			public void processRow(ResultSet rs) throws SQLException {
				Map<String, Object> row = new HashMap<String, Object>();
				row.put("DOMAIN_ID", rs.getString("DOMAIN_ID"));
				// row.put("DOMAIN_DESC", rs.getString("DOMAIN_DESC"));
				// row.put("DOMAIN_LEVEL", rs.getString("DOMAIN_LEVEL"));
				row.put("FIELD_NAME", rs.getString("FIELD_NAME"));
				row.put("FIELD_LEVEL", rs.getString("FIELD_LEVEL"));
				// row.put("FIELD_DESC", rs.getString("FIELD_DESC"));
				result.add(row);
			}
		});
		return result;

	}

	@Override
	public JdbcTemplate getJdbcTemplate() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void effect(String id) {
		// TODO Auto-generated method stub

	}

	@Override
	public List<DomainSrcLevel> findAll() {
		// TODO Auto-generated method stub
		String sql = " select * from MPI_DOMAIN_SRCLEVEL where 1=1 ";
		List<DomainSrcLevel> datas = find(sql);
		return datas;
	}

	@Override
	public List<DomainSrcLevel> findByID(String domainid) {
		// TODO Auto-generated method stub
		String sql = " select * from MPI_DOMAIN_SRCLEVEL where DOMAIN_ID='" + domainid + "'";
		List<DomainSrcLevel> datas = find(sql);
		return datas;
	}

	@Override
	public List<DomainSrcLevel> find(String sql, Object[] args) {
		List<DomainSrcLevel> datas = jdbcTemplate.query(sql, args, new RowMapper<DomainSrcLevel>() {
			@Override
			public DomainSrcLevel mapRow(ResultSet rs, int rowNum) throws SQLException {
				DomainSrcLevel result = new DomainSrcLevel();
				result.setID(rs.getString("ID"));
				result.setDOMAIN_ID(rs.getString("DOMAIN_ID"));
				result.setDOMAIN_DESC(rs.getString("DOMAIN_DESC"));
				result.setDOMAIN_LEVEL(rs.getString("DOMAIN_LEVEL"));
				result.setFIELD_NAME(rs.getString("FIELD_NAME"));
				result.setFIELD_LEVEL(rs.getString("FIELD_LEVEL"));
				result.setFIELD_DESC(rs.getString("FIELD_DESC"));
				result.setUPDATE_DATE(rs.getString("UPDATE_DATE"));
				result.setCREATE_DATE(rs.getString("CREATE_DATE"));
				return result;
			}

		});
		return datas;
	}

	@Override
	public void add(final DomainSrcLevel entity) {
		entity.setID(IDUtil.getUUID());
		StringBuilder sql = new StringBuilder();
		sql.append(" insert into MPI_DOMAIN_SRCLEVEL ( DOMAIN_ID, DOMAIN_DESC, DOMAIN_LEVEL, ")
				.append(" FIELD_NAME, FIELD_DESC, FIELD_LEVEL,ID, CREATE_DATE, UPDATE_DATE) values ")
				.append(" (?, ?, ?, ?, ?, ?,?,sysdate,sysdate) ");
		jdbcTemplate.update(sql.toString(), new PreparedStatementSetter() {
			public void setValues(PreparedStatement ps) throws SQLException {

				ps.setString(1, entity.getDOMAIN_ID());
				ps.setString(2, entity.getDOMAIN_DESC());
				ps.setString(3, entity.getDOMAIN_LEVEL());
				ps.setString(4, entity.getFIELD_NAME());
				ps.setString(5, entity.getFIELD_DESC());
				ps.setString(6, entity.getFIELD_LEVEL());
				ps.setString(7, entity.getID());
			}
		});

	}

	@Override
	public void deleteById(final DomainSrcLevel entity) {
		String sql = " delete from MPI_DOMAIN_SRCLEVEL where ID = ? ";
		jdbcTemplate.update(sql, new PreparedStatementSetter() {
			public void setValues(PreparedStatement ps) throws SQLException {
				ps.setString(1, entity.getID());
			}
		});

	}

	@Override
	public void update(final DomainSrcLevel entity) {
		if (entity == null) {
			return;
		}
		StringBuilder sql = new StringBuilder();
		sql.append(" update MPI_DOMAIN_SRCLEVEL set  ");
		sql.append(
				" DOMAIN_ID= ?, DOMAIN_DESC = ?,DOMAIN_LEVEL = ?,FIELD_NAME=?,FIELD_DESC=?,FIELD_LEVEL=?, UPDATE_DATE=sysdate");
		sql.append("  where ID= ?   ");
		jdbcTemplate.update(sql.toString(), new PreparedStatementSetter() {
			public void setValues(PreparedStatement ps) throws SQLException {
				ps.setString(1, entity.getDOMAIN_ID());
				ps.setString(2, entity.getDOMAIN_DESC());
				ps.setString(3, entity.getDOMAIN_LEVEL());
				ps.setString(4, entity.getFIELD_NAME());
				ps.setString(5, entity.getFIELD_DESC());
				ps.setString(6, entity.getFIELD_LEVEL());
				ps.setString(7, entity.getID());
			}
		});

	}

	@Override
	public void updateByDomainID(final DomainSrcLevel entity) {
		if (entity == null) {
			return;
		}
		StringBuilder sql = new StringBuilder();
		sql.append(" update MPI_DOMAIN_SRCLEVEL set  ");
		sql.append(" DOMAIN_LEVEL = ?,FIELD_DESC=?,FIELD_LEVEL=?, UPDATE_DATE=sysdate");
		sql.append("  where DOMAIN_ID= ? and FIELD_NAME=?  ");
		jdbcTemplate.update(sql.toString(), new PreparedStatementSetter() {
			public void setValues(PreparedStatement ps) throws SQLException {
				ps.setString(1, entity.getDOMAIN_LEVEL());
				ps.setString(2, entity.getFIELD_DESC());
				ps.setString(3, entity.getFIELD_LEVEL());
				ps.setString(4, entity.getDOMAIN_ID());
				ps.setString(5, entity.getFIELD_NAME());
			}
		});

	}

	@Override
	public DomainSrcLevel findById(DomainSrcLevel entity) {
		String sql = " select * from MPI_DOMAIN_SRCLEVEL where ID = ? ";
		List<DomainSrcLevel> datas = find(sql, new Object[] { entity.getID() });
		DomainSrcLevel result = null;
		if (datas != null && !datas.isEmpty()) {
			result = datas.get(0);
		}
		return result;
	}

	@Override
	public int getCount(String sql) {
		return getCount(sql, new Object[] {});
	}

	@Override
	public int getCount(String sql, Object[] args) {
		return jdbcTemplate.queryForObject(sql, args, Integer.class);
	}

}
