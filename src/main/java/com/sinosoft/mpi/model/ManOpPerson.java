package com.sinosoft.mpi.model;

import java.io.Serializable;

/**
 * 需要人工干预的居民列表 table:MPI_MAN_OP_PERSON
 */
public class ManOpPerson implements Serializable {

	private static final long serialVersionUID = 7556697712906183059L;
	private String MAN_OP_ID;// 人工干预主键
	private String FIELD_PK;// 居民主键
	private String MAN_OP_STATUS; // 干预状态
	private String MAN_OP_TIME;// 生成时间
	private String MPI_PK;// 主索引id

	public String getMAN_OP_ID() {
		return MAN_OP_ID;
	}

	public void setMAN_OP_ID(String mAN_OP_ID) {
		MAN_OP_ID = mAN_OP_ID;
	}

	public String getFIELD_PK() {
		return FIELD_PK;
	}

	public void setFIELD_PK(String fIELD_PK) {
		FIELD_PK = fIELD_PK;
	}

	public String getMAN_OP_STATUS() {
		return MAN_OP_STATUS;
	}

	public void setMAN_OP_STATUS(String mAN_OP_STATUS) {
		MAN_OP_STATUS = mAN_OP_STATUS;
	}

	public String getMAN_OP_TIME() {
		return MAN_OP_TIME;
	}

	public void setMAN_OP_TIME(String mAN_OP_TIME) {
		MAN_OP_TIME = mAN_OP_TIME;
	}

	public String getMPI_PK() {
		return MPI_PK;
	}

	public void setMPI_PK(String mPI_PK) {
		MPI_PK = mPI_PK;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((MAN_OP_ID == null) ? 0 : MAN_OP_ID.hashCode());
		result = prime * result + ((MAN_OP_STATUS == null) ? 0 : MAN_OP_STATUS.hashCode());
		result = prime * result + ((MAN_OP_TIME == null) ? 0 : MAN_OP_TIME.hashCode());
		result = prime * result + ((FIELD_PK == null) ? 0 : FIELD_PK.hashCode());
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
		ManOpPerson other = (ManOpPerson) obj;
		if (MAN_OP_ID == null) {
			if (other.MAN_OP_ID != null)
				return false;
		} else if (!MAN_OP_ID.equals(other.MAN_OP_ID))
			return false;
		if (MAN_OP_STATUS == null) {
			if (other.MAN_OP_STATUS != null)
				return false;
		} else if (!MAN_OP_STATUS.equals(other.MAN_OP_STATUS))
			return false;
		if (MAN_OP_TIME == null) {
			if (other.MAN_OP_TIME != null)
				return false;
		} else if (!MAN_OP_TIME.equals(other.MAN_OP_TIME))
			return false;
		if (FIELD_PK == null) {
			if (other.FIELD_PK != null)
				return false;
		} else if (!FIELD_PK.equals(other.FIELD_PK))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "ManOpPerson [MAN_OP_ID=" + MAN_OP_ID + ", personId=" + FIELD_PK + ", MAN_OP_STATUS=" + MAN_OP_STATUS
				+ ", MAN_OP_TIME=" + MAN_OP_TIME + "]";
	}
}
