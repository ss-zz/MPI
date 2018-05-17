package com.sinosoft.linshi.entity.ge.mpi;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

public class MpiBizBlockCfg implements Serializable {
    private String blockId;

    private String blockDesc;

    private BigDecimal groupCount;

    private Date createDate;

    private String state;

    private static final long serialVersionUID = 1L;

    public String getBlockId() {
        return blockId;
    }

    public void setBlockId(String blockId) {
        this.blockId = blockId == null ? null : blockId.trim();
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
        MpiBizBlockCfg other = (MpiBizBlockCfg) that;
        return (this.getBlockId() == null ? other.getBlockId() == null : this.getBlockId().equals(other.getBlockId()))
            && (this.getBlockDesc() == null ? other.getBlockDesc() == null : this.getBlockDesc().equals(other.getBlockDesc()))
            && (this.getGroupCount() == null ? other.getGroupCount() == null : this.getGroupCount().equals(other.getGroupCount()))
            && (this.getCreateDate() == null ? other.getCreateDate() == null : this.getCreateDate().equals(other.getCreateDate()))
            && (this.getState() == null ? other.getState() == null : this.getState().equals(other.getState()));
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((getBlockId() == null) ? 0 : getBlockId().hashCode());
        result = prime * result + ((getBlockDesc() == null) ? 0 : getBlockDesc().hashCode());
        result = prime * result + ((getGroupCount() == null) ? 0 : getGroupCount().hashCode());
        result = prime * result + ((getCreateDate() == null) ? 0 : getCreateDate().hashCode());
        result = prime * result + ((getState() == null) ? 0 : getState().hashCode());
        return result;
    }
}