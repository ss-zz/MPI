package com.sinosoft.subscription.model;

/** 
* @ClassName: SubscribeTheme 
* @Description:订阅发布主题
* @author sinosoft_SunWG
* @date 2013-11-18 上午11:54:42  
*/
public class SubscribeTheme {

	/**
	 * @Fields topicId : 主题编号
	 */
	private String topicId;
	/**
	 * @Fields topicTypeCd : 主题代码
	 */
	private String topicTypeCd;
	/**
	 * @Fields topicName : 主题名称
	 */
	private String topicName;
	/**
	 * @Fields topicDesc : 主题描述
	 */
	private String topicDesc;
	/**
	 * @Fields flag : 有效标识
	 */
	private String flag;

	public String getTopicId() {
		return topicId;
	}

	public void setTopicId(String topicId) {
		this.topicId = topicId;
	}

	public String getTopicTypeCd() {
		return topicTypeCd;
	}

	public void setTopicTypeCd(String topicTypeCd) {
		this.topicTypeCd = topicTypeCd;
	}

	public String getTopicName() {
		return topicName;
	}

	public void setTopicName(String topicName) {
		this.topicName = topicName;
	}

	public String getTopicDesc() {
		return topicDesc;
	}

	public void setTopicDesc(String topicDesc) {
		this.topicDesc = topicDesc;
	}

	public String getFlag() {
		return flag;
	}

	public void setFlag(String flag) {
		this.flag = flag;
	}
}
