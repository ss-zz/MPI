package com.sinosoft.mpi.model.biz;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

import org.hibernate.annotations.GenericGenerator;

import com.sinosoft.match.model.MatchField;

@Entity
public class MpiBizMatchFieldCfg implements Serializable {

	private static final long serialVersionUID = 1L;

	/**
	 * id
	 */
	@Id
	@GenericGenerator(name = "system-uuid", strategy = "uuid")
	@GeneratedValue(generator = "system-uuid")
	private String fieldCfgId;

	/**
	 * 配置主键
	 */
	private String configId;

	/**
	 * 字段名称
	 */
	private String propertyName;

	/**
	 * 匹配协议
	 */
	private String agreeProb;

	/**
	 * 不匹配度
	 */
	private String disAgree;

	/**
	 * 匹配阀值
	 */
	private String matchThreshold;

	/**
	 * 匹配函数
	 */
	private String matchFunction;

	/**
	 * 权重
	 */
	private String weight;

	/**
	 * 描述
	 */
	private String cfgDesc;

	public MpiBizMatchFieldCfg() {
		super();
	}

	public MpiBizMatchFieldCfg(MatchField matchField) {
		super();
		this.propertyName = matchField.getFieldName();
		this.matchThreshold = String.valueOf(matchField.getMatchThreshold());
		this.matchFunction = matchField.getComparatorFunction();
		this.weight = String.valueOf(matchField.getWeight());
		this.cfgDesc = matchField.getDesc();
	}

	public MatchField toMatchField() {
		MatchField mf = new MatchField();
		mf.setComparatorFunction(this.matchFunction);
		mf.setDesc(this.cfgDesc);
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
