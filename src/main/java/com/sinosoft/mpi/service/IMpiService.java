package com.sinosoft.mpi.service;

import com.sinosoft.mpi.model.PersonIndex;
import com.sinosoft.mpi.model.PersonInfo;

public interface IMpiService {

	/**
	 * 主入口根据不同的信息状态进行合并，拆分，注册
	 * 
	 * @param personinfo
	 *            居民信息
	 */

	public void handleMpi(PersonInfo personinfo);

	/**
	 * 注册MPI
	 * 
	 * @param personinfo
	 *            居民信息
	 */
	public void regMpi(PersonInfo personinfo);

	/**
	 * 合并MPI 当居民信息
	 * 
	 * @param personinfo
	 *            居民信息
	 * @param personindex
	 *            居民索引信息
	 */
	public void MergeMpi(PersonInfo personinfo, PersonIndex personindex);

	/**
	 * 拆分MPI personinfo 居民信息
	 */
	public void MergeMpi(PersonInfo personinfo);

}
