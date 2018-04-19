package com.sinosoft.mpi.service;

import java.util.List;

import com.sinosoft.mpi.util.PageInfo;

public interface IService<T> {
	
	/**
	 * 保存
	 * @param t
	 */
	public void save(T t);
	
	/**
	 * 更新
	 * @param t
	 */
	public void update(T t);
	
	/**
	 * 删除
	 * @param t
	 */
	public void delete(T t);
	
	/**
	 * 根据主键取得对象
	 * @param id
	 * @return
	 */
	public T getObject(String id);
	
	/**
	 * 根据条件分页查询
	 * @param user 需要查询的用户模板
	 * @param page 分页参数
	 * @return
	 */
	List<T> queryForPage(T t,PageInfo page);
}
