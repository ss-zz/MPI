package com.sinosoft.index.entity;

import javax.persistence.Id;

import org.springframework.data.mongodb.core.mapping.Document;

/**
 * 业务匹配-公共字段配置
 *
 */
@Document(collection = "biz-common-field-config")
public class BizCommonFieldConfig {

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
	 * 顺序
	 */
	private Integer rank;

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

	public Integer getRank() {
		return rank;
	}

	public void setRank(Integer rank) {
		this.rank = rank;
	}

}
