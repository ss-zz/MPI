package com.sinosoft.mpi.dao;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.jdbc.core.PreparedStatementSetter;
import org.springframework.stereotype.Repository;

import com.sinosoft.mpi.model.EduCode;

@Repository
public class EduCodeDao extends IBaseDao<EduCode> {

	@Override
	public void add(final EduCode entity) {
		StringBuilder sql = new StringBuilder();
		sql.append(" insert into mpi_edu_code(code_id,code_name) values(?,?) ");
		jdbcTemplate.update(sql.toString(), new PreparedStatementSetter() {
			public void setValues(PreparedStatement ps) throws SQLException {
				ps.setString(1, entity.getCodeId());
				ps.setString(2, entity.getCodeName());
			}
		});

	}

	@Override
	public void deleteById(final EduCode entity) {
		String sql = " delete from mpi_edu_code where code_id = ?";
		jdbcTemplate.update(sql, new PreparedStatementSetter() {
			public void setValues(PreparedStatement ps) throws SQLException {
				ps.setString(1, entity.getCodeId());
			}
		});

	}

	@Override
	public void update(final EduCode entity) {
		if (entity == null || entity.getCodeId() == null) {
			return;
		}
		StringBuilder sql = new StringBuilder();
		sql.append(" update mpi_edu_code set code_name = ? where code_id = ? ");
		jdbcTemplate.update(sql.toString(), new PreparedStatementSetter() {
			public void setValues(PreparedStatement ps) throws SQLException {
				ps.setString(1, entity.getCodeName());
				ps.setString(2, entity.getCodeId());
			}
		});
	}

	@Override
	public EduCode findById(EduCode entity) {
		String sql = " select code_id,code_name from mpi_edu_code where code_id = ? ";
		List<EduCode> datas = find(sql, new Object[] { entity.getCodeId() });
		EduCode result = null;
		if (datas != null && !datas.isEmpty()) {
			result = datas.get(0);
		}
		return result;
	}

	@Override
	public List<EduCode> find(String sql) {
		List<EduCode> datas = find(sql, new Object[] {});
		return datas;
	}

	@Override
	public List<EduCode> find(String sql, Object[] args) {
		return new ArrayList<EduCode>();
	}

	@Override
	public List<EduCode> findAll() {
		String sql = " select * from mpi_edu_code where 1=1 ";
		List<EduCode> datas = find(sql);
		return datas;
	}

}
