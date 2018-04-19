package com.sinosoft.match.model;

import java.io.Serializable;

/**
 * 匹配的配置信息
 */
public class MatchField implements Serializable {

	private static final long serialVersionUID = 1244105837650056756L;

	private String fieldName;
	// 协议匹配度
	private float agreementProbability;
	// 不匹配度
	private float disagreementProbability;
	// 比较函数的标志
	private String comparatorFunction;
	// 匹配门槛
	private float matchThreshold;

	// 该属性的权重
	private float weight;
	// 描述lable
	private String desc;

	public String getFieldName() {
		return fieldName;
	}

	public void setFieldName(String fieldName) {
		this.fieldName = fieldName;
	}

	public float getAgreementProbability() {
		return agreementProbability;
	}

	public void setAgreementProbability(float agreementProbability) {
		this.agreementProbability = agreementProbability;
	}

	public float getDisagreementProbability() {
		return disagreementProbability;
	}

	public void setDisagreementProbability(float disagreementProbability) {
		this.disagreementProbability = disagreementProbability;
	}

	public String getComparatorFunction() {
		return comparatorFunction;
	}

	public void setComparatorFunction(String comparatorFunction) {
		this.comparatorFunction = comparatorFunction;
	}

	public float getMatchThreshold() {
		return matchThreshold;
	}

	public void setMatchThreshold(float matchThreshold) {
		this.matchThreshold = matchThreshold;
	}

	public float getWeight() {
		return weight;
	}

	public void setWeight(float weight) {
		this.weight = weight;
	}

	public String getDesc() {
		return desc;
	}

	public void setDesc(String desc) {
		this.desc = desc;
	}

}
