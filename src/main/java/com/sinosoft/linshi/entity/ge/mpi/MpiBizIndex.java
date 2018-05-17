package com.sinosoft.linshi.entity.ge.mpi;

import java.io.Serializable;
import java.util.Date;

public class MpiBizIndex implements Serializable {
    private String id;

    private String bizId;

    private String bizPatientId;

    private String bizSerialId;

    private String bizInpatientno;

    private String bizInpatientSerialno;

    private String bizClinicno;

    private String bizClinicSerialno;

    private Date createDate;

    private String state;

    private String remark;

    private Object bizSystemId;

    private static final long serialVersionUID = 1L;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id == null ? null : id.trim();
    }

    public String getBizId() {
        return bizId;
    }

    public void setBizId(String bizId) {
        this.bizId = bizId == null ? null : bizId.trim();
    }

    public String getBizPatientId() {
        return bizPatientId;
    }

    public void setBizPatientId(String bizPatientId) {
        this.bizPatientId = bizPatientId == null ? null : bizPatientId.trim();
    }

    public String getBizSerialId() {
        return bizSerialId;
    }

    public void setBizSerialId(String bizSerialId) {
        this.bizSerialId = bizSerialId == null ? null : bizSerialId.trim();
    }

    public String getBizInpatientno() {
        return bizInpatientno;
    }

    public void setBizInpatientno(String bizInpatientno) {
        this.bizInpatientno = bizInpatientno == null ? null : bizInpatientno.trim();
    }

    public String getBizInpatientSerialno() {
        return bizInpatientSerialno;
    }

    public void setBizInpatientSerialno(String bizInpatientSerialno) {
        this.bizInpatientSerialno = bizInpatientSerialno == null ? null : bizInpatientSerialno.trim();
    }

    public String getBizClinicno() {
        return bizClinicno;
    }

    public void setBizClinicno(String bizClinicno) {
        this.bizClinicno = bizClinicno == null ? null : bizClinicno.trim();
    }

    public String getBizClinicSerialno() {
        return bizClinicSerialno;
    }

    public void setBizClinicSerialno(String bizClinicSerialno) {
        this.bizClinicSerialno = bizClinicSerialno == null ? null : bizClinicSerialno.trim();
    }

    public Date getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state == null ? null : state.trim();
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark == null ? null : remark.trim();
    }

    public Object getBizSystemId() {
        return bizSystemId;
    }

    public void setBizSystemId(Object bizSystemId) {
        this.bizSystemId = bizSystemId;
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
        MpiBizIndex other = (MpiBizIndex) that;
        return (this.getId() == null ? other.getId() == null : this.getId().equals(other.getId()))
            && (this.getBizId() == null ? other.getBizId() == null : this.getBizId().equals(other.getBizId()))
            && (this.getBizPatientId() == null ? other.getBizPatientId() == null : this.getBizPatientId().equals(other.getBizPatientId()))
            && (this.getBizSerialId() == null ? other.getBizSerialId() == null : this.getBizSerialId().equals(other.getBizSerialId()))
            && (this.getBizInpatientno() == null ? other.getBizInpatientno() == null : this.getBizInpatientno().equals(other.getBizInpatientno()))
            && (this.getBizInpatientSerialno() == null ? other.getBizInpatientSerialno() == null : this.getBizInpatientSerialno().equals(other.getBizInpatientSerialno()))
            && (this.getBizClinicno() == null ? other.getBizClinicno() == null : this.getBizClinicno().equals(other.getBizClinicno()))
            && (this.getBizClinicSerialno() == null ? other.getBizClinicSerialno() == null : this.getBizClinicSerialno().equals(other.getBizClinicSerialno()))
            && (this.getCreateDate() == null ? other.getCreateDate() == null : this.getCreateDate().equals(other.getCreateDate()))
            && (this.getState() == null ? other.getState() == null : this.getState().equals(other.getState()))
            && (this.getRemark() == null ? other.getRemark() == null : this.getRemark().equals(other.getRemark()))
            && (this.getBizSystemId() == null ? other.getBizSystemId() == null : this.getBizSystemId().equals(other.getBizSystemId()));
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((getId() == null) ? 0 : getId().hashCode());
        result = prime * result + ((getBizId() == null) ? 0 : getBizId().hashCode());
        result = prime * result + ((getBizPatientId() == null) ? 0 : getBizPatientId().hashCode());
        result = prime * result + ((getBizSerialId() == null) ? 0 : getBizSerialId().hashCode());
        result = prime * result + ((getBizInpatientno() == null) ? 0 : getBizInpatientno().hashCode());
        result = prime * result + ((getBizInpatientSerialno() == null) ? 0 : getBizInpatientSerialno().hashCode());
        result = prime * result + ((getBizClinicno() == null) ? 0 : getBizClinicno().hashCode());
        result = prime * result + ((getBizClinicSerialno() == null) ? 0 : getBizClinicSerialno().hashCode());
        result = prime * result + ((getCreateDate() == null) ? 0 : getCreateDate().hashCode());
        result = prime * result + ((getState() == null) ? 0 : getState().hashCode());
        result = prime * result + ((getRemark() == null) ? 0 : getRemark().hashCode());
        result = prime * result + ((getBizSystemId() == null) ? 0 : getBizSystemId().hashCode());
        return result;
    }
}