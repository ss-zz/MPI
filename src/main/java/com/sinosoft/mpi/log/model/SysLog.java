package com.sinosoft.mpi.log.model;

import java.io.Serializable;
import java.sql.Timestamp;

/**
* @Description: 日志对象 
* @Package com.sinosoft.dhow.core.log.model 
* @author Qin Shouxin  
* @version v1.0,2010-11-30
* @since	v1.0	
 */
public class SysLog implements Serializable {

	 private static final long serialVersionUID = -7661785971369945996L;
	
	 private String fdObjectid;//主键
	 private String userID           ;       //用户标识
	 private String dispName         ;       //显示名称
	 private Timestamp   operateTime      ;  //操作时间(可以为空)
	 private String appID            ;     //应用标识
	 private String appName          ;     //应用名称
	 private String funcID           ;     //功能标识
	 private String funcName         ;     //功能名称
	 private String userIp           ;     //用户IP
	 private String operateData      ;      //操作数据
	 private Integer operateResult    ;       //操作结果 1成功 0失败
	 private String operateDes       ;      //操作结果描述
	public String getUserID() {
		return userID;
	}
	public void setUserID(String userID) {
		this.userID = userID;
	}
	public String getDispName() {
		return dispName;
	}
	public void setDispName(String dispName) {
		this.dispName = dispName;
	}


	public Timestamp getOperateTime() {
		return operateTime;
	}
	public void setOperateTime(Timestamp operateTime) {
		this.operateTime = operateTime;
	}
	public String getAppID() {
		return appID;
	}
	public void setAppID(String appID) {
		this.appID = appID;
	}
	public String getAppName() {
		return appName;
	}
	public void setAppName(String appName) {
		this.appName = appName;
	}
	public String getFuncID() {
		return funcID;
	}
	public void setFuncID(String funcID) {
		this.funcID = funcID;
	}
	public String getFuncName() {
		return funcName;
	}
	public void setFuncName(String funcName) {
		this.funcName = funcName;
	}
	public String getUserIp() {
		return userIp;
	}
	public void setUserIp(String userIp) {
		this.userIp = userIp;
	}
	public String getOperateData() {
		return operateData;
	}
	public void setOperateData(String operateData) {
		this.operateData = operateData;
	}
	
	public Integer getOperateResult() {
		return operateResult;
	}
	public void setOperateResult(Integer operateResult) {
		this.operateResult = operateResult;
	}
	public String getOperateDes() {
		return operateDes;
	}
	public void setOperateDes(String operateDes) {
		this.operateDes = operateDes;
	}
	public String getFdObjectid() {
		return fdObjectid;
	}
	public void setFdObjectid(String fdObjectid) {
		this.fdObjectid = fdObjectid;
	}
}
