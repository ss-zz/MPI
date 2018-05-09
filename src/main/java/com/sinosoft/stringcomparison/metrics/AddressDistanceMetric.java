package com.sinosoft.stringcomparison.metrics;

/**
 * 地址匹配算法-未完成
 */
public class AddressDistanceMetric implements IDistanceMetric {

	@Override
	public double score(String left, String right) {
		return 0;
	}

	@Override
	public String getName() {
		return "address";
	}

	public String getNameCn() {
		return "地址";
	}

}
