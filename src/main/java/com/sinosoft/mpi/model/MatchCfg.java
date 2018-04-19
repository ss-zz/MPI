package com.sinosoft.mpi.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import com.sinosoft.match.config.MatchConfig;
import com.sinosoft.match.model.MatchField;

/**
 * table: MPI_MATCH_CFG (匹配配置)
 */
public class MatchCfg implements Serializable {

	private static final long serialVersionUID = 1861979038212962634L;

	/* 配置主键 配置主键 */
	private String configId;

	/* 配置描述 配置描述 */
	private String configDesc;

	/* 完全匹配阀值 完全匹配阀值 */
	private String agreeThreshold;

	/* 可能匹配阀值 可能匹配阀值 */
	private String matchThreshold;

	/* 创建日期 创建日期 */
	private String createDate;

	/* 生效状态 0 - 未生效 1 - 生效 */
	private String state;

	/* 字段匹配信息 */
	private List<MatchFieldCfg> matchFieldCfgs;

	public MatchCfg() {
		super();
	}

	public MatchCfg(MatchConfig matchConfig) {
		super();
		this.agreeThreshold = String.valueOf(matchConfig.getAgreementWeightThreshold());
		this.matchThreshold = String.valueOf(matchConfig.getMatchWeightThreshold());
		matchFieldCfgs = new ArrayList<MatchFieldCfg>(matchConfig.getMatchFields().size());
		for (MatchField field : matchConfig.getMatchFields()) {
			MatchFieldCfg fieldCfg = new MatchFieldCfg(field);
			matchFieldCfgs.add(fieldCfg);
		}
	}

	// =======================setter&getter
	/**
	 * 配置主键 配置主键
	 **/
	public String getConfigId() {
		return configId;
	}

	/**
	 * 配置主键 配置主键
	 **/
	public void setConfigId(String configId) {
		this.configId = configId;
	}

	/**
	 * 配置描述 配置描述
	 **/
	public String getConfigDesc() {
		return configDesc;
	}

	/**
	 * 配置描述 配置描述
	 **/
	public void setConfigDesc(String configDesc) {
		this.configDesc = configDesc;
	}

	/**
	 * 完全匹配阀值 完全匹配阀值
	 **/
	public String getAgreeThreshold() {
		return agreeThreshold;
	}

	/**
	 * 完全匹配阀值 完全匹配阀值
	 **/
	public void setAgreeThreshold(String agreeThreshold) {
		this.agreeThreshold = agreeThreshold;
	}

	/**
	 * 可能匹配阀值 可能匹配阀值
	 **/
	public String getMatchThreshold() {
		return matchThreshold;
	}

	/**
	 * 可能匹配阀值 可能匹配阀值
	 **/
	public void setMatchThreshold(String matchThreshold) {
		this.matchThreshold = matchThreshold;
	}

	/**
	 * 创建日期 创建日期
	 **/
	public String getCreateDate() {
		return createDate;
	}

	/**
	 * 创建日期 创建日期
	 **/
	public void setCreateDate(String createDate) {
		this.createDate = createDate;
	}

	/**
	 * 生效状态 0 - 未生效 1 - 生效
	 **/
	public String getState() {
		return state;
	}

	/**
	 * 生效状态 0 - 未生效 1 - 生效
	 **/
	public void setState(String state) {
		this.state = state;
	}

	/**
	 * 字段匹配信息
	 */
	public List<MatchFieldCfg> getMatchFieldCfgs() {
		return matchFieldCfgs;
	}

	/**
	 * 字段匹配信息
	 */
	public void setMatchFieldCfgs(List<MatchFieldCfg> matchFieldCfgs) {
		this.matchFieldCfgs = matchFieldCfgs;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((agreeThreshold == null) ? 0 : agreeThreshold.hashCode());
		result = prime * result + ((configDesc == null) ? 0 : configDesc.hashCode());
		result = prime * result + ((configId == null) ? 0 : configId.hashCode());
		result = prime * result + ((createDate == null) ? 0 : createDate.hashCode());
		result = prime * result + ((matchFieldCfgs == null) ? 0 : matchFieldCfgs.hashCode());
		result = prime * result + ((matchThreshold == null) ? 0 : matchThreshold.hashCode());
		result = prime * result + ((state == null) ? 0 : state.hashCode());
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
		MatchCfg other = (MatchCfg) obj;
		if (agreeThreshold == null) {
			if (other.agreeThreshold != null)
				return false;
		} else if (!agreeThreshold.equals(other.agreeThreshold))
			return false;
		if (configDesc == null) {
			if (other.configDesc != null)
				return false;
		} else if (!configDesc.equals(other.configDesc))
			return false;
		if (configId == null) {
			if (other.configId != null)
				return false;
		} else if (!configId.equals(other.configId))
			return false;
		if (createDate == null) {
			if (other.createDate != null)
				return false;
		} else if (!createDate.equals(other.createDate))
			return false;
		if (matchFieldCfgs == null) {
			if (other.matchFieldCfgs != null)
				return false;
		} else if (!matchFieldCfgs.equals(other.matchFieldCfgs))
			return false;
		if (matchThreshold == null) {
			if (other.matchThreshold != null)
				return false;
		} else if (!matchThreshold.equals(other.matchThreshold))
			return false;
		if (state == null) {
			if (other.state != null)
				return false;
		} else if (!state.equals(other.state))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "MatchCfg [configId=" + configId + ", configDesc=" + configDesc + ", agreeThreshold=" + agreeThreshold
				+ ", matchThreshold=" + matchThreshold + ", createDate=" + createDate + ", state=" + state
				+ ", matchFieldCfgs=" + matchFieldCfgs + "]";
	}

}
