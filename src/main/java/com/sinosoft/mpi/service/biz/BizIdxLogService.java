package com.sinosoft.mpi.service.biz;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.sinosoft.mpi.context.QueryConditionType;
import com.sinosoft.mpi.dao.biz.BizIdxLogDao;
import com.sinosoft.mpi.dao.biz.BizIndexDao;
import com.sinosoft.mpi.model.biz.BizIdxLog;
import com.sinosoft.mpi.util.DateUtil;
import com.sinosoft.mpi.util.PageInfo;
import com.sinosoft.mpi.util.SqlUtils;

/**
 * 业务主索引日志服务
 */
@Service
public class BizIdxLogService {

	@Resource
	private BizIdxLogDao bizIdxLogDao;
	@Resource
	private BizIndexDao bizIndexDao;
	@Resource
	private BizIndexService bizIndexService;

	/**
	 * 增加条件
	 * 
	 * @param t
	 * @param sql
	 * @param args
	 */
	private void addconditions(final BizIdxLog t, final StringBuilder sql, final List<Object> args) {
		SqlUtils.appendCondition(t.getOpType(), "a.op_type", sql, args, QueryConditionType.EQUAL);
		SqlUtils.appendCondition(t.getOpStyle(), "a.op_style", sql, args, QueryConditionType.EQUAL);
		SqlUtils.appendCondition(t.getOpTime(), "a.op_time", sql, args, QueryConditionType.GREATER_OR_EQUAL);
		SqlUtils.appendCondition(t.getOpTimeEnd(), "a.op_time", sql, args, QueryConditionType.LESS_OR_EQUAL);
		SqlUtils.appendCondition(t.getOpUserId(), "d.name", sql, args, QueryConditionType.LIKE);
		SqlUtils.appendCondition(t.getInfoSour(), "a.info_sour", sql, args, QueryConditionType.EQUAL);
		SqlUtils.appendCondition(t.getPersonName(), "c.name_cn", sql, args, QueryConditionType.LIKE);
		SqlUtils.appendCondition(t.getPersonIdcard(), "c.id_no", sql, args, QueryConditionType.EQUAL);

	}

	/**
	 * 删除
	 * 
	 * @param t
	 */
	public void delete(BizIdxLog t) {
		bizIdxLogDao.deleteById(t);
	}

	/**
	 * 根据id获取
	 * 
	 * @param id
	 * @return
	 */
	public BizIdxLog getObject(String id) {
		BizIdxLog t = new BizIdxLog();
		t.setFieldpk(id);
		t = bizIdxLogDao.findById(t);
		return t;
	}

	/**
	 * 分页查询
	 * 
	 * @param t
	 * @param page
	 * @return
	 */
	public List<Map<String, Object>> queryForMapPage(BizIdxLog t, PageInfo page) {
		StringBuilder sql = new StringBuilder();
		sql.append(
				" select a.person_idx_log_id,a.op_time,a.op_type,a.op_style,a.op_desc,b.domain_desc,d.name,c.name_cn personname,c.id_no personIdcard from mpi_person_idx_log a  ")
				.append("left join  mpi_person_info c on a.field_pk = c.field_pk")
				.append(" left join mpi_identifier_domain b on a.info_sour = b.domain_id  ")
				.append(" left join mpi_sys_user d on a.op_user_id = d.user_id where 1=1 ");
		List<Object> args = new ArrayList<Object>();
		// 添加查询条件
		addconditions(t, sql, args);

		// 取得总数查询sql
		String countSql = page.buildCountSql(sql);
		// 查询设置分页记录的总记录数
		page.setTotal(bizIdxLogDao.getCount(countSql, args.toArray()));
		// 添加排序sql
		sql.append(" order by a.op_time desc ");
		// 取得分页查询语句
		String querySql = page.buildPageSql(sql);
		return bizIdxLogDao.findForMap(querySql, args.toArray());
	}

	/**
	 * 分页查询
	 * 
	 * @param t
	 * @param page
	 * @return
	 */
	public List<BizIdxLog> queryForPage(BizIdxLog t, PageInfo page) {
		String sql = " select * from mpi_person_idx_log where 1=1 ";
		String countSql = page.buildCountSql(sql);
		page.setTotal(bizIdxLogDao.getCount(countSql));
		sql = page.buildPageSql(sql);
		return bizIdxLogDao.find(sql, new Object[] {});
	}

	/**
	 * 根据人员id查询操作日志列表
	 * 
	 * @param personId
	 * @return
	 */
	public List<Map<String, Object>> queryOpLogByPersonId(String personId) {
		String sql = "select a.person_idx_log_id row_id,2 row_type,a.index_id,a.op_type optype,a.op_style,a.op_time domain_desc,b.name,b.gender_cd, "
				+ " b.birth_date,b.id_no,b.person_tel_no,'open' \"state\",b.nh_card,'' opcount "
				+ " from mpi_person_idx_log a left join mpi_person_index b on a.index_id = b.index_id where 1=1 and a.mpi_person_id = ? "
				+ " order by a.op_time desc ";
		return bizIdxLogDao.findForMap(sql, new Object[] { personId });
	}

	/**
	 * 保存
	 * 
	 * @param t
	 */
	public void save(BizIdxLog t) {
		bizIdxLogDao.add(t);
	}

	/**
	 * 更新
	 * 
	 * @param t
	 */
	public void update(BizIdxLog t) {
		bizIdxLogDao.update(t);
	}

	/**
	 * 新增主索引操作日志
	 * 
	 * @param person
	 *            居民id
	 * @param index
	 *            索引id
	 * @param domainId
	 *            域id
	 * @param opType
	 *            操作类型
	 * @param opStyle
	 *            操作方式
	 * @param desc
	 *            操作描述
	 */
	public void saveIndexLog(String person, String index, String domainId, String opType, String opStyle, String desc) {
		BizIdxLog result = new BizIdxLog();
		result.setOpType(opType);
		result.setOpStyle(opStyle);
		result.setOpTime(DateUtil.getTimeNow(new Date()));
		result.setOpUserId("0");
		result.setOpDesc(desc);
		result.setInfoSour(domainId);
		result.setMpipk(index);
		result.setFieldpk(person);
		// 自动标志
		save(result);
	}

}
