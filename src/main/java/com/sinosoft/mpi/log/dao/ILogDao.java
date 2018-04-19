package com.sinosoft.mpi.log.dao;

import java.util.List;

import com.sinosoft.mpi.log.model.SysLog;
/**
 * 
*    
* @Description: 日志存储
* 
* 
*
* 
* @Package com.sinosoft.mq.log 
* @author Qin,Shouxin  
* @version v1.0,2011-11-29
* @see	
* @since	v1.0
* 
*
*
*
 */
public interface ILogDao {
	/**
	 * 单条日志存储
	 * @param log
	 * @return
	 */
	 public boolean addLog( SysLog log);
	 /**
	  * 多条日志存储
	  * @param logs 
	  * @return
	  */
	 public  boolean addLog( List<SysLog> logs);
}
