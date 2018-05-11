package com.sinosoft.match.model;

import com.sinosoft.mpi.model.biz.BizIndex;
import com.sinosoft.mpi.model.biz.BizInfo;

/**
 * 标示两个对象的相识度，以及匹配的情况
 */
@SuppressWarnings("rawtypes")
public class RecordPairBiz {

	private Record<BizInfo> leftRecord;// 匹配方
	private Record<BizIndex> rightRecord;// 被匹配方
	private Double weight;// 总匹配度
	private ComparisonVector comparisonVector;// 匹配详细

	public RecordPairBiz(Record<BizInfo> leftRecord, Record<BizIndex> rightRecord) {
		this.leftRecord = leftRecord;
		this.rightRecord = rightRecord;
	}

	public Record<BizInfo> getLeftRecord() {
		return leftRecord;
	}

	public void setLeftRecord(Record<BizInfo> leftRecord) {
		this.leftRecord = leftRecord;
	}

	public Record<BizIndex> getRightRecord() {
		return rightRecord;
	}

	public void setRightRecord(Record<BizIndex> rightRecord) {
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
