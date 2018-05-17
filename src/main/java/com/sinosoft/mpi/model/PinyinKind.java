package com.sinosoft.mpi.model;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.Id;

/**
 * 拼音表
 */
@Entity
public class PinyinKind implements Serializable {

	private static final long serialVersionUID = 1L;

	/**
	 * 拼音键值
	 */
	@Id
	private String PINYINKEY;

	/**
	 * 该拼音出现次数
	 */
	private Integer TIMES;

	public String getPINYINKEY() {
		return PINYINKEY;
	}

	public void setPINYINKEY(String pINYINKEY) {
		PINYINKEY = pINYINKEY;
	}

	public Integer getTIMES() {
		return TIMES;
	}

	public void setTIMES(Integer tIMES) {
		TIMES = tIMES;
	}

}
