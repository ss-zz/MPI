package com.sinosoft.match.model;

import com.sinosoft.mpi.model.PersonIndex;
import com.sinosoft.mpi.model.PersonInfo;

/**
 *
 * @Description 标示两个对象的相识度，以及匹配的情况。
 *
 * @Package com.sinosoft.match.model
 * @author <a href="mailto:qinshouxin@sinosoft.com.cn">Qin Shouxin </a>
 * @version v1.0,2012-3-20
 * @see
 * @since （可选）
 *
 */
@SuppressWarnings("rawtypes")
public class RecordPair {

	private Record<PersonInfo> leftRecord;// 匹配方
	private Record<PersonIndex> rightRecord;// 被匹配方
	private Double weight;// 总匹配度
	private ComparisonVector comparisonVector;// 匹配详细

	public RecordPair(Record<PersonInfo> leftRecord, Record<PersonIndex> rightRecord) {
		this.leftRecord = leftRecord;
		this.rightRecord = rightRecord;
	}

	public Record<PersonInfo> getLeftRecord() {
		return leftRecord;
	}

	public void setLeftRecord(Record<PersonInfo> leftRecord) {
		this.leftRecord = leftRecord;
	}

	public Record<PersonIndex> getRightRecord() {
		return rightRecord;
	}

	public void setRightRecord(Record<PersonIndex> rightRecord) {
		this.rightRecord = rightRecord;
	}

	public Record getRecord(int index) {
		if (index == 0) {
			return leftRecord;
		}
		return rightRecord;
	}

	public Double getWeight() {
		return weight;
	}

	public void setWeight(Double weight) {
		this.weight = weight;
	}

	public ComparisonVector getComparisonVector() {
		return comparisonVector;
	}

	public void setComparisonVector(ComparisonVector comparisonVector) {
		this.comparisonVector = comparisonVector;
	}

}
