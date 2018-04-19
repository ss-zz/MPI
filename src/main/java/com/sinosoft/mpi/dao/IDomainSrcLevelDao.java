package com.sinosoft.mpi.dao;

import java.util.List;

import com.sinosoft.mpi.model.DomainSrcLevel;

/**
 * 数据源级别操作
 */
public interface IDomainSrcLevelDao extends IBaseDao<DomainSrcLevel> {
	void effect(String id);

	void updateByDomainID(DomainSrcLevel entity);

	List<DomainSrcLevel> findByID(String domainid);
}
