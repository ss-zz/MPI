package com.sinosoft.subscription.model;

/**
 * 订阅发布注册机构
 */
public class SubscribeOrg {
	/**
	 * @Fields id : 机构主键
	 */
	private String id;

	/**
	 * @Fields sbOrgcode :订阅机构代码
	 */
	private String sbOrgcode;

	/**
	 * @Fields sbOrgname : 订阅机构名称
	 */
	private String sbOrgname;

	/**
	 * @Fields sysName :系统名称
	 */
	private String sysName;

	/**
	 * @Fields sysCode : 系统标识
	 */
	private String sysCode;

	/**
	 * @Fields sysIp : ip
	 */
	private String sysIp;

	/**
	 * @Fields sysPort : 系统端口
	 */
	private String sysPort;

	/**
	 * @Fields sysDeply : 系统开发商
	 */
	private String sysDeply;

	/**
	 * @Fields sysAdmin : 系统管理员
	 */
	private String sysAdmin;

	/**
	 * @Fields sysTel : 联系电话
	 */
	private String sysTel;

	/**
	 * @Fields sysFlag :有效标识
	 */
	private String sysFlag;

	/**
	 * @Fields domainId : 域标识
	 */
	private String domainId;

	/**
	 * @Fields zcDate :注册时间
	 */
	private String zcDate;

	/**
	 * @Fields wsUrl : ws服务地址
	 */
	private String wsUrl;

	/**
	 * @Fields wsMethod : ws服务方法
	 */
	private String wsMethod;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getSbOrgcode() {
		return sbOrgcode;
	}

	public void setSbOrgcode(String sbOrgcode) {
		this.sbOrgcode = sbOrgcode;
	}

	public String getSbOrgname() {
		return sbOrgname;
	}

	public void setSbOrgname(String sbOrgname) {
		this.sbOrgname = sbOrgname;
	}

	public String getSysName() {
		return sysName;
	}

	public void setSysName(String sysName) {
		this.sysName = sysName;
	}

	public String getSysCode() {
		return sysCode;
	}

	public void setSysCode(String sysCode) {
		this.sysCode = sysCode;
	}

	public String getSysIp() {
		return sysIp;
	}

	public void setSysIp(String sysIp) {
		this.sysIp = sysIp;
	}

	public String getSysPort() {
		return sysPort;
	}

	public void setSysPort(String sysPort) {
		this.sysPort = sysPort;
	}

	public String getSysDeply() {
		return sysDeply;
	}

	public void setSysDeply(String sysDeply) {
		this.sysDeply = sysDeply;
	}

	public String getSysAdmin() {
		return sysAdmin;
	}

	public void setSysAdmin(String sysAdmin) {
		this.sysAdmin = sysAdmin;
	}

	public String getSysTel() {
		return sysTel;
	}

	public void setSysTel(String sysTel) {
		this.sysTel = sysTel;
	}

	public String getSysFlag() {
		return sysFlag;
	}

	public void setSysFlag(String sysFlag) {
		this.sysFlag = sysFlag;
	}

	public String getDomainId() {
		return domainId;
	}

	public void setDomainId(String domainId) {
		this.domainId = domainId;
	}

	public String getZcDate() {
		return zcDate;
	}

	public void setZcDate(String zcDate) {
		this.zcDate = zcDate;
	}

	public String getWsUrl() {
		return wsUrl;
	}

	public void setWsUrl(String wsUrl) {
		this.wsUrl = wsUrl;
	}

	public String getWsMethod() {
		return wsMethod;
	}

	public void setWsMethod(String wsMethod) {
		this.wsMethod = wsMethod;
	}
}
