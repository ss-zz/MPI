package com.sinosoft.mpi.service;

import com.sinosoft.mpi.model.PersonIndex;
import com.sinosoft.mpi.model.PersonInfo;

/**   
*    
* @Description 索引更新接口
* 
* 根据系统索引更新规则更新索引信息
* 
* @Package com.sinosoft.mpi.service 
* @author Bysun
* @version v1.0,2012-5-3
* @see	
* @since	（可选）	
*   
*/ 
public interface IPersonIndexUpdateService {
	
	/**
	 * 更新索引信息
	 * @param person 居民信息 
	 * @param indexId 需要更新的索引id
	 */
	void updateIndex(PersonInfo person,String indexId);
	
	

	public PersonIndex updateIndex(PersonIndex personindex,PersonInfo person) ;
}
