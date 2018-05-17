package com.sinosoft.mpi.model;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

import org.hibernate.annotations.GenericGenerator;

/**
 * 需要人工干预的人员列表
 */
@Entity(name = "MPI_MAN_OP_PERSON")
public class ManOpPerson implements Serializable {

	private static final long serialVersionUID = 1L;

	/**
	 * 人工干预主键
	 */
	@Id
	@GenericGenerator(name = "system-uuid", strategy = "uuid")
	@GeneratedValue(generator = "system-uuid")
	private String manOpId;

	/**
	 * 居民主键
	 */
	private String fieldPk;

	/**
	 * 干预状态
	 */
	private String manOpStatus;

	/**
	 * 生成时间
	 */
	private String manOpTime;

	/**
	 * 主索引id
	 */
	private String mpiPk;

	public String getManOpId() {
		return manOpId;
	}

	public void setManOpId(String manOpId) {
		this.manOpId = manOpId;
	}

	public String getFieldPk() {
		return fieldPk;
	}

	public void setFieldPk(String fieldPk) {
		this.fieldPk = fieldPk;
	}

	public String getManOpStatus() {
		return manOpStatus;
	}

	public void setManOpStatus(String manOpStatus) {
		this.manOpStatus = manOpStatus;
	}

	public String getManOpTime() {
		return manOpTime;
	}

	public void setManOpTime(String manOpTime) {
		this.manOpTime = manOpTime;
	}

	public String getMpiPk() {
		return mpiPk;
	}

	public void setMpiPk(String mpiPk) {
		this.mpiPk = mpiPk;
	}

}
