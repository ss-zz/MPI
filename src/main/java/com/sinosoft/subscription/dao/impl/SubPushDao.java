package com.sinosoft.subscription.dao.impl;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementSetter;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import com.sinosoft.subscription.dao.ISubPushDao;
import com.sinosoft.subscription.model.SubscribePush;

@Repository("subPushDao")
public class SubPushDao implements ISubPushDao {

	@Resource
	private JdbcTemplate jdbcTemplate;

	@Override
	public void add(final SubscribePush push) {
		StringBuilder sb = new StringBuilder();
		sb.append("  INSERT INTO PS_PUSH_CONFIG	");
		sb.append("    (PUSH_ID,PUSH_NO, PUSH_NAME,TOPIC_TYPE_CD,DOMAIN_CODE,PUSH_DATE,");
		sb.append("     DISABLE_DATE,FLAG,PUSH_DESC) VALUES(SEQ_TABLE_ID.NEXTVAL,");
		sb.append(" ?,?,?,?,SYSDATE,SYSDATE + 365,?,?)");
		jdbcTemplate.update(sb.toString(), new PreparedStatementSetter() {
			public void setValues(PreparedStatement ps) throws SQLException {
				ps.setString(1, push.getPushNo());
				ps.setString(2, push.getPushName());
				ps.setString(3, push.getTopicType());
				ps.setString(4, push.getOrgCode());
				ps.setString(5, push.getFlag());
				ps.setString(6, push.getPushDesc());
			}
		});
	}

	@Override
	public void deleteById(final SubscribePush push) {
		String dSql = "DELETE FROM PS_SUB_CONFIG T WHERE T.SB_TOPIC_CODE = ?";
		jdbcTemplate.update(dSql, new PreparedStatementSetter() {
			public void setValues(PreparedStatement ps) throws SQLException {
				ps.setString(1, push.getId());
			}
		});
		String sql = "DELETE FROM PS_PUSH_CONFIG T WHERE T.PUSH_ID =?";
		jdbcTemplate.update(sql, new PreparedStatementSetter() {
			public void setValues(PreparedStatement ps) throws SQLException {
				ps.setString(1, push.getId());
			}
		});
	}

	@Override
	public void update(final SubscribePush push) {
		if (push == null || push.getId() == null) {
			return;
		}
		StringBuilder sb = new StringBuilder();
		sb.append("UPDATE PS_PUSH_CONFIG T ");
		sb.append(" SET T.PUSH_NO=?, ");
		sb.append("   T.PUSH_NAME=?, ");
		sb.append("   T.TOPIC_TYPE_CD=?,");
		sb.append("   T.DOMAIN_CODE=?,	");
		sb.append("   T.FLAG = ?, ");
		sb.append("   T.PUSH_DESC=? ");
		sb.append(" WHERE T.PUSH_ID=?");
		jdbcTemplate.update(sb.toString(), new PreparedStatementSetter() {
			public void setValues(PreparedStatement ps) throws SQLException {
				ps.setString(1, push.getPushNo());
				ps.setString(2, push.getPushName());
				ps.setString(3, push.getTopicType());
				ps.setString(4, push.getOrgCode());
				ps.setString(5, push.getFlag());
				ps.setString(6, push.getPushDesc());
				ps.setString(7, push.getId());
			}
		});
	}

	@Override
	public List<Map<String, String>> findAll(String sql) {
		return findAll(sql, new Object[] {});
	}

	public List<Map<String, String>> findAll(String sql, Object[] args) {
		List<Map<String, String>> datas = jdbcTemplate.query(sql, args, new RowMapper<Map<String, String>>() {
			@Override
			public Map<String, String> mapRow(ResultSet rs, int row) throws SQLException {
				Map<String, String> m = new HashMap<String, String>();
				m.put("ID", rs.getString("ID"));
				m.put("PUSHNO", rs.getString("PUSHNO"));
				m.put("PUSHNAME", rs.getString("PUSHNAME"));
				m.put("TOPICTYPE", rs.getString("TOPICTYPE"));
				m.put("TOPICNAME", rs.getString("TOPICNAME"));
				m.put("ORGCODE", rs.getString("ORGCODE"));
				m.put("ORGNAME", rs.getString("ORGNAME"));
				m.put("PUSHDATE", rs.getString("PUSHDATE"));
				m.put("DISABLEDATE", rs.getString("DISABLEDATE"));
				m.put("FLAG", rs.getString("FLAG"));
				m.put("PUSHDESC", rs.getString("PUSHDESC"));
				return m;
			}
		});
		return datas;
	}

	@Override
	public List<SubscribePush> findAll() {
		return null;
	}

	@Override
	public int getCount(String sql) {
		return getCount(sql, new Object[] {});
	}

	@Override
	public int getCount(String sql, Object[] args) {
		return jdbcTemplate.queryForObject(sql, args, Integer.class);
	}

	public JdbcTemplate getJdbcTemplate() {
		return jdbcTemplate;
	}

	public void setJdbcTemplate(JdbcTemplate jdbcTemplate) {
		this.jdbcTemplate = jdbcTemplate;
	}

	@Override
	public List<Map<String, Object>> findForMap(String sql, Object[] args) {
		return jdbcTemplate.queryForList(sql, args);
	}

	@Override
	public List<Map<String, Object>> findForMap(String sql) {
		return jdbcTemplate.queryForList(sql);
	}

	@Override
	public List<SubscribePush> find(String sql) {
		return null;
	}

	@Override
	public List<SubscribePush> find(String sql, Object[] args) {
		return null;
	}

	@Override
	public SubscribePush findById(SubscribePush entity) {
		return null;
	}
}
