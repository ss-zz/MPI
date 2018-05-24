package com.sinosoft.mpi.model.biz;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

import org.hibernate.annotations.GenericGenerator;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import com.sinosoft.mpi.annotation.PropertyDesc;

/**
 * 业务主索引
 */
@Entity
@EntityListeners(AuditingEntityListener.class)
public class MpiBizIndex implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GenericGenerator(name = "system-uuid", strategy = "uuid")
	@GeneratedValue(generator = "system-uuid")
	private String id;

	@PropertyDesc(name = "原始业务唯一标识", column = "BIZ_ID")
	private String bizId;

	/**
	 * 患者唯一标识
	 */
	private String bizPatientId;

	/*
	 * 患者就诊流水号ID(标识同一次就诊)
	 */
	private String bizSerialId;

	@PropertyDesc(name = "住院号", column = "BIZ_INPATIENTNO")
	private String bizInpatientno;

	@PropertyDesc(name = "门诊号", column = "BIZ_CLINICNO")
	private String bizClinicno;

	/**
	 * 业务唯一标识
	 */
	private String bizSystemId;

	/**
	 * 创建日期
	 */
	@CreatedBy
	private Date createDate;

	/**
	 * 数据注册类型
	 */
	private Short state;

	/**
	 * 备注
	 */
	private String remark;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getBizId() {
		return bizId;
	}

	public void setBizId(String bizId) {
		this.bizId = bizId;
	}

	public String getBizPatientId() {
		return bizPatientId;
	}

	public void setBizPatientId(String bizPatientId) {
		this.bizPatientId = bizPatientId;
	}

	public String getBizSerialId() {
		return bizSerialId;
	}

	public void setBizSerialId(String bizSerialId) {
		this.bizSerialId = bizSerialId;
	}

	public String getBizInpatientno() {
		return bizInpatientno;
	}

	public void setBizInpatientno(String bizInpatientno) {
		this.bizInpatientno = bizInpatientno;
	}

	public String getBizClinicno() {
		return bizClinicno;
	}

	public void setBizClinicno(String bizClinicno) {
		this.bizClinicno = bizClinicno;
	}

	public String getBizSystemId() {
		return bizSystemId;
	}

	public void setBizSystemId(String bizSystemId) {
		this.bizSystemId = bizSystemId;
	}

	public Date getCreateDate() {
		return createDate;
	}

	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}

	public Short getState() {
		return state;
	}

	public void setState(Short state) {
		this.state = state;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	public MpiBizIndex(String id, String bizId, String bizPatientId, String bizSerialId, String bizInpatientno,
			String bizInpatientSerialno, String bizClinicno, String bizClinicSerialno, String bizSystemId,
			Date createDate, Short state, String remark) {
		super();
		this.id = id;
		this.bizId = bizId;
		this.bizPatientId = bizPatientId;
		this.bizSerialId = bizSerialId;
		this.bizInpatientno = bizInpatientno;
		this.bizClinicno = bizClinicno;
		this.bizSystemId = bizSystemId;
		this.createDate = createDate;
		this.state = state;
		this.remark = remark;
	}

	public MpiBizIndex() {
		super();
	}

}
