package com.sinosoft.mpi.model.biz;

import java.io.Serializable;

import com.sinosoft.mpi.model.code.IBaseCode;

public class MpiBizBPropertiesDesc implements IBaseCode, Serializable {

	private static final long serialVersionUID = 7389151965152987583L;
	private String propertyName; // 属性名
	private String propertyDesc; // 属性描述
	private String column;// 对应数据库字段

	public MpiBizBPropertiesDesc() {
		super();
	}

	public MpiBizBPropertiesDesc(String propertyName, String propertyDesc, String column) {
		super();
		this.propertyName = propertyName;
		this.propertyDesc = propertyDesc;
		this.column = column;
	}

	public String getPropertyName() {
		return propertyName;
	}

	public void setPropertyName(String propertyName) {
		this.propertyName = propertyName;
	}

	public String getPropertyDesc() {
		return propertyDesc;
	}

	public void setPropertyDesc(String propertyDesc) {
		this.propertyDesc = propertyDesc;
	}

	@Override
	public String getCodeId() {
		return propertyName;
	}

	@Override
	public void setCodeId(String codeId) {
		this.propertyName = codeId;
	}

	@Override
	public String getCodeName() {
		return propertyDesc;
	}

	@Override
	public void setCodeName(String codeName) {
		this.propertyDesc = codeName;
	}

	public String getColumn() {
		return column;
	}

	public void setColumn(String column) {
		this.column = column;
	}
}
