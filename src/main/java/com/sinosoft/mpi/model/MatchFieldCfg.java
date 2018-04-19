package com.sinosoft.mpi.model;

import java.io.Serializable;

import com.sinosoft.match.model.MatchField;

/**
 * table: MPI_MATCH_FIELD_CFG (匹配字段设置)
 */
public class MatchFieldCfg implements Serializable {

	private static final long serialVersionUID = 582728448321065385L;

	/* 匹配字段ID(FIELD_CFG_ID) 匹配字段ID */
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

	public MatchFieldCfg() {
		super();
	}

	public MatchFieldCfg(MatchField matchField) {
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

	// =======================setter&getter
	/**
	 * 匹配字段ID(FIELD_CFG_ID) 匹配字段ID
	 **/
	public String getFieldCfgId() {
		return fieldCfgId;
	}

	/**
	 * 匹配字段ID(FIELD_CFG_ID) 匹配字段ID
	 **/
	public void setFieldCfgId(String fieldCfgId) {
		this.fieldCfgId = fieldCfgId;
	}

	/**
	 * 配置主键(CONFIG_ID) 配置主键
	 **/
	public String getConfigId() {
		return configId;
	}

	/**
	 * 配置主键(CONFIG_ID) 配置主键
	 **/
	public void setConfigId(String configId) {
		this.configId = configId;
	}

	/**
	 * 字段名称(PROPERTY_NAME) 字段名称
	 **/
	public String getPropertyName() {
		return propertyName;
	}

	/**
	 * 字段名称(PROPERTY_NAME) 字段名称
	 **/
	public void setPropertyName(String propertyName) {
		this.propertyName = propertyName;
	}

	/**
	 * 匹配协议(AGREE_PROB) 匹配协议
	 **/
	public String getAgreeProb() {
		return agreeProb;
	}

	/**
	 * 匹配协议(AGREE_PROB) 匹配协议
	 **/
	public void setAgreeProb(String agreeProb) {
		this.agreeProb = agreeProb;
	}

	/**
	 * 不匹配度(DIS_AGREE) 不匹配度
	 **/
	public String getDisAgree() {
		return disAgree;
	}

	/**
	 * 不匹配度(DIS_AGREE) 不匹配度
	 **/
	public void setDisAgree(String disAgree) {
		this.disAgree = disAgree;
	}

	/**
	 * 匹配阀值(MATCH_THRESHOLD) 匹配阀值
	 **/
	public String getMatchThreshold() {
		return matchThreshold;
	}

	/**
	 * 匹配阀值(MATCH_THRESHOLD) 匹配阀值
	 **/
	public void setMatchThreshold(String matchThreshold) {
		this.matchThreshold = matchThreshold;
	}

	/**
	 * 匹配函数(MATCH_FUNCTION) 匹配函数
	 **/
	public String getMatchFunction() {
		return matchFunction;
	}

	/**
	 * 匹配函数(MATCH_FUNCTION) 匹配函数
	 **/
	public void setMatchFunction(String matchFunction) {
		this.matchFunction = matchFunction;
	}

	/**
	 * 权重(WEIGHT) 权重
	 **/
	public String getWeight() {
		return weight;
	}

	/**
	 * 权重(WEIGHT) 权重
	 **/
	public void setWeight(String weight) {
		this.weight = weight;
	}

	/**
	 * 描述(CFG_DESC) 描述
	 **/
	public String getCfgDesc() {
		return cfgDesc;
	}

	/**
	 * 描述(CFG_DESC) 描述
	 **/
	public void setCfgDesc(String cfgDesc) {
		this.cfgDesc = cfgDesc;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((agreeProb == null) ? 0 : agreeProb.hashCode());
		result = prime * result + ((cfgDesc == null) ? 0 : cfgDesc.hashCode());
		result = prime * result + ((configId == null) ? 0 : configId.hashCode());
		result = prime * result + ((disAgree == null) ? 0 : disAgree.hashCode());
		result = prime * result + ((fieldCfgId == null) ? 0 : fieldCfgId.hashCode());
		result = prime * result + ((matchFunction == null) ? 0 : matchFunction.hashCode());
		result = prime * result + ((matchThreshold == null) ? 0 : matchThreshold.hashCode());
		result = prime * result + ((propertyName == null) ? 0 : propertyName.hashCode());
		result = prime * result + ((weight == null) ? 0 : weight.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		MatchFieldCfg other = (MatchFieldCfg) obj;
		if (agreeProb == null) {
			if (other.agreeProb != null)
				return false;
		} else if (!agreeProb.equals(other.agreeProb))
			return false;
		if (cfgDesc == null) {
			if (other.cfgDesc != null)
				return false;
		} else if (!cfgDesc.equals(other.cfgDesc))
			return false;
		if (configId == null) {
			if (other.configId != null)
				return false;
		} else if (!configId.equals(other.configId))
			return false;
		if (disAgree == null) {
			if (other.disAgree != null)
				return false;
		} else if (!disAgree.equals(other.disAgree))
			return false;
		if (fieldCfgId == null) {
			if (other.fieldCfgId != null)
				return false;
		} else if (!fieldCfgId.equals(other.fieldCfgId))
			return false;
		if (matchFunction == null) {
			if (other.matchFunction != null)
				return false;
		} else if (!matchFunction.equals(other.matchFunction))
			return false;
		if (matchThreshold == null) {
			if (other.matchThreshold != null)
				return false;
		} else if (!matchThreshold.equals(other.matchThreshold))
			return false;
		if (propertyName == null) {
			if (other.propertyName != null)
				return false;
		} else if (!propertyName.equals(other.propertyName))
			return false;
		if (weight == null) {
			if (other.weight != null)
				return false;
		} else if (!weight.equals(other.weight))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "MatchFieldCfg [fieldCfgId=" + fieldCfgId + ", configId=" + configId + ", propertyName=" + propertyName
				+ ", agreeProb=" + agreeProb + ", disAgree=" + disAgree + ", matchThreshold=" + matchThreshold
				+ ", matchFunction=" + matchFunction + ", weight=" + weight + ", cfgDesc=" + cfgDesc + "]";
	}

}
