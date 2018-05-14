package com.sinosoft.mpi.dao.biz;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import com.sinosoft.mpi.model.biz.MpiBizBlockCfg;

/**
 * 业务初筛配置dao
 */
public interface MpiBizBlockCfgDao
		extends JpaRepository<MpiBizBlockCfg, String>, JpaSpecificationExecutor<MpiBizBlockCfg> {

	/**
	 * 查询所有生效的数据
	 * 
	 * @param state
	 * @return
	 */
	@Query("select cfg from  MpiBizBlockCfg cfg where cfg.state = '1' ")
	List<MpiBizBlockCfg> findAllEffect();

	/**
	 * 使所有配置失效
	 * 
	 * @param state
	 *            状态
	 * @param id
	 *            id
	 */
	@Modifying
	@Query(value = "update MpiBizBlockCfg cfg set cfg.state = '0' ")
	void uneffectAll();

	/**
	 * 根据主键使配置生效
	 * 
	 * @param state
	 *            状态
	 * @param id
	 *            id
	 */
	@Modifying
	@Query(value = "update MpiBizBlockCfg cfg set cfg.state = '0' where  cfg.blockId = ?1  ")
	void effect(String id);

}
