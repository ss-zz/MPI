package com.sinosoft.stringcomparison.metrics;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

/**
 * 算法：Cosine 余弦相似度<br/>
 * 通过测量两个向量之间的角的余弦值来度量它们之间的相似性。0度角的余弦值是1，而其他任何角度的余弦值都不大于1;并且其最小值是-1。
 * 从而两个向量之间的角度的余弦值确定两个向量是否大致指向相同的方向。所以，它通常用于文本文件比较，文本位置不同内容相同算完全匹配，适用于易颠倒文本
 */
public class CosineMetric implements IDistanceMetric {

	@Override
	public String getName() {
		return "cosine";
	}

	@Override
	public String getNameCn() {
		return "余弦相似度";
	}

	@Override
	public double score(String left, String right) {
		double result = 0;
		Map<Character, int[]> vectorMap = new HashMap<Character, int[]>();
		int[] tempArray = null;
		for (Character leftChar : left.toCharArray()) {
			if (vectorMap.containsKey(leftChar)) {
				vectorMap.get(leftChar)[0]++;
			} else {
				tempArray = new int[2];
				tempArray[0] = 1;
				tempArray[1] = 0;
				vectorMap.put(leftChar, tempArray);
			}
		}
		for (Character rightChar : right.toCharArray()) {
			if (vectorMap.containsKey(rightChar)) {
				vectorMap.get(rightChar)[1]++;
			} else {
				tempArray = new int[2];
				tempArray[0] = 0;
				tempArray[1] = 1;
				vectorMap.put(rightChar, tempArray);
			}
		}
		result = pointMulti(vectorMap) / sqrtMulti(vectorMap);
		return result;
	}

	private double sqrtMulti(Map<Character, int[]> paramMap) {
		double result = 0;
		result = squares(paramMap);
		result = Math.sqrt(result);
		return result;
	}

	// 求平方和
	private double squares(Map<Character, int[]> paramMap) {
		double result1 = 0;
		double result2 = 0;
		Set<Character> keySet = paramMap.keySet();
		for (Character character : keySet) {
			int temp[] = paramMap.get(character);
			result1 += (temp[0] * temp[0]);
			result2 += (temp[1] * temp[1]);
		}
		return result1 * result2;
	}

	// 点乘法
	private double pointMulti(Map<Character, int[]> paramMap) {
		double result = 0;
		Set<Character> keySet = paramMap.keySet();
		for (Character character : keySet) {
			int temp[] = paramMap.get(character);
			result += (temp[0] * temp[1]);
		}
		return result;
	}

}
