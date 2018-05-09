package com.sinosoft.mpi.dao;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

/**
 * 基本数据操作类接口
 */
@Component
public abstract class IBaseDao<T> {

	@Autowired
	public JdbcTemplate jdbcTemplate;

	/**
	 * 添加对象数据到数据库
	 * 
	 * @param entity
	 */
	public abstract void add(T entity);

	/**
	 * 根据id删除数据
	 * 
	 * @param entity
	 */
	public abstract void deleteById(T entity);

	/**
	 * 更新对象数据到数据库
	 * 
	 * @param entity
	 */
	public abstract void update(T entity);

	/**
	 * 根据id查找对象
	 * 
	 * @param entity
	 * @return 没有找到返回null
	 */
	public abstract T findById(T entity);

	/**
	 * 根据sql一句查询数据
	 * 
	 * @param sql
	 *            查询语句
	 * @return
	 */
	public abstract List<T> find(String sql);

	/**
	 * 根据sql查询数据
	 * 
	 * @param sql
	 *            查询语句
	 * @param args
	 *            参数
	 * @return
	 */
	public abstract List<T> find(String sql, Object[] args);

	/**
	 * 查询所有数据
	 */
	public abstract List<T> findAll();

	/**
	 * 执行sql语句,以map形式返回结果
	 * 
	 * @param sql
	 * @param args
	 * @return
	 */
	public List<Map<String, Object>> findForMap(String sql, Object[] args) {
		return jdbcTemplate.queryForList(sql, args);
	};

	/**
	 * 执行sql语句,以map形式返回结果
	 * 
	 * @param sql
	 * @return
	 */
	public List<Map<String, Object>> findForMap(String sql) {
		return jdbcTemplate.queryForList(sql);
	};

	/**
	 * 根据sql获取数据量
	 * 
	 * @param sql
	 * @return
	 */
	public int getCount(String sql) {
		return getCount(sql, new Object[] {});
	}

	/**
	 * 根据sql获取数据量
	 * 
	 * @param sql
	 * @param args
	 * @return
	 */
	public int getCount(String sql, Object[] args) {
		return jdbcTemplate.queryForObject(sql, args, Integer.class);
	}

}
