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

import com.sinosoft.mpi.model.MatchResult;
import com.sinosoft.mpi.util.IDUtil;

@Repository("matchResultDao")
public class MatchResultDao implements IMatchResultDao {
	@Resource
	private JdbcTemplate jdbcTemplate;

	@Override
	public void add(final MatchResult entity) {
		entity.setMatchResultId(IDUtil.getUUID());
		StringBuilder sql = new StringBuilder();
		sql.append(" insert into mpi_match_result (match_result_id, mpi_pk, field_pk, ");
		sql.append(" field_mat_degrees, match_degree) values (?, ?, ?, ?, ?) ");
		jdbcTemplate.update(sql.toString(), new PreparedStatementSetter() {
			public void setValues(PreparedStatement ps) throws SQLException {
				ps.setString(1, entity.getMatchResultId());
				ps.setString(2, entity.getMpiPk());
				ps.setString(3, entity.getFieldPk());
				ps.setString(4, entity.getFieldMatDegrees());
				ps.setString(5, entity.getMatchDegree());
			}
		});
	}

	@Override
	public void deleteById(final MatchResult entity) {
		String sql = " delete mpi_match_result where match_result_id = ? ";
		jdbcTemplate.update(sql, new PreparedStatementSetter() {
			public void setValues(PreparedStatement ps) throws SQLException {
				ps.setString(1, entity.getMatchResultId());
			}
		});
	}

	@Override
	public void update(final MatchResult entity) {
		if (entity == null || entity.getMatchResultId() == null) {
			return;
		}
		StringBuilder sql = new StringBuilder();
		sql.append(" update mpi_match_result set index_id = ?, mpi_person_id = ?,  ");
		sql.append(" field_mat_degrees = ?, match_degree = ? where match_result_id = ? ");
		jdbcTemplate.update(sql.toString(), new PreparedStatementSetter() {
			public void setValues(PreparedStatement ps) throws SQLException {
				ps.setString(1, entity.getMpiPk());
				ps.setString(2, entity.getFieldPk());
				ps.setString(3, entity.getFieldMatDegrees());
				ps.setString(4, entity.getMatchDegree());
				ps.setString(5, entity.getMatchResultId());
			}
		});

	}

	@Override
	public MatchResult findById(MatchResult entity) {
		String sql = " select * from mpi_match_result where match_result_id = ? ";
		List<MatchResult> datas = find(sql, new Object[] { entity.getMatchResultId() });
		MatchResult result = null;
		if (datas != null && !datas.isEmpty()) {
			result = datas.get(0);
		}
		return result;
	}

	@Override
	public List<MatchResult> find(String sql) {
		return find(sql, new Object[] {});
	}

	@Override
	public List<MatchResult> find(String sql, Object[] args) {
		List<MatchResult> datas = jdbcTemplate.query(sql, args, new RowMapper<MatchResult>() {
			@Override
			public MatchResult mapRow(ResultSet rs, int row) throws SQLException {
				MatchResult result = new MatchResult();
				result.setMatchResultId(rs.getString("MATCH_RESULT_ID"));
				result.setMpiPk(rs.getString("MPI_PK"));
				result.setFieldPk(rs.getString("FIELD_PK"));
				result.setMatchDegree(rs.getString("MATCH_DEGREE"));
				result.setFieldMatDegrees(rs.getString("FIELD_MAT_DEGREES"));
				return result;
			}
		});
		return datas;

	}

	@Override
	public List<MatchResult> findAll() {
		String sql = " select * from mpi_match_result where 1=1 ";
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

	@Override
	public List<Map<String, Object>> findForMap(String sql, Object[] args) {
		return jdbcTemplate.queryForList(sql, args);
	}

	@Override
	public List<Map<String, Object>> findForMap(String sql) {
		return jdbcTemplate.queryForList(sql);
	}

	public void setJdbcTemplate(JdbcTemplate jdbcTemplate) {
		this.jdbcTemplate = jdbcTemplate;
	}

	@Override
	public void deleteByPersonID(String personID) {
		String sql = " delete mpi_match_result where field_pk = ? ";
		jdbcTemplate.update(sql, new Object[] { personID });
	}

	@Override
	public MatchResult findByPersonAndIndex(String fieldPk, String mpiPk) {
		String sql = " select * from mpi_match_result where mpi_pk = ? and field_pk = ?";
		List<MatchResult> datas = find(sql, new Object[] { mpiPk, fieldPk });
		MatchResult result = null;
		if (datas != null && !datas.isEmpty()) {
			result = datas.get(0);
		}
		return result;
	}

	@Override
	public JdbcTemplate getJdbcTemplate() {
		return this.jdbcTemplate;
	}

}
