package com.sinosoft.stringcomparison.service;

import java.util.Collection;

import org.springframework.stereotype.Service;

import com.sinosoft.stringcomparison.MetricsException;
import com.sinosoft.stringcomparison.config.StringComparisionConfig;
import com.sinosoft.stringcomparison.model.DistanceMetricType;

@Service("stringComparisonService")
public class StringComparisonService implements IStringComparisonService {

	@Override
	public Collection<DistanceMetricType> getDistanceMetricTypes() {
		return StringComparisionConfig.getInstanse().getDistanceMetricTypes().values();
	}

	@Override
	public Collection<String> getComparisonFunctionNames() {
		return StringComparisionConfig.getInstanse().getDistanceMetricTypes().keySet();
	}

	@Override
	public DistanceMetricType getDistanceMetricType(String name) {
		DistanceMetricType type = StringComparisionConfig.getInstanse().getDistanceMetricTypes().get(name);
		if (type == null) {
			throw new MetricsException("该匹配计算函数不存在");
		}
		return type;
	}

	@Override
	public double score(String metricType, String value1, String value2) {
		return this.getDistanceMetricType(metricType).getDistanceMetric().score(value1, value2);
	}

}
