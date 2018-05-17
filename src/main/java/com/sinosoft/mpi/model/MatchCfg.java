package com.sinosoft.mpi.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Transient;

import org.hibernate.annotations.GenericGenerator;

import com.sinosoft.match.config.MatchConfig;
import com.sinosoft.match.model.MatchField;

/**
 * 匹配配置
 */
@Entity(name = "MPI_MATCH_CFG")
public class MatchCfg implements Serializable {

	private static final long serialVersionUID = 1L;

	/**
	 * 配置主键
	 */
	@Id
	@GenericGenerator(name = "system-uuid", strategy = "uuid")
	@GeneratedValue(generator = "system-uuid")
	private String configId;

	/**
	 * 配置描述
	 */
	private String configDesc;

	/**
	 * 完全匹配阀值
	 */
	private String agreeThreshold;

	/**
	 * 可能匹配阀值
	 */
	private String matchThreshold;

	/**
	 * 创建日期
	 */
	private String createDate;

	/**
	 * 生效状态 0 - 未生效 1 - 生效
	 */
	private String state;

	/**
	 * 字段匹配信息
	 */
	@Transient
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

	public String getConfigId() {
		return configId;
	}

	public void setConfigId(String configId) {
		this.configId = configId;
	}

	public String getConfigDesc() {
		return configDesc;
	}

	public void setConfigDesc(String configDesc) {
		this.configDesc = configDesc;
	}

	public String getAgreeThreshold() {
		return agreeThreshold;
	}

	public void setAgreeThreshold(String agreeThreshold) {
		this.agreeThreshold = agreeThreshold;
	}

	public String getMatchThreshold() {
		return matchThreshold;
	}

	public void setMatchThreshold(String matchThreshold) {
		this.matchThreshold = matchThreshold;
	}

	public String getCreateDate() {
		return createDate;
	}

	public void setCreateDate(String createDate) {
		this.createDate = createDate;
	}

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}

	public List<MatchFieldCfg> getMatchFieldCfgs() {
		return matchFieldCfgs;
	}

	public void setMatchFieldCfgs(List<MatchFieldCfg> matchFieldCfgs) {
		this.matchFieldCfgs = matchFieldCfgs;
	}

}
