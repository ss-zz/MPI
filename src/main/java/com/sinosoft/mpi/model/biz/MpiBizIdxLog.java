package com.sinosoft.mpi.model.biz;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

/**
 * 业务主索引操作日志
 */
@Entity
public class MpiBizIdxLog implements Serializable {

	private static final long serialVersionUID = 6936464128927712060L;

	@Id
	@Column
	@GeneratedValue
	private String blIdxLogId;
	private Date blTime;
	private String blUserId;
	private String blDesc;
	private String blMatched;
	private String blBizId;
	private String blSerialId;
	private String blInfoSour;
	private String blType;

	public String getBlIdxLogId() {
		return blIdxLogId;
	}

	public void setBlIdxLogId(String blIdxLogId) {
		this.blIdxLogId = blIdxLogId;
	}

	public Date getBlTime() {
		return blTime;
	}

	public void setBlTime(Date blTime) {
		this.blTime = blTime;
	}

	public String getBlUserId() {
		return blUserId;
	}

	public void setBlUserId(String blUserId) {
		this.blUserId = blUserId;
	}

	public String getBlDesc() {
		return blDesc;
	}

	public void setBlDesc(String blDesc) {
		this.blDesc = blDesc;
	}

	public String getBlMatched() {
		return blMatched;
	}

	public void setBlMatched(String blMatched) {
		this.blMatched = blMatched;
	}

	public String getBlBizId() {
		return blBizId;
	}

	public void setBlBizId(String blBizId) {
		this.blBizId = blBizId;
	}

	public String getBlSerialId() {
		return blSerialId;
	}

	public void setBlSerialId(String blSerialId) {
		this.blSerialId = blSerialId;
	}

	public String getBlInfoSour() {
		return blInfoSour;
	}

	public void setBlInfoSour(String blInfoSour) {
		this.blInfoSour = blInfoSour;
	}

	public String getBlType() {
		return blType;
	}

	public void setBlType(String blType) {
		this.blType = blType;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

}
