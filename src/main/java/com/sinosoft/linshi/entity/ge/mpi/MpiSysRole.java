package com.sinosoft.linshi.entity.ge.mpi;

import java.io.Serializable;

public class MpiSysRole implements Serializable {
    private String sysRoleId;

    private String roleName;

    private static final long serialVersionUID = 1L;

    public String getSysRoleId() {
        return sysRoleId;
    }

    public void setSysRoleId(String sysRoleId) {
        this.sysRoleId = sysRoleId == null ? null : sysRoleId.trim();
    }

    public String getRoleName() {
        return roleName;
    }

    public void setRoleName(String roleName) {
        this.roleName = roleName == null ? null : roleName.trim();
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
        MpiSysRole other = (MpiSysRole) that;
        return (this.getSysRoleId() == null ? other.getSysRoleId() == null : this.getSysRoleId().equals(other.getSysRoleId()))
            && (this.getRoleName() == null ? other.getRoleName() == null : this.getRoleName().equals(other.getRoleName()));
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((getSysRoleId() == null) ? 0 : getSysRoleId().hashCode());
        result = prime * result + ((getRoleName() == null) ? 0 : getRoleName().hashCode());
        return result;
    }
}