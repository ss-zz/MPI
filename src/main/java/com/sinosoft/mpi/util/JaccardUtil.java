package com.sinosoft.mpi.util;

import java.util.HashSet;
import java.util.Set;

/**
 * 计算两个字符串的Jaccard相似度，每个字符串可以转化为一个集合，如“abc”->{#a,ab,bc,c#}结果为两个字符串的交集/并集。值越大越相似。适用于任意两个字符串
 * @author hsg
 *
 */
public class JaccardUtil {
	public static void main(String[] args) {
		float j = jaccard("dave", "dave");
		int o = o("dave", "dave");
		System.out.println(o);
		System.out.println(j);
	}

	public static int o(String a, String b) {
		Set<String> setA = set(a);
		Set<String> setB = set(b);
		Set<String> o = new HashSet<String>();
		o.addAll(setA);
		o.retainAll(setB);
		return o.size();
	}

	public static float jaccard(String a, String b) {

		Set<String> setA = set(a);
		Set<String> setB = set(b);
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

	public static Set<String> set(String x) {
		Set<String> set = new HashSet<String>();

		set.add("#" + x.charAt(0));
		set.add(x.charAt(x.length() - 1) + "#");

		for (int i = 0; i < x.length() - 1; i++) {
			set.add(x.charAt(i) + "" + x.charAt(i + 1));
		}

		return set;
	}
}
