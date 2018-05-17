package com.sinosoft.linshi.entity.ge.mpi;

import java.io.Serializable;

public class MpiManOpPerson implements Serializable {
    private String manOpId;

    private String manOpStatus;

    private String manOpTime;

    private String fieldPk;

    private String mpiPk;

    private static final long serialVersionUID = 1L;

    public String getManOpId() {
        return manOpId;
    }

    public void setManOpId(String manOpId) {
        this.manOpId = manOpId == null ? null : manOpId.trim();
    }

    public String getManOpStatus() {
        return manOpStatus;
    }

    public void setManOpStatus(String manOpStatus) {
        this.manOpStatus = manOpStatus == null ? null : manOpStatus.trim();
    }

    public String getManOpTime() {
        return manOpTime;
    }

    public void setManOpTime(String manOpTime) {
        this.manOpTime = manOpTime == null ? null : manOpTime.trim();
    }

    public String getFieldPk() {
        return fieldPk;
    }

    public void setFieldPk(String fieldPk) {
        this.fieldPk = fieldPk == null ? null : fieldPk.trim();
    }

    public String getMpiPk() {
        return mpiPk;
    }

    public void setMpiPk(String mpiPk) {
        this.mpiPk = mpiPk == null ? null : mpiPk.trim();
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
        MpiManOpPerson other = (MpiManOpPerson) that;
        return (this.getManOpId() == null ? other.getManOpId() == null : this.getManOpId().equals(other.getManOpId()))
            && (this.getManOpStatus() == null ? other.getManOpStatus() == null : this.getManOpStatus().equals(other.getManOpStatus()))
            && (this.getManOpTime() == null ? other.getManOpTime() == null : this.getManOpTime().equals(other.getManOpTime()))
            && (this.getFieldPk() == null ? other.getFieldPk() == null : this.getFieldPk().equals(other.getFieldPk()))
            && (this.getMpiPk() == null ? other.getMpiPk() == null : this.getMpiPk().equals(other.getMpiPk()));
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((getManOpId() == null) ? 0 : getManOpId().hashCode());
        result = prime * result + ((getManOpStatus() == null) ? 0 : getManOpStatus().hashCode());
        result = prime * result + ((getManOpTime() == null) ? 0 : getManOpTime().hashCode());
        result = prime * result + ((getFieldPk() == null) ? 0 : getFieldPk().hashCode());
        result = prime * result + ((getMpiPk() == null) ? 0 : getMpiPk().hashCode());
        return result;
    }
}