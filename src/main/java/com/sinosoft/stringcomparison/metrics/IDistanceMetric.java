package com.sinosoft.stringcomparison.metrics;

/**
 * 算法接口
 */
public interface IDistanceMetric {

	public String getName();

	public String getNameCn();

	public double score(String left, String right);

}
