package com.sinosoft.index.model.match;

import java.util.Map;

import com.sinosoft.match.model.ComparisonVector;
import com.sinosoft.match.model.Record;

/**
 * 主索引匹配对
 */
public class MatchPair {

	private Record<Map<String, String>> srcRecord;// 原始数据
	private Record<Map<String, String>> indexRecord;// 索引数据
	private Double weight;// 总匹配度
	private ComparisonVector comparisonVector;// 匹配详细

	public MatchPair(Record<Map<String, String>> srcRecord, Record<Map<String, String>> indexRecord) {
		this.srcRecord = srcRecord;
		this.indexRecord = indexRecord;
	}

	public Record<Map<String, String>> getSrcRecord() {
		return srcRecord;
	}

	public void setLeftRecord(Record<Map<String, String>> srcRecord) {
		this.srcRecord = srcRecord;
	}

	public Record<Map<String, String>> getIndexRecord() {
		return indexRecord;
	}

	public void setIndexRecord(Record<Map<String, String>> indexRecord) {
		this.indexRecord = indexRecord;
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
