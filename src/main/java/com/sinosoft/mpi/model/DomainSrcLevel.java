package com.sinosoft.mpi.model;

import java.io.Serializable;

public class DomainSrcLevel implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = -2050100598477414203L;

	private String ID			;			//主键					  
	private String DOMAIN_ID	;				//域主键        
	private String DOMAIN_DESC	;				//域描述        
	private String DOMAIN_LEVEL	;			//域数据源级别    
	private String FIELD_NAME	;			//字段名称        
	private String FIELD_DESC	;			//字段描述        
	private String FIELD_LEVEL	;				//字段级别      
	private String CREATE_DATE	;				//数据创建时间  
	private String UPDATE_DATE	;				//数据更新时间  
	public String getID() {
		return ID;
	}
	public void setID(String iD) {
		ID = iD;
	}
	public String getDOMAIN_ID() {
		return DOMAIN_ID;
	}
	public void setDOMAIN_ID(String dOMAIN_ID) {
		DOMAIN_ID = dOMAIN_ID;
	}
	public String getDOMAIN_DESC() {
		return DOMAIN_DESC;
	}
	public void setDOMAIN_DESC(String dOMAIN_DESC) {
		DOMAIN_DESC = dOMAIN_DESC;
	}
	public String getDOMAIN_LEVEL() {
		return DOMAIN_LEVEL;
	}
	public void setDOMAIN_LEVEL(String dOMAIN_LEVEL) {
		DOMAIN_LEVEL = dOMAIN_LEVEL;
	}
	public String getFIELD_NAME() {
		return FIELD_NAME;
	}
	public void setFIELD_NAME(String fIELD_NAME) {
		FIELD_NAME = fIELD_NAME;
	}
	public String getFIELD_DESC() {
		return FIELD_DESC;
	}
	public void setFIELD_DESC(String fIELD_DESC) {
		FIELD_DESC = fIELD_DESC;
	}
	public String getFIELD_LEVEL() {
		return FIELD_LEVEL;
	}
	public void setFIELD_LEVEL(String fIELD_LEVEL) {
		FIELD_LEVEL = fIELD_LEVEL;
	}
	public String getCREATE_DATE() {
		return CREATE_DATE;
	}
	public void setCREATE_DATE(String cREATE_DATE) {
		CREATE_DATE = cREATE_DATE;
	}
	public String getUPDATE_DATE() {
		return UPDATE_DATE;
	}
	public void setUPDATE_DATE(String uPDATE_DATE) {
		UPDATE_DATE = uPDATE_DATE;
	}


}
