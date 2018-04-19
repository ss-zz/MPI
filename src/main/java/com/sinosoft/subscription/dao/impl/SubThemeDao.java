package com.sinosoft.subscription.dao.impl;

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

import com.sinosoft.subscription.dao.ISubThemeDao;
import com.sinosoft.subscription.model.SubscribeTheme;

@Repository("subThemeDao")
public class SubThemeDao implements ISubThemeDao {

	@Resource
	private JdbcTemplate jdbcTemplate;

	@Override
	public void add(final SubscribeTheme theme) {
		StringBuilder sb = new StringBuilder();
		sb.append(" INSERT INTO PS_topic_type  ");
		sb.append(" (TOPIC_ID,TOPIC_TYPE_CD,TOPIC_NAME,TOPIC_DESC,FLAG) ");
		sb.append(" VALUES(SEQ_TABLE_ID.NEXTVAL,?,?,?,?) ");
		jdbcTemplate.update(sb.toString(), new PreparedStatementSetter() {
			public void setValues(PreparedStatement ps) throws SQLException {
				ps.setString(1, theme.getTopicTypeCd());
				ps.setString(2, theme.getTopicName());
				ps.setString(3, theme.getTopicDesc());
				ps.setString(4, theme.getFlag());
			}
		});
	}

	@Override
	public void deleteById(final SubscribeTheme entity) {
		String sql = " delete from PS_topic_type where TOPIC_ID = ?";
		jdbcTemplate.update(sql, new PreparedStatementSetter() {
			public void setValues(PreparedStatement ps) throws SQLException {
				ps.setString(1, entity.getTopicId());
			}
		});
	}

	@Override
	public void update(final SubscribeTheme theme) {
		if (theme == null || theme.getTopicId() == null) {
			return;
		}
		StringBuilder sql = new StringBuilder();

		sql.append("UPDATE PS_topic_type T ");
		sql.append(" SET T.TOPIC_TYPE_CD = ?, ");
		sql.append("     T.TOPIC_NAME    = ?, ");
		sql.append("     T.TOPIC_DESC    = ?, ");
		sql.append("     T.FLAG          = ? ");
		sql.append(" WHERE T.TOPIC_ID = ? ");
		jdbcTemplate.update(sql.toString(), new PreparedStatementSetter() {
			public void setValues(PreparedStatement ps) throws SQLException {
				ps.setString(1, theme.getTopicTypeCd());
				ps.setString(2, theme.getTopicName());
				ps.setString(3, theme.getTopicDesc());
				ps.setString(4, theme.getFlag());
				ps.setString(5, theme.getTopicId());
			}
		});
	}

	@Override
	public SubscribeTheme findById(SubscribeTheme entity) {
		return null;
	}

	@Override
	public List<SubscribeTheme> find(String sql) {
		return find(sql, new Object[] {});
	}

	public List<SubscribeTheme> find(String sql, Object[] args) {
		List<SubscribeTheme> datas = jdbcTemplate.query(sql, args, new RowMapper<SubscribeTheme>() {
			@Override
			public SubscribeTheme mapRow(ResultSet rs, int row) throws SQLException {
				SubscribeTheme result = new SubscribeTheme();
				result.setTopicId(rs.getString("TOPICID"));
				result.setTopicTypeCd(rs.getString("TOPICTYPECD"));
				result.setTopicName(rs.getString("TOPICNAME"));
				result.setTopicDesc(rs.getString("TOPICDESC"));
				result.setFlag(rs.getString("FLAG"));
				return result;
			}
		});
		return datas;
	}

	@Override
	public List<SubscribeTheme> findAll() {
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
}
