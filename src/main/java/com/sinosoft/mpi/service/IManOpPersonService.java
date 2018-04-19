package com.sinosoft.mpi.service;

import java.util.List;
import java.util.Map;

import com.sinosoft.mpi.model.ManOpPerson;
import com.sinosoft.mpi.util.PageInfo;

/**
 * 需要人工干预的居民列表操作
 */
public interface IManOpPersonService extends IService<ManOpPerson> {

	/**
	 * 查询分页数据
	 * 
	 * @param t
	 * @param page
	 * @return
	 */
	List<Map<String, Object>> queryForMapPage(ManOpPerson t, PageInfo page);

}
