package com.sinosoft.mpi.service;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.sinosoft.mpi.dao.MatchResultDao;
import com.sinosoft.mpi.model.MatchResult;
import com.sinosoft.mpi.util.PageInfo;

/**
 * 匹配结果服务
 */
@Service
public class MatchResultService {

	@Resource
	private MatchResultDao matchResultDao;

	/**
	 * 保存
	 * 
	 * @param t
	 */
	public void save(MatchResult t) {
		matchResultDao.add(t);
	}

	/**
	 * 更新
	 * 
	 * @param t
	 */
	public void update(MatchResult t) {
		matchResultDao.update(t);
	}

	/**
	 * 删除
	 * 
	 * @param t
	 */
	public void delete(MatchResult t) {
		matchResultDao.deleteById(t);
	}

	/**
	 * 根据id获取
	 * 
	 * @param id
	 * @return
	 */
	public MatchResult getObject(String id) {
		MatchResult t = new MatchResult();
		t.setMatchResultId(id);
		t = matchResultDao.findById(t);
		return t;
	}

	/**
	 * 分页查询
	 * 
	 * @param t
	 * @param page
	 * @return
	 */
	public List<MatchResult> queryForPage(MatchResult t, PageInfo page) {
		String sql = " select * from mpi_match_result where 1=1 ";
		sql = page.buildPageSql(sql);
		return matchResultDao.find(sql, new Object[] {});
	}

	/**
	 * 根据人员id删除
	 * 
	 * @param personID
	 */
	public void deleteByPersonID(String personID) {
		matchResultDao.deleteByPersonID(personID);
	}

}
