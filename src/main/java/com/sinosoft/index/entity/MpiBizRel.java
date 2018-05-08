package com.sinosoft.index.entity;

import java.util.Map;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

/**
 * 业务数据与原始数据关联表
 */
@Document(collection = "mpi-biz-rel")
public class MpiBizRel {

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
	 * 次id/分组id
	 */
	private String groupId;

	/**
	 * 原始业务数据id
	 */
	private String srcBizId;

	/**
	 * 原始业务数据内容
	 */
	private Map<String, String> srcData;

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

	public String getGroupId() {
		return groupId;
	}

	public void setGroupId(String groupId) {
		this.groupId = groupId;
	}

	public String getSrcBizId() {
		return srcBizId;
	}

	public void setSrcBizId(String srcBizId) {
		this.srcBizId = srcBizId;
	}

	public Map<String, String> getSrcData() {
		return srcData;
	}

	public void setSrcData(Map<String, String> srcData) {
		this.srcData = srcData;
	}

}
