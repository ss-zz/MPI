package com.sinosoft.mpi.model;

/**
 * 居民更新对索引的影响策略
 * 
 * 0.不更新(默认) 1.来就更新 2.不更新,添加事件,人为干预更新 3.根据数据级别进行更新 4.根据数据字段来源级别更新
 */

public enum UpdateStrategy {
	UNUPDATE(0), UPDATE(1), UNUPDATE_MAN(2), UPDATE_LEVEL(3), UPDATE_LEVEL_NEW(4);
	private int updateStrategy;

	private UpdateStrategy(int updateStrategy) {
		this.updateStrategy = updateStrategy;
	}

	public int getUpdateStrategy() {
		return updateStrategy;
	}

}
