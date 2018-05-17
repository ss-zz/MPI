package com.sinosoft.mpi.model;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

import org.hibernate.annotations.GenericGenerator;

/**
 * 居民标志
 */
@Entity(name = "MPI_PERSON_IDENTIFIER")
public class PersonIdentifier implements Serializable {

	private static final long serialVersionUID = 1L;

	/**
	 * 居民标志主键
	 */
	@Id
	@GenericGenerator(name = "system-uuid", strategy = "uuid")
	@GeneratedValue(generator = "system-uuid")
	private String identifierId;

	/**
	 * 居民主键
	 */
	private String personId;

	/**
	 * 域主键
	 */
	private String domainId;

	/**
	 * 居民标志
	 */
	private String personIdentifier;

	public String getPersonId() {
		return personId;
	}

	public String getDomainId() {
		return domainId;
	}

	public void setDomainId(String domainId) {
		this.domainId = domainId;
	}

	public void setPersonId(String personId) {
		this.personId = personId;
	}

	public String getIdentifierId() {
		return identifierId;
	}

	public void setIdentifierId(String identifierId) {
		this.identifierId = identifierId;
	}

	public String getPersonIdentifier() {
		return personIdentifier;
	}

	public void setPersonIdentifier(String personIdentifier) {
		this.personIdentifier = personIdentifier;
	}

}
