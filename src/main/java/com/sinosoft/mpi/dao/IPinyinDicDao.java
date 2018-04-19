package com.sinosoft.mpi.dao;

/**
 * 拼音字典操作 负责处理人员摘要的拼音次数
 */
public interface IPinyinDicDao {

	/**
	 * 查询是否可以入库
	 * 
	 * @param pinyinStr
	 * @return
	 */
	public boolean isOverPinYinTimes(String pinyinStr);

	/**
	 * 更新该拼音入库次数
	 * 
	 * @param pinyinString
	 */
	public void updatePinyinTimes(String pinyinString);

}
