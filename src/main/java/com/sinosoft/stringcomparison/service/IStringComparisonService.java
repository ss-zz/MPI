
package com.sinosoft.stringcomparison.service;

import java.util.Collection;

import com.sinosoft.stringcomparison.model.DistanceMetricType;

/**
 * 字符比较/算法接口
 */
public interface IStringComparisonService {

	/**
	 * 得到所有距离匹配类的描述
	 */
	public Collection<DistanceMetricType> getDistanceMetricTypes();

	/**
	 * 得到所有距离匹配类的名称（标志）
	 */
	public Collection<String> getComparisonFunctionNames();

	/**
	 * 得到实现类
	 * 
	 * @param name
	 *            实现类名称（标志）
	 */
	public DistanceMetricType getDistanceMetricType(String name);

	/**
	 * 用特定的比较实现，去比较两个字符的距离，返回相似度
	 * 
	 * @param metricType
	 * @param value1
	 * @param value2
	 * @return
	 */
	public double score(String metricType, String value1, String value2);

}
