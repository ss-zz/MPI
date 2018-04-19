package com.sinosoft.mpi.dao;

import com.sinosoft.mpi.model.PersonIndex;

/**
* @Description: 系统用户数据操作类接口
* 
* @Package com.sinosoft.mpi.dao 
* @author lpk
* @version v1.0,2013年2月27日
* @see	
* @since	v1.0
 */
public interface IMpiAbstDao extends IBaseDao<PersonIndex> {
	public void addMpiAbst(final PersonIndex personIndex,final String seach_condition,final String abst);
}
