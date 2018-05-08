package com.sinosoft.index.entity;

import java.util.List;

import org.springframework.data.mongodb.core.mapping.Document;

/**
 * 业务匹配配置-单个业务
 */
@Document(collection = "biz-match-serv-config")
public class BizMatchServConfig {

	private String id;

	/**
	 * 业务名
	 */
	private String servName;

	/**
	 * 业务描述
	 */
	private String servDesc;

	/**
	 * 业务字段匹配配置
	 */
	private List<BizMatchFieldConfig> bizMatchFieldConfigs;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getServName() {
		return servName;
	}

	public void setServName(String servName) {
		this.servName = servName;
	}

	public String getServDesc() {
		return servDesc;
	}

	public void setServDesc(String servDesc) {
		this.servDesc = servDesc;
	}

	public List<BizMatchFieldConfig> getBizMatchFieldConfigs() {
		return bizMatchFieldConfigs;
	}

	public void setBizMatchFieldConfigs(List<BizMatchFieldConfig> bizMatchFieldConfigs) {
		this.bizMatchFieldConfigs = bizMatchFieldConfigs;
	}

}
