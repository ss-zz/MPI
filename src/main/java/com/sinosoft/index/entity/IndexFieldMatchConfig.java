package com.sinosoft.index.entity;

import javax.persistence.Id;

import org.springframework.data.mongodb.core.mapping.Document;

/**
 * 主索引字段匹配配置
 */
@Document(collection = "index-field-match-config")
public class IndexFieldMatchConfig {

	/**
	 * id
	 */
	@Id
	private String id;

	/**
	 * 字段唯一标识
	 */
	private String key;

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

	/**
	 * 权重
	 */
	private Float weight;

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

	public String getKey() {
		return key;
	}

	public void setKey(String key) {
		this.key = key;
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

	public Float getWeight() {
		return weight;
	}

	public void setWeight(Float weight) {
		this.weight = weight;
	}

	public Integer getRank() {
		return rank;
	}

	public void setRank(Integer rank) {
		this.rank = rank;
	}

}
