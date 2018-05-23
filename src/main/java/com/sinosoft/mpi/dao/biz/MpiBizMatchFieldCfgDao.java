package com.sinosoft.mpi.dao.biz;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import com.sinosoft.mpi.model.biz.MpiBizMatchFieldCfg;

/**
 * 业务初筛配置dao
 */
public interface MpiBizMatchFieldCfgDao
		extends JpaRepository<MpiBizMatchFieldCfg, String>, JpaSpecificationExecutor<MpiBizMatchFieldCfg> {

	/**
	 * 根据configid查询
	 * 
	 * @param configId
	 * @return
	 */
	List<MpiBizMatchFieldCfg> findByConfigId(String configId);

	/**
	 * 根据configId删除
	 * 
	 * @param configId
	 * @return
	 */
	long deleteByConfigId(String configId);

}
