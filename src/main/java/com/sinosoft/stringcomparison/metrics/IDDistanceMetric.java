package com.sinosoft.stringcomparison.metrics;

import com.sinosoft.mpi.util.IdcardUtils;

/**
 * 身份证匹配算法
 */
public class IDDistanceMetric implements IDistanceMetric {

	@Override
	public double score(String left, String right) {
		double result = 0;
		if (left == null || right == null) {
			return result;
		}
		int length = left.length();

		if (left.length() == right.length()) {
			if (left.equals(right)) {
				result = 1;
			}
		} else {
			if (length - 18 == 0) {
				if (IdcardUtils.conver18CardTo15(left).equals(right)) {
					result = 1;
				}
			} else if (length - 15 == 0) {
				System.out.print("left=" + left + ",right=" + right);
				if (IdcardUtils.conver15CardTo18(left).equals(right)) {
					result = 1;
				}
			} else {
				result = 0;
			}
		}

		return result;

	}

	@Override
	public String getName() {
		return "id";
	}

	@Override
	public String getNameCn() {
		return "身份证";
	}

}
