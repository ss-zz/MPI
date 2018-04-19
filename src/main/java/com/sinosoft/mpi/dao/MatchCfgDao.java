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

import com.sinosoft.mpi.exception.BaseBussinessException;
import com.sinosoft.mpi.model.MatchCfg;
import com.sinosoft.mpi.util.IDUtil;

@Repository("matchCfgDao")
public class MatchCfgDao implements IMatchCfgDao {

	@Resource
	private JdbcTemplate jdbcTemplate;

	@Override
	public void add(final MatchCfg entity) {
		entity.setConfigId(IDUtil.getUUID());
		StringBuilder sql = new StringBuilder();
		sql.append(
				" insert into MPI_MATCH_CFG ( CONFIG_ID,CONFIG_DESC,AGREE_THRESHOLD,MATCH_THRESHOLD,CREATE_DATE,STATE) values ")
				.append(" (?,?,?,?,?,?) ");
		jdbcTemplate.update(sql.toString(), new PreparedStatementSetter() {
			public void setValues(PreparedStatement ps) throws SQLException {
				ps.setString(1, entity.getConfigId());
				ps.setString(2, entity.getConfigDesc());
				ps.setString(3, entity.getAgreeThreshold());
				ps.setString(4, entity.getMatchThreshold());
				ps.setString(5, entity.getCreateDate());
				ps.setString(6, entity.getState());
			}
		});
	}

	@Override
	public void deleteById(final MatchCfg entity) {
		String sql = " delete from MPI_MATCH_CFG where CONFIG_ID = ?";
		jdbcTemplate.update(sql, new PreparedStatementSetter() {
			public void setValues(PreparedStatement ps) throws SQLException {
				ps.setString(1, entity.getConfigId());
			}
		});

	}

	@Override
	public void update(final MatchCfg entity) {
		StringBuilder sql = new StringBuilder();
		sql.append(" update MPI_MATCH_CFG set CONFIG_DESC=?,AGREE_THRESHOLD=?,MATCH_THRESHOLD=?,CREATE_DATE=?,STATE=? ")
				.append(" where CONFIG_ID = ? ");
		jdbcTemplate.update(sql.toString(), new PreparedStatementSetter() {
			public void setValues(PreparedStatement ps) throws SQLException {
				ps.setString(1, entity.getConfigDesc());
				ps.setString(2, entity.getAgreeThreshold());
				ps.setString(3, entity.getMatchThreshold());
				ps.setString(4, entity.getCreateDate());
				ps.setString(5, entity.getState());
				ps.setString(6, entity.getConfigId());
			}
		});
	}

	@Override
	public MatchCfg findById(MatchCfg entity) {
		String sql = "select * from MPI_MATCH_CFG where CONFIG_ID = ?";
		List<MatchCfg> datas = find(sql, new Object[] { entity.getConfigId() });
		MatchCfg result = null;
		if (datas != null && !datas.isEmpty()) {
			result = datas.get(0);
		}
		return result;
	}

	@Override
	public List<MatchCfg> find(String sql) {
		List<MatchCfg> datas = find(sql, new Object[] {});
		return datas;
	}

	@Override
	public List<MatchCfg> find(String sql, Object[] args) {
		List<MatchCfg> datas = jdbcTemplate.query(sql, args, new RowMapper<MatchCfg>() {
			@Override
			public MatchCfg mapRow(ResultSet rs, int rowNum) throws SQLException {
				MatchCfg result = new MatchCfg();
				result.setConfigId(rs.getString("CONFIG_ID"));
				result.setConfigDesc(rs.getString("CONFIG_DESC"));
				result.setAgreeThreshold(rs.getString("AGREE_THRESHOLD"));
				result.setMatchThreshold(rs.getString("MATCH_THRESHOLD"));
				result.setCreateDate(rs.getString("CREATE_DATE"));
				result.setState(rs.getString("STATE"));
				return result;
			}

		});
		return datas;
	}

	@Override
	public List<MatchCfg> findAll() {
		String sql = " select * from MPI_MATCH_CFG where 1=1 ";
		List<MatchCfg> datas = find(sql);
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
	public void effect(final String cfgId) {
		String countSql = "select count(*) from MPI_MATCH_CFG where CONFIG_ID=? ";
		int count = getCount(countSql, new Object[] { cfgId });
		if (count != 1)
			throw new BaseBussinessException("无法根据id[" + cfgId + "]取得配置信息");
		String diseffectSql = " update MPI_MATCH_CFG set STATE='0' where CONFIG_ID != ?";
		jdbcTemplate.update(diseffectSql, new PreparedStatementSetter() {
			public void setValues(PreparedStatement ps) throws SQLException {
				ps.setString(1, cfgId);
			}
		});
		String effectSql = " update MPI_MATCH_CFG set STATE='1' where CONFIG_ID = ?";
		jdbcTemplate.update(effectSql, new PreparedStatementSetter() {
			public void setValues(PreparedStatement ps) throws SQLException {
				ps.setString(1, cfgId);
			}
		});
	}

	@Override
	public JdbcTemplate getJdbcTemplate() {
		return this.jdbcTemplate;
	}
}
