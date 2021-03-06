package com.sinosoft.stringcomparison.metrics;

import java.util.Arrays;

/**
 * 算法工具：Jaro-Winkler 算法<br/>
 * Jaro-Winkler是计算2个字符之间相似度的一种算法,主要用于 record
 * linkage/数据连接(duplicate/重复记录)方面的领域，Jaro-Winkler distance 最后得分越高说明相似度越大,
 * 适合于字符串比较如名字这样较短字符之间计算相似度。0分标识没有任何相似度，1标识完全匹配。
 */
public class JaroWinklerMetric implements IDistanceMetric {

	// 默认值
	private static final double DEFAULT_THRESHOLD = 0.7;

	@Override
	public String getName() {
		return "Jaro-Winkler";
	}

	@Override
	public String getNameCn() {
		return "Jaro-Winkler";
	}

	@Override
	public double score(String left, String right) {
		int[] mtp = matches(left, right);
		float m = (float) mtp[0];
		if (m == 0) {

			return 0f;

		}
		float j = ((m / left.length() + m / right.length() + (m - mtp[1]) / m)) / 3;
		float jw = j < DEFAULT_THRESHOLD ? j : j + Math.min(0.1f, 1f / mtp[3]) * mtp[2] * (1 - j);
		return jw;
	}

	private int[] matches(String s1, String s2) {

		String max, min;
		if (s1.length() > s2.length()) {
			max = s1;
			min = s2;
		} else {
			max = s2;
			min = s1;
		}
		int range = Math.max(max.length() / 2 - 1, 0);
		int[] matchIndexes = new int[min.length()];
		Arrays.fill(matchIndexes, -1);
		boolean[] matchFlags = new boolean[max.length()];
		int matches = 0;
		for (int mi = 0; mi < min.length(); mi++) {

			char c1 = min.charAt(mi);
			for (int xi = Math.max(mi - range, 0), xn = Math.min(mi + range + 1, max.length()); xi < xn; xi++) {
				if (!matchFlags[xi] && c1 == max.charAt(xi)) {
					matchIndexes[mi] = xi;
					matchFlags[xi] = true;
					matches++;
					break;
				}
			}
		}
		char[] ms1 = new char[matches];
		char[] ms2 = new char[matches];
		for (int i = 0, si = 0; i < min.length(); i++) {
			if (matchIndexes[i] != -1) {
				ms1[si] = min.charAt(i);
				si++;

			}

		}
		for (int i = 0, si = 0; i < max.length(); i++) {

			if (matchFlags[i]) {

				ms2[si] = max.charAt(i);
				si++;
			}
		}
		int transpositions = 0;
		for (int mi = 0; mi < ms1.length; mi++) {
			if (ms1[mi] != ms2[mi]) {
				transpositions++;
			}
		}
		int prefix = 0;
		for (int mi = 0; mi < min.length(); mi++) {
			if (s1.charAt(mi) == s2.charAt(mi)) {
				prefix++;
			} else {
				break;
			}
		}
		return new int[] { matches, transpositions / 2, prefix, max.length() };

	}

}
