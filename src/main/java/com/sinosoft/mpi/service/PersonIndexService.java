package com.sinosoft.mpi.service;

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
import javax.cache.annotation.CacheResult;

import org.apache.commons.beanutils.PropertyUtils;
import org.apache.log4j.Logger;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Service;

import com.sinosoft.mpi.context.Constant;
import com.sinosoft.mpi.context.QueryConditionType;
import com.sinosoft.mpi.dao.BookLogDao;
import com.sinosoft.mpi.dao.IndexIdentifierRelDao;
import com.sinosoft.mpi.dao.IndexOperateDao;
import com.sinosoft.mpi.dao.MpiCombineLevelDao;
import com.sinosoft.mpi.dao.PersonIdxLogDao;
import com.sinosoft.mpi.dao.PersonIndexDao;
import com.sinosoft.mpi.dao.PersonInfoDao;
import com.sinosoft.mpi.exception.ValidationException;
import com.sinosoft.mpi.model.IndexIdentifierRel;
import com.sinosoft.mpi.model.IndexOperate;
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
	private BookLogService bookLogService;
	@Resource
	private PersonIndexUpdateService personIndexUpdateService;
	@Resource
	private MpiCombineLevelDao mpiCombineLevelDao;
	@Resource
	private BookLogDao bookLogDao;
	@Resource
	private PersonIdxLogDao personIdxLogDao;
	@Resource
	private JdbcTemplate jdbcTemplate;
	@Resource
	private IndexOperateDao indexOperateDao;

	private List<Object> buildQueryConditions(final StringBuilder sql, PersonIndex p) {
		List<Object> args = new ArrayList<Object>();
		SqlUtils.appendCondition(p.getGENDER_CD(), "a.gender_cd", sql, args, QueryConditionType.EQUAL);
		SqlUtils.appendCondition(p.getID_NO_CD(), "a.id_no_cd", sql, args, QueryConditionType.EQUAL);
		SqlUtils.appendCondition(p.getID_NO(), "a.id_no", sql, args, QueryConditionType.EQUAL);
		SqlUtils.appendCondition(p.getMEDICAL_INSURANCE_NO(), "a.medical_insurance_no", sql, args,
				QueryConditionType.EQUAL);
		SqlUtils.appendCondition(p.getNH_CARD(), "a.nh_card", sql, args, QueryConditionType.EQUAL);
		SqlUtils.appendCondition(p.getNAME_CN(), "a.name_CN", sql, args, QueryConditionType.LIKE);
		if (p.getBIRTH_DATE() != null) {
			sql.append(" and to_char(a.birth_date,'yyyy-mm-dd' ) =  '"
					+ new SimpleDateFormat("yyyy-MM-dd").format(p.getBIRTH_DATE()) + "'");
		}

		SqlUtils.appendCondition(p.getCARD_NATION_CD(), "a.card_nation_cd", sql, args, QueryConditionType.EQUAL);
		SqlUtils.appendCondition(p.getCARD_ED_BG_CD(), "a.card_ed_bg_cd", sql, args, QueryConditionType.EQUAL);
		SqlUtils.appendCondition(p.getCARD_MARITAL_ST_CD(), "a.card_marital_st_cd", sql, args,
				QueryConditionType.EQUAL);
		SqlUtils.appendCondition(p.getCARD_OCCU_TYPE_CD(), "a.card_occu_type_cd", sql, args, QueryConditionType.EQUAL);
		SqlUtils.appendCondition(p.getPERSON_TEL_NO(), "a.person_tel_no", sql, args, QueryConditionType.EQUAL);
		SqlUtils.appendCondition(p.getLIVING_ADDR(), "a.living_addr", sql, args, QueryConditionType.LIKE);
		SqlUtils.appendCondition(p.getMPI_PK(), "a.mpi_pk", sql, args, QueryConditionType.EQUAL);
		return args;
	}

	public void delete(PersonIndex t) {
		personIndexDao.deleteById(t);
		logger.debug("Del PersonIndex:indexId=" + t.getMPI_PK());
	}

	public PersonIndex getObject(String id) {
		PersonIndex t = new PersonIndex();
		t.setMPI_PK(id);
		t = personIndexDao.findById(t);
		logger.debug("Load PersonIndex:indexId=" + id + ",result=" + t);
		return t;
	}

	public List<PersonIndex> queryForPage(PersonIndex t, PageInfo page) {
		String sql = " select * from mpi_person_index where 1=1 ";
		sql = page.buildPageSql(sql);
		logger.debug("Execute sql:[" + sql + "],params[]");
		return personIndexDao.find(sql, new Object[] {});
	}

	@CacheResult
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
		if (index.getSTATE() != null) {
			if (index.getSTATE().toString().equals("0")) {
				sql.append(" where l.mergeStatus is null");
			}
			if (index.getSTATE().toString().equals("1")) {
				sql.append(" where l.mergeStatus is not null");
			}
		}

		return querySplitData(page, sql, args);
	}

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

	private List<Map<String, Object>> querySplitData(PageInfo page, StringBuilder sql, List<Object> args) {
		// 取得总数查询sql
		String countSql = page.buildCountSql(sql);
		// 查询设置分页记录的总记录数
		page.setTotal(personIndexDao.getCount(countSql, args.toArray()));
		logger.debug("Execute sql:" + countSql);
		// 取得分页查询语句
		String querySql = page.buildPageSql(sql);
		logger.debug("Execute sql:" + querySql);
		return personIndexDao.findForMap(querySql, args.toArray());
	}

	@SuppressWarnings("unchecked")
	public void mergeIndex(String retiredPk, String survivingPk) {
		// 非空验证
		if (retiredPk.equals("") || survivingPk.equals("")) {
			logger.debug("需要进行合并的主索引为空,无法继续进行合并");
			throw new ValidationException("需要进行合并的主索引为空,无法继续进行合并");
		}
		@SuppressWarnings("rawtypes")
		Collection nuCon = new Vector();
		nuCon.add(null);
		// 原主索引对象
		PersonIndex retired = new PersonIndex();
		retired.setMPI_PK(retiredPk);
		retired = personIndexDao.findById(retired);
		// 目标主索引对象
		PersonIndex surviving = new PersonIndex();
		surviving.setMPI_PK(survivingPk);
		surviving = personIndexDao.findById(surviving);

		retired.setSTATE((short) 1);
		personIndexDao.update(retired);
		// 被合并主索引人员集合
		List<PersonInfo> retiredList = new ArrayList<PersonInfo>();
		// 根据被合并主索引MPI_PK获取合并信息标示关系
		List<IndexIdentifierRel> retiredRels = indexIdentifierRelDao.findByMpiPK(retired.getMPI_PK());

		if (retiredRels.size() > 0) {
			for (int i = 0; i < retiredRels.size(); i++) {
				retiredList.add(personInfoDao.findByPK(retiredRels.get(i).getFIELD_PK()));
			}
			retiredList.removeAll(nuCon);
			// 将被合并主索引下的居民信息关联关系解除
			if (retiredList.size() > 0) {
				for (int i = 0; i < retiredList.size(); i++) {
					if (retiredList.get(i) != null) {
						// 原主索引人员信息
						PersonInfo personInfo = personInfoDao.findByPK(retiredList.get(i).getFIELD_PK());

						// 原主索引居民信息原合并标识关系对象
						IndexIdentifierRel riir = indexIdentifierRelDao.findByFieldPK(personInfo.getFIELD_PK());

						// 记录订阅日志
						bookLogDao.autoFillAdd(personInfo.getFIELD_PK());
						// 将 被合并主索引下居民信息 解除索引关系
						/* indexIdentifierRelDao.deleteByFieldPK(personInfo.getFIELD_PK()); */
						// 添加解除索引log
						/*
						 * saveIndexLog(personInfo.getFIELD_PK(), retired.getMPI_PK(),
						 * riir.getDOMAIN_ID(), Constant.IDX_LOG_TYPE_MODIFY,
						 * Constant.IDX_LOG_STYLE_MAN_REMOVE, "主索引[" + retired.getNAME_CN() +
						 * "]解除绑定",null);
						 */

						// 新增主索引操作关系
						savaIndexOperate(personInfo.getFIELD_PK(), retired.getMPI_PK(), riir.getDOMAIN_ID(),
								Constant.IDX_LOG_TYPE_MODIFY, Constant.IDX_LOG_STYLE_MAN_SPLIT,
								"主索引[" + retired.getNAME_CN() + "]进行拆分", null);

						// 1.合并相关主索引信息,更新主索引
						/* personIndexUpdateService.updateIndex(surviving, personInfo); */

						// 2.合并相关主索引信息,不更新主索引
						if (surviving.getMPI_PK() != null) {
							// 获取索引字段的级别权限,与当前居民信息级别做比较,判别应替换字段信息，进行字段级别合并
							// 合并信息入库（第一次入库也记录一次合并信息）
							// 查询最新一次的合并记录
							IndexIdentifierRel iirlatest = indexIdentifierRelDao
									.findByMpiPKByLatest(surviving.getMPI_PK());
							logger.debug("合并的combine_no: " + iirlatest.getCOMBINE_NO());
							IndexIdentifierRel iir = new IndexIdentifierRel();
							iir.setCOMBINE_REC(iirlatest.getCOMBINE_NO());// 指代上一条的替换记录combine_rec
							iir.setDOMAIN_ID(personInfo.getDOMAIN_ID());
							iir.setFIELD_PK(personInfo.getFIELD_PK());
							iir.setDOMAIN_ID(iirlatest.getDOMAIN_ID());
							iir.setMPI_PK(surviving.getMPI_PK());
							if (personInfo.getTYPE() == "0") {
								iir.setPERSON_IDENTIFIER(personInfo.getHR_ID());
							} else if (personInfo.getTYPE() == "1") {
								iir.setPERSON_IDENTIFIER(personInfo.getMEDICALSERVICE_NO());
							} else if (personInfo.getTYPE() == "3") {
								iir.setPERSON_IDENTIFIER(personInfo.getPATIENT_ID());
							}

							indexIdentifierRelDao.add(iir);
						}
						// 添加新关联索引log
						saveIndexLog(personInfo.getFIELD_PK(), surviving.getMPI_PK(), riir.getDOMAIN_ID(),
								Constant.IDX_LOG_TYPE_MODIFY, Constant.IDX_LOG_STYLE_MAN_MERGE,
								"[" + personInfo.getNAME_CN() + "]合并到主索引[" + surviving.getNAME_CN() + "]",
								retired.getMPI_PK());
						// 新增主索引操作关系
						savaIndexOperate(personInfo.getFIELD_PK(), surviving.getMPI_PK(), riir.getDOMAIN_ID(),
								Constant.IDX_LOG_TYPE_MODIFY, Constant.IDX_LOG_STYLE_MAN_MERGE,
								"[" + personInfo.getNAME_CN() + "]合并到主索引[" + surviving.getNAME_CN() + "]",
								retired.getMPI_PK());

						bookLogDao.autoFillAdd(personInfo.getFIELD_PK(), surviving.getMPI_PK(),
								Constant.BOOK_LOG_TYPE_ADD);
					}
				}
			}
		}
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
	private void saveIndexLog(String person, String index, String domainId, String opType, String opStyle, String desc,
			String formermpipk) {
		PersonIdxLog result = new PersonIdxLog();
		result.setOpType(opType);
		result.setOpStyle(opStyle);
		result.setOpTime(DateUtil.getTimeNow(new Date()));
		result.setOpUserId("0");
		result.setOpDesc(desc);
		result.setInfoSour(domainId);
		result.setMpipk(index);
		result.setFieldpk(person);
		result.setFormermpipk(formermpipk);
		// 自动标志
		personIdxLogDao.add(result);
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
		result.setMpipk(index);
		result.setFieldpk(person);
		result.setFormermpipk(formermpipk);
		// 自动标志
		indexOperateDao.add(result);
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
		List<Map<String, Object>> orgincollevellist = mpiCombineLevelDao.findForMap("select * from MPI_COMBINE_LEVEL");
		Iterator<Map<String, Object>> it = orgincollevellist.iterator();
		while (it.hasNext()) {
			Map<String, Object> map = it.next();
			String combofield = (String) map.get("COMBINE_FIELD");
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

	public void save(PersonIndex t) {
		personIndexDao.add(t);
	}

	public List<PersonIndex> findPersonIndexBysplitIndex(String splitIndex) {
		List<PersonIndex> personIndexs = new ArrayList<PersonIndex>();
		PersonIndex personIndex = new PersonIndex();
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
				personIndex.setMPI_PK(list.get(i).get("former_mpi_pk"));
				personIndexs.add(personIndexDao.findById(personIndex));
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
		List<IndexOperate> mergeLogs = indexOperateDao.find(sb.toString());
		IndexOperate Operate = new IndexOperate();
		// 原主索引
		PersonIndex formerIndex = new PersonIndex();
		formerIndex.setMPI_PK(formerPK);
		formerIndex = personIndexDao.findById(formerIndex);

		// 拆分主索引
		PersonIndex retiredIndex = new PersonIndex();
		retiredIndex.setMPI_PK(retiredPk);
		retiredIndex = personIndexDao.findById(retiredIndex);

		// 拆分索引下居民
		PersonInfo personInfo = new PersonInfo();
		if (mergeLogs.size() > 0) {
			for (int i = 0; i < mergeLogs.size(); i++) {
				personInfo.setFIELD_PK(mergeLogs.get(i).getFieldpk());
				personInfo = personInfoDao.findById(personInfo);

				IndexIdentifierRel riir = indexIdentifierRelDao.findByFieldPK(mergeLogs.get(i).getFieldpk());
				// 添加解除索引log
				saveIndexLog(mergeLogs.get(i).getFieldpk(), formerPK, riir.getDOMAIN_ID(), Constant.IDX_LOG_TYPE_MODIFY,
						Constant.IDX_LOG_STYLE_MAN_SPLIT, "主索引[" + formerIndex.getNAME_CN() + "]进行人工拆分", null);

				indexIdentifierRelDao.deleteByMpiPKFieldPk(formerPK, mergeLogs.get(i).getFieldpk());

				// 添加新关联索引log
				/*
				 * saveIndexLog(mergeLogs.get(i).getFieldpk(), retiredPk, riir.getDOMAIN_ID(),
				 * Constant.IDX_LOG_TYPE_MODIFY, Constant.IDX_LOG_STYLE_MAN_MERGE, "[" +
				 * personInfo.getNAME_CN() + "]合并到主索引[" + retiredIndex.getNAME_CN() +
				 * "]",formerIndex.getMPI_PK());
				 */
			}
			// 删除原主索引合并记录
			for (int i = 0; i < mergeLogs.size(); i++) {
				Operate.setPersonIdxLogId(mergeLogs.get(i).getPersonIdxLogId());
				indexOperateDao.deleteById(Operate);
			}
		}
		/*
		 * //重新按拆分点后的主索引合并关系记录进行重新合并主索引信息 try { updateIndex(formerPK,mergeLogs); } catch
		 * (Exception e) { e.printStackTrace(); }
		 */
		// 删除主索引相关的拆分记录
		StringBuilder splitStr = new StringBuilder();
		splitStr.append(" select * from mpi_index_operate i where");
		splitStr.append(" i.op_style = 6 and i.mpi_pk = '" + retiredPk + "' and i.field_pk in(");
		splitStr.append(" select l.field_pk from mpi_person_idx_log l where l.op_style = 4");
		splitStr.append(" 								and l.mpi_pk = '" + formerPK + "')");
		List<IndexOperate> splitLogs = indexOperateDao.find(splitStr.toString());
		if (splitLogs.size() > 0) {
			// 获取原主索引与被拆分主索引的拆分记录
			for (int i = 0; i < splitLogs.size(); i++) {
				Operate.setPersonIdxLogId(splitLogs.get(i).getPersonIdxLogId());
				indexOperateDao.deleteById(Operate);
			}
		}
		// 将拆分的主索引记录恢复有效
		PersonIndex personIndex = new PersonIndex();
		personIndex.setMPI_PK(retiredPk);
		personIndex = personIndexDao.findById(personIndex);
		personIndex.setSTATE((short) 0);
		personIndexDao.update(personIndex);
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
		List<PersonIdxLog> logs = personIdxLogDao.find(sb.toString());
		PersonInfo personInfo = new PersonInfo();
		if (logs.size() > 0) {
			// 拆分点后以最新的主索引操作日志信息更新主索引
			try {
				personInfo = personInfoDao.findByPK(logs.get(0).getFieldpk());
				updateIndexDirect(personInfo, formerPK);
			} catch (Exception e) {
				e.printStackTrace();
			}
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
				List<PersonIdxLog> idxLogs = personIdxLogDao.find(merge_sql.toString());
				if (idxLogs.size() > 0) {
					try {
						personInfo.setFIELD_PK(idxLogs.get(0).getFieldpk());
						personInfo = personInfoDao.findById(personInfo);
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
	private void updateIndexDirect(PersonInfo person, String mpi_pk) {
		PersonIndex idx = person.personInfoToPersonIndex();
		idx.setMPI_PK(mpi_pk);
		personIndexDao.update(idx);
	}

	public void update(PersonIndex t) {
		personIndexDao.update(t);
	}

}
