package com.sinosoft.mpi.model.biz;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

import com.sinosoft.match.model.MatchField;

@Entity()
public class MpiBizMatchFieldCfg implements Serializable {

	private static final long serialVersionUID = 1L;

	/**
	 * id
	 */
	@Id
	@Column
	@GeneratedValue
	private String fieldCfgId;

	/* 配置主键(CONFIG_ID) 配置主键 */
	private String configId;

	/* 字段名称(PROPERTY_NAME) 字段名称 */
	private String propertyName;

	/* 匹配协议(AGREE_PROB) 匹配协议 */
	private String agreeProb;

	/* 不匹配度(DIS_AGREE) 不匹配度 */
	private String disAgree;

	/* 匹配阀值(MATCH_THRESHOLD) 匹配阀值 */
	private String matchThreshold;

	/* 匹配函数(MATCH_FUNCTION) 匹配函数 */
	private String matchFunction;

	/* 权重(WEIGHT) 权重 */
	private String weight;

	/* 描述(CFG_DESC) 描述 */
	private String cfgDesc;

	public MpiBizMatchFieldCfg() {
		super();
	}

	public MpiBizMatchFieldCfg(MatchField matchField) {
		super();
		this.propertyName = matchField.getFieldName();
		this.agreeProb = String.valueOf(matchField.getAgreementProbability());
		this.disAgree = String.valueOf(matchField.getDisagreementProbability());
		this.matchThreshold = String.valueOf(matchField.getMatchThreshold());
		this.matchFunction = matchField.getComparatorFunction();
		this.weight = String.valueOf(matchField.getWeight());
		this.cfgDesc = matchField.getDesc();
	}

	public MatchField toMatchField() {
		MatchField mf = new MatchField();
		mf.setAgreementProbability(Float.parseFloat(this.agreeProb));
		mf.setComparatorFunction(this.matchFunction);
		mf.setDesc(this.cfgDesc);
		mf.setDisagreementProbability(Float.parseFloat(this.disAgree));
		mf.setFieldName(this.propertyName);
		mf.setMatchThreshold(Float.parseFloat(this.matchThreshold));
		mf.setWeight(Float.parseFloat(this.weight));
		return mf;
	}

	public String getFieldCfgId() {
		return fieldCfgId;
	}

	public void setFieldCfgId(String fieldCfgId) {
		this.fieldCfgId = fieldCfgId;
	}

	public String getConfigId() {
		return configId;
	}

	public void setConfigId(String configId) {
		this.configId = configId;
	}

	public String getPropertyName() {
		return propertyName;
	}

	public void setPropertyName(String propertyName) {
		this.propertyName = propertyName;
	}

	public String getAgreeProb() {
		return agreeProb;
	}

	public void setAgreeProb(String agreeProb) {
		this.agreeProb = agreeProb;
	}

	public String getDisAgree() {
		return disAgree;
	}

	public void setDisAgree(String disAgree) {
		this.disAgree = disAgree;
	}

	public String getMatchThreshold() {
		return matchThreshold;
	}

	public void setMatchThreshold(String matchThreshold) {
		this.matchThreshold = matchThreshold;
	}

	public String getMatchFunction() {
		return matchFunction;
	}

	public void setMatchFunction(String matchFunction) {
		this.matchFunction = matchFunction;
	}

	public String getWeight() {
		return weight;
	}

	public void setWeight(String weight) {
		this.weight = weight;
	}

	public String getCfgDesc() {
		return cfgDesc;
	}

	public void setCfgDesc(String cfgDesc) {
		this.cfgDesc = cfgDesc;
	}

}
