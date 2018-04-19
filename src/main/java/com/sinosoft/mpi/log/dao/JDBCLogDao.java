package com.sinosoft.mpi.log.dao;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;
import java.util.UUID;

import javax.annotation.Resource;

import org.apache.log4j.Logger;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementSetter;
import org.springframework.stereotype.Component;

import com.sinosoft.mpi.log.model.SysLog;

/**
 * 将日志持久化到数据库
 * 
 * @author zhaobs
 * 
 *         修改为使用JdbcTemplate bysun 2012-3-20
 */
@Component
public class JDBCLogDao implements ILogDao {

	private Logger logger = Logger.getLogger(JDBCLogDao.class);
	@Resource
	private JdbcTemplate jdbcTemplate;

	private String sql = "insert into MPI_SYS_LOG(FD_OBJECTID,USER_ID,DISP_NAME,OPERATE_TIME,APP_ID,APP_NAME,"
			+ "FUNC_ID,FUNC_NAME,USER_IP,OPERATE_DATA,OPERATE_RESULT,OPERATE_DES) values(?,?,?,?,?,?,?,?,?,?,?,?)";

	public boolean addLog(final SysLog log) {
		boolean result = true;
		try {
			jdbcTemplate.update(sql, new PreparedStatementSetter() {
				@Override
				public void setValues(PreparedStatement ps) throws SQLException {
					if (log.getFdObjectid() == null)
						log.setFdObjectid(UUID.randomUUID().toString());
					ps.setString(1, log.getFdObjectid());
					ps.setString(2, log.getUserID());
					ps.setString(3, log.getDispName());
					ps.setTimestamp(4, log.getOperateTime());
					ps.setString(5, log.getAppID());
					ps.setString(6, log.getAppName());
					ps.setString(7, log.getFuncID());
					ps.setString(8, log.getFuncName());
					ps.setString(9, log.getUserIp());
					String data = log.getOperateData();
					ps.setString(10, data.length() > 400 ? data.substring(0, 399) : data);
					ps.setInt(11, log.getOperateResult() == null ? 0 : log.getOperateResult());
					ps.setString(12, log.getOperateDes());
				}
			});
		} catch (DataAccessException e) {
			logger.error("addLog", e);
			result = false;
		}
		return result;
	}

	public boolean addLog(List<SysLog> logs) {
		boolean result = true;
		try {
			for (final SysLog log : logs) {
				jdbcTemplate.update(sql, new PreparedStatementSetter() {
					@Override
					public void setValues(PreparedStatement ps) throws SQLException {
						if (log.getFdObjectid() == null)
							log.setFdObjectid(UUID.randomUUID().toString());
						ps.setString(1, log.getFdObjectid());
						ps.setString(2, log.getUserID());
						ps.setString(3, log.getDispName());
						ps.setTimestamp(4, log.getOperateTime());
						ps.setString(5, log.getAppID());
						ps.setString(6, log.getAppName());
						ps.setString(7, log.getFuncID());
						ps.setString(8, log.getFuncName());
						ps.setString(9, log.getUserIp());
						String data = log.getOperateData();
						ps.setString(10, data.length() > 400 ? data.substring(0, 399) : data);
						ps.setInt(11, log.getOperateResult() == null ? 0 : log.getOperateResult());
						ps.setString(12, log.getOperateDes());
					}
				});
			}
		} catch (DataAccessException e) {
			logger.error("addLog", e);
			result = false;
		}
		return result;
	}

	public void setJdbcTemplate(JdbcTemplate jdbcTemplate) {
		this.jdbcTemplate = jdbcTemplate;
	}
}
