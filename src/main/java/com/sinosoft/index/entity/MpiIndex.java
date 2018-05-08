package com.sinosoft.index.entity;

import java.util.Date;
import java.util.Map;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

/**
 * 主索引表
 */
@Document(collection = "mpi-index")
public class MpiIndex {

	/**
	 * 主索引id
	 */
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private String id;

	/**
	 * 数据内容
	 */
	private Map<String, String> data;

	/**
	 * 创建时间
	 */
	private Date createTime;

	/**
	 * 更新时间
	 */
	private Date updateTime;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	public Date getUpdateTime() {
		return updateTime;
	}

	public void setUpdateTime(Date updateTime) {
		this.updateTime = updateTime;
	}

	public Map<String, String> getData() {
		return data;
	}

	public void setData(Map<String, String> data) {
		this.data = data;
	}

}
