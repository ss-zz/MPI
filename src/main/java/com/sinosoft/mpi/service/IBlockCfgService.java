package com.sinosoft.mpi.service;


import com.sinosoft.mpi.model.BlockCfg;

/**   
*    
* @Description  操作    
*   
*/ 
public interface IBlockCfgService extends IService<BlockCfg> {
	
	/**
	 * 更新匹配配置 试生效
	 * @param cfgId
	 */
	void updateEffect(String cfgId);
	
	/**
	 * 取得有效配置
	 */
	BlockCfg queryEffectCfg();

}
