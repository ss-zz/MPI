package com.sinosoft.mpi.model.biz;

import java.io.Serializable;
import java.util.Date;

/**
 * 业务主索引操作日志查询条件
 */
public class MpiBizIdxLogSearch implements Serializable {

	private static final long serialVersionUID = 6936464128927712060L;

	private String blInfoSour;
	private String blType;
	private String blUserId;
	private Date begin;
	private Date end;
	private String blTimeBegin;
	private String blTimeEnd;
	private String blMatchedBegin;
	private String blMatchedEnd;

	public Date getBegin() {
		return begin;
	}

	public void setBegin(Date begin) {
		this.begin = begin;
	}

	public Date getEnd() {
		return end;
	}

	public void setEnd(Date end) {
		this.end = end;
	}

	public String getBlTimeBegin() {
		return blTimeBegin;
	}

	public void setBlTimeBegin(String blTimeBegin) {
		this.blTimeBegin = blTimeBegin;
	}

	public String getBlTimeEnd() {
		return blTimeEnd;
	}

	public void setBlTimeEnd(String blTimeEnd) {
		this.blTimeEnd = blTimeEnd;
	}

	public String getBlMatchedBegin() {
		return blMatchedBegin;
	}

	public void setBlMatchedBegin(String blMatchedBegin) {
		this.blMatchedBegin = blMatchedBegin;
	}

	public String getBlMatchedEnd() {
		return blMatchedEnd;
	}

	public void setBlMatchedEnd(String blMatchedEnd) {
		this.blMatchedEnd = blMatchedEnd;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	public String getBlInfoSour() {
		return blInfoSour;
	}

	public void setBlInfoSour(String blInfoSour) {
		this.blInfoSour = blInfoSour;
	}

	public String getBlType() {
		return blType;
	}

	public void setBlType(String blType) {
		this.blType = blType;
	}

	public String getBlUserId() {
		return blUserId;
	}

	public void setBlUserId(String blUserId) {
		this.blUserId = blUserId;
	}

}
