package com.sinosoft.mpi.dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import org.springframework.jdbc.core.PreparedStatementSetter;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;

import com.sinosoft.mpi.model.BlockGroup;
import com.sinosoft.mpi.util.IDUtil;

/**
 * 初筛配置组管理
 */
@Component
public class BlockGroupDao extends IBaseDao<BlockGroup> {

	@Override
	public void add(final BlockGroup entity) {
		entity.setGroupId(IDUtil.getUUID());
		StringBuilder sql = new StringBuilder();
		sql.append(
				" insert into MPI_BLOCK_GROUP ( GROUP_ID,BOLCK_ID,GROUP_SIGN,DB_FIELD,PROPERTY_NAME,FUN_NAME) values ")
				.append(" (?,?,?,?,?,?) ");
		jdbcTemplate.update(sql.toString(), new PreparedStatementSetter() {
			public void setValues(PreparedStatement ps) throws SQLException {
				ps.setString(1, entity.getGroupId());
				ps.setString(2, entity.getBolckId());
				ps.setInt(3, entity.getGroupSign());
				ps.setString(4, entity.getDbField());
				ps.setString(5, entity.getPropertyName());
				ps.setString(6, entity.getFunName());
			}
		});
	}

	@Override
	public void deleteById(final BlockGroup entity) {
		String sql = " delete from MPI_BLOCK_GROUP where GROUP_ID = ?";
		jdbcTemplate.update(sql, new PreparedStatementSetter() {
			public void setValues(PreparedStatement ps) throws SQLException {
				ps.setString(1, entity.getGroupId());
			}
		});

	}

	@Override
	public void update(final BlockGroup entity) {
		StringBuilder sql = new StringBuilder();
		sql.append(" update MPI_BLOCK_GROUP set BOLCK_ID=?,GROUP_SIGN=?,DB_FIELD=?,PROPERTY_NAME=?,FUN_NAME=? ")
				.append(" where GROUP_ID = ? ");
		jdbcTemplate.update(sql.toString(), new PreparedStatementSetter() {
			public void setValues(PreparedStatement ps) throws SQLException {
				ps.setString(1, entity.getBolckId());
				ps.setInt(2, entity.getGroupSign());
				ps.setString(3, entity.getDbField());
				ps.setString(4, entity.getPropertyName());
				ps.setString(5, entity.getFunName());
				ps.setString(6, entity.getGroupId());
			}
		});
	}

	@Override
	public BlockGroup findById(BlockGroup entity) {
		String sql = "select * from MPI_BLOCK_GROUP where GROUP_ID = ?";
		List<BlockGroup> datas = find(sql, new Object[] { entity.getGroupId() });
		BlockGroup result = null;
		if (datas != null && !datas.isEmpty()) {
			result = datas.get(0);
		}
		return result;
	}

	@Override
	public List<BlockGroup> find(String sql) {
		List<BlockGroup> datas = find(sql, new Object[] {});
		return datas;
	}

	@Override
	public List<BlockGroup> find(String sql, Object[] args) {
		List<BlockGroup> datas = jdbcTemplate.query(sql, args, new RowMapper<BlockGroup>() {
			@Override
			public BlockGroup mapRow(ResultSet rs, int rowNum) throws SQLException {
				BlockGroup result = new BlockGroup();
				result.setGroupId(rs.getString("GROUP_ID"));
				result.setBolckId(rs.getString("BOLCK_ID"));
				result.setGroupSign(rs.getInt("GROUP_SIGN"));
				result.setDbField(rs.getString("DB_FIELD"));
				result.setPropertyName(rs.getString("PROPERTY_NAME"));
				result.setFunName(rs.getString("FUN_NAME"));
				return result;
			}

		});
		return datas;
	}

	@Override
	public List<BlockGroup> findAll() {
		String sql = " select * from MPI_BLOCK_GROUP where 1=1 ";
		List<BlockGroup> datas = find(sql);
		return datas;
	}

}
