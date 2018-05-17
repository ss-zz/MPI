package com.sinosoft.linshi.entity.ge.mpi;

import java.io.Serializable;

public class MpiMatchResult implements Serializable {
    private String matchResultId;

    private String mpiPk;

    private String fieldPk;

    private String matchDegree;

    private String fieldMatDegrees;

    private static final long serialVersionUID = 1L;

    public String getMatchResultId() {
        return matchResultId;
    }

    public void setMatchResultId(String matchResultId) {
        this.matchResultId = matchResultId == null ? null : matchResultId.trim();
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

    public String getMatchDegree() {
        return matchDegree;
    }

    public void setMatchDegree(String matchDegree) {
        this.matchDegree = matchDegree == null ? null : matchDegree.trim();
    }

    public String getFieldMatDegrees() {
        return fieldMatDegrees;
    }

    public void setFieldMatDegrees(String fieldMatDegrees) {
        this.fieldMatDegrees = fieldMatDegrees == null ? null : fieldMatDegrees.trim();
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
        MpiMatchResult other = (MpiMatchResult) that;
        return (this.getMatchResultId() == null ? other.getMatchResultId() == null : this.getMatchResultId().equals(other.getMatchResultId()))
            && (this.getMpiPk() == null ? other.getMpiPk() == null : this.getMpiPk().equals(other.getMpiPk()))
            && (this.getFieldPk() == null ? other.getFieldPk() == null : this.getFieldPk().equals(other.getFieldPk()))
            && (this.getMatchDegree() == null ? other.getMatchDegree() == null : this.getMatchDegree().equals(other.getMatchDegree()))
            && (this.getFieldMatDegrees() == null ? other.getFieldMatDegrees() == null : this.getFieldMatDegrees().equals(other.getFieldMatDegrees()));
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((getMatchResultId() == null) ? 0 : getMatchResultId().hashCode());
        result = prime * result + ((getMpiPk() == null) ? 0 : getMpiPk().hashCode());
        result = prime * result + ((getFieldPk() == null) ? 0 : getFieldPk().hashCode());
        result = prime * result + ((getMatchDegree() == null) ? 0 : getMatchDegree().hashCode());
        result = prime * result + ((getFieldMatDegrees() == null) ? 0 : getFieldMatDegrees().hashCode());
        return result;
    }
}