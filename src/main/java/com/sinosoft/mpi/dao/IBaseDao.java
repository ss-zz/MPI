package com.sinosoft.mpi.dao;

import java.util.List;
import java.util.Map;

import org.springframework.jdbc.core.JdbcTemplate;

/**
 * 基本数据操作类接口
 */
public interface IBaseDao<T> {
	
	/**
	 * 添加对象数据到数据库
	 * 
	 * @param entity
	 */
	void add(T entity);

	/**
	 * 根据id删除数据
	 * 
	 * @param entity
	 */
	void deleteById(T entity);

	/**
	 * 更新对象数据到数据库
	 * 
	 * @param entity
	 */
	void update(T entity);

	/**
	 * 根据id查找对象
	 * 
	 * @param entity
	 * @return 没有找到返回null
	 */
	T findById(T entity);

	/**
	 * 根据sql一句查询数据
	 * 
	 * @param sql
	 *            查询语句
	 * @return
	 */
	List<T> find(String sql);

	/**
	 * @param sql
	 *            查询语句
	 * @param args
	 *            参数
	 * @return
	 */
	List<T> find(String sql, Object[] args);

	/**
	 * @return
	 */
	List<T> findAll();

	/**
	 * 根据sql语句取得数量
	 * 
	 * @param sql
	 *            查询语句
	 * @return
	 */
	int getCount(String sql);

	/**
	 * 根据sql语句取得数量
	 * 
	 * @param sql
	 *            查询语句
	 * @param args
	 *            参数
	 * @return
	 */
	int getCount(String sql, Object[] args);

	/**
	 * 执行sql语句,已map形式返回结果
	 * 
	 * @param sql
	 * @param args
	 * @return
	 */
	List<Map<String, Object>> findForMap(String sql, Object[] args);

	/**
	 * 执行sql语句,已map形式返回结果
	 * 
	 * @param sql
	 * @return
	 */
	List<Map<String, Object>> findForMap(String sql);

	/**
	 * 取得 jdbc模板
	 * 
	 * @return
	 */
	JdbcTemplate getJdbcTemplate();
}
