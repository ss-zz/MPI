package com.sinosoft.mpi.service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import com.sinosoft.mpi.context.Constant;
import com.sinosoft.mpi.context.QueryConditionType;
import com.sinosoft.mpi.dao.mpi.IndexIdentifierRelDao;
import com.sinosoft.mpi.dao.mpi.ManOpPersonDao;
import com.sinosoft.mpi.dao.mpi.MatchResultDao;
import com.sinosoft.mpi.dao.mpi.PersonIdentifierDao;
import com.sinosoft.mpi.dao.mpi.PersonIdxLogDao;
import com.sinosoft.mpi.dao.mpi.PersonIndexDao;
import com.sinosoft.mpi.dao.mpi.PersonInfoDao;
import com.sinosoft.mpi.dao.mpi.PersonInfoHistoryDao;
import com.sinosoft.mpi.exception.BaseBussinessException;
import com.sinosoft.mpi.exception.ValidationException;
import com.sinosoft.mpi.model.IdentifierDomain;
import com.sinosoft.mpi.model.IndexIdentifierRel;
import com.sinosoft.mpi.model.ManOpPerson;
import com.sinosoft.mpi.model.MatchResult;
import com.sinosoft.mpi.model.PersonIdentifier;
import com.sinosoft.mpi.model.PersonIdxLog;
import com.sinosoft.mpi.model.PersonIndex;
import com.sinosoft.mpi.model.PersonInfo;
import com.sinosoft.mpi.model.PersonInfoSimple;
import com.sinosoft.mpi.util.CodeConvertUtils;
import com.sinosoft.mpi.util.DateUtil;
import com.sinosoft.mpi.util.IDUtil;
import com.sinosoft.mpi.util.NumberUtils;
import com.sinosoft.mpi.util.PageInfo;
import com.sinosoft.mpi.util.SqlUtils;

/**
 * 人员服务
 */
@Service
public class PersonInfoService {

	static final String PERSONTABLE = "mpi_person_info";
	static final String PERSONTABLEHISTORY = "mpi_personinfo_history";

	private Logger logger = Logger.getLogger(PersonInfoService.class);

	@Resource
	private IdentifierDomainService identifierDomainService;
	@Resource
	private IndexIdentifierRelDao indexIdentifierRelDao;
	@Resource
	private ManOpPersonDao manOpPersonDao;
	@Resource
	private MatchResultDao matchResultDao;
	@Resource
	private PersonIdentifierDao personIdentifierDao;
	@Resource
	private PersonIdxLogDao personIdxLogDao;
	@Resource
	private PersonIndexDao personIndexDao;
	@Resource
	private PersonIndexUpdateService personIndexUpdateService;
	@Resource
	private PersonInfoDao personInfoDao;
	@Resource
	private PersonInfoHistoryDao personInfoHistoryService;
	@Resource
	private PersonInfoVerifier personInfoVerifier;
	@Resource
	private JdbcTemplate jdbcTemplate;

	/**
	 * 人工干预生成新索引
	 * 
	 * @param opId
	 *            操作id
	 * @param personId
	 *            居民id
	 */
	public void addNewIndex(String opId, String personId) {
		// 取得人员
		PersonInfo person = getObject(personId);
		// 取得人员对应域id信息
		PersonIdentifier pi = getPersonIdentifierByPersonId(personId);
		// 验证数据
		if (person == null || pi == null) {
			throw new ValidationException("无法取得相关信息:PersonInfo=" + person + ",\n PersonIdentifier=" + pi);
		}
		indexIdentifierRelDao.deleteByFieldPk(pi.getIdentifierId());

		// 新生成索引
		PersonIndex newIndex = person.toPersonIndex();
		// 设置索引数据源级别
		IdentifierDomain domain = identifierDomainService.getByPersonId(personId);
		newIndex.setDomainLevel(domain.getDomainLevel());
		// 保存新索引
		personIndexDao.save(newIndex);
		// 保存居民索引关系
		saveIndexIdentifierRel(pi.getIdentifierId(), newIndex.getMpiPk());

		// 新建索引日志
		saveIndexLog(person.getFieldPk(), newIndex.getMpiPk(), "", Constant.IDX_LOG_TYPE_MODIFY,
				Constant.IDX_LOG_STYLE_MAN_NEW, "新建主索引[" + newIndex.getNameCn() + "]");

		// 更新人工干预信息为已操作
		modifyManOpPerson(opId);
	}

	/**
	 * 批量添加 居民信息
	 * 
	 * @param persons
	 */
	public void addPersonBatch(PersonInfo[] persons) {
		for (PersonInfo p : persons) {
			save(p);
		}
	}

	/**
	 * 手工添加居民信息到已存在索引
	 * 
	 * @param opId
	 *            人工干预主键
	 * @param personId
	 *            居民主键
	 * @param indexId
	 *            索引主键
	 */
	public void addToIndex(String opId, String personId, String indexId) {
		// 取得人员
		PersonInfo person = getObject(personId);
		// 取得人员对应域id信息
		PersonIdentifier pi = getPersonIdentifierByPersonId(personId);
		// 取得索引
		PersonIndex index = getPersonIndex(indexId);
		// 验证数据
		if (person == null || pi == null || index == null) {
			throw new ValidationException(
					"无法取得相关信息:PersonInfo=" + person + ",\n PersonIdentifier=" + pi + ",\n PersonIndex=" + index);
		}
		indexIdentifierRelDao.deleteByFieldPk(pi.getIdentifierId());

		// 保存人员至索引
		saveIndexIdentifierRel(pi.getIdentifierId(), index.getMpiPk());
		// 取得匹配记录
		MatchResult match = matchResultDao.findFirstByMpiPkAndFieldPk(index.getMpiPk(), person.getFieldPk());
		// 新建索引日志 标识为2 手工合并
		saveIndexLog(person.getFieldPk(), index.getMpiPk(), "", Constant.IDX_LOG_TYPE_MATCH,
				Constant.IDX_LOG_STYLE_MAN_MERGE, "[" + person.getNameCn() + "]合并到主索引[" + index.getNameCn() + "],系统匹配度:"
						+ NumberUtils.toPercentStr(Double.parseDouble(match.getMatchDegree())));
		personIndexUpdateService.updateIndex(person, index.getMpiPk());
		// 更新人工干预信息为已操作
		modifyManOpPerson(opId);
	}

	/**
	 * 构建查询条件
	 * 
	 * @param sql
	 * @param p
	 * @return
	 */
	private List<Object> buildQueryConditions(final StringBuilder sql, PersonInfo p) {
		List<Object> args = new ArrayList<Object>();
		SqlUtils.appendCondition(p.getGenderCd(), "a.gender_cd", sql, args, QueryConditionType.EQUAL);
		SqlUtils.appendCondition(p.getIdNo(), "a.ID_NO", sql, args, QueryConditionType.EQUAL);
		SqlUtils.appendCondition(p.getMedicalInsuranceNo(), "a.medical_insurance_no", sql, args,
				QueryConditionType.EQUAL);
		SqlUtils.appendCondition(p.getNhCard(), "a.nh_card", sql, args, QueryConditionType.EQUAL);
		SqlUtils.appendCondition(p.getNameCn(), "a.NAME_CN", sql, args, QueryConditionType.LIKE);
		SqlUtils.appendCondition(p.getGenderCd(), "a.gender_cd", sql, args, QueryConditionType.EQUAL);
		SqlUtils.appendCondition(p.getBirthDate(), "a.birth_date", sql, args, QueryConditionType.EQUAL);
		SqlUtils.appendCondition(p.getCardNationCd(), "a.card_nation_cd", sql, args, QueryConditionType.EQUAL);
		SqlUtils.appendCondition(p.getCardEdBgCd(), "a.card_ed_bg_cd", sql, args, QueryConditionType.EQUAL);
		SqlUtils.appendCondition(p.getCardMaritalStCd(), "a.card_marital_st_cd", sql, args, QueryConditionType.EQUAL);
		SqlUtils.appendCondition(p.getCardOccuTypeCd(), "a.card_occu_type_cd", sql, args, QueryConditionType.EQUAL);
		SqlUtils.appendCondition(p.getPersonTelNo(), "a.person_tel_no", sql, args, QueryConditionType.EQUAL);
		SqlUtils.appendCondition(p.getLivingAddr(), "a.living_addr", sql, args, QueryConditionType.LIKE);
		SqlUtils.appendCondition(p.getDomainDesc(), "c.DOMAIN_DESC", sql, args, QueryConditionType.EQUAL);
		SqlUtils.appendCondition(p.getUniqueSign(), "c.unique_sign", sql, args, QueryConditionType.EQUAL);
		SqlUtils.appendCondition(p.getFieldPk(), "a.getFIELD_PK", sql, args, QueryConditionType.EQUAL);

		return args;
	}

	/**
	 * 删除
	 * 
	 * @param t
	 */
	public void delete(PersonInfo t) {
		personInfoDao.delete(t);
	}

	/**
	 * 根据域id和居民在该域的标识查找用户
	 * 
	 * @param domainId
	 *            域id
	 * @param identifier
	 *            居民在该域的主键
	 * @return
	 */
	private PersonInfo getByPersonIdentifier(IdentifierDomain domainId, String identifier) {
		String sql = "select * from mpi_person_info t where t.FIELD_PK in "
				+ " ( select FIELD_PK from MPI_INDEX_IDENTIFIER_REL where PERSON_IDENTIFIER = ? and DOMAIN_ID = ? )";
		List<PersonInfo> list = jdbcTemplate.query(sql, new Object[] { identifier, domainId },
				new BeanPropertyRowMapper<PersonInfo>(PersonInfo.class));
		return list.size() > 0 ? list.get(0) : null;
	}

	/**
	 * 根据域唯一标识和居民在该域的标识查找用户
	 * 
	 * @param domainUnique
	 *            域唯一标识
	 * @param identifier
	 *            居民在该域的主键
	 * @return nullable
	 */
	public PersonInfo getByPersonIdentifier(String domainUnique, String identifier) {
		IdentifierDomain domain = identifierDomainService.getByUniqueSign(domainUnique);
		PersonInfo result = null;
		if (domain != null) {
			result = getByPersonIdentifier(domain.getDomainId(), identifier);
		}
		return result;
	}

	/**
	 * 根据域id和唯一标示查询人员信息
	 * 
	 * @param domainid
	 *            域id
	 * @param identifier
	 *            唯一标识
	 * @return
	 */
	public PersonInfo getByDomainid(String domainid, String identifier) {
		IdentifierDomain domain = identifierDomainService.getObject(domainid);
		PersonInfo result = null;
		if (domain != null)
			result = getByPersonIdentifier(domain, identifier);
		return result;
	}

	/**
	 * 根据id获取人员详情
	 * 
	 * @param id
	 * @return
	 */
	public PersonInfo getObject(String id) {
		return personInfoDao.findOne(id);
	}

	/**
	 * 根据id 查询居民信息(并附带域相关信息)
	 * 
	 * @param id
	 * @return
	 */
	public PersonInfo getObjectWithDomainInfo(String id) {
		String sql = " select a.*,c.domain_id,c.unique_sign,c.domain_desc from "
				+ " mpi_person_info a left join mpi_index_identifier_rel b on a.field_pk = b.field_pk "
				+ " left join mpi_identifier_domain c on b.domain_id = c.domain_id where a.field_pk = ? ";
		List<PersonInfo> datas = jdbcTemplate.query(sql, new Object[] { id },
				new BeanPropertyRowMapper<PersonInfo>(PersonInfo.class));
		return datas.size() > 0 ? datas.get(0) : null;
	}

	/**
	 * 根据personId查询PersonIdentifier
	 * 
	 * @param personId
	 * @return
	 */
	private PersonIdentifier getPersonIdentifierByPersonId(String personId) {
		List<PersonIdentifier> datas = personIdentifierDao.findByPersonId(personId);
		return datas.size() > 0 ? datas.get(0) : null;
	}

	/**
	 * 根据id查询
	 * 
	 * @param indexId
	 * @return
	 */
	private PersonIndex getPersonIndex(String id) {
		return personIndexDao.findOne(id);
	}

	/**
	 * 合并两个居民信息
	 * 
	 * @param retired
	 *            要删除的居民信息
	 * @param surviving
	 *            要保留的居民信息
	 */
	public void mergePerson(PersonInfo retired, PersonInfo surviving) {
		// 验证基本信息
		try {
			validMergePerson(retired, surviving);
		} catch (Exception e) {
			throw new ValidationException("合并用户时,基本信息验证失败!", e);
		}
		// 暂按不删除处理
		// 查找居民信息
		// lpk update 2013年5月9日
		PersonInfo rp;
		if (retired.getType() == "0") {// 个人
			rp = getByPersonIdentifier(retired.getUniqueSign(), retired.getHrId());
		} else {// 患者
			rp = getByPersonIdentifier(retired.getUniqueSign(), retired.getMedicalserviceNo());
		}
		PersonInfo sp;
		if (retired.getType() == "0") {// 个人
			sp = getByPersonIdentifier(surviving.getUniqueSign(), surviving.getHrId());
		} else {// 患者
			sp = getByPersonIdentifier(surviving.getUniqueSign(), surviving.getMedicalserviceNo());
		}
		mergePersonFlow(rp, sp);
	}

	/**
	 * 批量合并 居民信息
	 * 
	 * @param retireds
	 * @param surviving
	 */
	public void mergePersonBatch(PersonInfoSimple[] retireds, PersonInfoSimple surviving) {
		for (PersonInfoSimple rp : retireds) {
			mergePerson(rp.toPersonInfo(), surviving.toPersonInfo());
		}
	}

	private void mergePersonFlow(PersonInfo rp, PersonInfo sp) {

		if (rp == null || sp == null) {
			logger.debug("MPI系统中未取得相关居民信息");
			throw new ValidationException("MPI系统中未取得相关居民信息");
		}
		// 索引关联信息查询
		// rp被合并居民
		IndexIdentifierRel riir = indexIdentifierRelDao.findFirstByFieldPk(rp.getFieldPk());
		// sp目标居民
		IndexIdentifierRel siir = indexIdentifierRelDao.findFirstByFieldPk(sp.getFieldPk());

		// 如 surviving 居民信息 无关联索引 返回处理错误
		if (siir == null) {
			logger.debug("合并到的居民信息还没有关联到索引中,无法完成合并");
			throw new ValidationException("合并到的居民信息还没有关联到索引中,无法完成合并");
		}

		if (riir != null) {
			// 取得索引内容
			PersonIndex rIdx = getPersonIndex(riir.getMpiPk());
			// 将 retired居民信息 解除索引关系 // 2018-01-09
			indexIdentifierRelDao.deleteByFieldPk(sp.getFieldPk());
			// 添加解除索引log
			saveIndexLog(rp.getFieldPk(), riir.getFieldPk(), riir.getDomainId(), Constant.IDX_LOG_TYPE_MODIFY,
					Constant.IDX_LOG_STYLE_AUTO_SPLIT, "主索引[" + rIdx.getNameCn() + "]进行拆分");
		}

		// 取得索引内容
		PersonIndex sIdx = getPersonIndex(siir.getMpiPk());
		// 2012年11月22日 lpk 将 retired居民信息 关联到 surviving关联的索引上
		saveIndexRelByPer(riir.getFieldPk(), siir);
		// 索引log
		saveIndexLog(rp.getFieldPk(), siir.getMpiPk(), riir.getDomainId(), Constant.IDX_LOG_TYPE_MODIFY,
				Constant.IDX_LOG_STYLE_AUTO_MERGE, "[" + rp.getNameCn() + "]合并到主索引[" + sIdx.getNameCn() + "]");
		// 更新索引信息
		personIndexUpdateService.updateIndex(rp, siir.getMpiPk());
	}

	/**
	 * 页面来源合并居民
	 * 
	 * @param retiredPersonId
	 *            被合并的居民
	 * @param survivePersonId
	 *            目标居民
	 */
	public void mergePersonFromPage(String retiredPersonId, String survivePersonId) {
		PersonInfo survive = getObject(survivePersonId);
		PersonInfo retired = getObject(retiredPersonId);
		if (survive == null || retired == null) {
			throw new ValidationException("找不到居民信息,无法完成合并.");
		}
		mergePersonFlow(retired, survive);
	}

	private void modifyManOpPerson(String opId) {
		// 取得人工干预
		ManOpPerson mop = manOpPersonDao.findOne(opId);
		// 验证数据
		if (mop == null) {
			throw new ValidationException("无法取得相关信息:ManOpPerson=" + mop);
		}
		// 修改为已干预 保存
		mop.setManOpStatus("1");
		mop.setManOpTime(DateUtil.getDateTime(DateUtil.getDateTimePattern(), new Date()));
		manOpPersonDao.save(mop);
	}

	/**
	 * 分页查询居民信息
	 * 
	 * @param t
	 *            居民查询模板
	 * @param isSurvive
	 *            是否是选择目标居民
	 * @param page
	 *            分页信息
	 * @return
	 */
	public List<Map<String, Object>> queryForMapPage(PersonInfo t, boolean isSurvive, PageInfo page) {
		String domainId = t.getDomainId();
		if (!isSurvive && StringUtils.isBlank(domainId)) {
			// 选择合并目标居民的时候 如果无域信息 则返回空数据
			return Collections.emptyList();
		}

		if (StringUtils.isNotBlank(domainId)) {
			// 取得domain信息
			IdentifierDomain domain = identifierDomainService.getObject(domainId);
			if (domain != null)
				t.setUniqueSign(domain.getUniqueSign());
		}

		StringBuilder sql = new StringBuilder();
		// lpk 2012年11月20日 update
		sql.append(
				" select a.field_pk,a.name_CN,a.id_no,c.domain_desc,c.unique_sign,c.domain_id from mpi_person_info a ")
				.append(" left join mpi_index_identifier_rel d on a.field_pk = d.field_pk left join mpi_identifier_domain c on c.domain_id = d.domain_id ")
				.append(" where 1=1 ");
		if (isSurvive) {
			sql.append(" and d.mpi_pk is not null ");
		}
		Object[] args = buildQueryConditions(sql, t).toArray();
		// 查询数据
		String countSql = page.buildCountSql(sql);
		page.setTotal(jdbcTemplate.queryForObject(countSql, args, Integer.class));
		String pageSql = page.buildPageSql(sql);
		return jdbcTemplate.queryForList(pageSql, args);
	}

	/**
	 * 取得居民信息对比数据
	 * 
	 * @param survivePersonId
	 *            目标居民id
	 * @param retiredPersonId
	 *            被合并居民id
	 */
	public Map<String, Object> queryComparePersonData(String survivePersonId, String retiredPersonId) {
		// 取得两个居民信息
		PersonInfo survive = getObjectWithDomainInfo(survivePersonId);
		PersonInfo retired = getObjectWithDomainInfo(retiredPersonId);
		Map<String, Object> result = new HashMap<String, Object>();
		IdentifierDomain domain = identifierDomainService.getObject(survive.getDomainId());
		// 转码
		CodeConvertUtils.convert(retired);
		CodeConvertUtils.convert(survive);
		result.put("survive", survive);
		result.put("retired", retired);
		result.put("domain", domain);
		return result;
	}

	/**
	 * 分页查询
	 * 
	 * @param t
	 * @param page
	 * @return
	 */
	public List<PersonInfo> queryForPage(PersonInfo t, PageInfo page) {
		return personInfoDao.findAll(page).getContent();
	}

	/**
	 * 根据索引id查询其关联的居民数据
	 * 
	 * @param indexId
	 * @return
	 */
	public List<Map<String, Object>> queryForPersonByIndexId(String indexId) {
		StringBuilder sql = new StringBuilder();
		sql.append(
				" select a.person_id,a.person_id row_id,2 row_type,b.identifier_id,a.name,a.sex,a.birth_date,a.id_no, ")
				.append(" a.phone_one,'open' \"state\",d.domain_desc from mpi_person_info a left join mpi_person_identifier b on a.person_id = b.person_id ")
				.append(" left join mpi_index_identifier_rel c on c.identifier_id = b.identifier_id left join mpi_identifier_domain d ")
				.append(" on b.domain_id = d.domain_id where c.index_id = ? ");
		return jdbcTemplate.queryForList(sql.toString(), new Object[] { indexId });
	}

	/**
	 * 查找某索引下所有关联的居民数据
	 * 
	 * @param indexId
	 *            索引id
	 * @param page
	 *            分页信息
	 * @return
	 */
	public List<Map<String, Object>> queryForSplitPersonPage(String indexId, PageInfo page) {
		StringBuilder sql = new StringBuilder();
		sql.append(" select a.person_id,b.identifier_id,a.name,a.sex,a.birth_date,a.id_no,a.phone_one ");
		sql.append(" from mpi_person_info a left join mpi_person_identifier b on a.person_id = b.person_id ");
		sql.append(" left join mpi_index_identifier_rel c on c.identifier_id = b.identifier_id where c.index_id = ? ");
		// 取得总数查询sql
		String countSql = page.buildCountSql(sql);
		// 查询设置分页记录的总记录数
		page.setTotal(jdbcTemplate.queryForObject(countSql, new Object[] { indexId }, Integer.class));
		// 取得分页查询语句
		String querySql = page.buildPageSql(sql);
		return jdbcTemplate.queryForList(querySql, new Object[] { indexId });
	}

	/**
	 * 根据居民属性查询居民信息
	 * 
	 * @param p
	 *            居民信息
	 * @return
	 */
	public List<PersonInfo> queryPersonByAttributes(PersonInfo p) {
		StringBuilder sql = new StringBuilder();
		sql.append(" select a.*,b.person_identifier,c.unique_sign from mpi_person_info a left join ");
		sql.append(" mpi_index_identifier_rel b on a.field_pk = b.field_pk left join mpi_identifier_domain c ");
		sql.append(" on b.domain_id = c.domain_id where 1=1 ");
		List<Object> args = buildQueryConditions(sql, p);
		PageInfo page = new PageInfo(0, 100);
		String countSql = page.buildCountSql(sql);
		int count = jdbcTemplate.queryForObject(countSql, args.toArray(), Integer.class);
		if (count > 100) {
			throw new BaseBussinessException("条件查询居民信息时,返回结果过多(" + count + "条)!需增加查询条件.");
		}
		return jdbcTemplate.query(sql.toString(), args.toArray(),
				new BeanPropertyRowMapper<PersonInfo>(PersonInfo.class));
	}

	/**
	 * 根据 域唯一标识和域主键取得索引信息
	 * 
	 * @param p
	 *            居民信息(需要包含域唯一标识和域主键)
	 * @return
	 */
	public PersonIndex queryPersonIndexByPersonInfo(PersonInfo p) {
		String type = p.getType();
		if ("0".equals(type)) {
			if (StringUtils.isBlank(p.getUniqueSign()) || StringUtils.isBlank(p.getHrId())) {
				throw new ValidationException(
						"查询索引信息时数据校验错误[DomainUniqueSign=" + p.getUniqueSign() + ",Identifier=" + p.getHrId() + "]");
			}
		} else {
			if (StringUtils.isBlank(p.getUniqueSign()) || StringUtils.isBlank(p.getMedicalserviceNo())) {
				throw new ValidationException("查询索引信息时数据校验错误[DomainUniqueSign=" + p.getUniqueSign() + ",Identifier="
						+ p.getMedicalserviceNo() + "]");
			}

		}

		StringBuilder sb = new StringBuilder();
		sb.append("select *  from mpi_person_index a ");
		sb.append("where exists (select 1 from mpi_index_identifier_rel b");
		sb.append(" left join mpi_identifier_domain c");
		sb.append(" on b.domain_id = c.domain_id");
		sb.append(" where a.mpi_pk = b.mpi_pk");
		sb.append(" and b.person_identifier = ?");
		sb.append("  and c.unique_sign = ? )");
		PageInfo page = new PageInfo(0, 1);
		String sql = page.buildPageSql(sb.toString());
		List<PersonIndex> list = null;
		if (type == "0") {
			list = jdbcTemplate.query(sql, new Object[] { p.getHrId(), p.getUniqueSign() },
					new BeanPropertyRowMapper<PersonIndex>(PersonIndex.class));
		} else {
			list = jdbcTemplate.query(sql, new Object[] { p.getMedicalserviceNo(), p.getUniqueSign() },
					new BeanPropertyRowMapper<PersonIndex>(PersonIndex.class));
		}
		return list.size() > 0 ? list.get(0) : null;
	}

	/**
	 * 根据索引id查询 所绑定的居民信息
	 * 
	 * @param indexId
	 * @return
	 */
	public List<PersonInfo> queryPersonsByIndex(String indexId) {
		String sql = "select a.*,b.person_identifier from mpi_person_info a left join "
				+ " mpi_index_identifier_rel b on a.field_pk = b.field_pk where  b.mpi_pk = ?  ";
		return jdbcTemplate.queryForList(sql, new Object[] { indexId }, PersonInfo.class);
	}

	/**
	 * 根据索引id和居民所在域唯一标识查询居民信息
	 * 
	 * @param indexId
	 * @return
	 */
	public List<PersonInfo> queryPersonsByIndex(String indexId, String domainUniqueSign) {
		String sql = "select a.*,b.person_identifier,c.unique_sign from mpi_person_info a left join "
				+ " mpi_index_identifier_rel b on a.field_pk = b.field_pk left join mpi_identifier_domain c "
				+ " on b.domain_id = c.domain_id where  b.mpi_pk = ? and c.unique_sign=?";
		return jdbcTemplate.query(sql, new Object[] { indexId, domainUniqueSign },
				new BeanPropertyRowMapper<PersonInfo>(PersonInfo.class));
	}

	// 根据机构号和ID查询
	private PersonInfo findByOrgId(PersonInfo entity) {
		String type = entity.getType();
		String sql = " select * from mpi_person_info  t where t.MEDICALSERVICE_NO= ? and t.REGISTER_ORG_CODE=? and t.type=?";
		List<PersonInfo> datas = new ArrayList<>();
		if ("0".equals(type)) {// 个人
			sql = " select * from mpi_person_info  t where t.HR_ID= ? and t.REGISTER_ORG_CODE=?  and t.type=?";
			datas = jdbcTemplate.query(sql, new Object[] { entity.getHrId(), entity.getRegisterOrgCode(), type },
					new BeanPropertyRowMapper<PersonInfo>(PersonInfo.class));

		} else if ("1".equals(type)) {
			datas = jdbcTemplate.query(sql,
					new Object[] { entity.getMedicalserviceNo(), entity.getRegisterOrgCode(), type },
					new BeanPropertyRowMapper<PersonInfo>(PersonInfo.class));
		} else if ("3".equals(type)) {
			datas = jdbcTemplate.query(sql, new Object[] { entity.getPatientId(), entity.getRegisterOrgCode(), type },
					new BeanPropertyRowMapper<PersonInfo>(PersonInfo.class));
		}
		return datas.size() > 0 ? datas.get(0) : null;
	}

	public Map<String, Object> findById(String person_id, String org_code, String type) {
		StringBuilder sql = new StringBuilder();
		List<Map<String, Object>> list = null;
		if ("0".equals(type)) {// 个人
			sql.append("select * from mpi_person_info a where a.type='" + type + "' and a.hr_id='" + person_id
					+ "' and a.REGISTER_ORG_CODE='" + org_code + "'");
		} else if ("1".equals(type)) {// 患者
			sql.append("select * from mpi_person_info a where a.type='" + type + "' and a.MEDICALSERVICE_NO='"
					+ person_id + "' and a.REGISTER_ORG_CODE='" + org_code + "'");
		} else if ("3".equals(type)) {
			sql.append("select * from mpi_person_info a where a.type='" + type + "' and a.PATIENT_ID='" + person_id
					+ "' and a.REGISTER_ORG_CODE='" + org_code + "'");
		}
		list = jdbcTemplate.queryForList(sql.toString());
		return list.size() > 0 ? list.get(0) : null;
	}

	public void save(PersonInfo t) {
		// 校验居民信息
		VerifyResult flag = personInfoVerifier.verify(t);
		if (!flag.isSuccess()) {
			throw new ValidationException("居民信息数据校验失败:" + flag.getMsg());
		}

		// 校验系统中是否有对应注册域
		IdentifierDomain domain = identifierDomainService.getByUniqueSign(t.getUniqueSign());
		if (domain == null) {
			throw new ValidationException("无法找到域,没有找到域标识为:" + t.getUniqueSign() + "的身份域.");
		}

		// 设置一些必要的居民信息
		t.setVersionNum("1");
		t.setDomainId(domain.getDomainId());
		t.setUniqueSign(domain.getUniqueSign());
		// 设置默认状态
		if (t.getState() == null) {
			t.setState(new Short("0"));
		}
		String domainlevel = domain.getDomainLevel();
		if ("".equals(domainlevel) || null == domainlevel) {
			t.setSrcLevel(new Short("0"));
		} else {
			t.setSrcLevel(new Short(domainlevel));
		}

		try {
			if (t.getState() == 0) {
				// 校验该居民信息是否已注册
				PersonInfo p = findByOrgId(t);
				if (p != null) {
					throw new ValidationException(
							"居民信息已注册,域标识为:" + t.getUniqueSign() + ",居民信息主键为:" + t.getFieldPk() + "的用户已注册.");
				}

				// 为居民分配唯一标识
				t.setFieldPk(IDUtil.getUUID().toString());
				// 保存历史信息 update 2013年5月15日17:56:08
				addHistory(t);
				// 保存居民信息
				personInfoDao.save(t);
			} else if (t.getState() == 1) {
				// 查看对应居民信息记录更新的居民信息的FIELD_PK;
				if (t.getRelationPk() == null || "".equals(t.getRelationPk())) {
					throw new ValidationException("居民信息RelationPk=null,域标识为:" + t.getUniqueSign() + ",居民健康档案号为:"
							+ t.getHrId() + ",居民医疗服务编号为:" + t.getMedicalserviceNo() + ".");
				}
				Map<String, Object> realtionPerson = findById(t.getRelationPk(), t.getRegisterOrgCode(), t.getType());
				if (realtionPerson != null) {
					// 插入更新历史记录 update 2013年5月15日
					t.setFieldPk(IDUtil.getUUID().toString());
					addHistory(t);
					String relationpk = (String) realtionPerson.get("FIELD_PK");
					logger.debug("relationpk=" + relationpk);
					if ("0".equals(t.getType())) {// 个人
						t.setHrId(t.getRelationPk());
					} else {// 患者
						t.setMedicalserviceNo(t.getRelationPk());
					}
					t.setRelationPk(relationpk);
					t.setFieldPk(relationpk);
					personInfoDao.save(t);
				} else {
					throw new ValidationException("未找到要更新的居民信息,主键为" + t.getRelationPk() + " 域标识为" + t.getUniqueSign());
				}
			} else if (t.getState() == 2) {
				// 查看对应居民信息记录更新的居民信息的FIELD_PK;
				if (t.getRelationPk() == null || "".equals(t.getRelationPk())) {
					throw new ValidationException("居民信息RelationPk=null ,域标识为:" + t.getUniqueSign() + ",居民健康档案号为:"
							+ t.getHrId() + ",居民医疗服务编号为:" + t.getMedicalserviceNo() + ".");
				}

				Map<String, Object> realtionPerson = findById(t.getRelationPk(), t.getRegisterOrgCode(), t.getType());
				if (realtionPerson != null) {
					// 备份新来数据信息
					t.setFieldPk(IDUtil.getUUID().toString());
					addHistory(t);

					String relationpk = (String) realtionPerson.get("FIELD_PK");
					t.setRelationPk(relationpk);
					t.setFieldPk(relationpk);
					// 删除原始数据信息
					personInfoDao.delete(t);
				} else {
					throw new ValidationException("未找到要更新的居民信息,主键为" + t.getRelationPk() + " 域标识为" + t.getUniqueSign());
				}
			} else {
				throw new ValidationException("居民信息异常: State=" + t.getState() + ";域标识为:" + t.getUniqueSign()
						+ ",居民健康档案号为:" + t.getHrId() + ",居民医疗服务编号为:" + t.getMedicalserviceNo() + ".");
			}

		} catch (Exception e) {
			e.printStackTrace();
			throw new ValidationException("居民信息入库失败！");
		}

	}

	public void addHistory(final PersonInfo entity) {
		entity.setCreatetime(new Date());
		personInfoHistoryService.save(entity.toPersonInfoHistory());
	}

	/**
	 * 被合并居民插入MPI_INDEX_IDENTIFIER_REL表
	 *
	 * @param fieldPk
	 *            被合并居民主键
	 *
	 * @param mpiPk
	 *            目标居民主索引号
	 */
	private void saveIndexIdentifierRel(String fieldPk, String mpiPk) {
		IndexIdentifierRel iir = new IndexIdentifierRel();
		iir.setFieldPk(fieldPk);
		iir.setMpiPk(mpiPk);
		indexIdentifierRelDao.save(iir);
	}

	/**
	 * 被合并居民插入MPI_INDEX_IDENTIFIER_REL表
	 *
	 * @param fieldPk
	 *            被合并居民主键
	 *
	 * @param IndexIdentifierRel
	 *            目标居民对象
	 */
	private void saveIndexRelByPer(String fieldPk, IndexIdentifierRel sp) {
		IndexIdentifierRel iir = new IndexIdentifierRel();
		iir.setFieldPk(fieldPk);
		iir.setMpiPk(sp.getMpiPk());
		iir.setCombineNo(sp.getCombineNo());
		iir.setDomainId(sp.getDomainId());
		iir.setPersonIdentifier(sp.getPersonIdentifier());
		indexIdentifierRelDao.save(iir);
	}

	/**
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
	private void saveIndexLog(String person, String index, String domainId, String opType, String opStyle,
			String desc) {
		PersonIdxLog result = new PersonIdxLog();
		result.setOpType(opType);
		result.setOpStyle(opStyle);
		result.setOpTime(DateUtil.getTimeNow(new Date()));
		result.setOpUserId("0");
		result.setOpDesc(desc);
		result.setInfoSour(domainId);
		result.setMpiPk(index);
		result.setFieldPk(person);
		// 自动标志
		personIdxLogDao.save(result);
	}

	/**
	 * 拆分居民信息
	 * 
	 * @param indexId
	 *            索引id
	 * @param personId
	 *            居民id
	 */
	public void splitPerson(String indexId, String personId) {
		// 取得索引信息
		PersonIndex index = getPersonIndex(indexId);
		// 取得居民信息
		PersonInfo person = getObject(personId);
		// 取得居民域主键信息
		PersonIdentifier pi = getPersonIdentifierByPersonId(personId);
		// 验证数据
		if (index == null || person == null || pi == null) {
			throw new ValidationException(
					"拆分居民信息时数据验证失败:PersonIndex=" + index + ",\n PersonInfo=" + person + ",\n PersonIdentifier=" + pi);
		}

		// 删除旧关联信息
		indexIdentifierRelDao.deleteByFieldPk(pi.getIdentifierId());
		// 记录索引日志 操作为3-拆分
		saveIndexLog(person.getFieldPk(), index.getMpiPk(), "", Constant.IDX_LOG_TYPE_MODIFY,
				Constant.IDX_LOG_STYLE_MAN_SPLIT, "主索引[" + index.getNameCn() + "]进行拆分");
		// 生成新索引信息
		// 新生成索引
		PersonIndex newIndex = person.toPersonIndex();
		// 设置索引源数据级别
		IdentifierDomain domain = identifierDomainService.getByPersonId(personId);
		newIndex.setDomainLevel(domain.getDomainLevel());
		// 保存新索引
		personIndexDao.save(newIndex);
		// 保存居民索引关系
		saveIndexIdentifierRel(pi.getIdentifierId(), newIndex.getMpiPk());
		// 保存索引操作日志 新增
		saveIndexLog(person.getFieldPk(), newIndex.getMpiPk(), "", Constant.IDX_LOG_TYPE_MODIFY,
				Constant.IDX_LOG_STYLE_MAN_NEW, "新建主索引[" + newIndex.getNameCn() + "]");
	}

	/**
	 * 拆分索引至指定索引
	 * 
	 * @param indexId
	 *            目标索引id
	 * @param personId
	 *            居民id
	 * @param fromIndexId
	 *            原索引id
	 */
	public void splitPersonToIndex(String indexId, String personId, String fromIndexId) {
		// 取得索引信息
		PersonIndex index = getPersonIndex(indexId);
		PersonIndex fromIndex = getPersonIndex(fromIndexId);
		// 取得居民信息
		PersonInfo person = getObject(personId);
		// 取得居民域主键信息
		PersonIdentifier pi = getPersonIdentifierByPersonId(personId);
		// 验证数据
		if (index == null || fromIndex == null || person == null || pi == null) {
			throw new ValidationException("拆分居民信息时数据验证失败:PersonIndex=[to:" + index + ",\nfrom:" + fromIndex
					+ "],\n PersonInfo=" + person + ",\n PersonIdentifier=" + pi);
		}
		// 删除旧关联信息
		indexIdentifierRelDao.deleteByFieldPk(pi.getIdentifierId());
		// 记录索引日志 操作为3-拆分
		saveIndexLog(personId, fromIndexId, "", Constant.IDX_LOG_TYPE_MODIFY, Constant.IDX_LOG_STYLE_MAN_SPLIT,
				"主索引[" + fromIndex.getNameCn() + "]进行拆分");

		// 保存居民索引关系
		saveIndexIdentifierRel(pi.getIdentifierId(), indexId);
		// 保存索引操作日志 新增
		saveIndexLog(person.getFieldPk(), indexId, "", Constant.IDX_LOG_TYPE_MODIFY, Constant.IDX_LOG_STYLE_MAN_MERGE,
				"[" + person.getNameCn() + "]合并到主索引[" + index.getNameCn() + "]");
	}

	public void update(PersonInfo t) {
		// 校验居民信息
		VerifyResult flag = personInfoVerifier.verify(t);
		if (!flag.isSuccess()) {
			throw new ValidationException("居民信息数据校验失败:" + flag.getMsg());
		}

		// 校验该居民信息在系统中是否已注册
		PersonInfo p = getByPersonIdentifier(t.getDomainId(), t.getFieldPk());
		if (p == null) {
			throw new ValidationException(
					"居民信息未注册,系统中未找到身份域为:" + t.getDomainId() + ",该域主键为:" + t.getFieldPk() + "的居民注册信息");
		}

		// 将找到的居民信息主键赋给修改数据
		t.setFieldPk(p.getFieldPk());
		// 更新居民信息
		personInfoDao.save(t);
	}

	/**
	 * 批量更新 居民信息
	 * 
	 * @param persons
	 */
	public void updatePersonBatch(PersonInfo[] persons) {
		for (PersonInfo p : persons) {
			update(p);
		}
	}

	private void validMergePerson(PersonInfo retired, PersonInfo surviving) throws Exception {
		// 验证基本信息
		if (StringUtils.isBlank(retired.getUniqueSign())
				|| (StringUtils.isBlank(retired.getHrId()) || StringUtils.isBlank(retired.getMedicalserviceNo()))
				|| StringUtils.isBlank(surviving.getUniqueSign())
				|| (StringUtils.isBlank(surviving.getHrId()) || StringUtils.isBlank(surviving.getMedicalserviceNo()))) {
			logger.debug("基本参数验证失败:\n" + "retired[domainUniqueSign=" + retired.getDomainId() + ",identifier="
					+ retired.getFieldPk() + "],\n" + "surviving[domainUniqueSign=" + surviving.getDomainId()
					+ ",identifier=" + surviving.getFieldPk() + "]");
			throw new Exception("基本参数验证失败:\n" + "retired[domainUniqueSign=" + retired.getDomainId() + ",identifier="
					+ retired.getFieldPk() + "],\n" + "surviving[domainUniqueSign=" + surviving.getDomainId()
					+ ",identifier=" + surviving.getFieldPk() + "]");
		}

		// 验证 要 merge的居民信息身份域是否一致
		if (!retired.getUniqueSign().equals(surviving.getUniqueSign())) {
			logger.debug("要合并的两居民信息身份域不同:\n" + "retired[domainUniqueSign=" + retired.getDomainId() + "],\n"
					+ "surviving[domainUniqueSign=" + surviving.getDomainId() + "]");
			throw new Exception("要合并的两居民信息身份域不同:\n" + "retired[domainUniqueSign=" + retired.getDomainId() + "],\n"
					+ "surviving[domainUniqueSign=" + surviving.getDomainId() + "]");
		}

		if (retired.getType() == surviving.getType()) {
			if (retired.getHrId().equals(surviving.getHrId())) {
				throw new Exception("不能合并同一个人:\n" + "retired[identifier=" + retired.getFieldPk() + "],\n"
						+ "surviving[identifier=" + surviving.getFieldPk() + "]");
			}
			if (retired.getMedicalserviceNo().equals(surviving.getMedicalserviceNo())) {
				throw new Exception("不能合并同一个人:\n" + "retired[identifier=" + retired.getFieldPk() + "],\n"
						+ "surviving[identifier=" + surviving.getFieldPk() + "]");
			}
		}

	}

	public PersonInfo findByRelationPK(String relationPk, String registerOrgCode) {
		return personInfoDao.findFirstByRelationPkAndRegisterOrgCode(relationPk, registerOrgCode);
	}

	/**
	 * 根据居民唯一标识查询居民信息
	 * 
	 * @param fieldpk
	 * @return
	 */
	public PersonInfo queryPersonsByFieldPK(String fieldpk) {
		return personInfoDao.findOne(fieldpk);
	}
}
