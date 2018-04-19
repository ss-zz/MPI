package com.sinosoft.mpi.model;

import java.io.Serializable;

/**
 * 主索引操作关系表 table: MPI_PERSON_IDX_LOG
 */
public class IndexOperate implements Serializable {

	private static final long serialVersionUID = 8710337587249709515L;

	private String personIdxLogId;// 主索引操作日志主键
	private String opType;// 操作类型 1-匹配 2-修订
	private String opStyle;// 操作方式 1-自动合并 2-自动新建 3-自动拆分 4-人工合并 5-人工新建 6-人工拆分
	private String opTime;// 操作时间
	private String opUserId;// 操作人员主键
	private String opDesc;// 操作描述
	private String infoSour; // 信息来源 记录域主键
	private String mpipk;// 主索引主键
	private String fieldpk;// 居民主键
	private String formermpipk;// 用于记录人工合并原主索引MPI_PK 2018-01-18

	public String getPersonIdxLogId() {
		return personIdxLogId;
	}

	public void setPersonIdxLogId(String personIdxLogId) {
		this.personIdxLogId = personIdxLogId;
	}

	public String getOpType() {
		return opType;
	}

	public void setOpType(String opType) {
		this.opType = opType;
	}

	public String getOpStyle() {
		return opStyle;
	}

	public void setOpStyle(String opStyle) {
		this.opStyle = opStyle;
	}

	public String getOpTime() {
		return opTime;
	}

	public void setOpTime(String opTime) {
		this.opTime = opTime;
	}

	public String getOpUserId() {
		return opUserId;
	}

	public void setOpUserId(String opUserId) {
		this.opUserId = opUserId;
	}

	public String getOpDesc() {
		return opDesc;
	}

	public void setOpDesc(String opDesc) {
		this.opDesc = opDesc;
	}

	public String getInfoSour() {
		return infoSour;
	}

	public void setInfoSour(String infoSour) {
		this.infoSour = infoSour;
	}

	public String getMpipk() {
		return mpipk;
	}

	public void setMpipk(String mpipk) {
		this.mpipk = mpipk;
	}

	public String getFieldpk() {
		return fieldpk;
	}

	public void setFieldpk(String fieldpk) {
		this.fieldpk = fieldpk;
	}

	public String getFormermpipk() {
		return formermpipk;
	}

	public void setFormermpipk(String formermpipk) {
		this.formermpipk = formermpipk;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((fieldpk == null) ? 0 : fieldpk.hashCode());
		result = prime * result + ((infoSour == null) ? 0 : infoSour.hashCode());
		result = prime * result + ((mpipk == null) ? 0 : mpipk.hashCode());
		result = prime * result + ((opDesc == null) ? 0 : opDesc.hashCode());
		result = prime * result + ((opStyle == null) ? 0 : opStyle.hashCode());
		result = prime * result + ((opTime == null) ? 0 : opTime.hashCode());
		result = prime * result + ((opType == null) ? 0 : opType.hashCode());
		result = prime * result + ((opUserId == null) ? 0 : opUserId.hashCode());
		result = prime * result + ((personIdxLogId == null) ? 0 : personIdxLogId.hashCode());
		result = prime * result + ((formermpipk == null) ? 0 : formermpipk.hashCode());
		return result;
	}

	@Override
	public String toString() {
		return "PersonIdxLog [personIdxLogId=" + personIdxLogId + ", opType=" + opType + ", opStyle=" + opStyle
				+ ", opTime=" + opTime + ", opUserId=" + opUserId + ", opDesc=" + opDesc + ", infoSour=" + infoSour
				+ ", mpipk=" + mpipk + ", fieldpk=" + fieldpk + "]";
	}
}
