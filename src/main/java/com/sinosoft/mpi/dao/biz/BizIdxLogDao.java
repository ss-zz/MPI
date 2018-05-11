package com.sinosoft.mpi.dao.biz;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import org.springframework.jdbc.core.PreparedStatementSetter;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import com.sinosoft.mpi.dao.IBaseDao;
import com.sinosoft.mpi.model.biz.BizIdxLog;
import com.sinosoft.mpi.util.IDUtil;

@Repository
public class BizIdxLogDao extends IBaseDao<BizIdxLog> {

	@Override
	public void add(final BizIdxLog entity) {
		entity.setPersonIdxLogId(IDUtil.getUUID());
		StringBuilder sql = new StringBuilder();
		sql.append(" insert into mpi_person_idx_log (person_idx_log_id, op_type, op_style, op_time,  ");
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
	public void deleteById(final BizIdxLog entity) {
		String sql = " delete mpi_person_idx_log where person_idx_log_id = ? ";
		jdbcTemplate.update(sql, new PreparedStatementSetter() {
			public void setValues(PreparedStatement ps) throws SQLException {
				ps.setString(1, entity.getPersonIdxLogId());
			}
		});

	}

	@Override
	public void update(final BizIdxLog entity) {
		if (entity == null || entity.getPersonIdxLogId() == null) {
			return;
		}
		StringBuilder sql = new StringBuilder();
		sql.append(" update mpi_person_idx_log set op_type=?,op_style=?,op_time=?,op_user_id=?, ");
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
	public BizIdxLog findById(BizIdxLog entity) {
		String sql = " select * from mpi_person_idx_log where person_idx_log_id = ? ";
		List<BizIdxLog> datas = find(sql, new Object[] { entity.getPersonIdxLogId() });
		BizIdxLog result = null;
		if (datas != null && !datas.isEmpty()) {
			result = datas.get(0);
		}
		return result;
	}

	@Override
	public List<BizIdxLog> find(String sql) {
		return find(sql, new Object[] {});
	}

	@Override
	public List<BizIdxLog> find(String sql, Object[] args) {
		List<BizIdxLog> datas = jdbcTemplate.query(sql, args, new RowMapper<BizIdxLog>() {
			@Override
			public BizIdxLog mapRow(ResultSet rs, int row) throws SQLException {
				BizIdxLog result = new BizIdxLog();
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
	public List<BizIdxLog> findAll() {
		String sql = " select * from mpi_person_idx_log where 1=1 ";
		return find(sql);
	}

}
