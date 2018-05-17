package com.sinosoft.mpi.dao.mpi;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import com.sinosoft.mpi.model.MatchFieldCfg;

/**
 * 人员字段匹配配置dao
 */
public interface MatchFieldCfgDao
		extends JpaRepository<MatchFieldCfg, String>, JpaSpecificationExecutor<MatchFieldCfg> {

	/**
	 * 根据configid查询
	 * 
	 * @param configId
	 * @return
	 */
	List<MatchFieldCfg> findByConfigId(String configId);

}
