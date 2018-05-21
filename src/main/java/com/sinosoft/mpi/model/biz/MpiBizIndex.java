package com.sinosoft.mpi.model.biz;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

import org.hibernate.annotations.GenericGenerator;

import com.sinosoft.mpi.annotation.PropertyDesc;

/**
 * 业务主索引
 */
@Entity
public class MpiBizIndex implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GenericGenerator(name = "system-uuid", strategy = "uuid")
	@GeneratedValue(generator = "system-uuid")
	@PropertyDesc(name = "主键", column = "ID")
	private String id;
	@PropertyDesc(name = "原始业务Id", column = "BIZ_ID")
	private String bizId;
	@PropertyDesc(name = "患者id", column = "BIZ_PATIENT_ID")
	private String bizPatientId;
	@PropertyDesc(name = "患者就诊流水号ID(标识同一次就诊)", column = "BIZ_SERIAL_ID")
	private String bizSerialId;
	@PropertyDesc(name = "住院号", column = "BIZ_INPATIENTNO")
	private String bizInpatientno;
	@PropertyDesc(name = "住院就诊流水号", column = "BIZ_INPATIENT_SERIALNO")
	private String bizInpatientSerialno;
	@PropertyDesc(name = "门诊号", column = "BIZ_CLINICNO")
	private String bizClinicno;
	@PropertyDesc(name = "门诊就诊流水号", column = "BIZ_CLINIC_SERIALNO")
	private String bizClinicSerialno;
	@PropertyDesc(name = "域id (区别业务)", column = "BIZ_SYSTEM_ID")
	private String bizSystemId;
	@PropertyDesc(name = "创建日期", column = "CREATE_DATE")
	private Date createDate;
	@PropertyDesc(name = "数据注册类型：0-新增 1-更新 2-拆分", column = "STATE")
	private Short state;
	@PropertyDesc(name = "备注", column = "REMARK")
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

	public String getBizInpatientSerialno() {
		return bizInpatientSerialno;
	}

	public void setBizInpatientSerialno(String bizInpatientSerialno) {
		this.bizInpatientSerialno = bizInpatientSerialno;
	}

	public String getBizClinicno() {
		return bizClinicno;
	}

	public void setBizClinicno(String bizClinicno) {
		this.bizClinicno = bizClinicno;
	}

	public String getBizClinicSerialno() {
		return bizClinicSerialno;
	}

	public void setBizClinicSerialno(String bizClinicSerialno) {
		this.bizClinicSerialno = bizClinicSerialno;
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
		this.bizInpatientSerialno = bizInpatientSerialno;
		this.bizClinicno = bizClinicno;
		this.bizClinicSerialno = bizClinicSerialno;
		this.bizSystemId = bizSystemId;
		this.createDate = createDate;
		this.state = state;
		this.remark = remark;
	}

	public MpiBizIndex() {
		super();
	}

}
