package com.sinosoft.mpi.dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import org.springframework.jdbc.core.PreparedStatementSetter;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import com.sinosoft.mpi.model.ManOpPerson;
import com.sinosoft.mpi.util.IDUtil;

/**
 * 需要人工干预的居民服务
 */
@Repository
public class ManOpPersonDao extends IBaseDao<ManOpPerson> {

	/**
	 * 新增
	 */
	@Override
	public void add(final ManOpPerson entity) {
		entity.setMAN_OP_ID(IDUtil.getUUID());
		StringBuilder sql = new StringBuilder();
		sql.append(" insert into mpi_man_op_person (man_op_id, FIELD_PK, man_op_status, man_op_time,MPI_PK) ");
		sql.append(" values (?, ?, ?, ?,?) ");
		jdbcTemplate.update(sql.toString(), new PreparedStatementSetter() {
			public void setValues(PreparedStatement ps) throws SQLException {
				ps.setString(1, entity.getMAN_OP_ID());
				ps.setString(2, entity.getFIELD_PK());
				ps.setString(3, entity.getMAN_OP_STATUS());
				ps.setString(4, entity.getMAN_OP_TIME());
				ps.setString(5, entity.getMPI_PK());
			}
		});

	}

	@Override
	public void deleteById(final ManOpPerson entity) {
		String sql = " delete mpi_man_op_person where man_op_id = ? ";
		jdbcTemplate.update(sql, new PreparedStatementSetter() {
			public void setValues(PreparedStatement ps) throws SQLException {
				ps.setString(1, entity.getMAN_OP_ID());
			}
		});
	}

	@Override
	public void update(final ManOpPerson entity) {
		if (entity == null || entity.getMAN_OP_ID() == null) {
			return;
		}
		StringBuilder sql = new StringBuilder();
		sql.append(" update mpi_man_op_person set person_id = ?, man_op_status = ?, ");
		sql.append(" man_op_time = ?,MPI_PK=? where man_op_id = ? ");
		jdbcTemplate.update(sql.toString(), new PreparedStatementSetter() {
			public void setValues(PreparedStatement ps) throws SQLException {
				ps.setString(1, entity.getFIELD_PK());
				ps.setString(2, entity.getMAN_OP_STATUS());
				ps.setString(3, entity.getMAN_OP_TIME());
				ps.setString(4, entity.getMPI_PK());
				ps.setString(5, entity.getMAN_OP_ID());
			}
		});
	}

	@Override
	public ManOpPerson findById(ManOpPerson entity) {
		String sql = " select * from mpi_man_op_person where man_op_id = ? ";
		List<ManOpPerson> datas = find(sql, new Object[] { entity.getMAN_OP_ID() });
		ManOpPerson result = null;
		if (datas != null && !datas.isEmpty()) {
			result = datas.get(0);
		}
		return result;
	}

	@Override
	public List<ManOpPerson> find(String sql) {
		return find(sql, new Object[] {});
	}

	@Override
	public List<ManOpPerson> find(String sql, Object[] args) {
		List<ManOpPerson> datas = jdbcTemplate.query(sql, args, new RowMapper<ManOpPerson>() {
			@Override
			public ManOpPerson mapRow(ResultSet rs, int row) throws SQLException {
				ManOpPerson result = new ManOpPerson();
				result.setMAN_OP_ID(rs.getString("MAN_OP_ID"));
				result.setMAN_OP_STATUS(rs.getString("MAN_OP_STATUS"));
				result.setMAN_OP_TIME(rs.getString("MAN_OP_TIME"));
				result.setFIELD_PK(rs.getString("FIELD_PK"));
				result.setMPI_PK(rs.getString("MPI_PK"));
				return result;
			}
		});
		return datas;
	}

	@Override
	public List<ManOpPerson> findAll() {
		String sql = " select * from mpi_man_op_person where 1=1 ";
		return find(sql);
	}

}
