package com.sinosoft.match.service;

import java.util.List;

import com.sinosoft.match.model.Record;
import com.sinosoft.match.model.RecordPair;
import com.sinosoft.mpi.model.PersonIndex;
import com.sinosoft.mpi.model.PersonInfo;

/**
 * 对象匹配接口
 */
public interface IMatchService {

	/**
	 * 连个对象的匹配情况
	 * 
	 * @param leftRecord
	 * @param rightRecord
	 * @return
	 */
	public RecordPair match(Record<PersonInfo> leftRecord, Record<PersonIndex> rightRecord);

	/**
	 * 是否匹配
	 * 
	 * @param leftRecord
	 * @param rightRecord
	 * @return
	 */
	public boolean isMatched(Record<PersonInfo> leftRecord, Record<PersonIndex> rightRecord);

	/**
	 * 找出所有的可能符合条件匹配对
	 * 
	 * @param leftRecord
	 * @param rightRecords
	 *            参与被匹配的集合
	 * @return
	 */
	public List<RecordPair> match(Record<PersonInfo> leftRecord, List<Record<PersonIndex>> rightRecords);

	/**
	 * 找到匹配对
	 * 
	 * @param pairs
	 * @return
	 */
	public RecordPair matchedPair(List<RecordPair> pairs);

}
