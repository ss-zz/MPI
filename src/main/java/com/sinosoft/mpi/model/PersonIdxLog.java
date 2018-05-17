package com.sinosoft.mpi.model;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Transient;

import org.hibernate.annotations.GenericGenerator;

/**
 * 主索引操作日志
 */
@Entity(name = "MPI_PERSON_IDX_LOG")
public class PersonIdxLog implements Serializable {

	private static final long serialVersionUID = 1L;

	/**
	 * 主索引操作日志主键
	 */
	@Id
	@GenericGenerator(name = "system-uuid", strategy = "uuid")
	@GeneratedValue(generator = "system-uuid")
	private String personIdxLogId;

	/**
	 * 操作类型 1-匹配 2-修订
	 */
	private String opType;

	/**
	 * 操作方式 1-自动合并 2-自动新建 3-自动拆分 4-人工合并 5-人工新建 6-人工拆分
	 */
	private String opStyle;

	/**
	 * 操作时间
	 */
	private String opTime;

	/**
	 * 操作人员主键
	 */
	private String opUserId;

	/**
	 * 操作描述
	 */
	private String opDesc;

	/**
	 * 信息来源 记录域主键
	 */
	private String infoSour;

	/**
	 * 主索引主键
	 */
	private String mpiPk;

	/**
	 * 居民主键
	 */
	private String fieldPk;

	/**
	 * 用于记录人工合并原主索引MPI_PK 2018-01-18
	 */
	@Transient
	private String formermpipk;

	// 以下非库存自动用于传接值查询使用
	@Transient
	private String opTimeEnd;
	@Transient
	private String personName;
	@Transient
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

	public String getMpiPk() {
		return mpiPk;
	}

	public void setMpiPk(String mpiPk) {
		this.mpiPk = mpiPk;
	}

	public String getFieldPk() {
		return fieldPk;
	}

	public void setFieldPk(String fieldPk) {
		this.fieldPk = fieldPk;
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

}
