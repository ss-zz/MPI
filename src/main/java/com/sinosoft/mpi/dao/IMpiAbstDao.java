package com.sinosoft.mpi.dao;

import com.sinosoft.mpi.model.PersonIndex;

/**
 * 系统用户数据操作类接口
 */
public interface IMpiAbstDao extends IBaseDao<PersonIndex> {
	public void addMpiAbst(final PersonIndex personIndex, final String seach_condition, final String abst);
}
