package com.sinosoft.index.entity;

/**
 * 初筛配置
 */
public class IndexFirstMatchConfig {

	/**
	 * id
	 */
	private String id;

	/**
	 * 字段名
	 */
	private String fieldName;

	/**
	 * 字段描述
	 */
	private String fieldDesc;

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

}
