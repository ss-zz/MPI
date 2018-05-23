package com.sinosoft.mpi.dao.biz;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import com.sinosoft.mpi.model.biz.MpiBizBlockGroup;

/**
 * 业务初筛配置组dao
 */
public interface MpiBizBlockGroupDao
		extends JpaRepository<MpiBizBlockGroup, String>, JpaSpecificationExecutor<MpiBizBlockGroup> {

	/**
	 * 根据blockid和groupsign查询
	 * 
	 * @param blockId
	 * @param groupSign
	 * @return
	 */
	List<MpiBizBlockGroup> findByBolckIdAndGroupSign(String blockId, Integer groupSign);

	/**
	 * 根据bolckId删除
	 * @param bolckId
	 * @return
	 */
	long deleteByBolckId(String bolckId);

}
