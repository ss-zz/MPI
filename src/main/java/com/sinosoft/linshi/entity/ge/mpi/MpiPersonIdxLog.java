package com.sinosoft.linshi.entity.ge.mpi;

import java.io.Serializable;

public class MpiPersonIdxLog implements Serializable {
    private String personIdxLogId;

    private String opType;

    private String opStyle;

    private String opTime;

    private String opUserId;

    private String opDesc;

    private String infoSour;

    private String mpiPk;

    private String fieldPk;

    private static final long serialVersionUID = 1L;

    public String getPersonIdxLogId() {
        return personIdxLogId;
    }

    public void setPersonIdxLogId(String personIdxLogId) {
        this.personIdxLogId = personIdxLogId == null ? null : personIdxLogId.trim();
    }

    public String getOpType() {
        return opType;
    }

    public void setOpType(String opType) {
        this.opType = opType == null ? null : opType.trim();
    }

    public String getOpStyle() {
        return opStyle;
    }

    public void setOpStyle(String opStyle) {
        this.opStyle = opStyle == null ? null : opStyle.trim();
    }

    public String getOpTime() {
        return opTime;
    }

    public void setOpTime(String opTime) {
        this.opTime = opTime == null ? null : opTime.trim();
    }

    public String getOpUserId() {
        return opUserId;
    }

    public void setOpUserId(String opUserId) {
        this.opUserId = opUserId == null ? null : opUserId.trim();
    }

    public String getOpDesc() {
        return opDesc;
    }

    public void setOpDesc(String opDesc) {
        this.opDesc = opDesc == null ? null : opDesc.trim();
    }

    public String getInfoSour() {
        return infoSour;
    }

    public void setInfoSour(String infoSour) {
        this.infoSour = infoSour == null ? null : infoSour.trim();
    }

    public String getMpiPk() {
        return mpiPk;
    }

    public void setMpiPk(String mpiPk) {
        this.mpiPk = mpiPk == null ? null : mpiPk.trim();
    }

    public String getFieldPk() {
        return fieldPk;
    }

    public void setFieldPk(String fieldPk) {
        this.fieldPk = fieldPk == null ? null : fieldPk.trim();
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
        MpiPersonIdxLog other = (MpiPersonIdxLog) that;
        return (this.getPersonIdxLogId() == null ? other.getPersonIdxLogId() == null : this.getPersonIdxLogId().equals(other.getPersonIdxLogId()))
            && (this.getOpType() == null ? other.getOpType() == null : this.getOpType().equals(other.getOpType()))
            && (this.getOpStyle() == null ? other.getOpStyle() == null : this.getOpStyle().equals(other.getOpStyle()))
            && (this.getOpTime() == null ? other.getOpTime() == null : this.getOpTime().equals(other.getOpTime()))
            && (this.getOpUserId() == null ? other.getOpUserId() == null : this.getOpUserId().equals(other.getOpUserId()))
            && (this.getOpDesc() == null ? other.getOpDesc() == null : this.getOpDesc().equals(other.getOpDesc()))
            && (this.getInfoSour() == null ? other.getInfoSour() == null : this.getInfoSour().equals(other.getInfoSour()))
            && (this.getMpiPk() == null ? other.getMpiPk() == null : this.getMpiPk().equals(other.getMpiPk()))
            && (this.getFieldPk() == null ? other.getFieldPk() == null : this.getFieldPk().equals(other.getFieldPk()));
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((getPersonIdxLogId() == null) ? 0 : getPersonIdxLogId().hashCode());
        result = prime * result + ((getOpType() == null) ? 0 : getOpType().hashCode());
        result = prime * result + ((getOpStyle() == null) ? 0 : getOpStyle().hashCode());
        result = prime * result + ((getOpTime() == null) ? 0 : getOpTime().hashCode());
        result = prime * result + ((getOpUserId() == null) ? 0 : getOpUserId().hashCode());
        result = prime * result + ((getOpDesc() == null) ? 0 : getOpDesc().hashCode());
        result = prime * result + ((getInfoSour() == null) ? 0 : getInfoSour().hashCode());
        result = prime * result + ((getMpiPk() == null) ? 0 : getMpiPk().hashCode());
        result = prime * result + ((getFieldPk() == null) ? 0 : getFieldPk().hashCode());
        return result;
    }
}