package com.sinosoft.mpi.model.biz;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Transient;

import com.sinosoft.mpi.model.SysUser;


import org.hibernate.annotations.GenericGenerator;

/**
 * 业务主索引操作日志
 */
@Entity
public class MpiBizIdxLog implements Serializable {

	private static final long serialVersionUID = 6936464128927712060L;

	@Id
	@GenericGenerator(name = "system-uuid", strategy = "uuid")
	@GeneratedValue(generator = "system-uuid")
	private String blIdxLogId;
	private Date blTime;
	private String blUserId;
	private String blDesc;
	private Double blMatched;
	private String blBizId;
	private String blSerialId;
	private String blInfoSour;
	private String blType;
	
	@Transient
	private Date begin;
	@Transient
	private Date end;
	@Transient
	private String blTime_begin;
	@Transient
	private String blTime_end;
	@Transient
	private String blMatched_begin;
	@Transient
	private String blMatched_end;
	

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

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	public Date getBegin() {
		return begin;
	}

	public void setBegin(Date begin) {
		this.begin = begin;
	}

	public Date getEnd() {
		return end;
	}

	public void setEnd(Date end) {
		this.end = end;
	}

	public String getBlTime_begin() {
		return blTime_begin;
	}

	public void setBlTime_begin(String blTime_begin) {
		this.blTime_begin = blTime_begin;
	}

	public String getBlTime_end() {
		return blTime_end;
	}

	public void setBlTime_end(String blTime_end) {
		this.blTime_end = blTime_end;
	}

	public String getBlMatched_begin() {
		return blMatched_begin;
	}

	public void setBlMatched_begin(String blMatched_begin) {
		this.blMatched_begin = blMatched_begin;
	}

	public String getBlMatched_end() {
		return blMatched_end;
	}

	public void setBlMatched_end(String blMatched_end) {
		this.blMatched_end = blMatched_end;
	}
	
	

}
