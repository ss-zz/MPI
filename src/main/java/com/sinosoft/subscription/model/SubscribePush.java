package com.sinosoft.subscription.model;

/** 
 * @ClassName: SubscribePush 
 * @Description: 发布配置
 * @author sinosoft_SunWG
 * @date 2013-11-18 下午5:25:11  
 */
public class SubscribePush {
	/**
	 * @Fields id : 主键
	 */
	private String id;
	/**
	 * @Fields pushNo : 发布编号
	 */
	private String pushNo;

	/**
	 * @Fields pushName : 发布名称
	 */
	private String pushName;

	/**
	 * @Fields topicType : 主题代码
	 */
	private String topicType;

	/**
	 * @Fields orgCode : 机构代码
	 */
	private String orgCode;

	/**
	 * @Fields pushDate : 发布日期
	 */
	private String pushDate;
	/**
	 * @Fields disableDate :失效日期
	 */
	private String disableDate;
	/**
	 * @Fields flag : 有效标识
	 */
	private String flag;
	/**
	 * @Fields pushDesc : 发布描述
	 */
	private String pushDesc;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getPushNo() {
		return pushNo;
	}

	public void setPushNo(String pushNo) {
		this.pushNo = pushNo;
	}

	public String getPushName() {
		return pushName;
	}

	public void setPushName(String pushName) {
		this.pushName = pushName;
	}

	public String getTopicType() {
		return topicType;
	}

	public void setTopicType(String topicType) {
		this.topicType = topicType;
	}

	public String getOrgCode() {
		return orgCode;
	}

	public void setOrgCode(String orgCode) {
		this.orgCode = orgCode;
	}

	public String getPushDate() {
		return pushDate;
	}

	public void setPushDate(String pushDate) {
		this.pushDate = pushDate;
	}

	public String getDisableDate() {
		return disableDate;
	}

	public void setDisableDate(String disableDate) {
		this.disableDate = disableDate;
	}

	public String getFlag() {
		return flag;
	}

	public void setFlag(String flag) {
		this.flag = flag;
	}

	public String getPushDesc() {
		return pushDesc;
	}

	public void setPushDesc(String pushDesc) {
		this.pushDesc = pushDesc;
	}
}
