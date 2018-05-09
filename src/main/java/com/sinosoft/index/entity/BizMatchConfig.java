package com.sinosoft.index.entity;

import java.util.List;

/**
 * 业务匹配配置
 */
public class BizMatchConfig {

	private String id;

	/**
	 * 公共业务字段维护
	 */
	private List<BizCommonFieldConfig> commonFieldConfig;

	/**
	 * 详细业务匹配配置
	 */
	private List<BizMatchServConfig> bizMatchServConfigs;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public List<BizCommonFieldConfig> getCommonFieldConfig() {
		return commonFieldConfig;
	}

	public void setCommonFieldConfig(List<BizCommonFieldConfig> commonFieldConfig) {
		this.commonFieldConfig = commonFieldConfig;
	}

	public List<BizMatchServConfig> getBizMatchServConfigs() {
		return bizMatchServConfigs;
	}

	public void setBizMatchServConfigs(List<BizMatchServConfig> bizMatchServConfigs) {
		this.bizMatchServConfigs = bizMatchServConfigs;
	}

}
