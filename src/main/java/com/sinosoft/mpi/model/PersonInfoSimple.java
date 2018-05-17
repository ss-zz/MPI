package com.sinosoft.mpi.model;

import java.io.Serializable;

/**
 * 简单的person对象 用于业务系统向MPI请求使用
 */
public class PersonInfoSimple implements Serializable {

	private static final long serialVersionUID = 6881459932893583797L;

	private String domainUniqueSign; // 接收域唯一标识信息
	private String identifier; // 接收人员信息在该域的主键

	public PersonInfoSimple() {
		super();
	}

	public PersonInfoSimple(String domainUniqueSign, String identifier) {
		super();
		this.domainUniqueSign = domainUniqueSign;
		this.identifier = identifier;
	}

	public String getDomainUniqueSign() {
		return domainUniqueSign;
	}

	public void setDomainUniqueSign(String domainUniqueSign) {
		this.domainUniqueSign = domainUniqueSign;
	}

	public String getIdentifier() {
		return identifier;
	}

	public void setIdentifier(String identifier) {
		this.identifier = identifier;
	}

	public PersonInfo toPersonInfo() {
		PersonInfo person = new PersonInfo();
		person.setUniqueSign(domainUniqueSign);
		return person;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((domainUniqueSign == null) ? 0 : domainUniqueSign.hashCode());
		result = prime * result + ((identifier == null) ? 0 : identifier.hashCode());
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
		PersonInfoSimple other = (PersonInfoSimple) obj;
		if (domainUniqueSign == null) {
			if (other.domainUniqueSign != null)
				return false;
		} else if (!domainUniqueSign.equals(other.domainUniqueSign))
			return false;
		if (identifier == null) {
			if (other.identifier != null)
				return false;
		} else if (!identifier.equals(other.identifier))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "PersonInfoSimple [domainUniqueSign=" + domainUniqueSign + ", identifier=" + identifier + "]";
	}

}
