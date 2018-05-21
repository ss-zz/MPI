package com.sinosoft.match.model;

import com.sinosoft.mpi.model.biz.MpiBizIndex;
import com.sinosoft.mpi.model.biz.MpiBizInfoRegister;

/**
 * 标示两个对象的相识度，以及匹配的情况
 */
@SuppressWarnings("rawtypes")
public class RecordPairBiz {

	private Record<MpiBizInfoRegister> leftRecord;// 匹配方
	private Record<MpiBizIndex> rightRecord;// 被匹配方
	private Double weight;// 总匹配度
	private ComparisonVector comparisonVector;// 匹配详细

	public RecordPairBiz(Record<MpiBizInfoRegister> leftRecord, Record<MpiBizIndex> rightRecord) {
		this.leftRecord = leftRecord;
		this.rightRecord = rightRecord;
	}

	public Record<MpiBizInfoRegister> getLeftRecord() {
		return leftRecord;
	}

	public void setLeftRecord(Record<MpiBizInfoRegister> leftRecord) {
		this.leftRecord = leftRecord;
	}

	public Record<MpiBizIndex> getRightRecord() {
		return rightRecord;
	}

	public void setRightRecord(Record<MpiBizIndex> rightRecord) {
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
