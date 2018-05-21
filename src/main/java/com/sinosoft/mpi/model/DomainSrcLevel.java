package com.sinosoft.mpi.model;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

import org.hibernate.annotations.GenericGenerator;

/**
 * 域数据源级别
 */
@Entity(name = "MPI_DOMAIN_SRCLEVEL")
public class DomainSrcLevel implements Serializable {

	private static final long serialVersionUID = 1L;

	/**
	 * 主键
	 */
	@Id
	@GenericGenerator(name = "system-uuid", strategy = "uuid")
	@GeneratedValue(generator = "system-uuid")
	private String id;

	/**
	 * 域主键
	 */
	private String domainId;

	/**
	 * 域描述
	 */
	private String domainDesc;

	/**
	 * 域数据源级别
	 */
	private String domainLevel;

	/**
	 * 字段名称
	 */
	private String fieldName;

	/**
	 * 字段描述
	 */
	private String fieldDesc;

	/**
	 * 字段级别
	 */
	private String fieldLevel;

	/**
	 * 数据创建时间
	 */
	private Date createDate;

	/**
	 * 数据更新时间
	 */
	private Date updateDate;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getDomainId() {
		return domainId;
	}

	public void setDomainId(String domainId) {
		this.domainId = domainId;
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

	public String getFieldName() {
		return fieldName;
	}

	public void setFieldName(String fieldName) {
		this.fieldName = fieldName;
	}

	public String getFieldDesc() {
		return fieldDesc;
	}

	public void setFieldDesc(String fieldDesc) {
		this.fieldDesc = fieldDesc;
	}

	public String getFieldLevel() {
		return fieldLevel;
	}

	public void setFieldLevel(String fieldLevel) {
		this.fieldLevel = fieldLevel;
	}

	public Date getCreateDate() {
		return createDate;
	}

	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}

	public Date getUpdateDate() {
		return updateDate;
	}

	public void setUpdateDate(Date updateDate) {
		this.updateDate = updateDate;
	}

}
