package com.sinosoft.mpi.service;

import java.util.List;
import java.util.Map;

import com.sinosoft.mpi.model.ManOpPerson;
import com.sinosoft.mpi.util.PageInfo;


/**   
*    
* @Description 需要人工干预的居民列表操作
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
public interface IManOpPersonService extends IService<ManOpPerson> {

	/**
	 * 查询分页数据
	 * @param t
	 * @param page
	 * @return
	 */
	List<Map<String, Object>> queryForMapPage(ManOpPerson t, PageInfo page);

	
}
