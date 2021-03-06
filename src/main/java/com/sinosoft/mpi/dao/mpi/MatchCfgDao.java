package com.sinosoft.mpi.dao.mpi;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import com.sinosoft.mpi.model.MatchCfg;

/**
 * 人员匹配配置dao
 */
public interface MatchCfgDao extends JpaRepository<MatchCfg, String>, JpaSpecificationExecutor<MatchCfg> {

	/**
	 * 查询所有生效的数据
	 * 
	 * @param state
	 * @return
	 */
	@Query("select t from #{#entityName} t where t.state = '1' ")
	List<MatchCfg> findAllEffect();

	/**
	 * 使所有配置失效
	 * 
	 * @param state
	 *            状态
	 * @param id
	 *            id
	 */
	@Modifying
	@Query(value = "update #{#entityName} t set t.state = '0' ")
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
	@Query(value = "update #{#entityName} t set t.state = '1' where t.configId = ?1  ")
	void effect(String id);

}
