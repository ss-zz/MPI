package com.sinosoft.mpi.model;

/**
 * @Description: 基础数据接口
 * 
 * @Package com.sinosoft.mpi.model
 * @author bysun
 * @version v1.0,2012-3-14
 * @see
 * @since v1.0
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
