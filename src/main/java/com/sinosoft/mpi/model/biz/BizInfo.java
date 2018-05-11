package com.sinosoft.mpi.model.biz;

import java.io.Serializable;

/**
 * 业务信息
 */
public class BizInfo implements Serializable {

	private static final long serialVersionUID = 1L;

	private String id;
	private String bizId;
	private String bizPatientId;
	private String bizSerialId;
	private String bizInpatientno;
	private String bizInpatientSerialno;
	private String bizClinicno;
	private String bizClinicSerialno;
	private String systemId;

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

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getSystemId() {
		return systemId;
	}

	public void setSystemId(String systemId) {
		this.systemId = systemId;
	}

	public BizIndex toIndex() {
		// TODO info 转 index
		return new BizIndex();
	}

}
