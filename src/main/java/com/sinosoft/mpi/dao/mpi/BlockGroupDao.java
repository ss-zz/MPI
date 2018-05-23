package com.sinosoft.mpi.dao.mpi;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import com.sinosoft.mpi.model.BlockGroup;

/**
 * 主索引人员初筛配置dao
 */
public interface BlockGroupDao extends JpaRepository<BlockGroup, String>, JpaSpecificationExecutor<BlockGroup> {

	/**
	 * 根据blockid和groupsign查询
	 * 
	 * @param blockId
	 * @param groupSign
	 * @return
	 */
	List<BlockGroup> findByBolckIdAndGroupSign(String blockId, Integer groupSign);

	/**
	 * 根据blockId删除
	 * 
	 * @param blockId
	 * @return
	 */
	long deleteByBolckId(String blockId);

}
