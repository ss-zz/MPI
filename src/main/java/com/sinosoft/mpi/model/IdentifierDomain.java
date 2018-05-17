package com.sinosoft.mpi.model;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

import org.hibernate.annotations.GenericGenerator;

/**
 * 域
 */
@Entity(name = "MPI_IDENTIFIER_DOMAIN")
public class IdentifierDomain implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GenericGenerator(name = "system-uuid", strategy = "uuid")
	@GeneratedValue(generator = "system-uuid")
	private String domainId;

	private String uniqueSign;

	private String domainType;

	private String domainDesc;

	private String pushAddr;

	private String bookType;

	private String createUserId;

	private Date createDate;

	private String updateUserId;

	private Date updateDate;

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

	public String getDomainType() {
		return domainType;
	}

	public void setDomainType(String domainType) {
		this.domainType = domainType;
	}

	public String getDomainDesc() {
		return domainDesc;
	}

	public void setDomainDesc(String domainDesc) {
		this.domainDesc = domainDesc;
	}

	public String getPushAddr() {
		return pushAddr;
	}

	public void setPushAddr(String pushAddr) {
		this.pushAddr = pushAddr;
	}

	public String getBookType() {
		return bookType;
	}

	public void setBookType(String bookType) {
		this.bookType = bookType;
	}

	public String getCreateUserId() {
		return createUserId;
	}

	public void setCreateUserId(String createUserId) {
		this.createUserId = createUserId;
	}

	public Date getCreateDate() {
		return createDate;
	}

	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}

	public String getUpdateUserId() {
		return updateUserId;
	}

	public void setUpdateUserId(String updateUserId) {
		this.updateUserId = updateUserId;
	}

	public Date getUpdateDate() {
		return updateDate;
	}

	public void setUpdateDate(Date updateDate) {
		this.updateDate = updateDate;
	}

	public String getDomainLevel() {
		return domainLevel;
	}

	public void setDomainLevel(String domainLevel) {
		this.domainLevel = domainLevel;
	}

}