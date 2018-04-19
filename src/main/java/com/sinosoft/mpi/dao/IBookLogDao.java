package com.sinosoft.mpi.dao;

import java.util.List;

import com.sinosoft.mpi.model.BookLog;

/**
 * 订阅信息日志
 */
public interface IBookLogDao extends IBaseDao<BookLog> {

	/**
	 * 自动填充其他数据 入库
	 * 
	 * @param personId
	 *            居民id
	 * @param indexid
	 *            索引id
	 * @param eventType
	 *            操作类型 0-关联至索引 1-与索引解除关联
	 * @return 入库的BookLog对象
	 */
	BookLog autoFillAdd(String personId, String indexid, String eventType);

	/**
	 * 自动填充其他数据入库(与索引解除关联 批量)
	 * 
	 * @param personId
	 *            居民id
	 * @return 入库的BookLog对象
	 */
	List<BookLog> autoFillAdd(String personId);
}
