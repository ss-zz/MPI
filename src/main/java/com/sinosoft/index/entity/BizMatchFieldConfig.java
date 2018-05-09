package com.sinosoft.index.entity;

import javax.persistence.Id;

import org.springframework.data.mongodb.core.mapping.Document;

/**
 * 业务匹配字段配置
 */
@Document(collection = "biz-match-field-config")
public class BizMatchFieldConfig {

	@Id
	private String id;

	/**
	 * 字段名
	 */
	private String fieldName;

	/**
	 * 字段描述
	 */
	private String fieldDesc;

	/**
	 * 算法
	 */
	private String algorithm;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getFieldName() {
		return fieldName;
	}

	public void setFieldName(String fieldName) {
		this.fieldName = fieldName;
	}

	public String getFieldDesc() {
		return fieldDesc;
	}

	public void setFieldDesc(String fieldDesc) {
		this.fieldDesc = fieldDesc;
	}

	public String getAlgorithm() {
		return algorithm;
	}

	public void setAlgorithm(String algorithm) {
		this.algorithm = algorithm;
	}

}
