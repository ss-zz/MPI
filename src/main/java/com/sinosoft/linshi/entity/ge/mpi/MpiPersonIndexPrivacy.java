package com.sinosoft.linshi.entity.ge.mpi;

import java.io.Serializable;
import java.util.Date;

public class MpiPersonIndexPrivacy implements Serializable {
    private String id;

    private String personid;

    private String privacyitems;

    private String editname;

    private Date updatedate;

    private String flag;

    private static final long serialVersionUID = 1L;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id == null ? null : id.trim();
    }

    public String getPersonid() {
        return personid;
    }

    public void setPersonid(String personid) {
        this.personid = personid == null ? null : personid.trim();
    }

    public String getPrivacyitems() {
        return privacyitems;
    }

    public void setPrivacyitems(String privacyitems) {
        this.privacyitems = privacyitems == null ? null : privacyitems.trim();
    }

    public String getEditname() {
        return editname;
    }

    public void setEditname(String editname) {
        this.editname = editname == null ? null : editname.trim();
    }

    public Date getUpdatedate() {
        return updatedate;
    }

    public void setUpdatedate(Date updatedate) {
        this.updatedate = updatedate;
    }

    public String getFlag() {
        return flag;
    }

    public void setFlag(String flag) {
        this.flag = flag == null ? null : flag.trim();
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
        MpiPersonIndexPrivacy other = (MpiPersonIndexPrivacy) that;
        return (this.getId() == null ? other.getId() == null : this.getId().equals(other.getId()))
            && (this.getPersonid() == null ? other.getPersonid() == null : this.getPersonid().equals(other.getPersonid()))
            && (this.getPrivacyitems() == null ? other.getPrivacyitems() == null : this.getPrivacyitems().equals(other.getPrivacyitems()))
            && (this.getEditname() == null ? other.getEditname() == null : this.getEditname().equals(other.getEditname()))
            && (this.getUpdatedate() == null ? other.getUpdatedate() == null : this.getUpdatedate().equals(other.getUpdatedate()))
            && (this.getFlag() == null ? other.getFlag() == null : this.getFlag().equals(other.getFlag()));
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((getId() == null) ? 0 : getId().hashCode());
        result = prime * result + ((getPersonid() == null) ? 0 : getPersonid().hashCode());
        result = prime * result + ((getPrivacyitems() == null) ? 0 : getPrivacyitems().hashCode());
        result = prime * result + ((getEditname() == null) ? 0 : getEditname().hashCode());
        result = prime * result + ((getUpdatedate() == null) ? 0 : getUpdatedate().hashCode());
        result = prime * result + ((getFlag() == null) ? 0 : getFlag().hashCode());
        return result;
    }
}