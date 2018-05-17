package com.sinosoft.mpi.model;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

import org.hibernate.annotations.GenericGenerator;

/**
 * 匹配结果
 */
@Entity(name = "MPI_MATCH_RESULT")
public class MatchResult implements Serializable {

	private static final long serialVersionUID = 1L;

	/**
	 * 匹配结果主键
	 */
	@Id
	@GenericGenerator(name = "system-uuid", strategy = "uuid")
	@GeneratedValue(generator = "system-uuid")
	private String matchResultId;

	/**
	 * 索引信息主键
	 */
	private String mpiPk;

	/**
	 * 居民主键
	 */
	private String fieldPk;

	/**
	 * 匹配度
	 */
	private String matchDegree;

	/**
	 * 属性匹配数组
	 */
	private String fieldMatDegrees;

	public String getMatchResultId() {
		return matchResultId;
	}

	public void setMatchResultId(String matchResultId) {
		this.matchResultId = matchResultId;
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

	public String getMatchDegree() {
		return matchDegree;
	}

	public void setMatchDegree(String matchDegree) {
		this.matchDegree = matchDegree;
	}

	public String getFieldMatDegrees() {
		return fieldMatDegrees;
	}

	public void setFieldMatDegrees(String fieldMatDegrees) {
		this.fieldMatDegrees = fieldMatDegrees;
	}

}
