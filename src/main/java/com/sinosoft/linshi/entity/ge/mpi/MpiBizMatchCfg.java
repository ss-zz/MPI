package com.sinosoft.linshi.entity.ge.mpi;

import java.io.Serializable;
import java.util.Date;

public class MpiBizMatchCfg implements Serializable {
    private String configId;

    private String configDesc;

    private String agreeThreshold;

    private String matchThreshold;

    private Date createDate;

    private String state;

    private static final long serialVersionUID = 1L;

    public String getConfigId() {
        return configId;
    }

    public void setConfigId(String configId) {
        this.configId = configId == null ? null : configId.trim();
    }

    public String getConfigDesc() {
        return configDesc;
    }

    public void setConfigDesc(String configDesc) {
        this.configDesc = configDesc == null ? null : configDesc.trim();
    }

    public String getAgreeThreshold() {
        return agreeThreshold;
    }

    public void setAgreeThreshold(String agreeThreshold) {
        this.agreeThreshold = agreeThreshold == null ? null : agreeThreshold.trim();
    }

    public String getMatchThreshold() {
        return matchThreshold;
    }

    public void setMatchThreshold(String matchThreshold) {
        this.matchThreshold = matchThreshold == null ? null : matchThreshold.trim();
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
        MpiBizMatchCfg other = (MpiBizMatchCfg) that;
        return (this.getConfigId() == null ? other.getConfigId() == null : this.getConfigId().equals(other.getConfigId()))
            && (this.getConfigDesc() == null ? other.getConfigDesc() == null : this.getConfigDesc().equals(other.getConfigDesc()))
            && (this.getAgreeThreshold() == null ? other.getAgreeThreshold() == null : this.getAgreeThreshold().equals(other.getAgreeThreshold()))
            && (this.getMatchThreshold() == null ? other.getMatchThreshold() == null : this.getMatchThreshold().equals(other.getMatchThreshold()))
            && (this.getCreateDate() == null ? other.getCreateDate() == null : this.getCreateDate().equals(other.getCreateDate()))
            && (this.getState() == null ? other.getState() == null : this.getState().equals(other.getState()));
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((getConfigId() == null) ? 0 : getConfigId().hashCode());
        result = prime * result + ((getConfigDesc() == null) ? 0 : getConfigDesc().hashCode());
        result = prime * result + ((getAgreeThreshold() == null) ? 0 : getAgreeThreshold().hashCode());
        result = prime * result + ((getMatchThreshold() == null) ? 0 : getMatchThreshold().hashCode());
        result = prime * result + ((getCreateDate() == null) ? 0 : getCreateDate().hashCode());
        result = prime * result + ((getState() == null) ? 0 : getState().hashCode());
        return result;
    }
}