package com.sinosoft.subscription.model;

/** 
* @ClassName: SubscribeThemeItem 
* @Description:  订阅发布主题明细
* @author sinosoft_SunWG
* @date 2013-11-18 下午2:58:40  
*/
public class SubscribeThemeItem {
	/**
	 * @Fields itemId : 明细ID
	 */
	private String itemId;
	/**
	 * @Fields topicId : 主题编号
	 */
	private String topicId;
	/**
	 * @Fields itemName : 明细名称
	 */
	private String itemName;
	/**
	 * @Fields itemCode :明细代码
	 */
	private String itemCode;
	/**
	 * @Fields itemDesc : 明细描述
	 */
	private String itemDesc;

	public String getItemId() {
		return itemId;
	}

	public void setItemId(String itemId) {
		this.itemId = itemId;
	}

	public String getTopicId() {
		return topicId;
	}

	public void setTopicId(String topicId) {
		this.topicId = topicId;
	}

	public String getItemName() {
		return itemName;
	}

	public void setItemName(String itemName) {
		this.itemName = itemName;
	}

	public String getItemCode() {
		return itemCode;
	}

	public void setItemCode(String itemCode) {
		this.itemCode = itemCode;
	}

	public String getItemDesc() {
		return itemDesc;
	}

	public void setItemDesc(String itemDesc) {
		this.itemDesc = itemDesc;
	}
}
