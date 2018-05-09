package com.sinosoft.stringcomparison.config;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

import com.sinosoft.stringcomparison.metrics.IDistanceMetric;
import com.sinosoft.stringcomparison.model.DistanceMetricType;
import com.sinosoft.stringcomparison.util.ClassUtil;

/**
 * 字符串匹配配置
 *
 */
public class StringComparisionConfig {
	
	private static StringComparisionConfig stringComparisionConfig = new StringComparisionConfig();

	private Map<String, DistanceMetricType> metrices = new HashMap<String, DistanceMetricType>();

	private void init() {
		Set<Class<?>> clazzes = ClassUtil.getClasses("com.sinosoft.stringcomparison.metrics");
		for (Class<?> clazz : clazzes) {
			if (clazz.isInterface()) {
				continue;
			} else if (clazz.getName().indexOf("Test") > 0) {
				continue;
			} else {
				IDistanceMetric metric = null;
				try {
					metric = IDistanceMetric.class.cast(clazz.newInstance());
				} catch (InstantiationException e) {
					e.printStackTrace();
				} catch (IllegalAccessException e) {
					e.printStackTrace();
				}
				if (metric != null) {
					DistanceMetricType type = new DistanceMetricType(metric.getName(), metric.getNameCn(), metric);
					metrices.put(type.getName(), type);
				}
			}
		}
	}

	private StringComparisionConfig() {
		init();
	}

	public static StringComparisionConfig getInstanse() {
		return stringComparisionConfig;
	}

	public Map<String, DistanceMetricType> getDistanceMetricTypes() {
		return this.metrices;
	}
}
