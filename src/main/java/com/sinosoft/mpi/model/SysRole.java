package com.sinosoft.mpi.model;

import java.io.Serializable;

/**
 * 系统角色 table:MPI_SYS_ROLE
 */
public class SysRole implements Serializable {

	private static final long serialVersionUID = -1133070745219243196L;

	private String sysRoleId; // 角色id
	private String roleName; // 角色名

	// ========================setter&&getter
	public String getSysRoleId() {
		return sysRoleId;
	}

	public void setSysRoleId(String sysRoleId) {
		this.sysRoleId = sysRoleId;
	}

	public String getRoleName() {
		return roleName;
	}

	public void setRoleName(String roleName) {
		this.roleName = roleName;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((roleName == null) ? 0 : roleName.hashCode());
		result = prime * result + ((sysRoleId == null) ? 0 : sysRoleId.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		SysRole other = (SysRole) obj;
		if (roleName == null) {
			if (other.roleName != null)
				return false;
		} else if (!roleName.equals(other.roleName))
			return false;
		if (sysRoleId == null) {
			if (other.sysRoleId != null)
				return false;
		} else if (!sysRoleId.equals(other.sysRoleId))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "SysRole [sysRoleId=" + sysRoleId + ", roleName=" + roleName + "]";
	}
}
