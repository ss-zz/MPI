package com.sinosoft.index.entity;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import com.sinosoft.mpi.model.IBaseCode;

/**
 * 主索引字段配置表
 */
@Document(collection = "mpi-config-field")
public class MpiConfigField implements IBaseCode {

	/**
	 * id
	 */
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private String id;

	/**
	 * 属性名
	 */
	private String propertyName;

	/**
	 * 属性描述
	 */
	private String propertyDesc;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
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
		return this.propertyName;
	}

	@Override
	public void setCodeId(String codeId) {
		this.propertyName = codeId;
	}

	@Override
	public String getCodeName() {
		return this.propertyDesc;
	}

	@Override
	public void setCodeName(String codeName) {
		this.propertyDesc = codeName;
	}

}
