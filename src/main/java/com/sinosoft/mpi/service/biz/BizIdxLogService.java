package com.sinosoft.mpi.service.biz;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import com.sinosoft.mpi.context.QueryConditionType;
import com.sinosoft.mpi.dao.biz.MpiBizIdxLogDao;
import com.sinosoft.mpi.model.biz.MpiBizIdxLog;
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
	 * 新增主索引操作日志
	 * 
	 * @param srcBizId
	 *            原始业务id
	 * @param bizId
	 *            新业务id
	 * @param domainId
	 *            域id
	 * @param opType
	 *            操作类型
	 * @param desc
	 *            操作描述
	 * @param weight
	 *            匹配度
	 */
	public void saveIndexLog(String srcBizId, String bizId, String domainId, String opType, String desc,
			Double weight) {
		MpiBizIdxLog result = new MpiBizIdxLog();
		result.setBlType(opType);
		result.setBlTime(new Date());
		result.setBlDesc(desc);
		result.setBlInfoSour(domainId);
		result.setBlBizId(bizId);
		result.setBlMatched(weight == null ? null : weight);
		// 自动标志
		save(result);
	}

	/**
	 * 分页查询
	 * 
	 * @param t
	 * @param page
	 * @return
	 */
	public List<Map<String, Object>> queryForMapPage(MpiBizIdxLog t, PageInfo page) {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		StringBuilder sql = new StringBuilder();
		sql.append(
				"select l.bl_idx_log_id as blIdxLogId,l.bl_time as blTime,l.bl_user_id as blUserId,l.bl_desc as blDesc,l.bl_matched as blMatched,l.bl_biz_id as blBizId,l.bl_serial_id as blSerialId,l.bl_info_sour as blInfoSour,l.bl_type as blType,u.name as userName,d.domain_desc as ser_Desc from MPI_BIZ_IDX_LOG l")
				.append(" left join mpi_sys_user u on u.user_id = l.bl_user_id")
				.append(" left join mpi_identifier_domain d on l.bl_serial_id = d.domain_id where 1=1 ");

		try {
			if (t.getBlTime_begin() != null && t.getBlTime_end() == null) {
				sql.append("and l.bl_time >= TO_DATE('" + sdf.format(sdf.parse(t.getBlTime_begin())).toString()
						+ "','yyyy-mm-dd') ");
			}
			if (t.getBlTime_end() != null && t.getBlTime_begin() == null) {
				sql.append("and l.bl_time <= TO_DATE('" + sdf.format(sdf.parse(t.getBlTime_end())).toString()
						+ "','yyyy-mm-dd') ");
			}
			if (t.getBlTime_begin() != null && t.getBlTime_end() != null) {
				sql.append("and l.bl_time >= TO_DATE('" + sdf.format(sdf.parse(t.getBlTime_begin())).toString()
						+ "','yyyy-mm-dd') and l.bl_time <= TO_DATE('"
						+ sdf.format(sdf.parse(t.getBlTime_end())).toString() + "','yyyy-mm-dd') ");
			}
		} catch (ParseException e) {
			// TODO Auto-generated catch block
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
	private void addconditions(final MpiBizIdxLog t, final StringBuilder sql, final List<Object> args) {
		/*
		 * SqlUtils.appendCondition(t.getBegin(), "l.bl_time", sql, args,
		 * QueryConditionType.GREATER_OR_EQUAL); SqlUtils.appendCondition(t.getEnd(),
		 * "l.bl_time", sql, args, QueryConditionType.LESS_OR_EQUAL);
		 */
		SqlUtils.appendCondition(t.getBlType(), "l.bl_type", sql, args, QueryConditionType.EQUAL);
		SqlUtils.appendCondition(t.getBlInfoSour(), "l.bl_info_sour", sql, args, QueryConditionType.EQUAL);
		SqlUtils.appendCondition(t.getBlUserId(), "l.bl_user_id", sql, args, QueryConditionType.EQUAL);
		SqlUtils.appendCondition(t.getBlMatched_begin(), "l.bl_matched", sql, args,
				QueryConditionType.GREATER_OR_EQUAL);
		SqlUtils.appendCondition(t.getBlMatched_end(), "l.bl_matched", sql, args, QueryConditionType.LESS_OR_EQUAL);
	}

	/**
	 * 分页查询
	 * 
	 * @param t
	 * @param page
	 * @return
	 */
	/*
	 * public Page<MpiBizIdxLog> queryForPage(final MpiBizIdxLog t, PageInfo page) {
	 * return bizIdxLogDao.findAll(new Specification<MpiBizIdxLog>() {
	 * 
	 * @Override public Predicate toPredicate(Root<MpiBizIdxLog> root,
	 * CriteriaQuery<?> query, CriteriaBuilder cb) { List<Predicate> predicates =
	 * new ArrayList<>(); SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
	 * try { if (t.getBlTime_begin() != null && t.getBlTime_begin() != "") {
	 * t.setBegin(sdf.parse(t.getBlTime_begin()));
	 * 
	 * } if( t.getBlTime_end() !=null && t.getBlTime_end() != ""){
	 * t.setEnd(sdf.parse(t.getBlTime_end())); } } catch (ParseException e) {
	 * e.printStackTrace(); } if (null != t.getBegin() && null != t.getEnd()) {
	 * predicates.add(cb.between(root.<Date>get("blTime"), t.getBegin(),
	 * t.getEnd())); } if(null == t.getBegin() && null != t.getEnd()){
	 * predicates.add(cb.lessThanOrEqualTo(root.<Date>get("blTime"), t.getEnd())); }
	 * if(null == t.getEnd() && null != t.getBegin()){
	 * predicates.add(cb.greaterThanOrEqualTo(root.<Date>get("blTime"),
	 * t.getBegin())); } if (null != t.getBlInfoSour()) {
	 * predicates.add(cb.equal(root.get("blInfoSour"), t.getBlInfoSour())); } if
	 * (null != t.getBlType()) { predicates.add(cb.equal(root.get("blType"),
	 * t.getBlType())); } if (null != t.getBlUserId()) {
	 * predicates.add(cb.equal(root.get("blUserId"), t.getBlUserId())); } if(null !=
	 * t.getBlMatched_begin() && t.getBlMatched_begin() != "" && null !=
	 * t.getBlMatched_end() && "" != t.getBlMatched_end()){
	 * predicates.add(cb.between(root.<Double>get("blMatched"),
	 * Double.parseDouble(t.getBlMatched_begin()),
	 * Double.parseDouble(t.getBlMatched_end()))); } if(null !=
	 * t.getBlMatched_begin() && t.getBlMatched_begin() != "" && null ==
	 * t.getBlMatched_end()){ predicates.add(cb.gt(root.<Double>get("blMatched"),
	 * Double.parseDouble(t.getBlMatched_begin()))); } if(null !=
	 * t.getBlMatched_end() && t.getBlMatched_begin() != "" &&
	 * t.getBlMatched_begin() == null){
	 * predicates.add(cb.lt(root.<Double>get("blMatched"),
	 * Double.parseDouble(t.getBlMatched_end()))); }
	 * 
	 * //Join<MpiBizIdxLog, SysUser> fuJoin =
	 * root.join(root.getModel().getSingularAttribute("sUser", SysUser.class),
	 * JoinType.LEFT); return cb.and(predicates.toArray(new
	 * Predicate[predicates.size()])); } }, page); }
	 */

}
