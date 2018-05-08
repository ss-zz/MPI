package com.sinosoft.index.entity;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

/**
 * 主索引配置表
 */
@Document(collection = "mpi-index-config")
public class MpiIndexConfig {

	/**
	 * id
	 */
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private String id;

	/**
	 * 主索引匹配配置
	 */
	private IndexMatchConfig indexMatchConfig;

	/**
	 * 业务匹配配置表
	 */
	private BizMatchConfig bizMatchConfig;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public IndexMatchConfig getIndexMatchConfig() {
		return indexMatchConfig;
	}

	public void setIndexMatchConfig(IndexMatchConfig indexMatchConfig) {
		this.indexMatchConfig = indexMatchConfig;
	}

	public BizMatchConfig getBizMatchConfig() {
		return bizMatchConfig;
	}

	public void setBizMatchConfig(BizMatchConfig bizMatchConfig) {
		this.bizMatchConfig = bizMatchConfig;
	}

}
