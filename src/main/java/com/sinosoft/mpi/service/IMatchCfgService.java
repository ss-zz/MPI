package com.sinosoft.mpi.service;

import com.sinosoft.mpi.model.MatchCfg;

/**
 * 操作
 */
public interface IMatchCfgService extends IService<MatchCfg> {

	/**
	 * 更新匹配配置 试生效
	 * 
	 * @param cfgId
	 */
	void updateEffect(String cfgId);

	/**
	 * 取得有效配置
	 */
	MatchCfg queryEffectCfg();

}
