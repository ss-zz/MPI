package com.sinosoft.mpi.model.biz;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Transient;

import org.hibernate.annotations.GenericGenerator;

import com.sinosoft.bizmatch.config.BizMatchConfig;
import com.sinosoft.match.model.MatchField;

@Entity()
public class MpiBizMatchCfg implements Serializable {

	private static final long serialVersionUID = 1L;

	/**
	 * id
	 */
	@Id
	@Column
	@GenericGenerator(name = "system-uuid", strategy = "uuid")
	@GeneratedValue(generator = "system-uuid")
	private String configId;

	/* 配置描述 配置描述 */
	private String configDesc;

	/* 完全匹配阀值 完全匹配阀值 */
	private String agreeThreshold;

	/* 可能匹配阀值 可能匹配阀值 */
	private String matchThreshold;

	/* 创建日期 创建日期 */
	private Date createDate;

	/* 生效状态 0 - 未生效 1 - 生效 */
	private String state;

	/* 字段匹配信息 */
	@Transient
	private List<MpiBizMatchFieldCfg> matchFieldCfgs;

	public MpiBizMatchCfg() {
		super();
	}

	public MpiBizMatchCfg(BizMatchConfig matchConfig) {
		super();
		this.agreeThreshold = String.valueOf(matchConfig.getAgreementWeightThreshold());
		this.matchThreshold = String.valueOf(matchConfig.getMatchWeightThreshold());
		matchFieldCfgs = new ArrayList<MpiBizMatchFieldCfg>(matchConfig.getMatchFields().size());
		for (MatchField field : matchConfig.getMatchFields()) {
			MpiBizMatchFieldCfg fieldCfg = new MpiBizMatchFieldCfg(field);
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

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}

	public List<MpiBizMatchFieldCfg> getMatchFieldCfgs() {
		return matchFieldCfgs;
	}

	public void setMatchFieldCfgs(List<MpiBizMatchFieldCfg> matchFieldCfgs) {
		this.matchFieldCfgs = matchFieldCfgs;
	}

	public Date getCreateDate() {
		return createDate;
	}

	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}

}
