package com.sinosoft.mpi.model;

import java.io.Serializable;

/**
 * @author bysun 2012-3-12 民族编码 table:MPI_NATION_CODE schema:
 */
public class NationCode implements Serializable,IBaseCode {

	/**
	 * 
	 */
	private static final long serialVersionUID = 204192204016844507L;
	
	private String codeId;// 民族编码主键
	private String codeName;// 民族名称
	
	// ========================setter&&getter
	public String getCodeId() {
		return codeId;
	}
	public void setCodeId(String codeId) {
		this.codeId = codeId;
	}
	public String getCodeName() {
		return codeName;
	}
	public void setCodeName(String codeName) {
		this.codeName = codeName;
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((codeId == null) ? 0 : codeId.hashCode());
		result = prime * result + ((codeName == null) ? 0 : codeName.hashCode());
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
		NationCode other = (NationCode) obj;
		if (codeId == null) {
			if (other.codeId != null)
				return false;
		} else if (!codeId.equals(other.codeId))
			return false;
		if (codeName == null) {
			if (other.codeName != null)
				return false;
		} else if (!codeName.equals(other.codeName))
			return false;
		return true;
	}
	@Override
	public String toString() {
		return "NationCode [codeId=" + codeId + ", codeName=" + codeName + "]";
	}
}
