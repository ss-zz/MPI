package com.sinosoft.stringcomparison.service;

import java.util.Collection;
import java.util.Map;

import org.springframework.stereotype.Service;

import com.sinosoft.stringcomparison.config.StringComparisionConfig;
import com.sinosoft.stringcomparison.model.DistanceMetricType;

/**
 * 字符串匹配服务
 */
@Service
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
		Map<String, DistanceMetricType> types = StringComparisionConfig.getInstanse().getDistanceMetricTypes();
		DistanceMetricType type = types.get(name);
		if (type == null) {
			// 默认返回精确匹配
			return types.get("exact");
		}
		return type;
	}

	@Override
	public double score(String metricType, String value1, String value2) {
		return this.getDistanceMetricType(metricType).getDistanceMetric().score(value1, value2);
	}

}
