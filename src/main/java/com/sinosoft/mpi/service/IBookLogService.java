package com.sinosoft.mpi.service;

import java.util.List;

import com.sinosoft.mpi.model.BookLog;
import com.sinosoft.mpi.model.IdentifierDomain;

/**
 * 订阅日志业务操作类
 */
public interface IBookLogService extends IService<BookLog> {

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
	BookLog save(String personId, String indexid, String eventType);

	/**
	 * 自动填充其他数据入库(与索引解除关联 批量)
	 * 
	 * @param personId
	 *            居民id
	 * @return 入库的BookLog对象
	 */
	List<BookLog> save(String personId);

	/**
	 * 查询取得需要发送的数据
	 */
	List<BookLog> queryDatas(IdentifierDomain domain);

	/**
	 * 查询取得需要发送的数据
	 */
	List<BookLog> queryDatasByDomain(String domainUniqueSign);
}
