package com.sinosoft.stringcomparison.metrics;

/**
 * 精确匹配算法
 */
public class ExactDistanceMetric implements IDistanceMetric {

	@Override
	public double score(String left, String right) {
		double result = 0;
		if (left == null || right == null) {
			return result;
		}
		if (left.equals(right)) {
			result = 1;
		}
		return result;
	}

	@Override
	public String getName() {
		return "exact";
	}

	@Override
	public String getNameCn() {
		return "精确";
	}

}
