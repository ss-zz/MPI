
package com.sinosoft.stringcomparison.model;

import java.io.Serializable;

import com.sinosoft.stringcomparison.metrics.IDistanceMetric;

/**
 * 计算实现的名字（标志）和类的对应管理
 */
public class DistanceMetricType implements Serializable {

	private static final long serialVersionUID = -4642403967047414128L;

	private String name;
	private String nameCn;
	private IDistanceMetric distanceMetric;

	public DistanceMetricType(String name, String nameCn, IDistanceMetric distanceMetric) {
		super();
		this.name = name;
		this.nameCn = nameCn;
		this.distanceMetric = distanceMetric;
	}

	public IDistanceMetric getDistanceMetric() {
		return distanceMetric;
	}

	public void setDistanceMetric(IDistanceMetric distanceMetric) {
		this.distanceMetric = distanceMetric;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getNameCn() {
		return nameCn;
	}

	public void setNameCn(String nameCn) {
		this.nameCn = nameCn;
	}

	@Override
	public String toString() {
		return name;
	}
}
