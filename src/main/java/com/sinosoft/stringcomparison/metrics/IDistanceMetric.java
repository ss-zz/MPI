package com.sinosoft.stringcomparison.metrics;

/**
 * 距离计算函数接口
 */
public interface IDistanceMetric {

	public double score(String left, String right);

	public String getName();

	public String getNameCn();

}
