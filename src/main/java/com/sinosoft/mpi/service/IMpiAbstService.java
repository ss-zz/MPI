package com.sinosoft.mpi.service;

import java.util.List;
import java.util.Map;

import com.sinosoft.mpi.model.PersonIndex;
import com.sinosoft.mpi.util.PageInfo;

/**
 * 居民索引操作
 */
public interface IMpiAbstService extends IService<PersonIndex> {
	
	/**
	 * 查询拆分索引显示列表页面
	 * 
	 * @param index
	 * @param page
	 * @return
	 */
	List<Map<String, Object>> queryForSplitPage(PersonIndex index, PageInfo page);

	/**
	 * 查询拆分索引显示列表页面
	 * 
	 * @param index
	 * @param fromIndexId
	 *            需要排除显示的索引id
	 * @param page
	 * @return
	 */
	List<Map<String, Object>> queryForSplitPage(PersonIndex index, String fromIndexId, PageInfo page);

}
