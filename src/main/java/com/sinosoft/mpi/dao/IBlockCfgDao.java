package com.sinosoft.mpi.dao;


import com.sinosoft.mpi.model.BlockCfg;
/**
* @Description: 数据操作类接口
* 
 */
public interface IBlockCfgDao extends IBaseDao<BlockCfg> {
	/**
	 * 使符合id的配置生效 并将其他配置设为无效
	 * @param cfgId
	 */
	void effect(String cfgId);
	
}