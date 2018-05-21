package com.sinosoft.mpi.model;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;

/**
 * 合并信息标示关系
 */
@Entity(name = "MPI_INDEX_IDENTIFIER_REL")
public class IndexIdentifierRel implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "CombineNoSequence")
	@SequenceGenerator(name = "CombineNoSequence", sequenceName = "SEQ_COMBINE_NO", allocationSize = 1)
	private Long combineNo;

	private String domainId;

	private String mpiPk;

	private String fieldPk;

	private String personIdentifier;

	private Long combineRec;

	public Long getCombineNo() {
		return combineNo;
	}

	public void setCombineNo(Long combineNo) {
		this.combineNo = combineNo;
	}

	public String getDomainId() {
		return domainId;
	}

	public void setDomainId(String domainId) {
		this.domainId = domainId;
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

	public String getPersonIdentifier() {
		return personIdentifier;
	}

	public void setPersonIdentifier(String personIdentifier) {
		this.personIdentifier = personIdentifier;
	}

	public Long getCombineRec() {
		return combineRec;
	}

	public void setCombineRec(Long combineRec) {
		this.combineRec = combineRec;
	}

}