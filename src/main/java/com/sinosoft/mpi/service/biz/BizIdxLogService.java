package com.sinosoft.mpi.service.biz;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import com.sinosoft.mpi.context.QueryConditionType;
import com.sinosoft.mpi.dao.biz.MpiBizIdxLogDao;
import com.sinosoft.mpi.model.biz.MpiBizIdxLog;
import com.sinosoft.mpi.model.biz.MpiBizIdxLogSearch;
import com.sinosoft.mpi.util.PageInfo;
import com.sinosoft.mpi.util.SqlUtils;

/**
 * 业务主索引日志服务
 */
@Service
public class BizIdxLogService {

	@Resource
	private MpiBizIdxLogDao bizIdxLogDao;

	@Resource
	JdbcTemplate jdbcTemplate;

	/**
	 * 删除
	 * 
	 * @param t
	 */
	public void delete(MpiBizIdxLog t) {
		bizIdxLogDao.delete(t);
	}

	public MpiBizIdxLog findLogById(String logId) {
		return bizIdxLogDao.findLogById(logId);
	}

	/**
	 * 删除
	 * 
	 * @param id
	 */
	public void deleteById(String id) {
		bizIdxLogDao.delete(id);
	}

	/**
	 * 根据id获取
	 * 
	 * @param id
	 * @return
	 */
	public MpiBizIdxLog getObject(String id) {
		return bizIdxLogDao.getOne(id);
	}

	/**
	 * 根据人员id查询操作日志列表
	 * 
	 * @param personId
	 * @return
	 */
	public List<MpiBizIdxLog> queryOpLogByPersonId(String personId) {
		return bizIdxLogDao.findByBlUserId(personId);
	}

	/**
	 * 保存
	 * 
	 * @param t
	 */
	public MpiBizIdxLog save(MpiBizIdxLog t) {
		return bizIdxLogDao.save(t);
	}

	/**
	 * 更新
	 * 
	 * @param t
	 */
	public MpiBizIdxLog update(MpiBizIdxLog t) {
		return bizIdxLogDao.save(t);
	}

	/**
	 * 分页查询
	 * 
	 * @param t
	 * @param page
	 * @return
	 */
	public List<Map<String, Object>> queryForMapPage(MpiBizIdxLogSearch t, PageInfo page) {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		StringBuilder sql = new StringBuilder();
		sql.append(
				"select l.bl_idx_log_id as blIdxLogId,l.bl_time as blTime,l.bl_user_id as blUserId,l.bl_desc as blDesc,l.bl_matched as blMatched,l.bl_biz_id as blBizId,l.bl_serial_id as blSerialId,l.bl_info_sour as blInfoSour,l.bl_type as blType,u.name as userName,d.domain_desc as ser_Desc from MPI_BIZ_IDX_LOG l")
				.append(" left join mpi_sys_user u on u.user_id = l.bl_user_id")
				.append(" left join mpi_identifier_domain d on l.bl_serial_id = d.domain_id where 1=1 ");

		try {
			if (t.getBlTimeBegin() != null && t.getBlTimeEnd() == null) {
				sql.append("and l.bl_time >= TO_DATE('" + sdf.format(sdf.parse(t.getBlTimeBegin())).toString()
						+ "','yyyy-mm-dd') ");
			}
			if (t.getBlTimeEnd() != null && t.getBlTimeBegin() == null) {
				sql.append("and l.bl_time <= TO_DATE('" + sdf.format(sdf.parse(t.getBlTimeEnd())).toString()
						+ "','yyyy-mm-dd') ");
			}
			if (t.getBlTimeBegin() != null && t.getBlTimeEnd() != null) {
				sql.append("and l.bl_time >= TO_DATE('" + sdf.format(sdf.parse(t.getBlTimeBegin())).toString()
						+ "','yyyy-mm-dd') and l.bl_time <= TO_DATE('"
						+ sdf.format(sdf.parse(t.getBlTimeEnd())).toString() + "','yyyy-mm-dd') ");
			}
		} catch (ParseException e) {
			e.printStackTrace();
		}
		List<Object> args = new ArrayList<Object>();
		// 添加查询条件
		addconditions(t, sql, args);

		// 取得总数查询sql
		String countSql = page.buildCountSql(sql);
		// 查询设置分页记录的总记录数
		page.setTotal(jdbcTemplate.queryForObject(countSql, args.toArray(), Integer.class));
		// 添加排序sql
		sql.append(" order by blTime desc ");
		// 取得分页查询语句
		String querySql = page.buildPageSql(sql);
		return jdbcTemplate.queryForList(querySql, args.toArray());
	}

	/**
	 * 增加条件
	 * 
	 * @param t
	 * @param sql
	 * @param args
	 * @throws ParseException
	 */
	private void addconditions(final MpiBizIdxLogSearch t, final StringBuilder sql, final List<Object> args) {
		SqlUtils.appendCondition(t.getBlType(), "l.bl_type", sql, args, QueryConditionType.EQUAL);
		SqlUtils.appendCondition(t.getBlInfoSour(), "l.bl_info_sour", sql, args, QueryConditionType.EQUAL);
		SqlUtils.appendCondition(t.getBlUserId(), "l.bl_user_id", sql, args, QueryConditionType.EQUAL);
		SqlUtils.appendCondition(t.getBlMatchedBegin(), "l.bl_matched", sql, args, QueryConditionType.GREATER_OR_EQUAL);
		SqlUtils.appendCondition(t.getBlMatchedEnd(), "l.bl_matched", sql, args, QueryConditionType.LESS_OR_EQUAL);
	}

}
