package com.sinosoft.mpi.model.biz;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

import org.hibernate.annotations.GenericGenerator;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

/**
 * 业务主索引操作日志
 */
@Entity
@EntityListeners(AuditingEntityListener.class)
public class MpiBizIdxLog implements Serializable {

	private static final long serialVersionUID = 6936464128927712060L;

	@Id
	@GenericGenerator(name = "system-uuid", strategy = "uuid")
	@GeneratedValue(generator = "system-uuid")
	private String blIdxLogId;

	/**
	 * 操作时间
	 */
	@CreatedDate
	private Date blTime;

	/**
	 * 操作人
	 */
	@CreatedBy
	private String blUserId;

	private String blDesc;
	private Double blMatched;
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

	public Double getBlMatched() {
		return blMatched;
	}

	public void setBlMatched(Double blMatched) {
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

}
