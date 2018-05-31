package com.sinosoft.mpi.service;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.sinosoft.mpi.dao.mpi.MatchResultDao;
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
		matchResultDao.save(t);
	}

	/**
	 * 批量保存
	 * 
	 * @param items
	 */
	public void batchSave(List<MatchResult> items) {
		matchResultDao.save(items);
	}

	/**
	 * 更新
	 * 
	 * @param t
	 */
	public void update(MatchResult t) {
		matchResultDao.save(t);
	}

	/**
	 * 删除
	 * 
	 * @param t
	 */
	public void delete(MatchResult t) {
		matchResultDao.delete(t);
	}

	/**
	 * 根据id获取
	 * 
	 * @param id
	 * @return
	 */
	public MatchResult getObject(String id) {
		return matchResultDao.findOne(id);
	}

	/**
	 * 分页查询
	 * 
	 * @param t
	 * @param page
	 * @return
	 */
	public List<MatchResult> queryForPage(MatchResult t, PageInfo page) {
		return matchResultDao.findAll(page).getContent();
	}

	/**
	 * 根据人员id删除
	 * 
	 * @param personID
	 */
	public void deleteByPersonID(String personID) {
		matchResultDao.deleteByFieldPk(personID);
	}

	/**
	 * 根据mpiPk和fieldPk查询第一条数据
	 * 
	 * @param mpiPk
	 * @param fieldPk
	 * @return
	 */
	public MatchResult findByMpiPkAndFieldPk(String mpiPk, String fieldPk) {
		return matchResultDao.findFirstByMpiPkAndFieldPk(mpiPk, fieldPk);
	}

}
