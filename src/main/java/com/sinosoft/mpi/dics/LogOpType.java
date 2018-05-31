package com.sinosoft.mpi.dics;

/**
 * 索引操作日志 类型
 */
public enum LogOpType {

	MATCH("1", "匹配"), MODIFY("2", "修订");

	private String code;
	private String desc;

	private LogOpType(String code, String desc) {
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
		LogOpType[] items = values();
		for (LogOpType item : items) {
			if (item.getCode().equals(code)) {
				return item.getDesc();
			}
		}
		return null;
	}

}
