package com.sinosoft.mpi.model;

import java.io.Serializable;

import com.sinosoft.block.model.BlockField;
import com.sinosoft.mpi.cache.CacheManager;

/**
 * MPI_BLOCK_GROUP (初筛分组)
 */
public class BlockGroup implements Serializable {

	private static final long serialVersionUID = -3678787038666509186L;
	/* 分组主键(GROUP_ID) 分组主键 */
	private String groupId;

	/* 初筛主键(BOLCK_ID) 初筛主键 */
	private String bolckId;

	/* 分组标志(GROUP_SIGN) 分组标志 */
	private Integer groupSign;

	/* 数据库字段(DB_FIELD) 数据库字段 */
	private String dbField;

	/* 实体字段(PROPERTY_NAME) 实体字段 */
	private String propertyName;

	/* 转换函数(FUN_NAME) 转换函数 */
	private String funName;

	/**
	 * 字段中文名 非存库字段
	 */
	private String propertyCnName;

	public BlockGroup() {
		super();
	}

	public BlockGroup(BlockField field) {
		this.dbField = field.getDbField();
		this.funName = field.getFunName();
		this.propertyName = field.getField();
		this.propertyCnName = CacheManager.getCodeName(PersonPropertiesDesc.class, this.propertyName);
	}

	// =======================setter&getter
	/**
	 * 分组主键(GROUP_ID) 分组主键
	 **/
	public String getGroupId() {
		return groupId;
	}

	/**
	 * 分组主键(GROUP_ID) 分组主键
	 **/
	public void setGroupId(String groupId) {
		this.groupId = groupId;
	}

	/**
	 * 初筛主键(BOLCK_ID) 初筛主键
	 **/
	public String getBolckId() {
		return bolckId;
	}

	/**
	 * 初筛主键(BOLCK_ID) 初筛主键
	 **/
	public void setBolckId(String bolckId) {
		this.bolckId = bolckId;
	}

	/**
	 * 分组标志(GROUP_SIGN) 分组标志
	 **/
	public Integer getGroupSign() {
		return groupSign;
	}

	/**
	 * 分组标志(GROUP_SIGN) 分组标志
	 **/
	public void setGroupSign(Integer groupSign) {
		this.groupSign = groupSign;
	}

	/**
	 * 数据库字段(DB_FIELD) 数据库字段
	 **/
	public String getDbField() {
		return dbField;
	}

	/**
	 * 数据库字段(DB_FIELD) 数据库字段
	 **/
	public void setDbField(String dbField) {
		this.dbField = dbField;
	}

	/**
	 * 实体字段(PROPERTY_NAME) 实体字段
	 **/
	public String getPropertyName() {
		return propertyName;
	}

	/**
	 * 实体字段(PROPERTY_NAME) 实体字段
	 **/
	public void setPropertyName(String propertyName) {
		this.propertyName = propertyName;
	}

	/**
	 * 转换函数(FUN_NAME) 转换函数
	 **/
	public String getFunName() {
		return funName;
	}

	/**
	 * 转换函数(FUN_NAME) 转换函数
	 **/
	public void setFunName(String funName) {
		this.funName = funName;
	}

	/**
	 * 字段中文名
	 */
	public String getPropertyCnName() {
		return this.propertyCnName == null ? CacheManager.getCodeName(PersonPropertiesDesc.class, this.propertyName)
				: this.propertyCnName;
	}

	/**
	 * 字段中文名
	 */
	public void setPropertyCnName(String propertyCnName) {
		this.propertyCnName = propertyCnName;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((bolckId == null) ? 0 : bolckId.hashCode());
		result = prime * result + ((dbField == null) ? 0 : dbField.hashCode());
		result = prime * result + ((funName == null) ? 0 : funName.hashCode());
		result = prime * result + ((groupId == null) ? 0 : groupId.hashCode());
		result = prime * result + ((groupSign == null) ? 0 : groupSign.hashCode());
		result = prime * result + ((propertyName == null) ? 0 : propertyName.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		BlockGroup other = (BlockGroup) obj;
		if (bolckId == null) {
			if (other.bolckId != null)
				return false;
		} else if (!bolckId.equals(other.bolckId))
			return false;
		if (dbField == null) {
			if (other.dbField != null)
				return false;
		} else if (!dbField.equals(other.dbField))
			return false;
		if (funName == null) {
			if (other.funName != null)
				return false;
		} else if (!funName.equals(other.funName))
			return false;
		if (groupId == null) {
			if (other.groupId != null)
				return false;
		} else if (!groupId.equals(other.groupId))
			return false;
		if (groupSign == null) {
			if (other.groupSign != null)
				return false;
		} else if (!groupSign.equals(other.groupSign))
			return false;
		if (propertyName == null) {
			if (other.propertyName != null)
				return false;
		} else if (!propertyName.equals(other.propertyName))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "BlockGroup [groupId=" + groupId + ", bolckId=" + bolckId + ", groupSign=" + groupSign + ", dbField="
				+ dbField + ", propertyName=" + propertyName + ", funName=" + funName + "]";
	}

	public BlockField toBlockField() {
		BlockField bf = new BlockField();
		bf.setDbField(dbField);
		bf.setField(propertyName);
		bf.setFunName(funName);
		return bf;
	}

}
