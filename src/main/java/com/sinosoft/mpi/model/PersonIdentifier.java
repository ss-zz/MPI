package com.sinosoft.mpi.model;

import java.io.Serializable;

/**
 * 居民标志 table:MPI_PERSON_IDENTIFIER
 */
public class PersonIdentifier implements Serializable {

	private static final long serialVersionUID = 3314024038778094508L;

	private String identifierId; // 居民标志主键
	private String personId; // 居民主键
	private String domainId; // 域主键
	private String personIdentifier; // 居民标志

	// ========================setter&&getter
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

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((domainId == null) ? 0 : domainId.hashCode());
		result = prime * result + ((identifierId == null) ? 0 : identifierId.hashCode());
		result = prime * result + ((personId == null) ? 0 : personId.hashCode());
		result = prime * result + ((personIdentifier == null) ? 0 : personIdentifier.hashCode());
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
		PersonIdentifier other = (PersonIdentifier) obj;
		if (domainId == null) {
			if (other.domainId != null)
				return false;
		} else if (!domainId.equals(other.domainId))
			return false;
		if (identifierId == null) {
			if (other.identifierId != null)
				return false;
		} else if (!identifierId.equals(other.identifierId))
			return false;
		if (personId == null) {
			if (other.personId != null)
				return false;
		} else if (!personId.equals(other.personId))
			return false;
		if (personIdentifier == null) {
			if (other.personIdentifier != null)
				return false;
		} else if (!personIdentifier.equals(other.personIdentifier))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "PersonIdentifier [identifierId=" + identifierId + ", personId=" + personId + ", domainId=" + domainId
				+ ", personIdentifier=" + personIdentifier + "]";
	}

}
