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
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.stereotype.Service;

import com.sinosoft.mpi.context.Constant;
import com.sinosoft.mpi.context.QueryConditionType;
import com.sinosoft.mpi.dao.BookLogDao;
import com.sinosoft.mpi.dao.IdentifierDomainDao;
import com.sinosoft.mpi.dao.IndexIdentifierRelDao;
import com.sinosoft.mpi.dao.ManOpPersonDao;
import com.sinosoft.mpi.dao.MatchResultDao;
import com.sinosoft.mpi.dao.PersonIdentifierDao;
import com.sinosoft.mpi.dao.PersonIdxLogDao;
import com.sinosoft.mpi.dao.PersonIndexDao;
import com.sinosoft.mpi.dao.PersonInfoDao;
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

@Service("personInfoService")
public class PersonInfoService implements IPersonInfoService {
	@Resource
	private BookLogDao bookLogDao;

	@Resource
	private IdentifierDomainDao identifierDomainDao;
	@Resource
	private IndexIdentifierRelDao indexIdentifierRelDao;
	private Logger logger = Logger.getLogger(PersonInfoService.class);
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
	private IVerifier<PersonInfo> personInfoVerifier;
	@Resource
	private RabbitTemplate template;

	@Override
	public void addNewIndex(String opId, String personId) {
		// 取得人员
		PersonInfo person = getObject(personId);
		// 取得人员对应域id信息
		PersonIdentifier pi = getPersonIdentifierByPersonId(personId);
		// 验证数据
		if (person == null || pi == null) {
			logger.debug("无法取得相关信息:PersonInfo=" + person + ",\n PersonIdentifier=" + pi);
			throw new ValidationException("无法取得相关信息:PersonInfo=" + person + ",\n PersonIdentifier=" + pi);
		}
		// 记录订阅日志
		bookLogDao.autoFillAdd(pi.getPersonId());
		indexIdentifierRelDao.deleteByFieldPK(pi.getIdentifierId());

		// 新生成索引
		PersonIndex newIndex = person.personInfoToPersonIndex();
		// 设置索引数据源级别
		IdentifierDomain domain = identifierDomainDao.getByPersonId(personId);
		newIndex.setDOMAIN_LEVEL(domain.getDOMAIN_LEVEL());
		// 保存新索引
		personIndexDao.add(newIndex);
		// 保存居民索引关系
		saveIndexIdentifierRel(pi.getIdentifierId(), newIndex.getMPI_PK());

		// 记录订阅日志
		bookLogDao.autoFillAdd(pi.getPersonId(), newIndex.getMPI_PK(), Constant.BOOK_LOG_TYPE_ADD);

		// 新建索引日志
		saveIndexLog(person.getFIELD_PK(), newIndex.getMPI_PK(), "", Constant.IDX_LOG_TYPE_MODIFY,
				Constant.IDX_LOG_STYLE_MAN_NEW, "新建主索引[" + newIndex.getNAME_CN() + "]");

		// 更新人工干预信息为已操作
		modifyManOpPerson(opId);
	}

	@Override
	public void addPersonBatch(PersonInfo[] persons) {
		for (PersonInfo p : persons) {
			save(p);
		}
	}

	@Override
	public void addToIndex(String opId, String personId, String indexId) {
		// 取得人员
		PersonInfo person = getObject(personId);
		// 取得人员对应域id信息
		PersonIdentifier pi = getPersonIdentifierByPersonId(personId);
		// 取得索引
		PersonIndex index = getPersonIndex(indexId);
		// 验证数据
		if (person == null || pi == null || index == null) {
			logger.debug("无法取得相关信息:PersonInfo=" + person + ",\n PersonIdentifier=" + pi + ",\n PersonIndex=" + index);
			throw new ValidationException(
					"无法取得相关信息:PersonInfo=" + person + ",\n PersonIdentifier=" + pi + ",\n PersonIndex=" + index);
		}
		// 记录订阅日志
		bookLogDao.autoFillAdd(pi.getPersonId());
		indexIdentifierRelDao.deleteByFieldPK(pi.getIdentifierId());

		// 保存人员至索引
		saveIndexIdentifierRel(pi.getIdentifierId(), index.getMPI_PK());
		// 记录订阅日志
		bookLogDao.autoFillAdd(pi.getPersonId(), index.getMPI_PK(), Constant.BOOK_LOG_TYPE_ADD);
		// 取得匹配记录
		MatchResult match = matchResultDao.findByPersonAndIndex(person.getFIELD_PK(), index.getMPI_PK());
		// 新建索引日志 标识为2 手工合并
		saveIndexLog(person.getFIELD_PK(), index.getMPI_PK(), "", Constant.IDX_LOG_TYPE_MATCH,
				Constant.IDX_LOG_STYLE_MAN_MERGE, "[" + person.getNAME_CN() + "]合并到主索引[" + index.getNAME_CN()
						+ "],系统匹配度:" + NumberUtils.toPercentStr(Double.parseDouble(match.getMatchDegree())));
		personIndexUpdateService.updateIndex(person, index.getMPI_PK());
		// 更新人工干预信息为已操作
		modifyManOpPerson(opId);
	}

	private List<Object> buildQueryConditions(final StringBuilder sql, PersonInfo p) {
		List<Object> args = new ArrayList<Object>();
		SqlUtils.appendCondition(p.getGENDER_CD(), "a.gender_cd", sql, args, QueryConditionType.EQUAL);
		SqlUtils.appendCondition(p.getID_NO(), "a.ID_NO", sql, args, QueryConditionType.EQUAL);
		// SqlUtils.appendCondition(p.getCertNum(), "a.cert_num", sql, args,
		// QueryConditionType.EQUAL);
		SqlUtils.appendCondition(p.getMEDICAL_INSURANCE_NO(), "a.medical_insurance_no", sql, args,
				QueryConditionType.EQUAL);
		SqlUtils.appendCondition(p.getNH_CARD(), "a.nh_card", sql, args, QueryConditionType.EQUAL);
		SqlUtils.appendCondition(p.getNAME_CN(), "a.NAME_CN", sql, args, QueryConditionType.LIKE);
		SqlUtils.appendCondition(p.getGENDER_CD(), "a.gender_cd", sql, args, QueryConditionType.EQUAL);
		SqlUtils.appendCondition(p.getBIRTH_DATE(), "a.birth_date", sql, args, QueryConditionType.EQUAL);
		SqlUtils.appendCondition(p.getCARD_NATION_CD(), "a.card_nation_cd", sql, args, QueryConditionType.EQUAL);
		SqlUtils.appendCondition(p.getCARD_ED_BG_CD(), "a.card_ed_bg_cd", sql, args, QueryConditionType.EQUAL);
		SqlUtils.appendCondition(p.getCARD_MARITAL_ST_CD(), "a.card_marital_st_cd", sql, args,
				QueryConditionType.EQUAL);
		SqlUtils.appendCondition(p.getCARD_OCCU_TYPE_CD(), "a.card_occu_type_cd", sql, args, QueryConditionType.EQUAL);
		SqlUtils.appendCondition(p.getPERSON_TEL_NO(), "a.person_tel_no", sql, args, QueryConditionType.EQUAL);
		SqlUtils.appendCondition(p.getLIVING_ADDR(), "a.living_addr", sql, args, QueryConditionType.LIKE);
		SqlUtils.appendCondition(p.getDOMAIN_DESC(), "c.DOMAIN_DESC", sql, args, QueryConditionType.EQUAL);
		SqlUtils.appendCondition(p.getUNIQUE_SIGN(), "c.unique_sign", sql, args, QueryConditionType.EQUAL);
		SqlUtils.appendCondition(p.getFIELD_PK(), "a.getFIELD_PK", sql, args, QueryConditionType.EQUAL);

		return args;
	}

	@Override
	public void delete(PersonInfo t) {
		personInfoDao.deleteById(t);
		logger.debug("Del PersonInfo,FIELD_PK=" + t.getFIELD_PK());
	}

	private PersonInfo getByPersonIdentifier(IdentifierDomain domain, String PERSON_IDENTIFIER) {
		String sql = "select * from mpi_person_info t where t.FIELD_PK in "
				+ " ( select FIELD_PK from MPI_INDEX_IDENTIFIER_REL where PERSON_IDENTIFIER = ? and DOMAIN_ID = ? )";
		List<PersonInfo> list = personInfoDao.find(sql, new Object[] { PERSON_IDENTIFIER, domain.getDOMAIN_ID() });
		PersonInfo result = null;
		if (list != null && !list.isEmpty()) {
			result = list.get(0);
		}
		return result;
	}

	@Override
	public PersonInfo getByPersonIdentifier(String domainUnique, String identifier) {
		IdentifierDomain domain = identifierDomainDao.getByUniqueSign(domainUnique);
		PersonInfo result = null;
		if (domain != null)
			result = getByPersonIdentifier(domain, identifier);
		return result;
	}

	@Override
	public PersonInfo getByDomainid(String domainid, String identifier) {
		IdentifierDomain domain = identifierDomainDao.findById(domainid);
		PersonInfo result = null;
		if (domain != null)
			result = getByPersonIdentifier(domain, identifier);
		return result;
	}

	@Override
	public PersonInfo getObject(String id) {
		PersonInfo t = personInfoDao.findByPK(id);
		logger.debug("Load PersonInfo:FIELD_PK=" + id + ",result=" + t);
		return t;
	}

	@Override
	public PersonInfo getObjectWithDomainInfo(String id) {
		PersonInfo person = new PersonInfo();
		person.setFIELD_PK(id);
		person = personInfoDao.findWithDomainInfoById(person);
		return person;
	}

	private PersonIdentifier getPersonIdentifierByPersonId(String personId) {
		String sql = " select * from mpi_person_identifier where person_id = ? ";
		logger.debug("Execute sql:[" + sql + "],parameters:[" + personId + "]");
		List<PersonIdentifier> list = personIdentifierDao.find(sql, new Object[] { personId });
		PersonIdentifier result = null;
		if (list != null && !list.isEmpty()) {
			result = list.get(0);
		}
		return result;
	}

	private PersonIndex getPersonIndex(String indexId) {
		PersonIndex result = new PersonIndex();
		result.setMPI_PK(indexId);
		result = personIndexDao.findById(result);
		return result;
	}

	@Override
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
		if (retired.getTYPE() == "0") {// 个人
			rp = getByPersonIdentifier(retired.getUNIQUE_SIGN(), retired.getHR_ID());
		} else {// 患者
			rp = getByPersonIdentifier(retired.getUNIQUE_SIGN(), retired.getMEDICALSERVICE_NO());
		}
		PersonInfo sp;
		if (retired.getTYPE() == "0") {// 个人
			sp = getByPersonIdentifier(surviving.getUNIQUE_SIGN(), surviving.getHR_ID());
		} else {// 患者
			sp = getByPersonIdentifier(surviving.getUNIQUE_SIGN(), surviving.getMEDICALSERVICE_NO());
		}
		mergePersonFlow(rp, sp);
	}

	@Override
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
		IndexIdentifierRel riir = indexIdentifierRelDao.findByFieldPK(rp.getFIELD_PK());
		// sp目标居民
		IndexIdentifierRel siir = indexIdentifierRelDao.findByFieldPK(sp.getFIELD_PK());

		// 如 surviving 居民信息 无关联索引 返回处理错误
		if (siir == null) {
			logger.debug("合并到的居民信息还没有关联到索引中,无法完成合并");
			throw new ValidationException("合并到的居民信息还没有关联到索引中,无法完成合并");
		}

		if (riir != null) {
			// 取得索引内容
			PersonIndex rIdx = getPersonIndex(riir.getMPI_PK());
			// 记录订阅日志
			bookLogDao.autoFillAdd(rp.getFIELD_PK());
			// 将 retired居民信息 解除索引关系 // 2018-01-09
			// 原：indexIdentifierRelDao.deleteByFieldPK(sp.getFIELD_PK());
			indexIdentifierRelDao.deleteByFieldPK(sp.getFIELD_PK());
			// 添加解除索引log
			saveIndexLog(rp.getFIELD_PK(), riir.getFIELD_PK(), riir.getDOMAIN_ID(), Constant.IDX_LOG_TYPE_MODIFY,
					Constant.IDX_LOG_STYLE_AUTO_SPLIT, "主索引[" + rIdx.getNAME_CN() + "]进行拆分");
		}

		// 取得索引内容
		PersonIndex sIdx = getPersonIndex(siir.getMPI_PK());
		// 2012年11月22日 lpk 将 retired居民信息 关联到 surviving关联的索引上
		saveIndexRelByPer(riir.getFIELD_PK(), siir);
		// 记录订阅日志
		bookLogDao.autoFillAdd(rp.getFIELD_PK(), siir.getMPI_PK(), Constant.BOOK_LOG_TYPE_ADD);
		// 索引log
		saveIndexLog(rp.getFIELD_PK(), siir.getMPI_PK(), riir.getDOMAIN_ID(), Constant.IDX_LOG_TYPE_MODIFY,
				Constant.IDX_LOG_STYLE_AUTO_MERGE, "[" + rp.getNAME_CN() + "]合并到主索引[" + sIdx.getNAME_CN() + "]");
		// 更新索引信息
		personIndexUpdateService.updateIndex(rp, siir.getMPI_PK());
	}

	@Override
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
		ManOpPerson mop = new ManOpPerson();
		mop.setMAN_OP_ID(opId);
		mop = manOpPersonDao.findById(mop);
		// 验证数据
		if (mop == null) {
			logger.debug("无法取得相关信息:ManOpPerson=" + mop);
			throw new ValidationException("无法取得相关信息:ManOpPerson=" + mop);
		}

		// 修改为已干预 保存
		mop.setMAN_OP_STATUS("1");
		mop.setMAN_OP_TIME(DateUtil.getDateTime(DateUtil.getDateTimePattern(), new Date()));
		manOpPersonDao.update(mop);
	}

	/*
	 * * 主索引信息合并页面首次查询
	 *
	 * @author lpk
	 *
	 * @PersonInfo 主索引信息
	 *
	 * @isSurvive 限制条件
	 *
	 * @PageInfo
	 *
	 * @date 2012年11月27日
	 */
	@Override
	public List<Map<String, Object>> queryForMapPage(PersonInfo t, boolean isSurvive, PageInfo page) {
		String domainId = t.getDOMAIN_ID();
		if (!isSurvive && StringUtils.isBlank(domainId)) {
			// 选择合并目标居民的时候 如果无域信息 则返回空数据
			return Collections.emptyList();
		}

		if (StringUtils.isNotBlank(domainId)) {
			// 取得domain信息
			IdentifierDomain domain = new IdentifierDomain();
			domain.setDOMAIN_ID(domainId);
			domain = identifierDomainDao.findById(domain);
			if (domain != null)
				t.setUNIQUE_SIGN(domain.getUNIQUE_SIGN());
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
		page.setTotal(personInfoDao.getCount(countSql, args));
		logger.debug("Execute sql:[" + countSql + "],params[" + StringUtils.join(args) + "]");
		String pageSql = page.buildPageSql(sql);
		logger.debug("Execute sql:[" + pageSql + "],params[" + StringUtils.join(args) + "]");
		return personInfoDao.findForMap(pageSql, args);
	}

	/*
	 * 比较人员信息
	 *
	 * @author lpk
	 *
	 * @survivePersonId 目标居民
	 *
	 * @retiredPersonId 被合并居民
	 *
	 * @date 2012年11月27日
	 */
	@Override
	public Map<String, Object> queryComparePersonData(String survivePersonId, String retiredPersonId) {
		// 取得两个居民信息
		PersonInfo survive = getObjectWithDomainInfo(survivePersonId);
		PersonInfo retired = getObjectWithDomainInfo(retiredPersonId);
		Map<String, Object> result = new HashMap<String, Object>();
		IdentifierDomain domain = new IdentifierDomain();
		domain.setDOMAIN_ID(survive.getDOMAIN_ID());
		domain = identifierDomainDao.findById(domain);
		// 转码
		CodeConvertUtils.convert(retired);
		CodeConvertUtils.convert(survive);
		result.put("survive", survive);
		result.put("retired", retired);
		result.put("domain", domain);
		return result;
	}

	/*
	 * 主索引信息合并
	 *
	 * @author lpk
	 *
	 * @mpiPk 主索引主键
	 *
	 * @date 2012年11月27日
	 */
	public PersonIndex getPerIndexByMpiPk(String mpiPk) {
		PersonIndex personidex = new PersonIndex();
		personidex.setMPI_PK(mpiPk);
		personidex = personIndexDao.findById(personidex);
		return personidex;
	}

	@Override
	public List<PersonInfo> queryForPage(PersonInfo t, PageInfo page) {
		String sql = " select * from mpi_person_info where 1=1 ";
		sql = page.buildPageSql(sql);
		logger.debug("Execute sql:[" + sql + "],params[]");
		return personInfoDao.find(sql, new Object[] {});
	}

	@Override
	public List<Map<String, Object>> queryForPersonByIndexId(String indexId) {
		StringBuilder sql = new StringBuilder();
		sql.append(
				" select a.person_id,a.person_id row_id,2 row_type,b.identifier_id,a.name,a.sex,a.birth_date,a.id_no, ")
				.append(" a.phone_one,'open' \"state\",d.domain_desc from mpi_person_info a left join mpi_person_identifier b on a.person_id = b.person_id ")
				.append(" left join mpi_index_identifier_rel c on c.identifier_id = b.identifier_id left join mpi_identifier_domain d ")
				.append(" on b.domain_id = d.domain_id where c.index_id = ? ");
		return personIndexDao.findForMap(sql.toString(), new Object[] { indexId });
	}

	@Override
	public List<Map<String, Object>> queryForSplitPersonPage(String indexId, PageInfo page) {
		StringBuilder sql = new StringBuilder();
		sql.append(" select a.person_id,b.identifier_id,a.name,a.sex,a.birth_date,a.id_no,a.phone_one ");
		sql.append(" from mpi_person_info a left join mpi_person_identifier b on a.person_id = b.person_id ");
		sql.append(" left join mpi_index_identifier_rel c on c.identifier_id = b.identifier_id where c.index_id = ? ");
		// 取得总数查询sql
		String countSql = page.buildCountSql(sql);
		// 查询设置分页记录的总记录数
		page.setTotal(personIndexDao.getCount(countSql, new Object[] { indexId }));
		logger.debug("Execute sql:" + countSql);
		// 取得分页查询语句
		String querySql = page.buildPageSql(sql);
		logger.debug("Execute sql:" + querySql);
		return personIndexDao.findForMap(querySql, new Object[] { indexId });
	}

	@Override
	public List<PersonInfo> queryPersonByAttributes(PersonInfo p) {
		StringBuilder sql = new StringBuilder();
		sql.append(" select a.*,b.person_identifier,c.unique_sign from mpi_person_info a left join ");
		sql.append(" mpi_index_identifier_rel b on a.field_pk = b.field_pk left join mpi_identifier_domain c ");
		sql.append(" on b.domain_id = c.domain_id where 1=1 ");
		List<Object> args = buildQueryConditions(sql, p);
		PageInfo page = new PageInfo(0, 100);
		String countSql = page.buildCountSql(sql);
		logger.debug("Execute sql:[" + countSql + "],params:[" + args.toString() + "]");
		int count = personInfoDao.getCount(countSql, args.toArray());
		if (count > 100) {
			logger.debug("条件查询居民信息时,返回结果过多!需增加查询条件.");
			throw new BaseBussinessException("条件查询居民信息时,返回结果过多(" + count + "条)!需增加查询条件.");
		}
		logger.debug("Execute sql:[" + sql.toString() + "],params:[" + args.toString() + "]");

		return personInfoDao.findWithDomainInfo(sql.toString(), args.toArray());
	}

	@Override
	public PersonIndex queryPersonIndexByPersonInfo(PersonInfo p) {
		/*
		 * if (StringUtils.isBlank(p.getUNIQUE_SIGN()) ||
		 * StringUtils.isBlank(p.getPERSON_ID())) {
		 * logger.debug("查询索引信息时数据校验错误[DomainUniqueSign=" + p.getUNIQUE_SIGN() +
		 * ",Identifier=" + p.getPERSON_ID() + "]"); throw new
		 * ValidationException("查询索引信息时数据校验错误[DomainUniqueSign=" + p.getUNIQUE_SIGN() +
		 * ",Identifier=" + p.getPERSON_ID() + "]"); }
		 */
		// lpk update 2013年5月9日
		String type = p.getTYPE();
		if (type == "0") {
			if (StringUtils.isBlank(p.getUNIQUE_SIGN()) || StringUtils.isBlank(p.getHR_ID())) {
				logger.debug(
						"查询索引信息时数据校验错误[DomainUniqueSign=" + p.getUNIQUE_SIGN() + ",Identifier=" + p.getHR_ID() + "]");
				throw new ValidationException(
						"查询索引信息时数据校验错误[DomainUniqueSign=" + p.getUNIQUE_SIGN() + ",Identifier=" + p.getHR_ID() + "]");
			}
		} else {
			if (StringUtils.isBlank(p.getUNIQUE_SIGN()) || StringUtils.isBlank(p.getMEDICALSERVICE_NO())) {
				logger.debug("查询索引信息时数据校验错误[DomainUniqueSign=" + p.getUNIQUE_SIGN() + ",Identifier="
						+ p.getMEDICALSERVICE_NO() + "]");
				throw new ValidationException("查询索引信息时数据校验错误[DomainUniqueSign=" + p.getUNIQUE_SIGN() + ",Identifier="
						+ p.getMEDICALSERVICE_NO() + "]");
			}

		}

		// lpk update 2012年11月20日
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
			logger.debug("Execute sql:[" + sql + "],\nparams:[" + p.getDOMAIN_ID() + "," + p.getHR_ID() + "]");
			list = personIndexDao.find(sql, new Object[] { p.getHR_ID(), p.getUNIQUE_SIGN() });
		} else {
			logger.debug(
					"Execute sql:[" + sql + "],\nparams:[" + p.getDOMAIN_ID() + "," + p.getMEDICALSERVICE_NO() + "]");
			list = personIndexDao.find(sql, new Object[] { p.getMEDICALSERVICE_NO(), p.getUNIQUE_SIGN() });
		}

		PersonIndex result = null;
		if (list != null && !list.isEmpty()) {
			result = list.get(0);
		}
		return result;
	}

	@Override
	public List<PersonInfo> queryPersonsByIndex(String indexId) {
		String sql = "select a.*,b.person_identifier from mpi_person_info a left join "
				+ " mpi_index_identifier_rel b on a.field_pk = b.field_pk where  b.mpi_pk = ?  ";

		logger.debug("Execute sql:[" + sql + "],params:[" + indexId + "]");

		return personInfoDao.findWithDomainInfo(sql, new Object[] { indexId });
	}

	@Override
	public List<PersonInfo> queryPersonsByIndex(String indexId, String domainUniqueSign) {
		String sql = "select a.*,b.person_identifier,c.unique_sign from mpi_person_info a left join "
				+ " mpi_index_identifier_rel b on a.field_pk = b.field_pk left join mpi_identifier_domain c "
				+ " on b.domain_id = c.domain_id where  b.mpi_pk = ? and c.unique_sign=?";

		logger.debug("Execute sql:[" + sql + "],params:[" + indexId + "," + domainUniqueSign + "]");

		return personInfoDao.findWithDomainInfo(sql, new Object[] { indexId, domainUniqueSign });
	}

	@Override
	public void save(PersonInfo t) {
		// 校验居民信息
		VerifyResult flag = personInfoVerifier.verify(t);
		if (!flag.isSuccess()) {
			throw new ValidationException("居民信息数据校验失败:" + flag.getMsg());
		}

		// 校验系统中是否有对应注册域
		IdentifierDomain domain = identifierDomainDao.getByUniqueSign(t.getUNIQUE_SIGN());
		if (domain == null) {
			logger.debug("无法找到域,没有找到域标识为:" + t.getUNIQUE_SIGN() + "的身份域.");
			throw new ValidationException("无法找到域,没有找到域标识为:" + t.getUNIQUE_SIGN() + "的身份域.");
		}

		// 设置一些必要的居民信息
		// t.setSRC_LEVEL(new Short(domain.getDOMAIN_LEVEL()));
		t.setVERSION_NUM("1");
		t.setDOMAIN_ID(domain.getDOMAIN_ID());
		t.setUNIQUE_SIGN(domain.getUNIQUE_SIGN());
		String domainlevel = domain.getDOMAIN_LEVEL();
		if ("".equals(domainlevel) || null == domainlevel) {
			t.setSRC_LEVEL(new Short("0"));
		} else {
			t.setSRC_LEVEL(new Short(domainlevel));
		}

		try {
			if (t.getSTATE() == 0) {
				// WHN update 2014年5月26日
				// 校验该居民信息是否已注册
				PersonInfo p = personInfoDao.findByOrgId(t);
				if (p != null) {
					logger.debug("居民信息已注册,域标识为:" + t.getUNIQUE_SIGN() + ",居民健康档案号为:" + t.getHR_ID() + ",居民医疗服务编号为:"
							+ t.getMEDICALSERVICE_NO() + "的用户已注册.");
					throw new ValidationException(
							"居民信息已注册,域标识为:" + t.getUNIQUE_SIGN() + ",居民信息主键为:" + t.getFIELD_PK() + "的用户已注册.");
				}

				// 为居民分配唯一标识
				t.setFIELD_PK(IDUtil.getUUID().toString());
				// 保存历史信息 update 2013年5月15日17:56:08
				personInfoDao.addHistory(t);
				// 保存居民信息
				personInfoDao.add(t);
				// 触发添加居民信息事件
				// eventSender.fireEvent(EventType.ADD_PERSON, t);
				logger.debug("添加记录");
			} else if (t.getSTATE() == 1) {
				// 查看对应居民信息记录更新的居民信息的FIELD_PK;
				// WHN update 2014年5月26日
				if (t.getRELATION_PK() == null || "".equals(t.getRELATION_PK())) {
					logger.debug("居民信息RelationPk=null,域标识为:" + t.getUNIQUE_SIGN() + ",居民健康档案号为:" + t.getHR_ID()
							+ ",居民医疗服务编号为:" + t.getMEDICALSERVICE_NO() + ";RelationPk=null.");
					throw new ValidationException("居民信息RelationPk=null,域标识为:" + t.getUNIQUE_SIGN() + ",居民健康档案号为:"
							+ t.getHR_ID() + ",居民医疗服务编号为:" + t.getMEDICALSERVICE_NO() + ".");
				}
				Map<String, Object> realtionPerson = personInfoDao.findById(t.getRELATION_PK(),
						t.getREGISTER_ORG_CODE(), t.getTYPE());
				if (realtionPerson != null) {
					// 插入更新历史记录 update 2013年5月15日
					t.setFIELD_PK(IDUtil.getUUID().toString());
					personInfoDao.addHistory(t);
					String relationpk = (String) realtionPerson.get("FIELD_PK");
					logger.debug("relationpk=" + relationpk);
					if ("0".equals(t.getTYPE())) {// 个人
						t.setHR_ID(t.getRELATION_PK());
					} else {// 患者
						t.setMEDICALSERVICE_NO(t.getRELATION_PK());
					}
					t.setRELATION_PK(relationpk);
					t.setFIELD_PK(relationpk);
					personInfoDao.update(t);
					// eventSender.fireEvent(EventType.UPDATE_PERSON, t);
					logger.debug("更新记录");
				} else {
					logger.error("未找到要更新的居民信息,主键为" + t.getRELATION_PK() + " 域标识为" + t.getUNIQUE_SIGN());
					throw new ValidationException(
							"未找到要更新的居民信息,主键为" + t.getRELATION_PK() + " 域标识为" + t.getUNIQUE_SIGN());
				}
			} else if (t.getSTATE() == 2) {
				// 查看对应居民信息记录更新的居民信息的FIELD_PK;
				// WHN update 2014年5月26日
				if (t.getRELATION_PK() == null || "".equals(t.getRELATION_PK())) {
					logger.debug("居民信息RelationPk=null,域标识为:" + t.getUNIQUE_SIGN() + ",居民健康档案号为:" + t.getHR_ID()
							+ ",居民医疗服务编号为:" + t.getMEDICALSERVICE_NO() + ";RelationPk=null.");
					throw new ValidationException("居民信息RelationPk=null ,域标识为:" + t.getUNIQUE_SIGN() + ",居民健康档案号为:"
							+ t.getHR_ID() + ",居民医疗服务编号为:" + t.getMEDICALSERVICE_NO() + ".");
				}

				System.out.println("getRELATION_PK:" + t.getRELATION_PK());

				Map<String, Object> realtionPerson = personInfoDao.findById(t.getRELATION_PK(),
						t.getREGISTER_ORG_CODE(), t.getTYPE());
				if (realtionPerson != null) {
					// 备份新来数据信息
					t.setFIELD_PK(IDUtil.getUUID().toString());
					personInfoDao.addHistory(t);

					String relationpk = (String) realtionPerson.get("FIELD_PK");
					t.setRELATION_PK(relationpk);
					t.setFIELD_PK(relationpk);
					// 删除原始数据信息
					personInfoDao.deleteById(t);

					logger.debug("作废记录");
				} else {
					logger.error("未找到要作废的居民信息,主键为" + t.getRELATION_PK() + " 域标识为" + t.getUNIQUE_SIGN());
					throw new ValidationException(
							"未找到要更新的居民信息,主键为" + t.getRELATION_PK() + " 域标识为" + t.getUNIQUE_SIGN());
				}
			} else {
				logger.debug("居民信息异常: State=" + t.getSTATE() + ";域标识为:" + t.getUNIQUE_SIGN() + ",居民健康档案号为:"
						+ t.getHR_ID() + ",居民医疗服务编号为:" + t.getMEDICALSERVICE_NO() + ";");
				throw new ValidationException("居民信息异常: State=" + t.getSTATE() + ";域标识为:" + t.getUNIQUE_SIGN()
						+ ",居民健康档案号为:" + t.getHR_ID() + ",居民医疗服务编号为:" + t.getMEDICALSERVICE_NO() + ".");
			}

		} catch (Exception e) {
			e.printStackTrace(); // DEBUGGER在日志中打印异常SQL-201708
			throw new ValidationException("居民信息入库失败！");
		}

		// 发送居民信息至消息队列异步处理主索引
		template.convertAndSend(t);
	}

	/**
	 * 被合并居民插入MPI_INDEX_IDENTIFIER_REL表
	 *
	 * @param field_pk
	 *            被合并居民主键
	 *
	 * @param mpi_pk
	 *            目标居民主索引号
	 */
	private void saveIndexIdentifierRel(String field_pk, String mpi_pk) {
		IndexIdentifierRel iir = new IndexIdentifierRel();
		iir.setFIELD_PK(field_pk);
		iir.setMPI_PK(mpi_pk);
		indexIdentifierRelDao.add(iir);
	}

	/**
	 * 被合并居民插入MPI_INDEX_IDENTIFIER_REL表
	 *
	 * @param field_pk
	 *            被合并居民主键
	 *
	 * @param IndexIdentifierRel
	 *            目标居民对象
	 */
	private void saveIndexRelByPer(String field_pk, IndexIdentifierRel sp) {
		IndexIdentifierRel iir = new IndexIdentifierRel();
		iir.setFIELD_PK(field_pk);
		iir.setMPI_PK(sp.getMPI_PK());
		// iir.setCOMBINE_NO(sp.getCOMBINE_NO());
		iir.setCOMBINE_REC(sp.getCOMBINE_NO());
		iir.setDOMAIN_ID(sp.getDOMAIN_ID());
		iir.setPERSON_IDENTIFIER(sp.getPERSON_IDENTIFIER());
		indexIdentifierRelDao.add(iir);
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
		result.setMpipk(index);
		result.setFieldpk(person);
		// 自动标志
		personIdxLogDao.add(result);
	}

	@Override
	public void splitPerson(String indexId, String personId) {
		// 取得索引信息
		PersonIndex index = getPersonIndex(indexId);
		// 取得居民信息
		PersonInfo person = getObject(personId);
		// 取得居民域主键信息
		PersonIdentifier pi = getPersonIdentifierByPersonId(personId);
		// 验证数据
		if (index == null || person == null || pi == null) {
			logger.debug(
					"拆分居民信息时数据验证失败:PersonIndex=" + index + ",\n PersonInfo=" + person + ",\n PersonIdentifier=" + pi);
			throw new ValidationException(
					"拆分居民信息时数据验证失败:PersonIndex=" + index + ",\n PersonInfo=" + person + ",\n PersonIdentifier=" + pi);
		}

		// 记录订阅日志
		bookLogDao.autoFillAdd(personId);
		// 删除旧关联信息
		indexIdentifierRelDao.deleteByFieldPK(pi.getIdentifierId());
		// 记录索引日志 操作为3-拆分
		saveIndexLog(person.getFIELD_PK(), index.getMPI_PK(), "", Constant.IDX_LOG_TYPE_MODIFY,
				Constant.IDX_LOG_STYLE_MAN_SPLIT, "主索引[" + index.getNAME_CN() + "]进行拆分");
		// 生成新索引信息
		// 新生成索引
		PersonIndex newIndex = person.personInfoToPersonIndex();
		// 设置索引源数据级别
		IdentifierDomain domain = identifierDomainDao.getByPersonId(personId);
		newIndex.setDOMAIN_LEVEL(domain.getDOMAIN_LEVEL());
		// 保存新索引
		personIndexDao.add(newIndex);
		// 保存居民索引关系
		saveIndexIdentifierRel(pi.getIdentifierId(), newIndex.getMPI_PK());
		// 记录订阅日志
		bookLogDao.autoFillAdd(person.getFIELD_PK(), newIndex.getMPI_PK(), Constant.BOOK_LOG_TYPE_ADD);
		// 保存索引操作日志 新增
		saveIndexLog(person.getFIELD_PK(), newIndex.getMPI_PK(), "", Constant.IDX_LOG_TYPE_MODIFY,
				Constant.IDX_LOG_STYLE_MAN_NEW, "新建主索引[" + newIndex.getNAME_CN() + "]");
	}

	@Override
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
			logger.debug("拆分居民信息时数据验证失败:PersonIndex=[to:" + index + ",\nfrom:" + fromIndex + "],\n PersonInfo=" + person
					+ ",\n PersonIdentifier=" + pi);
			throw new ValidationException("拆分居民信息时数据验证失败:PersonIndex=[to:" + index + ",\nfrom:" + fromIndex
					+ "],\n PersonInfo=" + person + ",\n PersonIdentifier=" + pi);
		}
		// 记录订阅日志
		bookLogDao.autoFillAdd(personId);
		// 删除旧关联信息
		indexIdentifierRelDao.deleteByFieldPK(pi.getIdentifierId());
		// 记录索引日志 操作为3-拆分
		saveIndexLog(personId, fromIndexId, "", Constant.IDX_LOG_TYPE_MODIFY, Constant.IDX_LOG_STYLE_MAN_SPLIT,
				"主索引[" + fromIndex.getNAME_CN() + "]进行拆分");

		// 保存居民索引关系
		saveIndexIdentifierRel(pi.getIdentifierId(), indexId);
		// 记录订阅日志
		bookLogDao.autoFillAdd(person.getFIELD_PK(), indexId, Constant.BOOK_LOG_TYPE_ADD);
		// 保存索引操作日志 新增
		saveIndexLog(person.getFIELD_PK(), indexId, "", Constant.IDX_LOG_TYPE_MODIFY, Constant.IDX_LOG_STYLE_MAN_MERGE,
				"[" + person.getNAME_CN() + "]合并到主索引[" + index.getNAME_CN() + "]");
	}

	@Override
	public void update(PersonInfo t) {
		// 校验居民信息
		VerifyResult flag = personInfoVerifier.verify(t);
		if (!flag.isSuccess()) {
			throw new ValidationException("居民信息数据校验失败:" + flag.getMsg());
		}

		// 校验该居民信息在系统中是否已注册
		PersonInfo p = getByPersonIdentifier(t.getDOMAIN_ID(), t.getFIELD_PK());
		if (p == null) {
			logger.debug("居民信息未注册,系统中未找到身份域为:" + t.getDOMAIN_ID() + ",该域主键为:" + t.getFIELD_PK() + "的居民注册信息");
			throw new ValidationException(
					"居民信息未注册,系统中未找到身份域为:" + t.getDOMAIN_ID() + ",该域主键为:" + t.getFIELD_PK() + "的居民注册信息");
		}

		// 将找到的居民信息主键赋给修改数据
		t.setFIELD_PK(p.getFIELD_PK());
		// 更新居民信息
		personInfoDao.update(t);
		logger.debug("Update PersonInfo:" + t);
		// 触发居民信息更新事件
		// PersonIdentifier pi = getPersonIdentifierByPersonId(t.getFIELD_PK());
		// eventSender.fireEvent(EventType.UPDATE_PERSON, pi);
	}

	@Override
	public void updatePersonBatch(PersonInfo[] persons) {
		for (PersonInfo p : persons) {
			update(p);
		}
	}

	private void validMergePerson(PersonInfo retired, PersonInfo surviving) throws Exception {
		// 验证基本信息
		if (StringUtils.isBlank(retired.getUNIQUE_SIGN())
				|| (StringUtils.isBlank(retired.getHR_ID()) || StringUtils.isBlank(retired.getMEDICALSERVICE_NO()))
				|| StringUtils.isBlank(surviving.getUNIQUE_SIGN()) || (StringUtils.isBlank(surviving.getHR_ID())
						|| StringUtils.isBlank(surviving.getMEDICALSERVICE_NO()))) {
			logger.debug("基本参数验证失败:\n" + "retired[domainUniqueSign=" + retired.getDOMAIN_ID() + ",identifier="
					+ retired.getFIELD_PK() + "],\n" + "surviving[domainUniqueSign=" + surviving.getDOMAIN_ID()
					+ ",identifier=" + surviving.getFIELD_PK() + "]");
			throw new Exception("基本参数验证失败:\n" + "retired[domainUniqueSign=" + retired.getDOMAIN_ID() + ",identifier="
					+ retired.getFIELD_PK() + "],\n" + "surviving[domainUniqueSign=" + surviving.getDOMAIN_ID()
					+ ",identifier=" + surviving.getFIELD_PK() + "]");
		}

		// 验证 要 merge的居民信息身份域是否一致
		if (!retired.getUNIQUE_SIGN().equals(surviving.getUNIQUE_SIGN())) {
			logger.debug("要合并的两居民信息身份域不同:\n" + "retired[domainUniqueSign=" + retired.getDOMAIN_ID() + "],\n"
					+ "surviving[domainUniqueSign=" + surviving.getDOMAIN_ID() + "]");
			throw new Exception("要合并的两居民信息身份域不同:\n" + "retired[domainUniqueSign=" + retired.getDOMAIN_ID() + "],\n"
					+ "surviving[domainUniqueSign=" + surviving.getDOMAIN_ID() + "]");
		}

		/*
		 * if (retired.getPERSON_ID().equals(surviving.getPERSON_ID())) { throw new
		 * Exception("不能合并同一个人:\n" + "retired[identifier=" + retired.getFIELD_PK() +
		 * "],\n" + "surviving[identifier=" + surviving.getFIELD_PK() + "]"); }
		 */
		// lpk update 2013年5月9日
		if (retired.getTYPE() == surviving.getTYPE()) {
			if (retired.getHR_ID().equals(surviving.getHR_ID())) {
				throw new Exception("不能合并同一个人:\n" + "retired[identifier=" + retired.getFIELD_PK() + "],\n"
						+ "surviving[identifier=" + surviving.getFIELD_PK() + "]");
			}
			if (retired.getMEDICALSERVICE_NO().equals(surviving.getMEDICALSERVICE_NO())) {
				throw new Exception("不能合并同一个人:\n" + "retired[identifier=" + retired.getFIELD_PK() + "],\n"
						+ "surviving[identifier=" + surviving.getFIELD_PK() + "]");
			}
		}

	}

	@Override
	public Map<String, Object> findByRelationPK(String relationpk, String org_code) {
		return personInfoDao.findById(relationpk, org_code);
	}

	@Override
	public PersonInfo queryPersonsByFieldPK(String fieldpk) {
		return personInfoDao.findByPK(fieldpk);
	}
}
