package com.sinosoft.mpi.dao;

import java.util.List;

import com.sinosoft.mpi.model.DomainSrcLevel;
import com.sinosoft.mpi.model.IdentifierDomain;
import com.sinosoft.mpi.model.IndexIdentifierRel;
import com.sinosoft.mpi.model.MatchResult;


/**   
*    
* @Description 数据源级别操作
* 
* 
*
* 
* @Package com.sinosoft.mpi.service 
* @author lpk
* @version v1.0,2012-3-19
* @see	
* @since	（可选）	
*   
*/ 
public interface IDomainSrcLevelDao extends IBaseDao<DomainSrcLevel> {
	void effect(String id);

	void updateByDomainID(DomainSrcLevel entity);

	List<DomainSrcLevel> findByID(String domainid);
}
