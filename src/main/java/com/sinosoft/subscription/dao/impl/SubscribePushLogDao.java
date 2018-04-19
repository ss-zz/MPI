package com.sinosoft.subscription.dao.impl;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import com.sinosoft.subscription.dao.ISubscribePushLogDao;
import com.sinosoft.subscription.model.SubscribePushLog;

/**
 * 
 * @author chenzhongzheng
 */
@Repository("subscribePushLogDao")
public class SubscribePushLogDao implements ISubscribePushLogDao {

	@Resource
	private JdbcTemplate jdbcTemplate;

	@Override
	public void add(SubscribePushLog entity) {
		// TODO Auto-generated method stub

	}

	@Override
	public void deleteById(SubscribePushLog entity) {
		// TODO Auto-generated method stub

	}

	@Override
	public void update(SubscribePushLog entity) {
		// TODO Auto-generated method stub

	}

	@Override
	public SubscribePushLog findById(SubscribePushLog entity) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<SubscribePushLog> find(String sql) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<SubscribePushLog> find(String sql, Object[] args) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<SubscribePushLog> findAll() {
		// TODO Auto-generated method stub
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

	@Override
	public List<Map<String, Object>> findForMap(String sql, Object[] args) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Map<String, Object>> findForMap(String sql) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public JdbcTemplate getJdbcTemplate() {
		// TODO Auto-generated method stub
		return null;
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
				m.put("ORGCODE", rs.getString("ORGCODE"));
				m.put("ORGNAME", rs.getString("ORGNAME"));
				m.put("TOPICCD", rs.getString("TOPICCD"));
				m.put("TOPIC", rs.getString("TOPIC"));
				m.put("DT", rs.getString("DT"));
				m.put("PSTYPE", rs.getString("PSTYPE"));
				return m;
			}
		});
		return datas;
	}
}
