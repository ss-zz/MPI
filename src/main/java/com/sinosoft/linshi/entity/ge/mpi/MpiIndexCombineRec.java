package com.sinosoft.linshi.entity.ge.mpi;

import java.io.Serializable;

public class MpiIndexCombineRec implements Serializable {
    private Long addPk;

    private String mpiPk;

    private String combineTarget;

    private String bycombined;

    private static final long serialVersionUID = 1L;

    public Long getAddPk() {
        return addPk;
    }

    public void setAddPk(Long addPk) {
        this.addPk = addPk;
    }

    public String getMpiPk() {
        return mpiPk;
    }

    public void setMpiPk(String mpiPk) {
        this.mpiPk = mpiPk == null ? null : mpiPk.trim();
    }

    public String getCombineTarget() {
        return combineTarget;
    }

    public void setCombineTarget(String combineTarget) {
        this.combineTarget = combineTarget == null ? null : combineTarget.trim();
    }

    public String getBycombined() {
        return bycombined;
    }

    public void setBycombined(String bycombined) {
        this.bycombined = bycombined == null ? null : bycombined.trim();
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
        MpiIndexCombineRec other = (MpiIndexCombineRec) that;
        return (this.getAddPk() == null ? other.getAddPk() == null : this.getAddPk().equals(other.getAddPk()))
            && (this.getMpiPk() == null ? other.getMpiPk() == null : this.getMpiPk().equals(other.getMpiPk()))
            && (this.getCombineTarget() == null ? other.getCombineTarget() == null : this.getCombineTarget().equals(other.getCombineTarget()))
            && (this.getBycombined() == null ? other.getBycombined() == null : this.getBycombined().equals(other.getBycombined()));
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((getAddPk() == null) ? 0 : getAddPk().hashCode());
        result = prime * result + ((getMpiPk() == null) ? 0 : getMpiPk().hashCode());
        result = prime * result + ((getCombineTarget() == null) ? 0 : getCombineTarget().hashCode());
        result = prime * result + ((getBycombined() == null) ? 0 : getBycombined().hashCode());
        return result;
    }
}