package com.sinosoft.mpi.util.similarity;

/**
 * Levenshtein 距离
 * @author hsg
 * 算法介绍： Levenshtein距离是一种计算两个字符串间的差异程度的字符串度量（string metric）。
 * 我们可以认为Levenshtein距离就是从一个字符串修改到另一个字符串时，其中编辑单个字符（比如修改、插入、删除）所需要的最少次数。
 * 将每个词与词典中的词条比较，英文单词往往需要做词干提取等规范化处理，如果一个词在词典中不存在，就被认为是一个错误，然后试图提示N个最可能要输入的词——拼写建议。
 * 常用的提示单词的算法就是列出词典中与原词具有最小编辑距离的词条。
 *
 */
public class LevenshteinUtil {
	private static int min(int one, int two, int three) {
		int min = one;
		if (two < min) {
			min = two;
		}
		if (three < min) {
			min = three;
		}
		return min;
	}

	public static int ld(String str1, String str2) {
		int d[][]; // 矩阵
		int n = str1.length();
		int m = str2.length();
		int i; // 遍历str1的
		int j; // 遍历str2的
		char ch1; // str1的
		char ch2; // str2的
		int temp; // 记录相同字符,在某个矩阵位置值的增量,不是0就是1
		if (n == 0) {
			return m;
		}
		if (m == 0) {
			return n;
		}
		d = new int[n + 1][m + 1];
		for (i = 0; i <= n; i++) { // 初始化第一列
			d[i][0] = i;
		}
		for (j = 0; j <= m; j++) { // 初始化第一行
			d[0][j] = j;
		}
		for (i = 1; i <= n; i++) { // 遍历str1
			ch1 = str1.charAt(i - 1);
			// 去匹配str2
			for (j = 1; j <= m; j++) {
				ch2 = str2.charAt(j - 1);
				if (ch1 == ch2) {
					temp = 0;
				} else {
					temp = 1;
				}
				// 左边+1,上边+1, 左上角+temp取最小
				d[i][j] = min(d[i - 1][j] + 1, d[i][j - 1] + 1, d[i - 1][j - 1] + temp);
			}
		}
		return d[n][m];
	}

	public static double sim(String str1, String str2) {
		try {
			double ld = (double) ld(str1, str2);
			return (1 - ld / (double) Math.max(str1.length(), str2.length()));
		} catch (Exception e) {
			return 0.1;
		}
	}
}
