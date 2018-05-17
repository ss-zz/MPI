package com.sinosoft.mpi.dao.mpi;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import com.sinosoft.mpi.model.MpiCombineRec;

/**
 * 合并记录dao
 */
public interface MpiCombineRecDao extends JpaRepository<MpiCombineRec, Long>, JpaSpecificationExecutor<MpiCombineRec> {

}
