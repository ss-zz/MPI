package com.sinosoft.block.model;

import java.io.Serializable;

public class BlockField implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -1682703665869495046L;
	
	
	private String dbField;
	
	private String field;
	
	private String funName;

	public String getDbField() {
		return dbField;
	}

	public void setDbField(String dbField) {
		this.dbField = dbField;
	}

	public String getField() {
		return field;
	}

	public void setField(String field) {
		this.field = field;
	}

	public String getFunName() {
		return funName;
	}

	public void setFunName(String funName) {
		this.funName = funName;
	}
	
	
	

}
