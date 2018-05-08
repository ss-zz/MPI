package com.sinosoft.mpi.service;

import java.util.List;

import javax.annotation.Resource;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Service;

import com.sinosoft.mpi.dao.MatchResultDao;
import com.sinosoft.mpi.model.MatchResult;
import com.sinosoft.mpi.util.PageInfo;

@Service("matchResultService")
public class MatchResultService implements IMatchResultService {
	private Logger logger = Logger.getLogger(MatchResultService.class);
	@Resource
	private MatchResultDao matchResultDao;

	@Override
	public void save(MatchResult t) {
		matchResultDao.add(t);
		logger.debug("Add MatchResult:" + t);
	}

	@Override
	public void update(MatchResult t) {
		matchResultDao.update(t);
		logger.debug("update MatchResult:" + t);
	}

	@Override
	public void delete(MatchResult t) {
		matchResultDao.deleteById(t);
		logger.debug("delete MatchResult,matchResultId=" + t.getMatchResultId());
	}

	@Override
	public MatchResult getObject(String id) {
		MatchResult t = new MatchResult();
		t.setMatchResultId(id);
		t = matchResultDao.findById(t);
		logger.debug("Load MatchResult : matchResultId=" + id + ",result=" + t);
		return t;
	}

	@Override
	public List<MatchResult> queryForPage(MatchResult t, PageInfo page) {
		// XXX ben 实际应用的时候这里需要添加查询条件
		String sql = " select * from mpi_match_result where 1=1 ";
		sql = page.buildPageSql(sql);
		logger.debug("Execute sql:" + sql);
		return matchResultDao.find(sql, new Object[] {});
	}

	@Override
	public void deleteByPersonID(String personID) {
		matchResultDao.deleteByPersonID(personID);
	}

}
