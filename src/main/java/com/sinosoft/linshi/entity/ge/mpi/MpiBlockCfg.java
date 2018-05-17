package com.sinosoft.linshi.entity.ge.mpi;

import java.io.Serializable;
import java.math.BigDecimal;

public class MpiBlockCfg implements Serializable {
    private String bolckId;

    private String blockDesc;

    private BigDecimal groupCount;

    private String createDate;

    private String state;

    private static final long serialVersionUID = 1L;

    public String getBolckId() {
        return bolckId;
    }

    public void setBolckId(String bolckId) {
        this.bolckId = bolckId == null ? null : bolckId.trim();
    }

    public String getBlockDesc() {
        return blockDesc;
    }

    public void setBlockDesc(String blockDesc) {
        this.blockDesc = blockDesc == null ? null : blockDesc.trim();
    }

    public BigDecimal getGroupCount() {
        return groupCount;
    }

    public void setGroupCount(BigDecimal groupCount) {
        this.groupCount = groupCount;
    }

    public String getCreateDate() {
        return createDate;
    }

    public void setCreateDate(String createDate) {
        this.createDate = createDate == null ? null : createDate.trim();
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state == null ? null : state.trim();
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
        MpiBlockCfg other = (MpiBlockCfg) that;
        return (this.getBolckId() == null ? other.getBolckId() == null : this.getBolckId().equals(other.getBolckId()))
            && (this.getBlockDesc() == null ? other.getBlockDesc() == null : this.getBlockDesc().equals(other.getBlockDesc()))
            && (this.getGroupCount() == null ? other.getGroupCount() == null : this.getGroupCount().equals(other.getGroupCount()))
            && (this.getCreateDate() == null ? other.getCreateDate() == null : this.getCreateDate().equals(other.getCreateDate()))
            && (this.getState() == null ? other.getState() == null : this.getState().equals(other.getState()));
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((getBolckId() == null) ? 0 : getBolckId().hashCode());
        result = prime * result + ((getBlockDesc() == null) ? 0 : getBlockDesc().hashCode());
        result = prime * result + ((getGroupCount() == null) ? 0 : getGroupCount().hashCode());
        result = prime * result + ((getCreateDate() == null) ? 0 : getCreateDate().hashCode());
        result = prime * result + ((getState() == null) ? 0 : getState().hashCode());
        return result;
    }
}