package com.sinosoft.mpi.service;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.sinosoft.mpi.dao.MatchResultDao;
import com.sinosoft.mpi.model.MatchResult;
import com.sinosoft.mpi.util.PageInfo;

@Service
public class MatchResultService {

	@Resource
	private MatchResultDao matchResultDao;

	public void save(MatchResult t) {
		matchResultDao.add(t);
	}

	public void update(MatchResult t) {
		matchResultDao.update(t);
	}

	public void delete(MatchResult t) {
		matchResultDao.deleteById(t);
	}

	public MatchResult getObject(String id) {
		MatchResult t = new MatchResult();
		t.setMatchResultId(id);
		t = matchResultDao.findById(t);
		return t;
	}

	public List<MatchResult> queryForPage(MatchResult t, PageInfo page) {
		String sql = " select * from mpi_match_result where 1=1 ";
		sql = page.buildPageSql(sql);
		return matchResultDao.find(sql, new Object[] {});
	}

	public void deleteByPersonID(String personID) {
		matchResultDao.deleteByPersonID(personID);
	}

}
