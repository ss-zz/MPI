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

import com.sinosoft.mpi.model.IndexOperate;
import com.sinosoft.mpi.util.IDUtil;

@Repository("IndexOperateDao")
public class IndexOperateDao implements IIndexOperateDao {

	@Resource
	private JdbcTemplate jdbcTemplate;

	@Override
	public void add(final IndexOperate entity) {

		entity.setPersonIdxLogId(IDUtil.getUUID());
		StringBuilder sql = new StringBuilder();
		sql.append(" insert into mpi_index_operate (person_idx_log_id, op_type, op_style, op_time,  ");
		sql.append(
				" op_user_id, op_desc,info_sour,mpi_pk, field_pk, former_mpi_pk) values (?, ?, ?, ?, ?, ?, ?, ?,?,?) ");
		jdbcTemplate.update(sql.toString(), new PreparedStatementSetter() {
			public void setValues(PreparedStatement ps) throws SQLException {
				ps.setString(1, entity.getPersonIdxLogId());
				ps.setString(2, entity.getOpType());
				ps.setString(3, entity.getOpStyle());
				ps.setString(4, entity.getOpTime());
				ps.setString(5, entity.getOpUserId());
				ps.setString(6, entity.getOpDesc());
				ps.setString(7, entity.getInfoSour());
				ps.setString(8, entity.getMpipk());
				ps.setString(9, entity.getFieldpk());
				ps.setString(10, entity.getFormermpipk());
			}
		});
	}

	@Override
	public void deleteById(final IndexOperate entity) {
		String sql = " delete mpi_index_operate where person_idx_log_id = ? ";
		jdbcTemplate.update(sql, new PreparedStatementSetter() {
			public void setValues(PreparedStatement ps) throws SQLException {
				ps.setString(1, entity.getPersonIdxLogId());
			}
		});

	}

	@Override
	public void update(final IndexOperate entity) {
		if (entity == null || entity.getPersonIdxLogId() == null) {
			return;
		}
		StringBuilder sql = new StringBuilder();
		sql.append(" update mpi_index_operate set op_type=?,op_style=?,op_time=?,op_user_id=?, ");
		sql.append(" op_desc=?,info_sour=?,mpi_pk=?,field_pk=? where person_idx_log_id = ? ");
		jdbcTemplate.update(sql.toString(), new PreparedStatementSetter() {
			public void setValues(PreparedStatement ps) throws SQLException {
				ps.setString(1, entity.getOpType());
				ps.setString(2, entity.getOpStyle());
				ps.setString(3, entity.getOpTime());
				ps.setString(4, entity.getOpUserId());
				ps.setString(5, entity.getOpDesc());
				ps.setString(6, entity.getInfoSour());
				ps.setString(7, entity.getMpipk());
				ps.setString(8, entity.getFieldpk());
				ps.setString(9, entity.getPersonIdxLogId());
			}
		});
	}

	@Override
	public IndexOperate findById(IndexOperate entity) {
		String sql = " select * from mpi_index_operate where person_idx_log_id = ? ";
		List<IndexOperate> datas = find(sql, new Object[] { entity.getPersonIdxLogId() });
		IndexOperate result = null;
		if (datas != null && !datas.isEmpty()) {
			result = datas.get(0);
		}
		return result;
	}

	@Override
	public List<IndexOperate> find(String sql) {
		return find(sql, new Object[] {});
	}

	@Override
	public List<IndexOperate> find(String sql, Object[] args) {
		List<IndexOperate> datas = jdbcTemplate.query(sql, args, new RowMapper<IndexOperate>() {
			@Override
			public IndexOperate mapRow(ResultSet rs, int row) throws SQLException {
				IndexOperate result = new IndexOperate();
				result.setPersonIdxLogId(rs.getString("PERSON_IDX_LOG_ID"));
				result.setOpType(rs.getString("OP_TYPE"));
				result.setOpStyle(rs.getString("OP_STYLE"));
				result.setOpTime(rs.getString("OP_TIME"));
				result.setOpUserId(rs.getString("OP_USER_ID"));
				result.setOpDesc(rs.getString("OP_DESC"));
				result.setInfoSour(rs.getString("INFO_SOUR"));
				result.setMpipk(rs.getString("MPI_PK"));
				result.setFieldpk(rs.getString("FIELD_PK"));
				return result;
			}
		});
		return datas;
	}

	@Override
	public List<IndexOperate> findAll() {
		String sql = " select * from mpi_index_operate where 1=1 ";
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
	public JdbcTemplate getJdbcTemplate() {
		return this.jdbcTemplate;
	}
}
