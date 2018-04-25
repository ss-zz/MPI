package com.sinosoft.index.model;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

/**
 * 通用业务字段配置
 * 
 * @author sinosoft
 *
 */
@Document(collection = "mpi-biz-common-config")
public class BizCommonFieldConfigModel {

	/**
	 * id
	 */
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private String id;

	/**
	 * 字段唯一标识
	 */
	private String key;

	/**
	 * 字段名
	 */
	private String name;

	/**
	 * 字段备注
	 */
	private String comment;

	/**
	 * 算法
	 */
	private String algorithm;

	/**
	 * 顺序
	 */
	private Integer rank;

	/**
	 * 权重
	 */
	private Float weight;

	public String getKey() {
		return key;
	}

	public void setKey(String key) {
		this.key = key;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getComment() {
		return comment;
	}

	public void setComment(String comment) {
		this.comment = comment;
	}

	public String getAlgorithm() {
		return algorithm;
	}

	public void setAlgorithm(String algorithm) {
		this.algorithm = algorithm;
	}

	public Integer getRank() {
		return rank;
	}

	public void setRank(Integer rank) {
		this.rank = rank;
	}

	public Float getWeight() {
		return weight;
	}

	public void setWeight(Float weight) {
		this.weight = weight;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

}
