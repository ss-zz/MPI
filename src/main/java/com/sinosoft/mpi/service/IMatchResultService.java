package com.sinosoft.mpi.service;

import com.sinosoft.mpi.model.MatchResult;


/**   
*    
* @Description 匹配结果操作
* 
* 
*
* 
* @Package com.sinosoft.mpi.service 
* @author Bysun
* @version v1.0,2012-3-19
* @see	
* @since	（可选）	
*   
*/ 
public interface IMatchResultService extends IService<MatchResult> {
	
	/**
	 * 根据居民主键删除该居民的结果
	 * @param personID
	 */
	public void deleteByPersonID(String personID);
	

}
