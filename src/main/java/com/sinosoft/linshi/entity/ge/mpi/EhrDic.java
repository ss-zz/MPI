package com.sinosoft.linshi.entity.ge.mpi;

import java.io.Serializable;

public class EhrDic implements Serializable {
    private String fieldPk;

    private String dicId;

    private String dicName;

    private String dicTypeId;

    private String dicRemark;

    private String parentId;

    private static final long serialVersionUID = 1L;

    public String getFieldPk() {
        return fieldPk;
    }

    public void setFieldPk(String fieldPk) {
        this.fieldPk = fieldPk == null ? null : fieldPk.trim();
    }

    public String getDicId() {
        return dicId;
    }

    public void setDicId(String dicId) {
        this.dicId = dicId == null ? null : dicId.trim();
    }

    public String getDicName() {
        return dicName;
    }

    public void setDicName(String dicName) {
        this.dicName = dicName == null ? null : dicName.trim();
    }

    public String getDicTypeId() {
        return dicTypeId;
    }

    public void setDicTypeId(String dicTypeId) {
        this.dicTypeId = dicTypeId == null ? null : dicTypeId.trim();
    }

    public String getDicRemark() {
        return dicRemark;
    }

    public void setDicRemark(String dicRemark) {
        this.dicRemark = dicRemark == null ? null : dicRemark.trim();
    }

    public String getParentId() {
        return parentId;
    }

    public void setParentId(String parentId) {
        this.parentId = parentId == null ? null : parentId.trim();
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
        EhrDic other = (EhrDic) that;
        return (this.getFieldPk() == null ? other.getFieldPk() == null : this.getFieldPk().equals(other.getFieldPk()))
            && (this.getDicId() == null ? other.getDicId() == null : this.getDicId().equals(other.getDicId()))
            && (this.getDicName() == null ? other.getDicName() == null : this.getDicName().equals(other.getDicName()))
            && (this.getDicTypeId() == null ? other.getDicTypeId() == null : this.getDicTypeId().equals(other.getDicTypeId()))
            && (this.getDicRemark() == null ? other.getDicRemark() == null : this.getDicRemark().equals(other.getDicRemark()))
            && (this.getParentId() == null ? other.getParentId() == null : this.getParentId().equals(other.getParentId()));
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((getFieldPk() == null) ? 0 : getFieldPk().hashCode());
        result = prime * result + ((getDicId() == null) ? 0 : getDicId().hashCode());
        result = prime * result + ((getDicName() == null) ? 0 : getDicName().hashCode());
        result = prime * result + ((getDicTypeId() == null) ? 0 : getDicTypeId().hashCode());
        result = prime * result + ((getDicRemark() == null) ? 0 : getDicRemark().hashCode());
        result = prime * result + ((getParentId() == null) ? 0 : getParentId().hashCode());
        return result;
    }
}