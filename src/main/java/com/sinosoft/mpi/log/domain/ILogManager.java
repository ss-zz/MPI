package com.sinosoft.mpi.log.domain;

import com.sinosoft.mpi.log.model.SysLog;

/**
 * 日志接口，功能，提供些日子的不同方法
 */
public interface ILogManager {

	/**
	 * @Description: 写日志
	 * @param SysLog
	 *            <br>
	 * 			必须赋值属性 <br>
	 * 			userID 用户ID <br>
	 * 			funcID 功能点ID <br>
	 * 			operateData 操作数据 <br>
	 * 			operateResult 操作结果 1成功 0失败 <br>
	 * 			operateDes 操作结果描述
	 * @throws Exception
	 * @see Syslog
	 */
	public void writeLog(SysLog log);
}
