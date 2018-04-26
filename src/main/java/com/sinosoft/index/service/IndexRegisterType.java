package com.sinosoft.index.service;

/**
 * 主索引注册类型
 */
public enum IndexRegisterType {

	ADD("01"), // 新增
	EDIT("02"), // 编辑
	DEL("09");// 删除

	private String type;

	private IndexRegisterType(String type) {
		this.setType(type);
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

}
