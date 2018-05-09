package com.sinosoft.mpi.dao;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

@Repository
public class PinyinDicDao {

	@Resource
	private JdbcTemplate jdbcTemplate;

	public boolean isOverPinYinTimes(String pinyinStr) {
		StringBuilder sql = new StringBuilder();
		sql.append(" select * from PINYIN_KIND t where t.PINYINKEY=?");
		List<Map<String, Object>> rec = jdbcTemplate.queryForList(sql.toString(), pinyinStr);
		if (rec != null && rec.size() > 0) {
			int times = 0;
			times = new Integer(rec.get(0).get("times").toString()).intValue();
			if (times < 20) {
				return true;
			}

		} else {
			return true;
		}

		return false;
	}

	public void updatePinyinTimes(String pinyinString) {
		StringBuilder sql = new StringBuilder();
		sql.append("select count(*) from PINYIN_KIND t where t.PINYINKEY=? ");

		int count = jdbcTemplate.queryForObject(sql.toString(), new String[] { pinyinString }, Integer.class);
		sql.setLength(0);
		if (count > 0) {
			sql.append("update PINYIN_KIND t set times=times+1 where t.PINYINKEY=?");
			jdbcTemplate.update(sql.toString(), pinyinString);
		} else {
			sql.append("insert into pinyin_kind values( ?,?)");
			jdbcTemplate.update(sql.toString(), pinyinString, 1);
		}

	}

}
