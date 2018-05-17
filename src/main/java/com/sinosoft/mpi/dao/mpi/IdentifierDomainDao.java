package com.sinosoft.mpi.dao.mpi;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;

import com.sinosoft.mpi.model.IdentifierDomain;

/**
 * 域dao
 */
public interface IdentifierDomainDao
		extends JpaRepository<IdentifierDomain, String>, JpaSpecificationExecutor<IdentifierDomain> {

	/**
	 * 根据uniqueSign和domainId统计数量
	 * 
	 * @param uniqueSign
	 * @param domainId
	 * @return
	 */
	int countByUniqueSignAndDomainId(String uniqueSign, String domainId);

	/**
	 * 根据uniqueSign统计数量
	 * 
	 * @param uniqueSign
	 * @return
	 */
	int countByUniqueSign(String uniqueSign);

	/**
	 * 根据booktype查询
	 * 
	 * @param bookType
	 * @return
	 */
	List<IdentifierDomain> findByBookType(String bookType);

	/**
	 * 根据uniqueSign查询
	 * 
	 * @param uniqueSign
	 * @return
	 */
	List<IdentifierDomain> findByUniqueSign(String uniqueSign);

	/**
	 * 根据personId查询数据
	 * 
	 * @param personId
	 * @return
	 */
	@Query(value = " select * from mpi_identifier_domain a where exists( select 1 from mpi_index_identifier_rel b "
			+ " where b.domain_id = a.domain_id and b.field_pk = ? ) ", nativeQuery = true)
	List<IdentifierDomain> getByPersonId(String personId);

	/**
	 * 根据personId查询数据
	 * 
	 * @param personId
	 * @return
	 */
	@Query(value = " select * from mpi_identifier_domain a where exists( select 1 from mpi_index_identifier_rel b "
			+ " where b.domain_id = a.domain_id and b.field_pk = ? ) ", nativeQuery = true)
	IdentifierDomain getFirstByPersonId(String personId);

	/**
	 * 根据uniqueSign查询第一个
	 * 
	 * @param uniqueSign
	 * @return
	 */
	IdentifierDomain findFirstByUniqueSign(String uniqueSign);

}
