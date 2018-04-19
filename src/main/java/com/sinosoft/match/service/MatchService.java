package com.sinosoft.match.service;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.sinosoft.match.MatchException;
import com.sinosoft.match.config.MatchConfig;
import com.sinosoft.match.model.ComparisonVector;
import com.sinosoft.match.model.MatchField;
import com.sinosoft.match.model.Record;
import com.sinosoft.match.model.RecordPair;
import com.sinosoft.mpi.model.PersonIndex;
import com.sinosoft.mpi.model.PersonInfo;
import com.sinosoft.stringcomparison.service.IStringComparisonService;

/**
 * 匹配服务
 */
@Service("matchService")
public class MatchService implements IMatchService {

	@Resource
	private IStringComparisonService stringComparisonService;

	List<MatchField> matchFields = MatchConfig.getInstanse().getMatchFields();

	@Override
	public RecordPair match(Record<PersonInfo> leftRecord, Record<PersonIndex> rightRecord) {
		RecordPair pair = new RecordPair(leftRecord, rightRecord);
		// 计算比较
		this.scoreRecordPair(pair);
		// 计算合并匹配度
		this.calculateWeight(pair);

		return pair;
	}

	@Override
	public List<RecordPair> match(Record<PersonInfo> leftRecord, List<Record<PersonIndex>> rightRecords) {
		List<RecordPair> result = new ArrayList<RecordPair>(0);
		for (Record<PersonIndex> rightRecord : rightRecords) {
			RecordPair pair = this.match(leftRecord, rightRecord);
			if (pair.getWeight() > MatchConfig.getInstanse().getMatchWeightThreshold()) {
				result.add(pair);
			}
		}
		return result;
	}

	// 计算每个属性的匹配度
	private void scoreRecordPair(RecordPair pair) {
		pair.setComparisonVector(new ComparisonVector(matchFields));
		double distance = 0.0;
		for (int i = 0; i < matchFields.size(); i++) {
			MatchField matchField = matchFields.get(i);
			String fieldName = matchField.getFieldName();
			String value1 = pair.getLeftRecord().getAsString(fieldName);
			String value2 = pair.getRightRecord().getAsString(fieldName);
			/*
			 * double distance =
			 * stringComparisonService.score(matchField.getComparatorFunction(), value1,
			 * value2);
			 */
			if (!value1.equals("") && !value2.equals("")) {
				distance = stringComparisonService.score(matchField.getComparatorFunction(), value1, value2);
			}
			pair.getComparisonVector().setScore(i, distance);
		}
	}

	// 计算总的匹配度
	private void calculateWeight(RecordPair pair) {
		double wight = 0;
		double[] scores = pair.getComparisonVector().getScores();
		if (pair.getComparisonVector() != null && matchFields.size() == pair.getComparisonVector().getScores().length) {
			for (int i = 0; i < matchFields.size(); i++) {
				wight += matchFields.get(i).getWeight() * scores[i];
			}
			if (wight > 1.0) {
				wight = 1.0;
			}
		} else {
			throw new MatchException("匹配失败，检查是否计算匹配度");
		}
		if (wight > 1) {
			wight = 1;
		}
		pair.setWeight(wight);
	}

	@SuppressWarnings({ "rawtypes", "unchecked" })
	@Override
	public boolean isMatched(Record leftRecord, Record rightRecord) {
		return MatchConfig.getInstanse().getAgreementWeightThreshold() <= this.match(leftRecord, rightRecord)
				.getWeight();
	}

	@Override
	public RecordPair matchedPair(List<RecordPair> pairs) {
		for (RecordPair pair : pairs) {
			if (MatchConfig.getInstanse().getAgreementWeightThreshold() <= pair.getWeight()) {
				return pair;
			} else {// update 匹配值介于0.4-1.0之间 2013年5月14日
				if (pair.getWeight() >= MatchConfig.getInstanse().getMatchWeightThreshold()) {
					return pair;
				}
			}
		}
		return null;
	}

}
