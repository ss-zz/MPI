package com.sinosoft.mpi.dao;

import com.sinosoft.mpi.model.MatchResult;



/**
* @Description: 匹配结果数据操作类接口
* 
* @Package com.sinosoft.mpi.dao 
* @author bysun
* @version v1.0,2012-3-12
* @see	
* @since	v1.0
 */
public interface IMatchResultDao extends IBaseDao<MatchResult> {
	/**
	 * 根据居民主键删除结果
	 * @param personID
	 */
	public void deleteByPersonID(String personID);
	
	/**
	 * 根据 居民id和索引id取得匹配记录
	 * @param personId 居民id
	 * @param indexId 索引id
	 * @return
	 */
	public MatchResult findByPersonAndIndex(String personId,String indexId);
	
}
