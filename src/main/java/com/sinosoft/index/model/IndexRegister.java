package com.sinosoft.index.model;

/**
 * 主索引注册参数
 * 
 * @author sinosoft
 *
 */
public class IndexRegister {

	/**
	 * 业务唯一标识
	 */
	private String bizKey;

	/**
	 * 数据唯一标识
	 */
	private String dataId;

	/**
	 * 数据类型-1、新增；2-编辑；3-删除。默认：1
	 */
	private String type;

	/**
	 * 数据对象
	 */
	private String jsonData;

	public String getDataId() {
		return dataId;
	}

	public void setDataId(String dataId) {
		this.dataId = dataId;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getJsonData() {
		return jsonData;
	}

	public void setJsonData(String jsonData) {
		this.jsonData = jsonData;
	}

	public String getBizKey() {
		return bizKey;
	}

	public void setBizKey(String bizKey) {
		this.bizKey = bizKey;
	}

}
