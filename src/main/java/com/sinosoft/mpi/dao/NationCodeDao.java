package com.sinosoft.mpi.dao;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.jdbc.core.PreparedStatementSetter;
import org.springframework.stereotype.Repository;

import com.sinosoft.mpi.model.NationCode;

@Repository
public class NationCodeDao extends IBaseDao<NationCode> {

	@Override
	public void add(final NationCode entity) {
		StringBuilder sql = new StringBuilder();
		sql.append("insert into mpi_nation_code(code_id,code_name) values(?,?)");
		jdbcTemplate.update(sql.toString(), new PreparedStatementSetter() {
			public void setValues(PreparedStatement ps) throws SQLException {
				ps.setString(1, entity.getCodeId());
				ps.setString(2, entity.getCodeName());
			}
		});

	}

	@Override
	public void deleteById(final NationCode entity) {
		String sql = " delete from mpi_nation_code where code_id = ?";
		jdbcTemplate.update(sql, new PreparedStatementSetter() {
			public void setValues(PreparedStatement ps) throws SQLException {
				ps.setString(1, entity.getCodeId());
			}
		});

	}

	@Override
	public void update(final NationCode entity) {
		if (entity == null || entity.getCodeId() == null) {
			return;
		}
		StringBuilder sql = new StringBuilder();
		sql.append(" update mpi_nation_code set code_name = ? where code_id = ? ");
		jdbcTemplate.update(sql.toString(), new PreparedStatementSetter() {
			public void setValues(PreparedStatement ps) throws SQLException {
				ps.setString(1, entity.getCodeName());
				ps.setString(2, entity.getCodeId());
			}
		});
	}

	@Override
	public NationCode findById(NationCode entity) {
		String sql = " select code_id,code_name from mpi_nation_code where code_id = ? ";
		List<NationCode> datas = find(sql, new Object[] { entity.getCodeId() });
		NationCode result = null;
		if (datas != null && !datas.isEmpty()) {
			result = datas.get(0);
		}
		return result;
	}

	@Override
	public List<NationCode> find(String sql) {
		List<NationCode> datas = find(sql, new Object[] {});
		return datas;
	}

	@Override
	public List<NationCode> find(String sql, Object[] args) {
		return new ArrayList<NationCode>();

	}

	@Override
	public List<NationCode> findAll() {
		String sql = " select code_id,code_name from mpi_nation_code where 1=1";
		List<NationCode> datas = find(sql);
		return datas;
	}

}
