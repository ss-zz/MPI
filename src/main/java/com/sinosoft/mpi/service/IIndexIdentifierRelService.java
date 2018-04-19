package com.sinosoft.mpi.service;

import java.util.List;

import com.sinosoft.mpi.model.IndexIdentifierRel;

/**   
*    
* @Description  索引标志关系操作    
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
public interface IIndexIdentifierRelService extends IService<IndexIdentifierRel> {
	
	
	/**
	 * 通过(居民标志)返回是否已经匹配了索引
	 * @param identifierID
	 * @return
	 */
	public IndexIdentifierRel  queryByIdentifierID(String identifierID);
	/**
	 * 通过(居民标志)返回是否已经匹配了索引
	 * @param field_pk
	 * @return
	 */
	public IndexIdentifierRel queryByFieldPK(String field_pk);
	/**
	 * 通过(主索引）查询所有主索引相关居民信息
	 * @param mpi_pk
	 * @return
	 */
	
	public List<IndexIdentifierRel> queryByMpiPK(String mpi_pk);
	/**
	 * 级联删除合并相关关系
	 * @param combinNo
	 * @return
	 */
	
	public void deleteRecurByCombinNo(Long combineNo);
	public void deleteByFieldPk(String fieldpk);
}
