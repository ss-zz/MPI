package com.sinosoft.mpi.ws;

import java.io.Serializable;

import com.sinosoft.mpi.model.PersonIndex;
import com.sinosoft.mpi.model.PersonInfo;

/**
 * 查询结果返回
 */
public class QueryResult implements Serializable {

	private static final long serialVersionUID = 2910570116889043673L;
	private boolean success = true; // 操作结果
	private String msg; // 失败消息
	private PersonIndex index; // 取得的索引
	private PersonInfo[] persons; // 取得的居民

	public QueryResult() {
		super();
	}

	public QueryResult(boolean success, String msg) {
		super();
		this.success = success;
		this.msg = msg;
	}

	public boolean isSuccess() {
		return success;
	}

	public void setSuccess(boolean success) {
		this.success = success;
	}

	public String getMsg() {
		return msg;
	}

	public void setMsg(String msg) {
		this.msg = msg;
	}

	public PersonIndex getIndex() {
		return index;
	}

	public void setIndex(PersonIndex index) {
		this.index = index;
	}

	public PersonInfo[] getPersons() {
		return persons;
	}

	public void setPersons(PersonInfo[] persons) {
		this.persons = persons;
	}
}
