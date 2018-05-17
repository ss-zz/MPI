package com.sinosoft.linshi.entity.ge.mpi;

import java.io.Serializable;
import java.util.Date;

public class MpiIdentifierDomain implements Serializable {
    private String domainId;

    private String uniqueSign;

    private String domainType;

    private String domainDesc;

    private String pushAddr;

    private String bookType;

    private String createUserId;

    private Date createDate;

    private String updateUserId;

    private Date updateDate;

    private String domainLevel;

    private String zonecode;

    private String username;

    private String userPwd;

    private String ip;

    private String syscode;

    private String flag;

    private String telephone;

    private String linkState;

    private String onOff;

    private static final long serialVersionUID = 1L;

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

    public String getDomainType() {
        return domainType;
    }

    public void setDomainType(String domainType) {
        this.domainType = domainType == null ? null : domainType.trim();
    }

    public String getDomainDesc() {
        return domainDesc;
    }

    public void setDomainDesc(String domainDesc) {
        this.domainDesc = domainDesc == null ? null : domainDesc.trim();
    }

    public String getPushAddr() {
        return pushAddr;
    }

    public void setPushAddr(String pushAddr) {
        this.pushAddr = pushAddr == null ? null : pushAddr.trim();
    }

    public String getBookType() {
        return bookType;
    }

    public void setBookType(String bookType) {
        this.bookType = bookType == null ? null : bookType.trim();
    }

    public String getCreateUserId() {
        return createUserId;
    }

    public void setCreateUserId(String createUserId) {
        this.createUserId = createUserId == null ? null : createUserId.trim();
    }

    public Date getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }

    public String getUpdateUserId() {
        return updateUserId;
    }

    public void setUpdateUserId(String updateUserId) {
        this.updateUserId = updateUserId == null ? null : updateUserId.trim();
    }

    public Date getUpdateDate() {
        return updateDate;
    }

    public void setUpdateDate(Date updateDate) {
        this.updateDate = updateDate;
    }

    public String getDomainLevel() {
        return domainLevel;
    }

    public void setDomainLevel(String domainLevel) {
        this.domainLevel = domainLevel == null ? null : domainLevel.trim();
    }

    public String getZonecode() {
        return zonecode;
    }

    public void setZonecode(String zonecode) {
        this.zonecode = zonecode == null ? null : zonecode.trim();
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username == null ? null : username.trim();
    }

    public String getUserPwd() {
        return userPwd;
    }

    public void setUserPwd(String userPwd) {
        this.userPwd = userPwd == null ? null : userPwd.trim();
    }

    public String getIp() {
        return ip;
    }

    public void setIp(String ip) {
        this.ip = ip == null ? null : ip.trim();
    }

    public String getSyscode() {
        return syscode;
    }

    public void setSyscode(String syscode) {
        this.syscode = syscode == null ? null : syscode.trim();
    }

    public String getFlag() {
        return flag;
    }

    public void setFlag(String flag) {
        this.flag = flag == null ? null : flag.trim();
    }

    public String getTelephone() {
        return telephone;
    }

    public void setTelephone(String telephone) {
        this.telephone = telephone == null ? null : telephone.trim();
    }

    public String getLinkState() {
        return linkState;
    }

    public void setLinkState(String linkState) {
        this.linkState = linkState == null ? null : linkState.trim();
    }

    public String getOnOff() {
        return onOff;
    }

    public void setOnOff(String onOff) {
        this.onOff = onOff == null ? null : onOff.trim();
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
        MpiIdentifierDomain other = (MpiIdentifierDomain) that;
        return (this.getDomainId() == null ? other.getDomainId() == null : this.getDomainId().equals(other.getDomainId()))
            && (this.getUniqueSign() == null ? other.getUniqueSign() == null : this.getUniqueSign().equals(other.getUniqueSign()))
            && (this.getDomainType() == null ? other.getDomainType() == null : this.getDomainType().equals(other.getDomainType()))
            && (this.getDomainDesc() == null ? other.getDomainDesc() == null : this.getDomainDesc().equals(other.getDomainDesc()))
            && (this.getPushAddr() == null ? other.getPushAddr() == null : this.getPushAddr().equals(other.getPushAddr()))
            && (this.getBookType() == null ? other.getBookType() == null : this.getBookType().equals(other.getBookType()))
            && (this.getCreateUserId() == null ? other.getCreateUserId() == null : this.getCreateUserId().equals(other.getCreateUserId()))
            && (this.getCreateDate() == null ? other.getCreateDate() == null : this.getCreateDate().equals(other.getCreateDate()))
            && (this.getUpdateUserId() == null ? other.getUpdateUserId() == null : this.getUpdateUserId().equals(other.getUpdateUserId()))
            && (this.getUpdateDate() == null ? other.getUpdateDate() == null : this.getUpdateDate().equals(other.getUpdateDate()))
            && (this.getDomainLevel() == null ? other.getDomainLevel() == null : this.getDomainLevel().equals(other.getDomainLevel()))
            && (this.getZonecode() == null ? other.getZonecode() == null : this.getZonecode().equals(other.getZonecode()))
            && (this.getUsername() == null ? other.getUsername() == null : this.getUsername().equals(other.getUsername()))
            && (this.getUserPwd() == null ? other.getUserPwd() == null : this.getUserPwd().equals(other.getUserPwd()))
            && (this.getIp() == null ? other.getIp() == null : this.getIp().equals(other.getIp()))
            && (this.getSyscode() == null ? other.getSyscode() == null : this.getSyscode().equals(other.getSyscode()))
            && (this.getFlag() == null ? other.getFlag() == null : this.getFlag().equals(other.getFlag()))
            && (this.getTelephone() == null ? other.getTelephone() == null : this.getTelephone().equals(other.getTelephone()))
            && (this.getLinkState() == null ? other.getLinkState() == null : this.getLinkState().equals(other.getLinkState()))
            && (this.getOnOff() == null ? other.getOnOff() == null : this.getOnOff().equals(other.getOnOff()));
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((getDomainId() == null) ? 0 : getDomainId().hashCode());
        result = prime * result + ((getUniqueSign() == null) ? 0 : getUniqueSign().hashCode());
        result = prime * result + ((getDomainType() == null) ? 0 : getDomainType().hashCode());
        result = prime * result + ((getDomainDesc() == null) ? 0 : getDomainDesc().hashCode());
        result = prime * result + ((getPushAddr() == null) ? 0 : getPushAddr().hashCode());
        result = prime * result + ((getBookType() == null) ? 0 : getBookType().hashCode());
        result = prime * result + ((getCreateUserId() == null) ? 0 : getCreateUserId().hashCode());
        result = prime * result + ((getCreateDate() == null) ? 0 : getCreateDate().hashCode());
        result = prime * result + ((getUpdateUserId() == null) ? 0 : getUpdateUserId().hashCode());
        result = prime * result + ((getUpdateDate() == null) ? 0 : getUpdateDate().hashCode());
        result = prime * result + ((getDomainLevel() == null) ? 0 : getDomainLevel().hashCode());
        result = prime * result + ((getZonecode() == null) ? 0 : getZonecode().hashCode());
        result = prime * result + ((getUsername() == null) ? 0 : getUsername().hashCode());
        result = prime * result + ((getUserPwd() == null) ? 0 : getUserPwd().hashCode());
        result = prime * result + ((getIp() == null) ? 0 : getIp().hashCode());
        result = prime * result + ((getSyscode() == null) ? 0 : getSyscode().hashCode());
        result = prime * result + ((getFlag() == null) ? 0 : getFlag().hashCode());
        result = prime * result + ((getTelephone() == null) ? 0 : getTelephone().hashCode());
        result = prime * result + ((getLinkState() == null) ? 0 : getLinkState().hashCode());
        result = prime * result + ((getOnOff() == null) ? 0 : getOnOff().hashCode());
        return result;
    }
}