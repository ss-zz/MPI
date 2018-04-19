package com.sinosoft.mpi.service;

import com.sinosoft.mpi.model.MatchResult;

/**
 * 匹配结果操作
 */
public interface IMatchResultService extends IService<MatchResult> {

	/**
	 * 根据居民主键删除该居民的结果
	 * 
	 * @param personID
	 */
	public void deleteByPersonID(String personID);

}
