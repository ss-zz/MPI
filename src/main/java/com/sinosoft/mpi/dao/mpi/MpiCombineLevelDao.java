package com.sinosoft.mpi.dao.mpi;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import com.sinosoft.mpi.model.MpiCombineLevel;

/**
 * 合记录字段级别dao
 */
public interface MpiCombineLevelDao
		extends JpaRepository<MpiCombineLevel, String>, JpaSpecificationExecutor<MpiCombineLevel> {

}
