package com.sinosoft.mpi.dao;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementSetter;
import org.springframework.stereotype.Repository;

import com.sinosoft.mpi.model.CertCode;

/**
 * 证书类型操作
 */
@Repository("certCodeDao")
public class CertCodeDao implements ICertCodeDao {

	@Resource
	private JdbcTemplate jdbcTemplate;

	@Override
	public void add(final CertCode entity) {
		StringBuilder sql = new StringBuilder();
		sql.append(" insert into mpi_cert_code(code_id,code_name) values(?,?) ");
		jdbcTemplate.update(sql.toString(), new PreparedStatementSetter() {
			public void setValues(PreparedStatement ps) throws SQLException {
				ps.setString(1, entity.getCodeId());
				ps.setString(2, entity.getCodeName());
			}
		});
	}

	@Override
	public void deleteById(final CertCode entity) {
		String sql = " delete from mpi_cert_code where code_id = ?";
		jdbcTemplate.update(sql, new PreparedStatementSetter() {
			public void setValues(PreparedStatement ps) throws SQLException {
				ps.setString(1, entity.getCodeId());
			}
		});

	}

	@Override
	public void update(final CertCode entity) {
		if (entity == null || entity.getCodeId() == null) {
			return;
		}
		StringBuilder sql = new StringBuilder();
		sql.append(" update mpi_cert_code set code_name = ? where code_id = ? ");
		jdbcTemplate.update(sql.toString(), new PreparedStatementSetter() {
			public void setValues(PreparedStatement ps) throws SQLException {
				ps.setString(1, entity.getCodeName());
				ps.setString(2, entity.getCodeId());
			}
		});
	}

	@Override
	public CertCode findById(CertCode entity) {
		String sql = " select code_id,code_name from mpi_cert_code where code_id = ? ";
		List<CertCode> datas = find(sql, new Object[] { entity.getCodeId() });
		CertCode result = null;
		if (datas != null && !datas.isEmpty()) {
			result = datas.get(0);
		}
		return result;
	}

	@Override
	public List<CertCode> find(String sql) {
		return find(sql, new Object[] {});
	}

	public List<CertCode> find(String sql, Object[] args) {
		/*
		 * List<CertCode> datas = jdbcTemplate.query(sql, args, new
		 * RowMapper<CertCode>() {
		 * 
		 * @Override public CertCode mapRow(ResultSet rs, int row) throws SQLException {
		 * CertCode result = new CertCode(); result.setCodeId(rs.getString("CODE_ID"));
		 * result.setCodeName(rs.getString("CODE_NAME")); return result; } }); return
		 * datas;
		 */
		return new ArrayList<CertCode>();
	}

	@Override
	public List<CertCode> findAll() {
		String sql = " select * from mpi_cert_code where 1=1 ";
		return find(sql);
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
