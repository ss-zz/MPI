package com.sinosoft.stringcomparison.metrics;

/**
 * 全字符匹配
 */
public class ExactDistanceMetric implements IDistanceMetric {

	@Override
	public double score(String left, String right) {
		double result = 0;
		if (left == null || right == null) {

		} else {
			if (left.equals(right)) {
				result = 1;
			}
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
