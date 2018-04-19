package com.sinosoft.mpi.service;

import java.util.List;
import java.util.Map;

import com.sinosoft.mpi.model.PersonIndex;
import com.sinosoft.mpi.util.PageInfo;

/**
 * 居民索引操作
 */
public interface IPersonIndexService extends IService<PersonIndex> {

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

	/**
	 * 人工合并索引
	 * 
	 * @param retired
	 * @param surviving
	 */
	void mergeIndex(String retired, String surviving);

	/**
	 * 1.通过被拆分主索引Pk查询主索引操作日志表中为人工拆分的操作记录 2.根据记录获取被合并的主索引对象
	 * 
	 * @param splitIndex
	 * @return
	 */
	List<PersonIndex> findPersonIndexBysplitIndex(String splitIndex);

	/**
	 * 拆分主索引信息 1.通过原主索引主键获取mpi_person_idx_log对象 2.根据mpi_person_idx_log删除原主索引现关联关系
	 * 3.根据mpi_person_idx_log重新关联到删除主索引上 4.重新合并原主索引 5.删除mpi_person_idx_log对应的关联记录
	 * 6.恢复拆分主索引为有效
	 * 
	 * @param splitPK
	 *            拆分主索引PK
	 * @param formerPK
	 *            原主索引PK
	 */
	void splitIndex(String splitPK, String formerPK);
}
