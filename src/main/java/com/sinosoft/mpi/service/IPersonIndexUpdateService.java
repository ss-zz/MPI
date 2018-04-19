package com.sinosoft.mpi.service;

import com.sinosoft.mpi.model.PersonIndex;
import com.sinosoft.mpi.model.PersonInfo;

/**
 * 索引更新接口
 */
public interface IPersonIndexUpdateService {

	/**
	 * 更新索引信息
	 * 
	 * @param person
	 *            居民信息
	 * @param indexId
	 *            需要更新的索引id
	 */
	void updateIndex(PersonInfo person, String indexId);

	public PersonIndex updateIndex(PersonIndex personindex, PersonInfo person);
}
