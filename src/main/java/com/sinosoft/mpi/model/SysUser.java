package com.sinosoft.mpi.model;

import java.io.Serializable;
import java.util.Date;

/**
 * @author bysun 2012-3-12 系统用户 table:MPI_SYS_USER schema:
 */
public class SysUser implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 7063895869006599449L;

	private String	userId;	// 用户主键
	private String	sysRoleId;	// 系统角色主键
	private String	userName;	// 用户名
	private String	password;	// 密码
	private String	name;	// 用户姓名
	private String	email;	// 电子邮箱
	private String	state;	// 状态
	private String	createUserId;	// 创建人
	private Date	createDate;	// 创建时间
	private String	updateUserId;	// 更新人
	private Date	updateDate;	// 更新时间
	
	// ========================setter&&getter
	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
	public String getSysRoleId() {
		return sysRoleId;
	}
	public void setSysRoleId(String sysRoleId) {
		this.sysRoleId = sysRoleId;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String emaill) {
		this.email = emaill;
	}
	public String getState() {
		return state;
	}
	public void setState(String state) {
		this.state = state;
	}
	public String getCreateUserId() {
		return createUserId;
	}
	public void setCreateUserId(String createUserId) {
		this.createUserId = createUserId;
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
		this.updateUserId = updateUserId;
	}
	public Date getUpdateDate() {
		return updateDate;
	}
	public void setUpdateDate(Date updateDate) {
		this.updateDate = updateDate;
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((createDate == null) ? 0 : createDate.hashCode());
		result = prime * result + ((createUserId == null) ? 0 : createUserId.hashCode());
		result = prime * result + ((email == null) ? 0 : email.hashCode());
		result = prime * result + ((name == null) ? 0 : name.hashCode());
		result = prime * result + ((password == null) ? 0 : password.hashCode());
		result = prime * result + ((state == null) ? 0 : state.hashCode());
		result = prime * result + ((sysRoleId == null) ? 0 : sysRoleId.hashCode());
		result = prime * result + ((updateDate == null) ? 0 : updateDate.hashCode());
		result = prime * result + ((updateUserId == null) ? 0 : updateUserId.hashCode());
		result = prime * result + ((userId == null) ? 0 : userId.hashCode());
		result = prime * result + ((userName == null) ? 0 : userName.hashCode());
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
		SysUser other = (SysUser) obj;
		if (createDate == null) {
			if (other.createDate != null)
				return false;
		} else if (!createDate.equals(other.createDate))
			return false;
		if (createUserId == null) {
			if (other.createUserId != null)
				return false;
		} else if (!createUserId.equals(other.createUserId))
			return false;
		if (email == null) {
			if (other.email != null)
				return false;
		} else if (!email.equals(other.email))
			return false;
		if (name == null) {
			if (other.name != null)
				return false;
		} else if (!name.equals(other.name))
			return false;
		if (password == null) {
			if (other.password != null)
				return false;
		} else if (!password.equals(other.password))
			return false;
		if (state == null) {
			if (other.state != null)
				return false;
		} else if (!state.equals(other.state))
			return false;
		if (sysRoleId == null) {
			if (other.sysRoleId != null)
				return false;
		} else if (!sysRoleId.equals(other.sysRoleId))
			return false;
		if (updateDate == null) {
			if (other.updateDate != null)
				return false;
		} else if (!updateDate.equals(other.updateDate))
			return false;
		if (updateUserId == null) {
			if (other.updateUserId != null)
				return false;
		} else if (!updateUserId.equals(other.updateUserId))
			return false;
		if (userId == null) {
			if (other.userId != null)
				return false;
		} else if (!userId.equals(other.userId))
			return false;
		if (userName == null) {
			if (other.userName != null)
				return false;
		} else if (!userName.equals(other.userName))
			return false;
		return true;
	}
	@Override
	public String toString() {
		return "SysUser [userId=" + userId + ", sysRoleId=" + sysRoleId + ", userName=" + userName
				+ ", password=" + password + ", name=" + name + ", email=" + email + ", state="
				+ state + ", createUserId=" + createUserId + ", createDate=" + createDate
				+ ", updateUserId=" + updateUserId + ", updateDate=" + updateDate + "]";
	}
}
