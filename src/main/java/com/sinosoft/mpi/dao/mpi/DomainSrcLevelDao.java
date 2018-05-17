package com.sinosoft.mpi.dao.mpi;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import com.sinosoft.mpi.model.DomainSrcLevel;

/**
 * 域数据源级别dao
 */
public interface DomainSrcLevelDao
		extends JpaRepository<DomainSrcLevel, String>, JpaSpecificationExecutor<DomainSrcLevel> {

	/**
	 * 根据domainid和fieldname查询
	 * 
	 * @param domainId
	 * @param fieldName
	 * @return
	 */
	List<DomainSrcLevel> findByDomainIdAndFieldName(String domainId, String fieldName);

	/**
	 * 根据domainid查询
	 * 
	 * @param domainid
	 * @return
	 */
	List<DomainSrcLevel> findByDomainId(String domainid);

}
