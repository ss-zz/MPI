package com.sinosoft.mpi.sshl.dao.impl;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import com.sinosoft.mpi.sshl.dao.IDataUploadDetailDao;
import com.sinosoft.mpi.sshl.model.DataUploadDetail;

@Repository("dataUploadDetailDao")
public class DataUploadDetailDao implements IDataUploadDetailDao {

	@Resource
	private JdbcTemplate jdbcTemplate;

	@Override
	public void add(DataUploadDetail entity) {

	}

	@Override
	public void deleteById(DataUploadDetail entity) {

	}

	@Override
	public void update(DataUploadDetail entity) {

	}

	@Override
	public DataUploadDetail findById(DataUploadDetail entity) {
		return null;
	}

	@Override
	public List<DataUploadDetail> find(String sql) {
		return null;
	}

	@Override
	public List<DataUploadDetail> find(String sql, Object[] args) {
		return null;
	}

	@Override
	public List<DataUploadDetail> findAll() {
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
				m.put("ORGCODE", rs.getString("ORGCODE"));
				m.put("ORGNAME", rs.getString("ORGNAME"));
				m.put("TABLEID", rs.getString("TABLEID"));
				m.put("TABLENAME", rs.getString("TABLENAME"));
				m.put("TOTAL", rs.getString("TOTAL"));
				return m;
			}
		});
		return datas;
	}

}
