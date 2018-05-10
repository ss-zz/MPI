package com.sinosoft.mpi.form;

import java.io.Serializable;

/**
 * 匹配记录详情传值
 */
public class MatchDetailForm implements Serializable {

	private static final long serialVersionUID = 4816894582986450809L;

	/**
	 * 字段名
	 */
	private String fieldName;

	/**
	 * 字段中文名
	 */
	private String fieldCnName;

	/**
	 * 居民表中属性值
	 */
	private String personValue;

	/**
	 * 索引表中的属性值
	 */
	private String indexValue;

	/**
	 * 匹配度
	 */
	private String matchDegree;

	public String getFieldName() {
		return fieldName;
	}

	public void setFieldName(String fieldName) {
		this.fieldName = fieldName;
	}

	public String getFieldCnName() {
		return fieldCnName;
	}

	public void setFieldCnName(String fieldCnName) {
		this.fieldCnName = fieldCnName;
	}

	public String getPersonValue() {
		return personValue;
	}

	public void setPersonValue(String personValue) {
		this.personValue = personValue;
	}

	public String getIndexValue() {
		return indexValue;
	}

	public void setIndexValue(String indexValue) {
		this.indexValue = indexValue;
	}

	public String getMatchDegree() {
		return matchDegree;
	}

	public void setMatchDegree(String matchDegree) {
		this.matchDegree = matchDegree;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((fieldCnName == null) ? 0 : fieldCnName.hashCode());
		result = prime * result + ((fieldName == null) ? 0 : fieldName.hashCode());
		result = prime * result + ((indexValue == null) ? 0 : indexValue.hashCode());
		result = prime * result + ((matchDegree == null) ? 0 : matchDegree.hashCode());
		result = prime * result + ((personValue == null) ? 0 : personValue.hashCode());
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
		MatchDetailForm other = (MatchDetailForm) obj;
		if (fieldCnName == null) {
			if (other.fieldCnName != null)
				return false;
		} else if (!fieldCnName.equals(other.fieldCnName))
			return false;
		if (fieldName == null) {
			if (other.fieldName != null)
				return false;
		} else if (!fieldName.equals(other.fieldName))
			return false;
		if (indexValue == null) {
			if (other.indexValue != null)
				return false;
		} else if (!indexValue.equals(other.indexValue))
			return false;
		if (matchDegree == null) {
			if (other.matchDegree != null)
				return false;
		} else if (!matchDegree.equals(other.matchDegree))
			return false;
		if (personValue == null) {
			if (other.personValue != null)
				return false;
		} else if (!personValue.equals(other.personValue))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "MatchDetailForm [fieldName=" + fieldName + ", fieldCnName=" + fieldCnName + ", personValue="
				+ personValue + ", indexValue=" + indexValue + ", matchDegree=" + matchDegree + "]";
	}

}
