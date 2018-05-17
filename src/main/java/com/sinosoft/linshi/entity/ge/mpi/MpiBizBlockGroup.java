package com.sinosoft.linshi.entity.ge.mpi;

import java.io.Serializable;
import java.math.BigDecimal;

public class MpiBizBlockGroup implements Serializable {
    private String groupId;

    private String bolckId;

    private BigDecimal groupSign;

    private String dbField;

    private String propertyName;

    private String funName;

    private static final long serialVersionUID = 1L;

    public String getGroupId() {
        return groupId;
    }

    public void setGroupId(String groupId) {
        this.groupId = groupId == null ? null : groupId.trim();
    }

    public String getBolckId() {
        return bolckId;
    }

    public void setBolckId(String bolckId) {
        this.bolckId = bolckId == null ? null : bolckId.trim();
    }

    public BigDecimal getGroupSign() {
        return groupSign;
    }

    public void setGroupSign(BigDecimal groupSign) {
        this.groupSign = groupSign;
    }

    public String getDbField() {
        return dbField;
    }

    public void setDbField(String dbField) {
        this.dbField = dbField == null ? null : dbField.trim();
    }

    public String getPropertyName() {
        return propertyName;
    }

    public void setPropertyName(String propertyName) {
        this.propertyName = propertyName == null ? null : propertyName.trim();
    }

    public String getFunName() {
        return funName;
    }

    public void setFunName(String funName) {
        this.funName = funName == null ? null : funName.trim();
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
        MpiBizBlockGroup other = (MpiBizBlockGroup) that;
        return (this.getGroupId() == null ? other.getGroupId() == null : this.getGroupId().equals(other.getGroupId()))
            && (this.getBolckId() == null ? other.getBolckId() == null : this.getBolckId().equals(other.getBolckId()))
            && (this.getGroupSign() == null ? other.getGroupSign() == null : this.getGroupSign().equals(other.getGroupSign()))
            && (this.getDbField() == null ? other.getDbField() == null : this.getDbField().equals(other.getDbField()))
            && (this.getPropertyName() == null ? other.getPropertyName() == null : this.getPropertyName().equals(other.getPropertyName()))
            && (this.getFunName() == null ? other.getFunName() == null : this.getFunName().equals(other.getFunName()));
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((getGroupId() == null) ? 0 : getGroupId().hashCode());
        result = prime * result + ((getBolckId() == null) ? 0 : getBolckId().hashCode());
        result = prime * result + ((getGroupSign() == null) ? 0 : getGroupSign().hashCode());
        result = prime * result + ((getDbField() == null) ? 0 : getDbField().hashCode());
        result = prime * result + ((getPropertyName() == null) ? 0 : getPropertyName().hashCode());
        result = prime * result + ((getFunName() == null) ? 0 : getFunName().hashCode());
        return result;
    }
}