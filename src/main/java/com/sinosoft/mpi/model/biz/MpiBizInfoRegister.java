package com.sinosoft.mpi.model.biz;

import java.io.Serializable;
import java.lang.reflect.InvocationTargetException;

import org.apache.commons.beanutils.BeanUtils;

/**
 * 业务数据注册信息
 */
public class MpiBizInfoRegister implements Serializable {

	private static final long serialVersionUID = 1L;

	/**
	 * 原始业务id
	 */
	private String bizId;

	/**
	 * 住院号
	 */
	private String bizInpatientno;

	/**
	 * 住院流水号
	 */
	private String bizInpatientSerialno;

	/**
	 * 门诊号
	 */
	private String bizClinicno;

	/**
	 * 门诊流水号
	 */
	private String bizClinicSerialno;

	public String getBizId() {
		return bizId;
	}

	public void setBizId(String bizId) {
		this.bizId = bizId;
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

	public MpiBizIndex toIndex() {
		MpiBizIndex index = new MpiBizIndex();
		try {
			BeanUtils.copyProperties(index, this);
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		} catch (InvocationTargetException e) {
			e.printStackTrace();
		}
		return index;
	}

}
