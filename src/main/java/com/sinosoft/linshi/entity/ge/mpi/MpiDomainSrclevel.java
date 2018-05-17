package com.sinosoft.linshi.entity.ge.mpi;

import java.io.Serializable;
import java.util.Date;

public class MpiDomainSrclevel implements Serializable {
    private String id;

    private String domainId;

    private String domainDesc;

    private String domainLevel;

    private String fieldName;

    private String fieldDesc;

    private String fieldLevel;

    private Date createDate;

    private Date updateDate;

    private static final long serialVersionUID = 1L;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id == null ? null : id.trim();
    }

    public String getDomainId() {
        return domainId;
    }

    public void setDomainId(String domainId) {
        this.domainId = domainId == null ? null : domainId.trim();
    }

    public String getDomainDesc() {
        return domainDesc;
    }

    public void setDomainDesc(String domainDesc) {
        this.domainDesc = domainDesc == null ? null : domainDesc.trim();
    }

    public String getDomainLevel() {
        return domainLevel;
    }

    public void setDomainLevel(String domainLevel) {
        this.domainLevel = domainLevel == null ? null : domainLevel.trim();
    }

    public String getFieldName() {
        return fieldName;
    }

    public void setFieldName(String fieldName) {
        this.fieldName = fieldName == null ? null : fieldName.trim();
    }

    public String getFieldDesc() {
        return fieldDesc;
    }

    public void setFieldDesc(String fieldDesc) {
        this.fieldDesc = fieldDesc == null ? null : fieldDesc.trim();
    }

    public String getFieldLevel() {
        return fieldLevel;
    }

    public void setFieldLevel(String fieldLevel) {
        this.fieldLevel = fieldLevel == null ? null : fieldLevel.trim();
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

    @Override
    public boolean equals(Object that) {
        if (this == that) {
            return true;
        }
        if (that == null) {
            return false;
        }
        if (getClass() != that.getClass()) {
            return false;
        }
        MpiDomainSrclevel other = (MpiDomainSrclevel) that;
        return (this.getId() == null ? other.getId() == null : this.getId().equals(other.getId()))
            && (this.getDomainId() == null ? other.getDomainId() == null : this.getDomainId().equals(other.getDomainId()))
            && (this.getDomainDesc() == null ? other.getDomainDesc() == null : this.getDomainDesc().equals(other.getDomainDesc()))
            && (this.getDomainLevel() == null ? other.getDomainLevel() == null : this.getDomainLevel().equals(other.getDomainLevel()))
            && (this.getFieldName() == null ? other.getFieldName() == null : this.getFieldName().equals(other.getFieldName()))
            && (this.getFieldDesc() == null ? other.getFieldDesc() == null : this.getFieldDesc().equals(other.getFieldDesc()))
            && (this.getFieldLevel() == null ? other.getFieldLevel() == null : this.getFieldLevel().equals(other.getFieldLevel()))
            && (this.getCreateDate() == null ? other.getCreateDate() == null : this.getCreateDate().equals(other.getCreateDate()))
            && (this.getUpdateDate() == null ? other.getUpdateDate() == null : this.getUpdateDate().equals(other.getUpdateDate()));
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((getId() == null) ? 0 : getId().hashCode());
        result = prime * result + ((getDomainId() == null) ? 0 : getDomainId().hashCode());
        result = prime * result + ((getDomainDesc() == null) ? 0 : getDomainDesc().hashCode());
        result = prime * result + ((getDomainLevel() == null) ? 0 : getDomainLevel().hashCode());
        result = prime * result + ((getFieldName() == null) ? 0 : getFieldName().hashCode());
        result = prime * result + ((getFieldDesc() == null) ? 0 : getFieldDesc().hashCode());
        result = prime * result + ((getFieldLevel() == null) ? 0 : getFieldLevel().hashCode());
        result = prime * result + ((getCreateDate() == null) ? 0 : getCreateDate().hashCode());
        result = prime * result + ((getUpdateDate() == null) ? 0 : getUpdateDate().hashCode());
        return result;
    }
}