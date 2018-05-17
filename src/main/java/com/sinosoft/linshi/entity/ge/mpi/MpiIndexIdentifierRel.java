package com.sinosoft.linshi.entity.ge.mpi;

import java.io.Serializable;

public class MpiIndexIdentifierRel implements Serializable {
    private Long combineNo;

    private String domainId;

    private String mpiPk;

    private String fieldPk;

    private String personIdentifier;

    private Long combineRec;

    private static final long serialVersionUID = 1L;

    public Long getCombineNo() {
        return combineNo;
    }

    public void setCombineNo(Long combineNo) {
        this.combineNo = combineNo;
    }

    public String getDomainId() {
        return domainId;
    }

    public void setDomainId(String domainId) {
        this.domainId = domainId == null ? null : domainId.trim();
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

    public String getPersonIdentifier() {
        return personIdentifier;
    }

    public void setPersonIdentifier(String personIdentifier) {
        this.personIdentifier = personIdentifier == null ? null : personIdentifier.trim();
    }

    public Long getCombineRec() {
        return combineRec;
    }

    public void setCombineRec(Long combineRec) {
        this.combineRec = combineRec;
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
        MpiIndexIdentifierRel other = (MpiIndexIdentifierRel) that;
        return (this.getCombineNo() == null ? other.getCombineNo() == null : this.getCombineNo().equals(other.getCombineNo()))
            && (this.getDomainId() == null ? other.getDomainId() == null : this.getDomainId().equals(other.getDomainId()))
            && (this.getMpiPk() == null ? other.getMpiPk() == null : this.getMpiPk().equals(other.getMpiPk()))
            && (this.getFieldPk() == null ? other.getFieldPk() == null : this.getFieldPk().equals(other.getFieldPk()))
            && (this.getPersonIdentifier() == null ? other.getPersonIdentifier() == null : this.getPersonIdentifier().equals(other.getPersonIdentifier()))
            && (this.getCombineRec() == null ? other.getCombineRec() == null : this.getCombineRec().equals(other.getCombineRec()));
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((getCombineNo() == null) ? 0 : getCombineNo().hashCode());
        result = prime * result + ((getDomainId() == null) ? 0 : getDomainId().hashCode());
        result = prime * result + ((getMpiPk() == null) ? 0 : getMpiPk().hashCode());
        result = prime * result + ((getFieldPk() == null) ? 0 : getFieldPk().hashCode());
        result = prime * result + ((getPersonIdentifier() == null) ? 0 : getPersonIdentifier().hashCode());
        result = prime * result + ((getCombineRec() == null) ? 0 : getCombineRec().hashCode());
        return result;
    }
}