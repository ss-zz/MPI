package com.sinosoft.stringcomparison.metrics;

import java.util.HashSet;
import java.util.Set;

/**
 * 算法工具：Jaccard算法<br/>
 * 计算两个字符串的Jaccard相似度，每个字符串可以转化为一个集合，如“abc”->{#a,ab,bc,c#}结果为两个字符串的交集/并集。
 * 值越大越相似。适用于任意两个字符串。
 */
public class JaccardMetric implements IDistanceMetric {

	@Override
	public String getName() {
		return "Jaccard";
	}

	@Override
	public String getNameCn() {
		return "Jaccard算法";
	}

	@Override
	public double score(String left, String right) {
		Set<String> setA = set(left);
		Set<String> setB = set(right);
		Set<String> inter = new HashSet<String>();

		inter.addAll(setA);
		inter.retainAll(setB);

		Set<String> union = new HashSet<String>();
		union.addAll(setA);
		union.addAll(setB);

		float interSize = inter.size();
		float unionSize = union.size();

		return interSize / unionSize;
	}

	private Set<String> set(String x) {
		Set<String> set = new HashSet<String>();
		set.add("#" + x.charAt(0));
		set.add(x.charAt(x.length() - 1) + "#");
		for (int i = 0; i < x.length() - 1; i++) {
			set.add(x.charAt(i) + "" + x.charAt(i + 1));
		}
		return set;
	}

}
