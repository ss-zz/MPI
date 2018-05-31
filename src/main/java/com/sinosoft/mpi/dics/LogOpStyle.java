package com.sinosoft.mpi.dics;

/**
 * 索引操作日志-方式
 */
public enum LogOpStyle {

	AUTO_MERGE("1", "自动合并"),
	AUTO_NEW("2", "自动新增"),
	AUTO_SPLIT("3", "自动拆分"),
	AUTO_REMOVE("4", "自动删除"),
	MAN_MERGE("11", "手工合并"),
	MAN_NEW("12", "手工新增"),
	MAN_SPLIT("13", "手工拆分"),
	MAN_REMOVE("14", "手工删除");

	private String code;
	private String desc;

	private LogOpStyle(String code, String desc) {
		this.setCode(code);
		this.setDesc(desc);
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getDesc() {
		return desc;
	}

	public void setDesc(String desc) {
		this.desc = desc;
	}
	
	public String getDescByCode(String code) {
		LogOpStyle[] items = values();
		for (LogOpStyle item : items) {
			if (item.getCode().equals(code)) {
				return item.getDesc();
			}
		}
		return null;
	}

}
