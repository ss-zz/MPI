package com.sinosoft.mpi.dao.mpi;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;

import com.sinosoft.mpi.model.PersonInfo;

/**
 * 主索引人员dao
 */
public interface PersonInfoDao extends JpaRepository<PersonInfo, String>, JpaSpecificationExecutor<PersonInfo> {

	/**
	 * 根据主索引id查询带domain信息的人员对象
	 * 
	 * @param personId
	 * @return
	 */
	@Query(value = " select a.*,c.domain_id,c.unique_sign,c.domain_desc from "
			+ " mpi_person_info a left join mpi_index_identifier_rel b on a.field_pk = b.field_pk "
			+ " left join mpi_identifier_domain c on b.domain_id = c.domain_id where a.field_pk = ?1 ", nativeQuery = true)
	PersonInfo findWithDomainInfoById(String personId);

	/**
	 * 根据relationPk和registerOrgCode查询
	 * 
	 * @param relationPk
	 * @param registerOrgCode
	 * @return
	 */
	PersonInfo findFirstByRelationPkAndRegisterOrgCode(String relationPk, String registerOrgCode);

}
