package com.sinosoft.mpi.sshl.dao.impl;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import com.sinosoft.mpi.dao.IBaseDao;
import com.sinosoft.mpi.sshl.model.DataUploadDetail;

@Repository
public class DataUploadDetailDao extends IBaseDao<DataUploadDetail> {

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
