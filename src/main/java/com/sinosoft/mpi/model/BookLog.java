package com.sinosoft.mpi.model;

import java.io.Serializable;

/**
 * MPI_CERT_CODE 订阅日志
 */
public class BookLog implements Serializable {

	private static final long serialVersionUID = -7371689382932829413L;
	private String bookId; // 订阅日志主键
	private String eventType; // 事件类型 0-添加至索引 1-与索引解除关系
	private String mpipk; // 索引id
	private String fieldPK; // 居民id
	private String domainId; // 居民域id
	private String uniqueSign; // 居民域唯一标识
	private String personIdentifier; // 居民域下主键 暂不使用
	private String opTime; // 操作时间
	private String opCount; // 操作次数
	private String opResult; // 操作结果 0-未操作 1-操作成功 2-操作失败

	// =======================setter&getter
	public String getBookId() {
		return bookId;
	}

	public void setBookId(String bookId) {
		this.bookId = bookId;
	}

	public String getEventType() {
		return eventType;
	}

	public void setEventType(String eventType) {
		this.eventType = eventType;
	}

	public String getDomainId() {
		return domainId;
	}

	public void setDomainId(String domainId) {
		this.domainId = domainId;
	}

	public String getUniqueSign() {
		return uniqueSign;
	}

	public void setUniqueSign(String uniqueSign) {
		this.uniqueSign = uniqueSign;
	}

	public String getPersonIdentifier() {
		return personIdentifier;
	}

	public String getMpipk() {
		return mpipk;
	}

	public void setMpipk(String mpipk) {
		this.mpipk = mpipk;
	}

	public String getFieldPK() {
		return fieldPK;
	}

	public void setFieldPK(String fieldPK) {
		this.fieldPK = fieldPK;
	}

	public void setPersonIdentifier(String personIdentifier) {
		this.personIdentifier = personIdentifier;
	}

	public String getOpTime() {
		return opTime;
	}

	public void setOpTime(String opTime) {
		this.opTime = opTime;
	}

	public String getOpCount() {
		return opCount;
	}

	public void setOpCount(String opCount) {
		this.opCount = opCount;
	}

	public String getOpResult() {
		return opResult;
	}

	public void setOpResult(String opResult) {
		this.opResult = opResult;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((bookId == null) ? 0 : bookId.hashCode());
		result = prime * result + ((domainId == null) ? 0 : domainId.hashCode());
		result = prime * result + ((eventType == null) ? 0 : eventType.hashCode());
		result = prime * result + ((mpipk == null) ? 0 : mpipk.hashCode());
		result = prime * result + ((opCount == null) ? 0 : opCount.hashCode());
		result = prime * result + ((opResult == null) ? 0 : opResult.hashCode());
		result = prime * result + ((opTime == null) ? 0 : opTime.hashCode());
		result = prime * result + ((mpipk == null) ? 0 : mpipk.hashCode());
		result = prime * result + ((personIdentifier == null) ? 0 : personIdentifier.hashCode());
		result = prime * result + ((uniqueSign == null) ? 0 : uniqueSign.hashCode());
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
		BookLog other = (BookLog) obj;
		if (bookId == null) {
			if (other.bookId != null)
				return false;
		} else if (!bookId.equals(other.bookId))
			return false;
		if (domainId == null) {
			if (other.domainId != null)
				return false;
		} else if (!domainId.equals(other.domainId))
			return false;
		if (eventType == null) {
			if (other.eventType != null)
				return false;
		} else if (!eventType.equals(other.eventType))
			return false;
		if (mpipk == null) {
			if (other.mpipk != null)
				return false;
		} else if (!mpipk.equals(other.mpipk))
			return false;
		if (opCount == null) {
			if (other.opCount != null)
				return false;
		} else if (!opCount.equals(other.opCount))
			return false;
		if (opResult == null) {
			if (other.opResult != null)
				return false;
		} else if (!opResult.equals(other.opResult))
			return false;
		if (opTime == null) {
			if (other.opTime != null)
				return false;
		} else if (!opTime.equals(other.opTime))
			return false;
		if (mpipk == null) {
			if (other.mpipk != null)
				return false;
		} else if (!mpipk.equals(other.mpipk))
			return false;
		if (personIdentifier == null) {
			if (other.personIdentifier != null)
				return false;
		} else if (!personIdentifier.equals(other.personIdentifier))
			return false;
		if (uniqueSign == null) {
			if (other.uniqueSign != null)
				return false;
		} else if (!uniqueSign.equals(other.uniqueSign))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "BookLog [bookId=" + bookId + ", eventType=" + eventType + ", mpipk=" + mpipk + ", personId=" + mpipk
				+ ", domainId=" + domainId + ", uniqueSign=" + uniqueSign + ", personIdentifier=" + personIdentifier
				+ ", opTime=" + opTime + ", opCount=" + opCount + ", opResult=" + opResult + "]";
	}

}
