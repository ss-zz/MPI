package com.sinosoft.mpi.dao.mpi;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import com.sinosoft.mpi.model.PersonIndex;

/**
 * 主索引dao
 */
public interface PersonIndexDao extends JpaRepository<PersonIndex, String>, JpaSpecificationExecutor<PersonIndex> {

}
