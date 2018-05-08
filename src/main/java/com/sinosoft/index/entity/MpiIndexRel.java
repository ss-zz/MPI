package com.sinosoft.index.entity;

import java.util.Date;
import java.util.Map;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

/**
 * 主索引与原始数据关联表
 */
@Document(collection = "mpi-index-rel")
public class MpiIndexRel {

	/**
	 * id
	 */
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private String id;

	/**
	 * 主索引id
	 */
	private String mpiIndexId;

	/**
	 * 原始id
	 */
	private String srcId;

	/**
	 * 原始数据内容
	 */
	private Map<String, String> srcData;

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

	public String getMpiIndexId() {
		return mpiIndexId;
	}

	public void setMpiIndexId(String mpiIndexId) {
		this.mpiIndexId = mpiIndexId;
	}

	public String getSrcId() {
		return srcId;
	}

	public void setSrcId(String srcId) {
		this.srcId = srcId;
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

	public Map<String, String> getSrcData() {
		return srcData;
	}

	public void setSrcData(Map<String, String> srcData) {
		this.srcData = srcData;
	}

}
