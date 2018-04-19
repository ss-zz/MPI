package com.sinosoft.mpi.context;

public enum QueryConditionType {
	LIKE(" like ? "),
	EQUAL(" = ? "),
	GREATER(" > ? "),
	LESS(" < ? "),
	GREATER_OR_EQUAL(" >= ? "),
	LESS_OR_EQUAL(" <= ? ")
	;
	private String type;

	public String getType() {
		return type;
	}

	private QueryConditionType(String type) {
		this.type = type;
	}
}
