package com.sinosoft.mpi.dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementSetter;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import com.sinosoft.mpi.model.ProfessionCode;

@Repository("professionCodeDao")
public class ProfessionCodeDao implements IProfessionCodeDao {

	@Resource
	private JdbcTemplate jdbcTemplate;

	@Override
	public void add(final ProfessionCode entity) {
		StringBuilder sql = new StringBuilder();
		sql.append("insert into mpi_profession_code(code_id,code_name) values(?,?)");
		jdbcTemplate.update(sql.toString(), new PreparedStatementSetter() {
			public void setValues(PreparedStatement ps) throws SQLException {
				ps.setString(1, entity.getCodeId());
				ps.setString(2, entity.getCodeName());
			}
		});

	}

	@Override
	public void deleteById(final ProfessionCode entity) {
		String sql = " delete from mpi_profession_code where code_id = ?";
		jdbcTemplate.update(sql, new PreparedStatementSetter() {
			public void setValues(PreparedStatement ps) throws SQLException {
				ps.setString(1, entity.getCodeId());
			}
		});

	}

	@Override
	public void update(final ProfessionCode entity) {
		if (entity == null || entity.getCodeId() == null) {
			return;
		}
		StringBuilder sql = new StringBuilder();
		sql.append(" update mpi_profession_code set code_name = ? where code_id = ? ");
		jdbcTemplate.update(sql.toString(), new PreparedStatementSetter() {
			public void setValues(PreparedStatement ps) throws SQLException {
				ps.setString(1, entity.getCodeName());
				ps.setString(2, entity.getCodeId());
			}
		});
	}

	@Override
	public ProfessionCode findById(ProfessionCode entity) {
		String sql = " select code_id,code_name from mpi_profession_code where code_id = ? ";
		List<ProfessionCode> datas = find(sql, new Object[] { entity.getCodeId() });
		ProfessionCode result = null;
		if (datas != null && !datas.isEmpty()) {
			result = datas.get(0);
		}
		return result;
	}

	@Override
	public List<ProfessionCode> find(String sql) {
		List<ProfessionCode> datas = find(sql, new Object[] {});
		return datas;
	}

	@Override
	public List<ProfessionCode> find(String sql, Object[] args) {
		/*
		 * List<ProfessionCode> datas = jdbcTemplate.query(sql, args, new
		 * RowMapper<ProfessionCode>() {
		 * 
		 * @Override public ProfessionCode mapRow(ResultSet rs, int row) throws
		 * SQLException { ProfessionCode result = new ProfessionCode();
		 * result.setCodeId(rs.getString("CODE_ID"));
		 * result.setCodeName(rs.getString("CODE_NAME")); return result; } }); return
		 * datas;
		 */
		// TODO lpk 2012年11月18日11:29:10
		return new ArrayList();

	}

	@Override
	public List<ProfessionCode> findAll() {
		String sql = " select code_id,code_name from mpi_profession_code where 1=1 ";
		List<ProfessionCode> datas = find(sql, new Object[] {});
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
