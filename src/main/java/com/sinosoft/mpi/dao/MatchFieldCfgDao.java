package com.sinosoft.mpi.dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import org.springframework.jdbc.core.PreparedStatementSetter;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import com.sinosoft.mpi.model.MatchFieldCfg;
import com.sinosoft.mpi.util.IDUtil;

@Repository
public class MatchFieldCfgDao extends IBaseDao<MatchFieldCfg> {

	@Override
	public void add(final MatchFieldCfg entity) {
		entity.setFieldCfgId(IDUtil.getUUID());
		StringBuilder sql = new StringBuilder();
		sql.append(
				" insert into MPI_MATCH_FIELD_CFG ( FIELD_CFG_ID,CONFIG_ID,PROPERTY_NAME,AGREE_PROB,DIS_AGREE,MATCH_THRESHOLD,MATCH_FUNCTION,WEIGHT,CFG_DESC) values ")
				.append(" (?,?,?,?,?,?,?,?,?) ");
		jdbcTemplate.update(sql.toString(), new PreparedStatementSetter() {
			public void setValues(PreparedStatement ps) throws SQLException {
				ps.setString(1, entity.getFieldCfgId());
				ps.setString(2, entity.getConfigId());
				ps.setString(3, entity.getPropertyName());
				ps.setString(4, entity.getAgreeProb());
				ps.setString(5, entity.getDisAgree());
				ps.setString(6, entity.getMatchThreshold());
				ps.setString(7, entity.getMatchFunction());
				ps.setString(8, entity.getWeight());
				ps.setString(9, entity.getCfgDesc());
			}
		});
	}

	@Override
	public void deleteById(final MatchFieldCfg entity) {
		String sql = " delete from MPI_MATCH_FIELD_CFG where FIELD_CFG_ID = ?";
		jdbcTemplate.update(sql, new PreparedStatementSetter() {
			public void setValues(PreparedStatement ps) throws SQLException {
				ps.setString(1, entity.getFieldCfgId());
			}
		});

	}

	@Override
	public void update(final MatchFieldCfg entity) {
		StringBuilder sql = new StringBuilder();
		sql.append(
				" update MPI_MATCH_FIELD_CFG set CONFIG_ID=?,PROPERTY_NAME=?,AGREE_PROB=?,DIS_AGREE=?,MATCH_THRESHOLD=?,MATCH_FUNCTION=?,WEIGHT=?,CFG_DESC=? ")
				.append(" where FIELD_CFG_ID = ? ");
		jdbcTemplate.update(sql.toString(), new PreparedStatementSetter() {
			public void setValues(PreparedStatement ps) throws SQLException {
				ps.setString(1, entity.getConfigId());
				ps.setString(2, entity.getPropertyName());
				ps.setString(3, entity.getAgreeProb());
				ps.setString(4, entity.getDisAgree());
				ps.setString(5, entity.getMatchThreshold());
				ps.setString(6, entity.getMatchFunction());
				ps.setString(7, entity.getWeight());
				ps.setString(8, entity.getCfgDesc());
				ps.setString(9, entity.getFieldCfgId());
			}
		});
	}

	@Override
	public MatchFieldCfg findById(MatchFieldCfg entity) {
		String sql = "select * from MPI_MATCH_FIELD_CFG where FIELD_CFG_ID = ?";
		List<MatchFieldCfg> datas = find(sql, new Object[] { entity.getFieldCfgId() });
		MatchFieldCfg result = null;
		if (datas != null && !datas.isEmpty()) {
			result = datas.get(0);
		}
		return result;
	}

	@Override
	public List<MatchFieldCfg> find(String sql) {
		List<MatchFieldCfg> datas = find(sql, new Object[] {});
		return datas;
	}

	@Override
	public List<MatchFieldCfg> find(String sql, Object[] args) {
		List<MatchFieldCfg> datas = jdbcTemplate.query(sql, args, new RowMapper<MatchFieldCfg>() {
			@Override
			public MatchFieldCfg mapRow(ResultSet rs, int rowNum) throws SQLException {
				MatchFieldCfg result = new MatchFieldCfg();
				result.setFieldCfgId(rs.getString("FIELD_CFG_ID"));
				result.setConfigId(rs.getString("CONFIG_ID"));
				result.setPropertyName(rs.getString("PROPERTY_NAME"));
				result.setAgreeProb(rs.getString("AGREE_PROB"));
				result.setDisAgree(rs.getString("DIS_AGREE"));
				result.setMatchThreshold(rs.getString("MATCH_THRESHOLD"));
				result.setMatchFunction(rs.getString("MATCH_FUNCTION"));
				result.setWeight(rs.getString("WEIGHT"));
				result.setCfgDesc(rs.getString("CFG_DESC"));
				return result;
			}

		});
		return datas;
	}

	@Override
	public List<MatchFieldCfg> findAll() {
		String sql = " select * from MPI_MATCH_FIELD_CFG where 1=1 ";
		List<MatchFieldCfg> datas = find(sql);
		return datas;
	}

}
