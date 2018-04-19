package com.sinosoft.mpi.model;

import java.io.Serializable;

/**
 * 匹配结果 table: MPI_MATCH_RESULT
 */
public class MatchResult implements Serializable {

	private static final long serialVersionUID = 2355694833165918242L;
	private String matchResultId;// 匹配结果主键
	private String mpiPk;// 索引信息主键
	private String fieldPk;// 居民信_居民主键
	private String matchDegree;// 匹配度
	private String fieldMatDegrees;// 属性匹配数组

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

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((fieldMatDegrees == null) ? 0 : fieldMatDegrees.hashCode());
		result = prime * result + ((mpiPk == null) ? 0 : mpiPk.hashCode());
		result = prime * result + ((matchDegree == null) ? 0 : matchDegree.hashCode());
		result = prime * result + ((matchResultId == null) ? 0 : matchResultId.hashCode());
		result = prime * result + ((fieldPk == null) ? 0 : fieldPk.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		MatchResult other = (MatchResult) obj;
		if (fieldMatDegrees == null) {
			if (other.fieldMatDegrees != null)
				return false;
		} else if (!fieldMatDegrees.equals(other.fieldMatDegrees))
			return false;
		if (mpiPk == null) {
			if (other.mpiPk != null)
				return false;
		} else if (!mpiPk.equals(other.mpiPk))
			return false;
		if (matchDegree == null) {
			if (other.matchDegree != null)
				return false;
		} else if (!matchDegree.equals(other.matchDegree))
			return false;
		if (matchResultId == null) {
			if (other.matchResultId != null)
				return false;
		} else if (!matchResultId.equals(other.matchResultId))
			return false;
		if (fieldPk == null) {
			if (other.fieldPk != null)
				return false;
		} else if (!fieldPk.equals(other.fieldPk))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "MatchResult [matchResultId=" + matchResultId + ", mpiPk=" + mpiPk + ", fieldPk=" + fieldPk
				+ ", matchDegree=" + matchDegree + ", fieldMatDegrees=" + fieldMatDegrees + "]";
	}
}
