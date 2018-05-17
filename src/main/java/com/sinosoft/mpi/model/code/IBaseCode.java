package com.sinosoft.mpi.model.code;

/**
 * 基础数据接口
 */
public interface IBaseCode {
	
	/**
	 * 取得编码id
	 */
	String getCodeId();

	/**
	 * 设置编码id
	 */
	void setCodeId(String codeId);

	/**
	 * 取得编码名称
	 */
	String getCodeName();

	/**
	 * 设置编码名称
	 */
	void setCodeName(String codeName);
}
