package com.sinosoft.mpi.dao.mpi;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import com.sinosoft.mpi.model.PersonIdxLog;

/**
 * 主索引操作日志dao
 */
public interface PersonIdxLogDao extends JpaRepository<PersonIdxLog, String>, JpaSpecificationExecutor<PersonIdxLog> {

}
