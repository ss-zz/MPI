package com.sinosoft.mpi.model;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.Id;

/**
 * 域
 */
@Entity(name = "MPI_IDENTIFIER_DOMAIN")
public class IdentifierDomain implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	private String domainId;

	private String uniqueSign;

	private String domainDesc;

	private String domainLevel;

	public String getDomainId() {
		return domainId;
	}

	public void setDomainId(String domainId) {
		this.domainId = domainId;
	}

	public String getUniqueSign() {
		return uniqueSign;
	}

	public void setUniqueSign(String uniqueSign) {
		this.uniqueSign = uniqueSign;
	}

	public String getDomainDesc() {
		return domainDesc;
	}

	public void setDomainDesc(String domainDesc) {
		this.domainDesc = domainDesc;
	}

	public String getDomainLevel() {
		return domainLevel;
	}

	public void setDomainLevel(String domainLevel) {
		this.domainLevel = domainLevel;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

}