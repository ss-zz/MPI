package com.sinosoft.mpi.service;

import java.math.BigDecimal;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Vector;

import javax.annotation.Resource;
import javax.cache.annotation.CacheDefaults;

import org.apache.commons.beanutils.PropertyUtils;
import org.apache.log4j.Logger;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Service;

import com.sinosoft.mpi.context.Constant;
import com.sinosoft.mpi.context.QueryConditionType;
import com.sinosoft.mpi.dao.mpi.IndexIdentifierRelDao;
import com.sinosoft.mpi.dao.mpi.IndexOperateDao;
import com.sinosoft.mpi.dao.mpi.PersonIndexDao;
import com.sinosoft.mpi.dao.mpi.PersonInfoDao;
import com.sinosoft.mpi.exception.ValidationException;
import com.sinosoft.mpi.model.IndexIdentifierRel;
import com.sinosoft.mpi.model.IndexOperate;
import com.sinosoft.mpi.model.MpiCombineLevel;
import com.sinosoft.mpi.model.PersonIdxLog;
import com.sinosoft.mpi.model.PersonIndex;
import com.sinosoft.mpi.model.PersonInfo;
import com.sinosoft.mpi.util.DateUtil;
import com.sinosoft.mpi.util.PageInfo;
import com.sinosoft.mpi.util.SqlUtils;

/**
 * 主索引人员服务
 */
@Service
@CacheDefaults(cacheName = "peopleCache")
public class PersonIndexService {

	private Logger logger = Logger.getLogger(PersonIndexService.class);

	@Resource
	private PersonIndexDao personIndexDao;
	@Resource
	private IndexIdentifierRelDao indexIdentifierRelDao;
	@Resource
	private PersonInfoDao personInfoDao;
	@Resource
	private PersonIndexUpdateService personIndexUpdateService;
	@Resource
	private MpiCombineLevelService mpiCombineLevelService;
	@Resource
	private PersonIdxLogService personIdxLogService;
	@Resource
	private IndexOperateDao indexOperateDao;
	@Resource
	private JdbcTemplate jdbcTemplate;

	/**
	 * 构建查询条件
	 * 
	 * @param sql
	 * @param p
	 * @return
	 */
	private List<Object> buildQueryConditions(final StringBuilder sql, PersonIndex p) {
		List<Object> args = new ArrayList<Object>();
		SqlUtils.appendCondition(p.getGenderCd(), "a.gender_cd", sql, args, QueryConditionType.EQUAL);
		SqlUtils.appendCondition(p.getIdNoCd(), "a.id_no_cd", sql, args, QueryConditionType.EQUAL);
		SqlUtils.appendCondition(p.getIdNo(), "a.id_no", sql, args, QueryConditionType.EQUAL);
		SqlUtils.appendCondition(p.getMedicalInsuranceNo(), "a.medical_insurance_no", sql, args,
				QueryConditionType.EQUAL);
		SqlUtils.appendCondition(p.getNhCard(), "a.nh_card", sql, args, QueryConditionType.EQUAL);
		SqlUtils.appendCondition(p.getNameCn(), "a.name_CN", sql, args, QueryConditionType.LIKE);
		if (p.getBirthDate() != null) {
			sql.append(" and to_char(a.birth_date,'yyyy-mm-dd' ) =  '"
					+ new SimpleDateFormat("yyyy-MM-dd").format(p.getBirthDate()) + "'");
		}

		SqlUtils.appendCondition(p.getCardNationCd(), "a.card_nation_cd", sql, args, QueryConditionType.EQUAL);
		SqlUtils.appendCondition(p.getCardEdBgCd(), "a.card_ed_bg_cd", sql, args, QueryConditionType.EQUAL);
		SqlUtils.appendCondition(p.getCardMaritalStCd(), "a.card_marital_st_cd", sql, args, QueryConditionType.EQUAL);
		SqlUtils.appendCondition(p.getCardOccuTypeCd(), "a.card_occu_type_cd", sql, args, QueryConditionType.EQUAL);
		SqlUtils.appendCondition(p.getPersonTelNo(), "a.person_tel_no", sql, args, QueryConditionType.EQUAL);
		SqlUtils.appendCondition(p.getLivingAddr(), "a.living_addr", sql, args, QueryConditionType.LIKE);
		SqlUtils.appendCondition(p.getMpiPk(), "a.mpi_pk", sql, args, QueryConditionType.EQUAL);
		return args;
	}

	/**
	 * 删除
	 * 
	 * @param t
	 */
	public void delete(PersonIndex t) {
		personIndexDao.delete(t);
	}

	/**
	 * 根据id获取
	 * 
	 * @param id
	 * @return
	 */
	public PersonIndex getObject(String id) {
		return personIndexDao.findOne(id);
	}

	/**
	 * 分页查询
	 * 
	 * @param t
	 * @param page
	 * @return
	 */
	public List<PersonIndex> queryForPage(PersonIndex t, PageInfo page) {
		return personIndexDao.findAll(page).getContent();
	}

	/**
	 * 分页查询
	 * 
	 * @param index
	 * @param page
	 * @return
	 */
	// @CacheResult
	public List<Map<String, Object>> queryForSplitPage(PersonIndex index, PageInfo page) {

		StringBuilder sql = new StringBuilder();
		sql.append(
				"select * from (select a.MPI_PK,a.MPI_PK row_id,1 row_type,a.name_cn,getCodeValue('GBT226112003',a.gender_cd) gender_cd,a.gender_dn,a.birth_date,a.id_no,a.person_tel_no,nvl(b.person_count,0) person_count, ");
		sql.append(" decode(nvl(b.person_count,0),0,'open','closed') \"state\" ");
		sql.append(
				" ,(select distinct (g.mpi_pk) from mpi_index_operate g where g.op_style = 4 and g.mpi_pk = a.MPI_PK) mergeStatus");
		sql.append(" from mpi_person_index a left join ( select count(c.DOMAIN_ID) person_count,c.MPI_PK ");
		sql.append(
				" from mpi_index_identifier_rel c group by c.MPI_PK ) b on a.MPI_PK = b.MPI_PK where 1=1 and a.state != 1");

		// 添加查询条件
		List<Object> args = buildQueryConditions(sql, index);
		sql.append(" ) l ");
		if (index.getState() != null) {
			if (index.getState().toString().equals("0")) {
				sql.append(" where l.mergeStatus is null");
			}
			if (index.getState().toString().equals("1")) {
				sql.append(" where l.mergeStatus is not null");
			}
		}

		return querySplitData(page, sql, args);
	}

	/**
	 * 分页查询
	 * 
	 * @param index
	 * @param page
	 * @return
	 */
	public List<Map<String, Object>> queryForSplitPage(PersonIndex index, String fromIndexId, PageInfo page) {
		StringBuilder sql = new StringBuilder();
		sql.append(
				" select a.index_id,a.index_id row_id,1 row_type,a.name,a.sex,a.birth_date,a.id_no,a.phone_one,nvl(b.person_count,0) person_count, ");
		sql.append(" decode(nvl(b.person_count,0),0,'open','closed') \"state\" ");
		sql.append(" from mpi_person_index a left join ( select count(c.identifier_id) person_count,c.index_id ");
		sql.append(
				" from mpi_index_identifier_rel c group by c.index_id ) b on a.index_id = b.index_id where 1=1 and a.index_id != ? ");
		// 添加查询条件
		List<Object> args = buildQueryConditions(sql, index);
		args.add(0, fromIndexId);

		return querySplitData(page, sql, args);
	}

	/**
	 * 分页查询
	 * 
	 * @param page
	 * @param sql
	 * @param args
	 * @return
	 */
	private List<Map<String, Object>> querySplitData(PageInfo page, StringBuilder sql, List<Object> args) {
		// 取得总数查询sql
		String countSql = page.buildCountSql(sql);
		// 查询设置分页记录的总记录数
		page.setTotal(jdbcTemplate.queryForObject(countSql, args.toArray(), Integer.class));
		// 取得分页查询语句
		String querySql = page.buildPageSql(sql);
		return jdbcTemplate.queryForList(querySql, args.toArray());
	}

	/**
	 * 合并索引
	 * 
	 * @param retiredPk
	 * @param survivingPk
	 */
	@SuppressWarnings("unchecked")
	public void mergeIndex(String retiredPk, String survivingPk) {
		// 非空验证
		if (retiredPk.equals("") || survivingPk.equals("")) {
			throw new ValidationException("需要进行合并的主索引为空,无法继续进行合并");
		}
		@SuppressWarnings("rawtypes")
		Collection nuCon = new Vector();
		nuCon.add(null);
		// 原主索引对象
		PersonIndex retired = personIndexDao.findOne(retiredPk);
		// 目标主索引对象
		PersonIndex surviving = personIndexDao.findOne(survivingPk);

		retired.setState(new BigDecimal(1));
		personIndexDao.save(retired);
		// 被合并主索引人员集合
		List<PersonInfo> retiredList = new ArrayList<PersonInfo>();
		// 根据被合并主索引MPI_PK获取合并信息标示关系
		List<IndexIdentifierRel> retiredRels = indexIdentifierRelDao.findByMpiPk(retired.getMpiPk());

		if (retiredRels.size() > 0) {
			for (int i = 0; i < retiredRels.size(); i++) {
				retiredList.add(personInfoDao.findOne(retiredRels.get(i).getFieldPk()));
			}
			retiredList.removeAll(nuCon);
			// 将被合并主索引下的居民信息关联关系解除
			if (retiredList.size() > 0) {
				for (int i = 0; i < retiredList.size(); i++) {
					if (retiredList.get(i) != null) {
						// 原主索引人员信息
						PersonInfo personInfo = personInfoDao.findOne(retiredList.get(i).getFieldPk());

						// 原主索引居民信息原合并标识关系对象
						IndexIdentifierRel riir = indexIdentifierRelDao
								.findFirstByFieldPkOrderByCombineNoDesc(personInfo.getFieldPk());

						// 新增主索引操作关系
						savaIndexOperate(personInfo.getFieldPk(), retired.getMpiPk(), riir.getDomainId(),
								Constant.IDX_LOG_TYPE_MODIFY, Constant.IDX_LOG_STYLE_MAN_SPLIT,
								"主索引[" + retired.getNameCn() + "]进行拆分", null);

						// 1.合并相关主索引信息,更新主索引
						/* personIndexUpdateService.updateIndex(surviving, personInfo); */

						// 2.合并相关主索引信息,不更新主索引
						if (surviving.getMpiPk() != null) {
							// 获取索引字段的级别权限,与当前居民信息级别做比较,判别应替换字段信息，进行字段级别合并
							// 合并信息入库（第一次入库也记录一次合并信息）
							// 查询最新一次的合并记录
							IndexIdentifierRel iirlatest = indexIdentifierRelDao
									.findFirstByMpiPkOrderByCombineRecDesc(surviving.getMpiPk());
							logger.debug("合并的combine_no: " + iirlatest.getCombineNo());
							IndexIdentifierRel iir = new IndexIdentifierRel();
							iir.setCombineRec(iirlatest.getCombineNo());// 指代上一条的替换记录combine_rec
							iir.setDomainId(personInfo.getDomainId());
							iir.setFieldPk(personInfo.getFieldPk());
							iir.setDomainId(iirlatest.getDomainId());
							iir.setMpiPk(surviving.getMpiPk());
							if (personInfo.getType() == "0") {
								iir.setPersonIdentifier(personInfo.getHrId());
							} else if (personInfo.getType() == "1") {
								iir.setPersonIdentifier(personInfo.getMedicalserviceNo());
							} else if (personInfo.getType() == "3") {
								iir.setPersonIdentifier(personInfo.getPatientId());
							}
							indexIdentifierRelDao.save(iir);
						}
						// 添加新关联索引log
						personIdxLogService.save(personInfo.getFieldPk(), surviving.getMpiPk(), riir.getDomainId(),
								Constant.IDX_LOG_TYPE_MODIFY, Constant.IDX_LOG_STYLE_MAN_MERGE,
								"[" + personInfo.getNameCn() + "]合并到主索引[" + surviving.getNameCn() + "]",
								retired.getMpiPk());
						// 新增主索引操作关系
						savaIndexOperate(personInfo.getFieldPk(), surviving.getMpiPk(), riir.getDomainId(),
								Constant.IDX_LOG_TYPE_MODIFY, Constant.IDX_LOG_STYLE_MAN_MERGE,
								"[" + personInfo.getNameCn() + "]合并到主索引[" + surviving.getNameCn() + "]",
								retired.getMpiPk());

					}
				}
			}
		}
	}

	/**
	 * 新增主索引操作关系
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
	private void savaIndexOperate(String person, String index, String domainId, String opType, String opStyle,
			String desc, String formermpipk) {
		IndexOperate result = new IndexOperate();
		result.setOpType(opType);
		result.setOpStyle(opStyle);
		result.setOpTime(DateUtil.getTimeNow(new Date()));
		result.setOpUserId("0");
		result.setOpDesc(desc);
		result.setInfoSour(domainId);
		result.setMpiPk(index);
		result.setFieldPk(person);
		result.setFormerMpiPk(formermpipk);
		// 自动标志
		indexOperateDao.save(result);
	}

	/**
	 * 合并人员信息,优先输出目标人员信息字段,若目标人员信息字段为空从被合并人员信息字段获取
	 * 
	 * @param retired
	 *            被合并人员
	 * @param surviving
	 *            目标人员
	 * @return
	 */
	public PersonInfo mergeIdexPersonInfo(PersonInfo retired, PersonInfo surviving) {
		List<MpiCombineLevel> orgincollevellist = mpiCombineLevelService.findAll();
		Iterator<MpiCombineLevel> it = orgincollevellist.iterator();
		while (it.hasNext()) {
			MpiCombineLevel map = it.next();
			String combofield = map.getCombineField();
			try {
				Object survivingPeronInfo = PropertyUtils.getProperty(surviving, combofield);
				Object replaceval = PropertyUtils.getProperty(retired, combofield);
				if (survivingPeronInfo == null || "".equals(survivingPeronInfo)) {
					if (replaceval == null || "".equals(replaceval)) {
						PropertyUtils.setProperty(surviving, combofield, replaceval);
					}
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return surviving;
	}

	/**
	 * 保存
	 * 
	 * @param t
	 */
	public void save(PersonIndex t) {
		personIndexDao.save(t);
	}

	/**
	 * 根据拆分索引id查询列表
	 * 
	 * @param splitIndex
	 * @return
	 */
	public List<PersonIndex> findPersonIndexBysplitIndex(String splitIndex) {
		List<PersonIndex> personIndexs = new ArrayList<PersonIndex>();
		StringBuilder sb = new StringBuilder();
		sb.append(
				"select s.former_mpi_pk from (SELECT distinct former_mpi_pk , min(op_time)  b  FROM mpi_index_operate l where l.op_style = 4 and l.mpi_pk = '"
						+ splitIndex + "' group by former_mpi_pk order by b desc) s");
		RowMapper<Map<String, String>> mp = new RowMapper<Map<String, String>>() {
			@Override
			public Map<String, String> mapRow(ResultSet rs, int row) throws SQLException {
				Map<String, String> m = new HashMap<String, String>();
				m.put("former_mpi_pk", rs.getString("former_mpi_pk"));
				return m;
			}
		};
		List<Map<String, String>> list = jdbcTemplate.query(sb.toString(), mp);
		if (list.size() > 0) {
			for (int i = 0; i < list.size(); i++) {
				personIndexs.add(personIndexDao.findOne(list.get(i).get("former_mpi_pk")));
			}
		}
		return personIndexs;
	}

	/**
	 * * 拆分主索引信息 1.通过原主索引主键获取mpi_person_idx_log对象 2.根据mpi_person_idx_log删除原主索引现关联关系
	 * 3.根据mpi_person_idx_log重新关联到删除主索引上 4.重新合并原主索引 5.删除mpi_person_idx_log对应的关联记录
	 * 6.恢复拆分主索引为有效
	 * 
	 * @param retiredPk
	 *            拆分主索引
	 * @param formerPK
	 *            原主索引
	 */
	public void splitIndex(String retiredPk, String formerPK) {
		// 获取原主索引与被拆分主索引的合并关系
		StringBuilder sb = new StringBuilder();
		sb.append(" select * from mpi_index_operate i where");
		sb.append(" i.op_style = 4 and i.mpi_pk = '" + formerPK + "' and i.field_pk in(");
		sb.append(" select l.field_pk from mpi_index_operate l where l.op_style = 6");
		sb.append(" 								and l.mpi_pk = '" + retiredPk + "') order by i.op_time desc");
		List<IndexOperate> mergeLogs = jdbcTemplate.queryForList(sb.toString(), IndexOperate.class);
		// 原主索引
		PersonIndex formerIndex = personIndexDao.findOne(formerPK);

		// 拆分索引下居民
		if (mergeLogs.size() > 0) {
			for (int i = 0; i < mergeLogs.size(); i++) {
				IndexIdentifierRel riir = indexIdentifierRelDao.findFirstByFieldPk(mergeLogs.get(i).getFieldPk());
				// 添加解除索引log
				personIdxLogService.save(mergeLogs.get(i).getFieldPk(), formerPK, riir.getDomainId(),
						Constant.IDX_LOG_TYPE_MODIFY, Constant.IDX_LOG_STYLE_MAN_SPLIT,
						"主索引[" + formerIndex.getNameCn() + "]进行人工拆分", null);

				indexIdentifierRelDao.deleteByMpiPkAndFieldPk(formerPK, mergeLogs.get(i).getFieldPk());
			}
			// 删除原主索引合并记录
			for (int i = 0; i < mergeLogs.size(); i++) {
				indexOperateDao.delete(mergeLogs.get(i).getPersonIdxLogId());
			}
		}
		// 删除主索引相关的拆分记录
		StringBuilder splitStr = new StringBuilder();
		splitStr.append(" select * from mpi_index_operate i where");
		splitStr.append(" i.op_style = 6 and i.mpi_pk = '" + retiredPk + "' and i.field_pk in(");
		splitStr.append(" select l.field_pk from mpi_person_idx_log l where l.op_style = 4");
		splitStr.append(" 								and l.mpi_pk = '" + formerPK + "')");
		List<IndexOperate> splitLogs = jdbcTemplate.queryForList(splitStr.toString(), IndexOperate.class);
		if (splitLogs.size() > 0) {
			// 获取原主索引与被拆分主索引的拆分记录
			for (int i = 0; i < splitLogs.size(); i++) {
				indexOperateDao.delete(splitLogs.get(i).getPersonIdxLogId());
			}
		}
		// 将拆分的主索引记录恢复有效
		PersonIndex personIndex = personIndexDao.findOne(retiredPk);
		personIndex.setState(new BigDecimal(0));
		personIndexDao.save(personIndex);
	}

	/**
	 * 重新按拆分点后的主索引合并关系记录进行重新合并主索引信息
	 * 
	 * @param formerPK
	 *            主索引
	 * @param
	 */
	public void updateIndex(String formerPK, List<IndexOperate> mergeLogs) {
		// 获取拆分点以后的主索引操作日志
		StringBuilder sb = new StringBuilder();
		sb.append(" select * from mpi_person_idx_log l");
		sb.append(" 	where l.mpi_pk = '" + formerPK + "' ");
		sb.append(" 	and  l.op_style not in (3,6,7)");
		sb.append(" 	and  l.op_time > '" + mergeLogs.get(0).getOpTime() + "'");
		sb.append(" 	order by l.op_time desc");
		List<PersonIdxLog> logs = jdbcTemplate.queryForList(sb.toString(), PersonIdxLog.class);
		PersonInfo personInfo = new PersonInfo();
		if (logs.size() > 0) {
			// 拆分点后以最新的主索引操作日志信息更新主索引
			personInfo = personInfoDao.findOne(logs.get(0).getFieldPk());
			updateIndexDirect(personInfo, formerPK);
		} else {
			// 拆分点前以最新的主索引操作日志信息更新主索引
			String logId = "";
			for (int i = 0; i < mergeLogs.size(); i++) {
				logId += "'" + mergeLogs.get(i).getPersonIdxLogId() + "'" + ",";
			}
			if (!logId.equals("")) {
				logId = logId.substring(0, logId.length() - 1);
				StringBuilder merge_sql = new StringBuilder();
				merge_sql.append("	select * from mpi_person_idx_log l");
				merge_sql.append("	where l.op_style not in (4,6,7) and l.mpi_pk = '" + formerPK + "'	and");
				merge_sql.append("	l.person_idx_log_id not in (" + logId + ")");
				merge_sql.append("	order by l.op_time desc");
				List<PersonIdxLog> idxLogs = jdbcTemplate.queryForList(merge_sql.toString(), PersonIdxLog.class);
				if (idxLogs.size() > 0) {
					try {
						personInfo = personInfoDao.findOne(idxLogs.get(0).getFieldPk());
						updateIndexDirect(personInfo, formerPK);
					} catch (Exception e) {
						e.printStackTrace();
					}
				}
			}
		}
	}

	/**
	 * 直接更新 索引信息
	 */
	private void updateIndexDirect(PersonInfo person, String mpiPk) {
		PersonIndex idx = person.toPersonIndex();
		idx.setMpiPk(mpiPk);
		personIndexDao.save(idx);
	}

	/**
	 * 更新
	 * 
	 * @param t
	 */
	public void update(PersonIndex t) {
		personIndexDao.save(t);
	}

}
