package com.sinosoft.mpi.model;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Transient;

import org.hibernate.annotations.GenericGenerator;

import com.sinosoft.block.model.BlockField;
import com.sinosoft.mpi.cache.CacheManager;
import com.sinosoft.mpi.model.code.PersonPropertiesDesc;

/**
 * 初筛分组
 */
@Entity(name = "MPI_BLOCK_GROUP")
public class BlockGroup implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GenericGenerator(name = "system-uuid", strategy = "uuid")
	@GeneratedValue(generator = "system-uuid")
	private String groupId;

	/**
	 * 初筛主键
	 */
	private String bolckId;

	/**
	 * 分组标志
	 */
	private Integer groupSign;

	/**
	 * 数据库字段
	 */
	private String dbField;

	/**
	 * 实体字段
	 */
	private String propertyName;

	/**
	 * 转换函数
	 */
	private String funName;

	/**
	 * 字段中文名 非存库字段
	 */
	@Transient
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

	public String getGroupId() {
		return groupId;
	}

	public void setGroupId(String groupId) {
		this.groupId = groupId;
	}

	public String getBolckId() {
		return bolckId;
	}

	public void setBolckId(String bolckId) {
		this.bolckId = bolckId;
	}

	public Integer getGroupSign() {
		return groupSign;
	}

	public void setGroupSign(Integer groupSign) {
		this.groupSign = groupSign;
	}

	public String getDbField() {
		return dbField;
	}

	public void setDbField(String dbField) {
		this.dbField = dbField;
	}

	public String getPropertyName() {
		return propertyName;
	}

	public void setPropertyName(String propertyName) {
		this.propertyName = propertyName;
	}

	public String getFunName() {
		return funName;
	}

	public void setFunName(String funName) {
		this.funName = funName;
	}

	public String getPropertyCnName() {
		return this.propertyCnName == null ? CacheManager.getCodeName(PersonPropertiesDesc.class, this.propertyName)
				: this.propertyCnName;
	}

	public void setPropertyCnName(String propertyCnName) {
		this.propertyCnName = propertyCnName;
	}

	public BlockField toBlockField() {
		BlockField bf = new BlockField();
		bf.setDbField(dbField);
		bf.setField(propertyName);
		bf.setFunName(funName);
		return bf;
	}

}
