package com.sinosoft.block.service;

import java.util.List;

import com.sinosoft.match.model.Record;
import com.sinosoft.mpi.model.PersonIndex;
import com.sinosoft.mpi.model.PersonInfo;

/**
 * 过滤服务接口
 */
public interface IBlockService {

	/**
	 * 根据给出的对象，初步找出可能和他匹配的对象集合
	 *
	 * @param record
	 * @return
	 */
	public List<Record<PersonIndex>> findCandidates(Record<PersonInfo> record);
}
