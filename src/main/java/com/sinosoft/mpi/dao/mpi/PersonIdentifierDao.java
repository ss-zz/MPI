package com.sinosoft.mpi.dao.mpi;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import com.sinosoft.mpi.model.PersonIdentifier;

/**
 * 居民标志dao
 */
public interface PersonIdentifierDao
		extends JpaRepository<PersonIdentifier, String>, JpaSpecificationExecutor<PersonIdentifier> {

	/**
	 * 根据personId查询
	 * 
	 * @param personId
	 * @return
	 */
	List<PersonIdentifier> findByPersonId(String personId);

}
