package com.sinosoft.linshi.entity.ge.mpi;

import java.io.Serializable;

public class MpiBookLog implements Serializable {
    private String bookId;

    private String eventType;

    private String mpiPk;

    private String fieldPk;

    private String domainId;

    private String uniqueSign;

    private String personIdentifier;

    private String opTime;

    private String opCount;

    private String opResult;

    private static final long serialVersionUID = 1L;

    public String getBookId() {
        return bookId;
    }

    public void setBookId(String bookId) {
        this.bookId = bookId == null ? null : bookId.trim();
    }

    public String getEventType() {
        return eventType;
    }

    public void setEventType(String eventType) {
        this.eventType = eventType == null ? null : eventType.trim();
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

    public String getDomainId() {
        return domainId;
    }

    public void setDomainId(String domainId) {
        this.domainId = domainId == null ? null : domainId.trim();
    }

    public String getUniqueSign() {
        return uniqueSign;
    }

    public void setUniqueSign(String uniqueSign) {
        this.uniqueSign = uniqueSign == null ? null : uniqueSign.trim();
    }

    public String getPersonIdentifier() {
        return personIdentifier;
    }

    public void setPersonIdentifier(String personIdentifier) {
        this.personIdentifier = personIdentifier == null ? null : personIdentifier.trim();
    }

    public String getOpTime() {
        return opTime;
    }

    public void setOpTime(String opTime) {
        this.opTime = opTime == null ? null : opTime.trim();
    }

    public String getOpCount() {
        return opCount;
    }

    public void setOpCount(String opCount) {
        this.opCount = opCount == null ? null : opCount.trim();
    }

    public String getOpResult() {
        return opResult;
    }

    public void setOpResult(String opResult) {
        this.opResult = opResult == null ? null : opResult.trim();
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
        MpiBookLog other = (MpiBookLog) that;
        return (this.getBookId() == null ? other.getBookId() == null : this.getBookId().equals(other.getBookId()))
            && (this.getEventType() == null ? other.getEventType() == null : this.getEventType().equals(other.getEventType()))
            && (this.getMpiPk() == null ? other.getMpiPk() == null : this.getMpiPk().equals(other.getMpiPk()))
            && (this.getFieldPk() == null ? other.getFieldPk() == null : this.getFieldPk().equals(other.getFieldPk()))
            && (this.getDomainId() == null ? other.getDomainId() == null : this.getDomainId().equals(other.getDomainId()))
            && (this.getUniqueSign() == null ? other.getUniqueSign() == null : this.getUniqueSign().equals(other.getUniqueSign()))
            && (this.getPersonIdentifier() == null ? other.getPersonIdentifier() == null : this.getPersonIdentifier().equals(other.getPersonIdentifier()))
            && (this.getOpTime() == null ? other.getOpTime() == null : this.getOpTime().equals(other.getOpTime()))
            && (this.getOpCount() == null ? other.getOpCount() == null : this.getOpCount().equals(other.getOpCount()))
            && (this.getOpResult() == null ? other.getOpResult() == null : this.getOpResult().equals(other.getOpResult()));
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((getBookId() == null) ? 0 : getBookId().hashCode());
        result = prime * result + ((getEventType() == null) ? 0 : getEventType().hashCode());
        result = prime * result + ((getMpiPk() == null) ? 0 : getMpiPk().hashCode());
        result = prime * result + ((getFieldPk() == null) ? 0 : getFieldPk().hashCode());
        result = prime * result + ((getDomainId() == null) ? 0 : getDomainId().hashCode());
        result = prime * result + ((getUniqueSign() == null) ? 0 : getUniqueSign().hashCode());
        result = prime * result + ((getPersonIdentifier() == null) ? 0 : getPersonIdentifier().hashCode());
        result = prime * result + ((getOpTime() == null) ? 0 : getOpTime().hashCode());
        result = prime * result + ((getOpCount() == null) ? 0 : getOpCount().hashCode());
        result = prime * result + ((getOpResult() == null) ? 0 : getOpResult().hashCode());
        return result;
    }
}