package com.sinosoft.stringcomparison.metrics;

public class AddressDistanceMetric implements IDistanceMetric {

	private String name = "address";
	private String nameCn = "地址";

	@Override
	public double score(String left, String right) {
		return 0;
	}

	@Override
	public String getName() {
		return this.name;
	}

	public String getNameCn() {
		return nameCn;
	}

}
