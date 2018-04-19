package com.sinosoft.mpi.dao;


import com.sinosoft.mpi.model.MatchCfg;
/**
* @Description: 匹配配置数据操作类接口
* 
 */
public interface IMatchCfgDao extends IBaseDao<MatchCfg> {

	/**
	 * 使符合id的配置生效 并将其他配置设为无效
	 * @param cfgId
	 */
	void effect(String cfgId);
	
}