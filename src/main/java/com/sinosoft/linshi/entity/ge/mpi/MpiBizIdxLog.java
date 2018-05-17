package com.sinosoft.linshi.entity.ge.mpi;

import java.io.Serializable;
import java.util.Date;

public class MpiBizIdxLog implements Serializable {
    private String blIdxLogId;

    private Date blTime;

    private String blUserId;

    private String blDesc;

    private String blMatched;

    private String blBizId;

    private String blSerialId;

    private String blInfoSour;

    private String blType;

    private static final long serialVersionUID = 1L;

    public String getBlIdxLogId() {
        return blIdxLogId;
    }

    public void setBlIdxLogId(String blIdxLogId) {
        this.blIdxLogId = blIdxLogId == null ? null : blIdxLogId.trim();
    }

    public Date getBlTime() {
        return blTime;
    }

    public void setBlTime(Date blTime) {
        this.blTime = blTime;
    }

    public String getBlUserId() {
        return blUserId;
    }

    public void setBlUserId(String blUserId) {
        this.blUserId = blUserId == null ? null : blUserId.trim();
    }

    public String getBlDesc() {
        return blDesc;
    }

    public void setBlDesc(String blDesc) {
        this.blDesc = blDesc == null ? null : blDesc.trim();
    }

    public String getBlMatched() {
        return blMatched;
    }

    public void setBlMatched(String blMatched) {
        this.blMatched = blMatched == null ? null : blMatched.trim();
    }

    public String getBlBizId() {
        return blBizId;
    }

    public void setBlBizId(String blBizId) {
        this.blBizId = blBizId == null ? null : blBizId.trim();
    }

    public String getBlSerialId() {
        return blSerialId;
    }

    public void setBlSerialId(String blSerialId) {
        this.blSerialId = blSerialId == null ? null : blSerialId.trim();
    }

    public String getBlInfoSour() {
        return blInfoSour;
    }

    public void setBlInfoSour(String blInfoSour) {
        this.blInfoSour = blInfoSour == null ? null : blInfoSour.trim();
    }

    public String getBlType() {
        return blType;
    }

    public void setBlType(String blType) {
        this.blType = blType == null ? null : blType.trim();
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
        MpiBizIdxLog other = (MpiBizIdxLog) that;
        return (this.getBlIdxLogId() == null ? other.getBlIdxLogId() == null : this.getBlIdxLogId().equals(other.getBlIdxLogId()))
            && (this.getBlTime() == null ? other.getBlTime() == null : this.getBlTime().equals(other.getBlTime()))
            && (this.getBlUserId() == null ? other.getBlUserId() == null : this.getBlUserId().equals(other.getBlUserId()))
            && (this.getBlDesc() == null ? other.getBlDesc() == null : this.getBlDesc().equals(other.getBlDesc()))
            && (this.getBlMatched() == null ? other.getBlMatched() == null : this.getBlMatched().equals(other.getBlMatched()))
            && (this.getBlBizId() == null ? other.getBlBizId() == null : this.getBlBizId().equals(other.getBlBizId()))
            && (this.getBlSerialId() == null ? other.getBlSerialId() == null : this.getBlSerialId().equals(other.getBlSerialId()))
            && (this.getBlInfoSour() == null ? other.getBlInfoSour() == null : this.getBlInfoSour().equals(other.getBlInfoSour()))
            && (this.getBlType() == null ? other.getBlType() == null : this.getBlType().equals(other.getBlType()));
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((getBlIdxLogId() == null) ? 0 : getBlIdxLogId().hashCode());
        result = prime * result + ((getBlTime() == null) ? 0 : getBlTime().hashCode());
        result = prime * result + ((getBlUserId() == null) ? 0 : getBlUserId().hashCode());
        result = prime * result + ((getBlDesc() == null) ? 0 : getBlDesc().hashCode());
        result = prime * result + ((getBlMatched() == null) ? 0 : getBlMatched().hashCode());
        result = prime * result + ((getBlBizId() == null) ? 0 : getBlBizId().hashCode());
        result = prime * result + ((getBlSerialId() == null) ? 0 : getBlSerialId().hashCode());
        result = prime * result + ((getBlInfoSour() == null) ? 0 : getBlInfoSour().hashCode());
        result = prime * result + ((getBlType() == null) ? 0 : getBlType().hashCode());
        return result;
    }
}