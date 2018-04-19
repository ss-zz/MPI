package com.sinosoft.mpi.dao;

import java.util.List;

import com.sinosoft.mpi.model.IndexIdentifierRel;

/**
 * 索引与标志关系数据操作类接口
 */
public interface IIndexIdentifierRelDao extends IBaseDao<IndexIdentifierRel> {

	/**
	 * 根据居民身份标识Id删除关联数据
	 * 
	 * @param personIdentifierId
	 *            身份标识主键
	 */
	void deleteByFieldPK(String personIdentifierId);

	/**
	 * 根据索引id删除关联数据
	 * 
	 * @param indexId
	 *            索引主键
	 */
	void deleteByMpiPK(String mpi_pk);

	/**
	 * 根据mpi_pk field_pk联合删除关联数据
	 * 
	 * @param mpi_pk
	 */
	void deleteByMpiPKFieldPk(String mpi_pk, String field_pk);

	/**
	 * 根据居民身份标识Id查找居民索引关联关系
	 * 
	 * @param personid
	 * @return
	 */
	IndexIdentifierRel findByFieldPK(String field_pk);

	/**
	 * 根据索引id取得居民索引关联关系
	 * 
	 * @param indexId
	 * @return
	 */
	List<IndexIdentifierRel> findByMpiPK(String mpi_pk);

	/**
	 * 取最新一次mpi合并记录
	 **/
	IndexIdentifierRel findByMpiPKByLatest(String mpi_pk);

	/**
	 * 根据合并记录号Delete Combin information
	 * 
	 * @param combinNo
	 *            合并记录号
	 */
	void deleteRecurByCombinNo(String sql, Long args);
}
