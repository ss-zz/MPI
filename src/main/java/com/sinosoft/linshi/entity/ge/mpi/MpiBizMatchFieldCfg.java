package com.sinosoft.linshi.entity.ge.mpi;

import java.io.Serializable;

public class MpiBizMatchFieldCfg implements Serializable {
    private String fieldCfgId;

    private String configId;

    private String propertyName;

    private String agreeProb;

    private String disAgree;

    private String matchThreshold;

    private String matchFunction;

    private String weight;

    private String cfgDesc;

    private static final long serialVersionUID = 1L;

    public String getFieldCfgId() {
        return fieldCfgId;
    }

    public void setFieldCfgId(String fieldCfgId) {
        this.fieldCfgId = fieldCfgId == null ? null : fieldCfgId.trim();
    }

    public String getConfigId() {
        return configId;
    }

    public void setConfigId(String configId) {
        this.configId = configId == null ? null : configId.trim();
    }

    public String getPropertyName() {
        return propertyName;
    }

    public void setPropertyName(String propertyName) {
        this.propertyName = propertyName == null ? null : propertyName.trim();
    }

    public String getAgreeProb() {
        return agreeProb;
    }

    public void setAgreeProb(String agreeProb) {
        this.agreeProb = agreeProb == null ? null : agreeProb.trim();
    }

    public String getDisAgree() {
        return disAgree;
    }

    public void setDisAgree(String disAgree) {
        this.disAgree = disAgree == null ? null : disAgree.trim();
    }

    public String getMatchThreshold() {
        return matchThreshold;
    }

    public void setMatchThreshold(String matchThreshold) {
        this.matchThreshold = matchThreshold == null ? null : matchThreshold.trim();
    }

    public String getMatchFunction() {
        return matchFunction;
    }

    public void setMatchFunction(String matchFunction) {
        this.matchFunction = matchFunction == null ? null : matchFunction.trim();
    }

    public String getWeight() {
        return weight;
    }

    public void setWeight(String weight) {
        this.weight = weight == null ? null : weight.trim();
    }

    public String getCfgDesc() {
        return cfgDesc;
    }

    public void setCfgDesc(String cfgDesc) {
        this.cfgDesc = cfgDesc == null ? null : cfgDesc.trim();
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
        MpiBizMatchFieldCfg other = (MpiBizMatchFieldCfg) that;
        return (this.getFieldCfgId() == null ? other.getFieldCfgId() == null : this.getFieldCfgId().equals(other.getFieldCfgId()))
            && (this.getConfigId() == null ? other.getConfigId() == null : this.getConfigId().equals(other.getConfigId()))
            && (this.getPropertyName() == null ? other.getPropertyName() == null : this.getPropertyName().equals(other.getPropertyName()))
            && (this.getAgreeProb() == null ? other.getAgreeProb() == null : this.getAgreeProb().equals(other.getAgreeProb()))
            && (this.getDisAgree() == null ? other.getDisAgree() == null : this.getDisAgree().equals(other.getDisAgree()))
            && (this.getMatchThreshold() == null ? other.getMatchThreshold() == null : this.getMatchThreshold().equals(other.getMatchThreshold()))
            && (this.getMatchFunction() == null ? other.getMatchFunction() == null : this.getMatchFunction().equals(other.getMatchFunction()))
            && (this.getWeight() == null ? other.getWeight() == null : this.getWeight().equals(other.getWeight()))
            && (this.getCfgDesc() == null ? other.getCfgDesc() == null : this.getCfgDesc().equals(other.getCfgDesc()));
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((getFieldCfgId() == null) ? 0 : getFieldCfgId().hashCode());
        result = prime * result + ((getConfigId() == null) ? 0 : getConfigId().hashCode());
        result = prime * result + ((getPropertyName() == null) ? 0 : getPropertyName().hashCode());
        result = prime * result + ((getAgreeProb() == null) ? 0 : getAgreeProb().hashCode());
        result = prime * result + ((getDisAgree() == null) ? 0 : getDisAgree().hashCode());
        result = prime * result + ((getMatchThreshold() == null) ? 0 : getMatchThreshold().hashCode());
        result = prime * result + ((getMatchFunction() == null) ? 0 : getMatchFunction().hashCode());
        result = prime * result + ((getWeight() == null) ? 0 : getWeight().hashCode());
        result = prime * result + ((getCfgDesc() == null) ? 0 : getCfgDesc().hashCode());
        return result;
    }
}