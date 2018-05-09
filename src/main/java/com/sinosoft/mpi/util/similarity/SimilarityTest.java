package com.sinosoft.mpi.util.similarity;

/**
 * 匹配算法测试
 * @author admin
 */
public class SimilarityTest {
	public static void main(String[] args) {

		/*
		 * String str1 = "刘梅2018-04-26"; String str2 = "张梅2018-04-26";
		 */

		String str1 = "AAAB";
		String str2 = "AAAA";

		/**
		 * Jaro-Winkler Distance 算法
		 */
		float num = JaroWinklerUtil.getDistance(str1, str2, 0.7f);
		System.out.println("Jaro-Winkler 算法匹配值：" + num);

		/**
		 * Jaccard 适用于任意两个字符串，最严谨的匹配规则
		 */
		float j = JaccardUtil.jaccard(str1, str2);
		System.out.println("Jaccard 算法匹配值：" + j);

		/**
		 * Levenshtein Distance(编辑距离算法)
		 */
		double l = LevenshteinUtil.sim(str1, str2);
		System.out.println("Levenshtein 算法匹配值：" + l);

		/**
		 * Cosine (余弦定理) 文本位置不同内容相同算完全匹配，适用于易颠倒文本
		 */
		CosineUtil similarity = new CosineUtil(str1, str2);
		double s = similarity.sim();
		System.out.println("Cosine 算法匹配值：" + s);

	}
}
