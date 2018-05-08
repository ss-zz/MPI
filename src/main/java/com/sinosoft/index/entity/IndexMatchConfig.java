package com.sinosoft.index.entity;

import java.util.List;

/**
 * 主索引匹配配置
 */
public class IndexMatchConfig {

	/**
	 * 初筛配置
	 */
	private List<List<IndexFirstMatchConfig>> firstMatchConfigs;

	/**
	 * 字段匹配配置
	 */
	private List<IndexFieldMatchConfig> fieldMatchConfigs;

	public List<List<IndexFirstMatchConfig>> getFirstMatchConfigs() {
		return firstMatchConfigs;
	}

	public void setFirstMatchConfigs(List<List<IndexFirstMatchConfig>> firstMatchConfigs) {
		this.firstMatchConfigs = firstMatchConfigs;
	}

	public List<IndexFieldMatchConfig> getFieldMatchConfigs() {
		return fieldMatchConfigs;
	}

	public void setFieldMatchConfigs(List<IndexFieldMatchConfig> fieldMatchConfigs) {
		this.fieldMatchConfigs = fieldMatchConfigs;
	}

}
