package com.sinosoft.mpi.dao.mpi;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import com.sinosoft.mpi.model.PersonInfoHistory;

/**
 * 主索引人员dao
 */
public interface PersonInfoHistoryDao
		extends JpaRepository<PersonInfoHistory, String>, JpaSpecificationExecutor<PersonInfoHistory> {

}
