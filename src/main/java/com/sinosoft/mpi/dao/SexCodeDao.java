package com.sinosoft.mpi.dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import org.springframework.jdbc.core.PreparedStatementSetter;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import com.sinosoft.mpi.model.SexCode;

@Repository
public class SexCodeDao extends IBaseDao<SexCode> {

	@Override
	public void add(final SexCode entity) {
		StringBuilder sql = new StringBuilder();
		sql.append(" insert into mpi_sex_code(code_id,code_name) values(?,?) ");
		jdbcTemplate.update(sql.toString(), new PreparedStatementSetter() {
			public void setValues(PreparedStatement ps) throws SQLException {
				ps.setString(1, entity.getCodeId());
				ps.setString(2, entity.getCodeName());
			}
		});

	}

	@Override
	public void deleteById(final SexCode entity) {
		String sql = " delete from mpi_sex_code where code_id = ?";
		jdbcTemplate.update(sql, new PreparedStatementSetter() {
			public void setValues(PreparedStatement ps) throws SQLException {
				ps.setString(1, entity.getCodeId());
			}
		});

	}

	@Override
	public void update(final SexCode entity) {
		if (entity == null || entity.getCodeId() == null) {
			return;
		}
		StringBuilder sql = new StringBuilder();
		sql.append(" update mpi_sex_code set code_name = ? where code_id = ? ");
		jdbcTemplate.update(sql.toString(), new PreparedStatementSetter() {
			public void setValues(PreparedStatement ps) throws SQLException {
				ps.setString(1, entity.getCodeName());
				ps.setString(2, entity.getCodeId());
			}
		});
	}

	@Override
	public SexCode findById(SexCode entity) {
		String sql = " select code_id,code_name from mpi_sex_code where code_id = ? ";
		List<SexCode> datas = find(sql, new Object[] { entity.getCodeId() });
		SexCode result = null;
		if (datas != null && !datas.isEmpty()) {
			result = datas.get(0);
		}
		return result;
	}

	@Override
	public List<SexCode> find(String sql) {
		return find(sql, new Object[] {});
	}

	public List<SexCode> find(String sql, Object[] args) {
		List<SexCode> datas = jdbcTemplate.query(sql, args, new RowMapper<SexCode>() {
			@Override
			public SexCode mapRow(ResultSet rs, int row) throws SQLException {
				SexCode result = new SexCode();
				result.setCodeId(rs.getString("DIC_ID"));
				result.setCodeName(rs.getString("DIC_NAME"));
				return result;
			}
		});
		return datas;
	}

	@Override
	public List<SexCode> findAll() {
		String sql = "select  t.dic_id ,t.dic_name  from ehr_dic t where t.dic_type_id='GBT226112003'";
		return find(sql);
	}

}
