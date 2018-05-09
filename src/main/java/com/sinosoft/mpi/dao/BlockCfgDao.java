package com.sinosoft.mpi.dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import org.springframework.jdbc.core.PreparedStatementSetter;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;

import com.sinosoft.mpi.exception.BaseBussinessException;
import com.sinosoft.mpi.model.BlockCfg;
import com.sinosoft.mpi.util.IDUtil;

/**
 * 初筛配置管理
 */
@Component
public class BlockCfgDao extends IBaseDao<BlockCfg> {

	@Override
	public void add(final BlockCfg entity) {
		entity.setBolckId(IDUtil.getUUID());
		StringBuilder sql = new StringBuilder();
		sql.append(" insert into MPI_BLOCK_CFG ( BOLCK_ID,BLOCK_DESC,GROUP_COUNT,CREATE_DATE,STATE) values ")
				.append(" (?,?,?,?,?) ");
		jdbcTemplate.update(sql.toString(), new PreparedStatementSetter() {
			public void setValues(PreparedStatement ps) throws SQLException {
				ps.setString(1, entity.getBolckId());
				ps.setString(2, entity.getBlockDesc());
				ps.setInt(3, entity.getGroupCount());
				ps.setString(4, entity.getCreateDate());
				ps.setString(5, entity.getState());
			}
		});
	}

	@Override
	public void deleteById(final BlockCfg entity) {
		String sql = " delete from MPI_BLOCK_CFG where BOLCK_ID = ?";
		jdbcTemplate.update(sql, new PreparedStatementSetter() {
			public void setValues(PreparedStatement ps) throws SQLException {
				ps.setString(1, entity.getBolckId());
			}
		});
	}

	@Override
	public void update(final BlockCfg entity) {
		StringBuilder sql = new StringBuilder();
		sql.append(" update MPI_BLOCK_CFG set BLOCK_DESC=?,GROUP_COUNT=?,CREATE_DATE=?,STATE=? ")
				.append(" where BOLCK_ID = ? ");
		jdbcTemplate.update(sql.toString(), new PreparedStatementSetter() {
			public void setValues(PreparedStatement ps) throws SQLException {
				ps.setString(1, entity.getBlockDesc());
				ps.setInt(2, entity.getGroupCount());
				ps.setString(3, entity.getCreateDate());
				ps.setString(4, entity.getState());
				ps.setString(5, entity.getBolckId());
			}
		});
	}

	@Override
	public BlockCfg findById(BlockCfg entity) {
		String sql = "select * from MPI_BLOCK_CFG where BOLCK_ID = ?";
		List<BlockCfg> datas = find(sql, new Object[] { entity.getBolckId() });
		BlockCfg result = null;
		if (datas != null && !datas.isEmpty()) {
			result = datas.get(0);
		}
		return result;
	}

	@Override
	public List<BlockCfg> find(String sql) {
		List<BlockCfg> datas = find(sql, new Object[] {});
		return datas;
	}

	@Override
	public List<BlockCfg> find(String sql, Object[] args) {
		List<BlockCfg> datas = jdbcTemplate.query(sql, args, new RowMapper<BlockCfg>() {
			@Override
			public BlockCfg mapRow(ResultSet rs, int rowNum) throws SQLException {
				BlockCfg result = new BlockCfg();
				result.setBolckId(rs.getString("BOLCK_ID"));
				result.setBlockDesc(rs.getString("BLOCK_DESC"));
				result.setGroupCount(rs.getInt("GROUP_COUNT"));
				result.setCreateDate(rs.getString("CREATE_DATE"));
				result.setState(rs.getString("STATE"));
				return result;
			}
		});
		return datas;
	}

	@Override
	public List<BlockCfg> findAll() {
		String sql = " select * from MPI_BLOCK_CFG where 1=1 ";
		List<BlockCfg> datas = find(sql);
		return datas;
	}

	/**
	 * 根据配置id使配置生效
	 * 
	 * @param cfgId
	 */
	public void effect(final String cfgId) {
		String countSql = "select count(*) from MPI_BLOCK_CFG where BOLCK_ID=? ";
		int count = getCount(countSql, new Object[] { cfgId });
		if (count != 1)
			throw new BaseBussinessException("无法根据id[" + cfgId + "]取得配置信息");
		String diseffectSql = " update MPI_BLOCK_CFG set STATE='0' where BOLCK_ID != ?";
		jdbcTemplate.update(diseffectSql, new PreparedStatementSetter() {
			public void setValues(PreparedStatement ps) throws SQLException {
				ps.setString(1, cfgId);
			}
		});
		String effectSql = " update MPI_BLOCK_CFG set STATE='1' where BOLCK_ID = ?";
		jdbcTemplate.update(effectSql, new PreparedStatementSetter() {
			public void setValues(PreparedStatement ps) throws SQLException {
				ps.setString(1, cfgId);
			}
		});

	}

}
