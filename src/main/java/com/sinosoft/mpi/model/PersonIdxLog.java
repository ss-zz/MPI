package com.sinosoft.mpi.model;

import java.io.Serializable;

/**
 * 主索引操作日志 table: MPI_PERSON_IDX_LOG
 */
public class PersonIdxLog implements Serializable {

	private static final long serialVersionUID = 6936464128927712060L;
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

	// 一下非库存自动用于传接值查询使用
	private String opTimeEnd;
	private String personName;
	private String personIdcard;

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

	public String getFieldpk() {
		return fieldpk;
	}

	public void setFieldpk(String fieldpk) {
		this.fieldpk = fieldpk;
	}

	public String getMpipk() {
		return mpipk;
	}

	public void setMpipk(String mpipk) {
		this.mpipk = mpipk;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	public String getOpTimeEnd() {
		return opTimeEnd;
	}

	public void setOpTimeEnd(String opTimeEnd) {
		this.opTimeEnd = opTimeEnd;
	}

	public String getPersonName() {
		return personName;
	}

	public void setPersonName(String personName) {
		this.personName = personName;
	}

	public String getPersonIdcard() {
		return personIdcard;
	}

	public void setPersonIdcard(String personIdcard) {
		this.personIdcard = personIdcard;
	}

	public String getFormermpipk() {
		return formermpipk;
	}

	public void setFormermpipk(String formermpipk) {
		this.formermpipk = formermpipk;
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
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		PersonIdxLog other = (PersonIdxLog) obj;
		if (mpipk == null) {
			if (other.mpipk != null)
				return false;
		} else if (!mpipk.equals(mpipk))
			return false;
		if (infoSour == null) {
			if (other.infoSour != null)
				return false;
		} else if (!infoSour.equals(other.infoSour))
			return false;
		if (fieldpk == null) {
			if (other.fieldpk != null)
				return false;
		} else if (!fieldpk.equals(other.fieldpk))
			return false;
		if (opDesc == null) {
			if (other.opDesc != null)
				return false;
		} else if (!opDesc.equals(other.opDesc))
			return false;
		if (opStyle == null) {
			if (other.opStyle != null)
				return false;
		} else if (!opStyle.equals(other.opStyle))
			return false;
		if (opTime == null) {
			if (other.opTime != null)
				return false;
		} else if (!opTime.equals(other.opTime))
			return false;
		if (opType == null) {
			if (other.opType != null)
				return false;
		} else if (!opType.equals(other.opType))
			return false;
		if (opUserId == null) {
			if (other.opUserId != null)
				return false;
		} else if (!opUserId.equals(other.opUserId))
			return false;
		if (personIdxLogId == null) {
			if (other.personIdxLogId != null)
				return false;
		} else if (!personIdxLogId.equals(other.personIdxLogId))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "PersonIdxLog [personIdxLogId=" + personIdxLogId + ", opType=" + opType + ", opStyle=" + opStyle
				+ ", opTime=" + opTime + ", opUserId=" + opUserId + ", opDesc=" + opDesc + ", infoSour=" + infoSour
				+ ", mpipk=" + mpipk + ", fieldpk=" + fieldpk + "]";
	}
}
