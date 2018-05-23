package com.sinosoft.mpi.service;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.commons.beanutils.PropertyUtils;
import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import com.sinosoft.mpi.cache.CacheManager;
import com.sinosoft.mpi.context.QueryConditionType;
import com.sinosoft.mpi.dao.mpi.MatchResultDao;
import com.sinosoft.mpi.dao.mpi.PersonIdxLogDao;
import com.sinosoft.mpi.dao.mpi.PersonIndexDao;
import com.sinosoft.mpi.dao.mpi.PersonInfoDao;
import com.sinosoft.mpi.exception.ValidationException;
import com.sinosoft.mpi.form.MatchDetailForm;
import com.sinosoft.mpi.model.IdentifierDomain;
import com.sinosoft.mpi.model.MatchResult;
import com.sinosoft.mpi.model.PersonIdxLog;
import com.sinosoft.mpi.model.PersonIndex;
import com.sinosoft.mpi.model.PersonInfo;
import com.sinosoft.mpi.model.code.PerInfoPropertiesDesc;
import com.sinosoft.mpi.model.code.PersonPropertiesDesc;
import com.sinosoft.mpi.util.CodeConvertUtils;
import com.sinosoft.mpi.util.DateUtil;
import com.sinosoft.mpi.util.NumberUtils;
import com.sinosoft.mpi.util.PageInfo;
import com.sinosoft.mpi.util.SqlUtils;

/**
 * 人员主索引日志服务
 */
@Service
public class PersonIdxLogService {

	private Logger logger = Logger.getLogger(PersonIdxLogService.class);

	@Resource
	private IdentifierDomainService identifierDomainService;
	@Resource
	private MatchResultDao matchResultDao;
	@Resource
	private PersonIdxLogDao personIdxLogDao;
	@Resource
	private PersonIndexDao personIndexDao;
	@Resource
	private PersonInfoDao personInfoDao;
	@Resource
	private PersonIndexService personIndexService;
	@Resource
	JdbcTemplate jdbcTemplate;

	/**
	 * 增加条件
	 * 
	 * @param t
	 * @param sql
	 * @param args
	 */
	private void addconditions(final PersonIdxLog t, final StringBuilder sql, final List<Object> args) {
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
	 * 构建字段
	 * 
	 * @param obj
	 * @return
	 */
	private List<MatchDetailForm> buildField(Object obj) {
		List<PerInfoPropertiesDesc> fields = CacheManager.getAll(PerInfoPropertiesDesc.class);
		List<MatchDetailForm> result = new ArrayList<MatchDetailForm>(fields.size());
		for (PerInfoPropertiesDesc field : fields) {
			// 创建列表实体对象
			MatchDetailForm form = new MatchDetailForm();
			// 设置字段名
			form.setFieldName(field.getPropertyName());
			// 设置字段中文名
			form.setFieldCnName(field.getPropertyDesc());
			// 设置居民表属性值
			form.setPersonValue(getValue(obj, field.getPropertyName()));
			// 放入list
			result.add(form);
		}
		return result;
	}

	/**
	 * 构建匹配字段
	 * 
	 * @param mr
	 * @param person
	 * @param index
	 * @return
	 */
	private List<MatchDetailForm> buildFieldMatch(MatchResult mr, PersonInfo person, PersonIndex index) {
		// 取得字段匹配串
		String fieldMatchStr = mr.getFieldMatDegrees();
		// 验证数据
		if (StringUtils.isBlank(fieldMatchStr))
			return Collections.emptyList();
		// 切分
		String[] fieldMatchs = fieldMatchStr.split(",");
		// 创建返回结果
		List<MatchDetailForm> result = new ArrayList<MatchDetailForm>(fieldMatchs.length);
		// 迭代填充数据
		for (String fieldMatch : fieldMatchs) {
			// 切分字段=匹配度数据
			String[] matchKV = fieldMatch.split("=");
			// 验证数据有效性
			if (matchKV.length != 2)
				continue;

			String fieldName = matchKV[0];
			String degree = matchKV[1];
			// 创建列表实体对象
			MatchDetailForm form = new MatchDetailForm();
			// 设置字段名
			form.setFieldName(fieldName);
			// 设置字段中文名
			form.setFieldCnName(getFieldCnName(fieldName));
			// 设置匹配度
			form.setMatchDegree(NumberUtils.toPercentStr(Double.parseDouble(degree)));
			// 设置居民表属性值
			form.setPersonValue(getValue(person, fieldName));
			// 设置索引表属性值
			form.setIndexValue(getValue(index, fieldName));
			// 放入list
			result.add(form);
		}

		return result;
	}

	/**
	 * 构建所有字段
	 * 
	 * @param person
	 * @param index
	 * @return
	 */
	private List<MatchDetailForm> buildFullField(PersonInfo person, PersonIndex index) {
		// personindex居民主索引
		List<PersonPropertiesDesc> fields = CacheManager.getAll(PersonPropertiesDesc.class);
		// personinfo居民信息
		List<PerInfoPropertiesDesc> infofields = CacheManager.getAll(PerInfoPropertiesDesc.class);
		List<MatchDetailForm> result = new ArrayList<MatchDetailForm>(fields.size());
		// 遍历 居民主索引 字段
		for (PersonPropertiesDesc field : fields) {
			// 遍历 居民信息 字段
			for (PerInfoPropertiesDesc infofield : infofields) {
				// 如果字段相同显示
				if (infofield.getPropertyName() == field.getPropertyName()) {
					// 创建列表实体对象
					MatchDetailForm form = new MatchDetailForm();
					// 设置字段名
					form.setFieldName(field.getPropertyName());
					// 设置字段中文名
					form.setFieldCnName(field.getPropertyDesc());
					// 设置居民表属性值
					form.setPersonValue(getValue(person, field.getPropertyName()));
					// 设置索引表属性值
					form.setIndexValue(getValue(index, field.getPropertyName()));
					// 放入list
					result.add(form);
				}
			}
		}
		return result;
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
		SqlUtils.appendCondition(p.getIdNo(), "a.id_no", sql, args, QueryConditionType.EQUAL);
		SqlUtils.appendCondition(p.getIdNoCd(), "a.id_no_cd", sql, args, QueryConditionType.EQUAL);
		SqlUtils.appendCondition(p.getIdNo(), "a.id_no", sql, args, QueryConditionType.EQUAL);
		SqlUtils.appendCondition(p.getMedicalInsuranceNo(), "a.medical_insurance_no", sql, args,
				QueryConditionType.EQUAL);
		SqlUtils.appendCondition(p.getNhCard(), "a.nh_card", sql, args, QueryConditionType.EQUAL);
		SqlUtils.appendCondition(p.getNameCn(), "a.name", sql, args, QueryConditionType.LIKE);
		SqlUtils.appendCondition(p.getGenderCd(), "a.gender_cd", sql, args, QueryConditionType.EQUAL);
		SqlUtils.appendCondition(p.getBirthDate(), "a.birth_date", sql, args, QueryConditionType.LIKE);
		SqlUtils.appendCondition(p.getCardNationCd(), "a.card_nation_cd", sql, args, QueryConditionType.EQUAL);
		SqlUtils.appendCondition(p.getCardEdBgCd(), "a.card_ed_bg_cd", sql, args, QueryConditionType.EQUAL);
		SqlUtils.appendCondition(p.getCardMaritalStCd(), "a.card_marital_st_cd", sql, args, QueryConditionType.EQUAL);
		SqlUtils.appendCondition(p.getCardOccuTypeCd(), "a.card_occu_type_cd", sql, args, QueryConditionType.EQUAL);
		SqlUtils.appendCondition(p.getPersonTelNo(), "a.person_tel_no", sql, args, QueryConditionType.EQUAL);
		SqlUtils.appendCondition(p.getLivingAddr(), "a.living_addr", sql, args, QueryConditionType.EQUAL);
		SqlUtils.appendCondition(p.getUniqueSign(), "c.unique_sign", sql, args, QueryConditionType.EQUAL);
		SqlUtils.appendCondition(p.getFieldPk(), "a.field_pk", sql, args, QueryConditionType.EQUAL);
		return args;
	}

	/**
	 * 删除
	 * 
	 * @param t
	 */
	public void delete(PersonIdxLog t) {
		personIdxLogDao.delete(t);
	}

	/**
	 * 根据主键分页查询
	 * 
	 * @param fieldPk
	 * @param page
	 * @return
	 */
	private List<MatchResult> findMatchResult(String fieldPk, final PageInfo page) {
		return matchResultDao.findByFieldPkOrderByMatchDegreeDesc(fieldPk, page);
	}

	/**
	 * 根据主索引id和主键查询
	 * 
	 * @param mpi_pk
	 * @param field_pk
	 * @return
	 */
	private MatchResult findMatchResult(String mpiPk, String fieldPk) {
		List<MatchResult> list = matchResultDao.findByMpiPkAndFieldPkOrderByMatchDegreeDesc(mpiPk, fieldPk);
		return list.size() > 0 ? list.get(0) : null;
	}

	/**
	 * 根据人员id和主索引id列表查询
	 * 
	 * @param personId
	 *            人员id
	 * @param idxIds
	 *            主索引id列表
	 * @return
	 */
	private List<MatchResult> findMatchResult(String personId, String[] idxIds) {
		return matchResultDao.findByFieldPkAndMpiPkInOrderByMatchDegreeDesc(personId, idxIds);
	}

	/**
	 * 根据主索引id查询
	 * 
	 * @param mpiPk
	 * @return
	 */
	private PersonIndex findPersonIndex(String mpiPk) {
		return personIndexDao.findOne(mpiPk);
	}

	/**
	 * 根据人员id查询
	 * 
	 * @param personId
	 * @return
	 */
	private PersonInfo findPersonInfo(String personId) {
		return personInfoDao.findWithDomainInfoById(personId);
	}

	/**
	 * 获取字段中文名
	 * 
	 * @param fieldName
	 * @return
	 */
	private String getFieldCnName(String fieldName) {
		PersonPropertiesDesc desc = CacheManager.get(PersonPropertiesDesc.class, fieldName);
		return desc.getPropertyDesc();
	}

	/**
	 * 根据id获取
	 * 
	 * @param id
	 * @return
	 */
	public PersonIdxLog getObject(String id) {
		return personIdxLogDao.findOne(id);
	}

	/**
	 * 获取字段的属性值
	 * 
	 * @param obj
	 * @param fieldName
	 * @return
	 */
	private String getValue(Object obj, String fieldName) {
		String result = null;
		Object value = new Object();
		try {
			value = PropertyUtils.getProperty(obj, fieldName);
			if (value != null && value instanceof Date) {
				if (value != null) {
					SimpleDateFormat sdf = new SimpleDateFormat("yyyy年MM月dd日");
					result = sdf.format((Date) value);
				}
			} else {
				result = (value == null) ? null : value.toString();
			}
		} catch (Exception e) {
			logger.warn("无法从实体对象[" + obj + "]中取得属性:" + fieldName, e);
		}
		return result;
	}

	/**
	 * 根据人员id和主索引id获取
	 * 
	 * @param personId
	 * @param mpiPk
	 * @return
	 */
	public List<MatchDetailForm> queryCompareData(String personId, String mpiPk) {
		// 取得 居民
		PersonInfo person = findPersonInfo(personId);
		// 取得 索引
		PersonIndex index = findPersonIndex(mpiPk);
		// 校验
		if (person == null || index == null) {
			throw new ValidationException("无法取得相关信息:PersonInfo=" + person + ",\nPersonIndex=" + index);
		}
		// 转码
		CodeConvertUtils.convert(person);
		CodeConvertUtils.convert(index);
		// 构建对比 why???
		List<MatchDetailForm> list = buildFullField(person, index);

		return list;
	}

	/**
	 * 分页查询
	 * 
	 * @param t
	 * @param page
	 * @return
	 */
	public List<Map<String, Object>> queryForMapPage(PersonIdxLog t, PageInfo page) {
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
		page.setTotal(jdbcTemplate.queryForObject(countSql, args.toArray(), Integer.class));
		// 添加排序sql
		sql.append(" order by a.op_time desc ");
		// 取得分页查询语句
		String querySql = page.buildPageSql(sql);
		return jdbcTemplate.queryForList(querySql, args.toArray());
	}

	/**
	 * 分页查询
	 * 
	 * @param t
	 * @param page
	 * @return
	 */
	public List<PersonIdxLog> queryForPage(PersonIdxLog t, PageInfo page) {
		return personIdxLogDao.findAll(page).getContent();
	}

	/**
	 * 根据主索引id获取详情
	 * 
	 * @param mpiPk
	 * @return
	 */
	public Map<String, Object> queryIndexDetail(String mpiPk) {
		Map<String, Object> result = new HashMap<String, Object>();
		// 取得索引数据
		PersonIndex index = findPersonIndex(mpiPk);
		List<MatchDetailForm> fieldList = null;
		if (index != null) {
			// 转码
			CodeConvertUtils.convert(index);
			fieldList = buildFieldByIndex(index);
		}
		result.put("data", index);
		result.put("fields", fieldList);
		return result;
	}

	/**
	 * 根据主索引id查询详情
	 * 
	 * @param mpiPk
	 * @return
	 */
	public List<Map<String, Object>> queryIndexDetails(String mpiPk) {
		List<Map<String, Object>> result = new ArrayList<Map<String, Object>>();
		// 被合并的主索引集合
		List<PersonIndex> indexs = personIndexService.findPersonIndexBysplitIndex(mpiPk);
		for (int i = 0; i < indexs.size(); i++) {
			CodeConvertUtils.convert(indexs.get(i));
			Map<String, Object> map = new HashMap<String, Object>();
			map.put("fields", buildFieldByIndex(indexs.get(i)));
			result.add(map);
		}
		return result;
	}

	/**
	 * 根据主索引构建字段
	 * 
	 * @param obj
	 * @return
	 */
	private List<MatchDetailForm> buildFieldByIndex(Object obj) {
		List<PersonPropertiesDesc> fields = CacheManager.getAll(PersonPropertiesDesc.class);
		List<MatchDetailForm> result = new ArrayList<MatchDetailForm>(fields.size());
		for (PersonPropertiesDesc field : fields) {
			// 创建列表实体对象
			MatchDetailForm form = new MatchDetailForm();
			// 设置字段名
			form.setFieldName(field.getPropertyName());
			// 设置字段中文名
			form.setFieldCnName(field.getPropertyDesc());
			// 设置居民表属性值
			form.setPersonValue(getValue(obj, field.getPropertyName()));
			// 放入list
			result.add(form);
		}
		return result;
	}

	/**
	 * 查询匹配数据
	 * 
	 * @param logId
	 * @return
	 */
	public Map<String, Object> queryMatchDetail(String logId) {
		// 校验数据
		if (StringUtils.isBlank(logId)) {
			throw new ValidationException("参数为空:logId=" + logId);
		}
		Map<String, Object> result = new HashMap<String, Object>();
		// 取得 索引日志
		PersonIdxLog log = getObject(logId);
		if (log == null) {
			throw new ValidationException("无法取得相关信息:PersonIdxLog=" + log);
		}
		// 取得域信息
		if (StringUtils.isNotBlank(log.getInfoSour())) {
			IdentifierDomain domain = identifierDomainService.getObject(log.getInfoSour());
			if(domain != null) {
				log.setInfoSour(domain.getDomainDesc());
			}
		}
		// 取得居民数据
		PersonInfo person;
		// 取得索引数据
		PersonIndex index;
		person = findPersonInfo(log.getFieldPk());
		if (person == null) {
			person = new PersonInfo();
		}
		index = findPersonIndex(log.getMpiPk());
		if (index == null) {
			index = new PersonIndex();
		}
		// 转码
		CodeConvertUtils.convert(index);
		// 转码
		CodeConvertUtils.convert(person);
		// 取得匹配结果
		MatchResult mr = findMatchResult(log.getMpiPk(), log.getFieldPk());

		List<MatchDetailForm> matchField = null;
		if (mr != null) {
			// 匹配度转百分数
			mr.setMatchDegree(NumberUtils.toPercentStr(Double.parseDouble(mr.getMatchDegree())));
			matchField = buildFieldMatch(mr, person, index);
		}
		// 构建全字段对比
		List<MatchDetailForm> fullField = buildFullField(person, index);
		// 放入匹配日志
		result.put("log", log);
		// 放入匹配结果
		result.put("matchResult", mr);
		// 放入匹配结果对比列
		result.put("matchField", matchField);
		// 放入全字段对比列
		result.put("fullField", fullField);
		// 放入索引信息
		result.put("index", index);
		// 放入人员信息
		result.put("person", person);
		return result;
	}

	/**
	 * 查询匹配数据-分页
	 * 
	 * @param personId
	 * @param start
	 * @param end
	 * @return
	 */
	public List<Map<String, Object>> queryMatchDetailPage(String personId, int start, int end) {

		// 创建分页信息,每次查询出一条匹配记录
		PageInfo page = PageInfo.getByStartAndEnd(start, end);
		// 取得匹配数据
		List<MatchResult> mrs = findMatchResult(personId, page);
		if (mrs == null || mrs.isEmpty()) {
			logger.debug("无法取得相关信息:MatchResult[]=" + mrs);
			throw new ValidationException("无法取得相关信息:MatchResult[]=" + mrs);
		}
		List<Map<String, Object>> result = new ArrayList<Map<String, Object>>(mrs.size());
		for (MatchResult mr : mrs) {
			// 取得索引数据
			PersonIndex index = findPersonIndex(mr.getMpiPk());
			if (index == null) {
				logger.debug("无法取得相关信息:PersonIndex=" + index);
				throw new ValidationException("无法取得相关信息:PersonIndex=" + index);
			}
			Map<String, Object> map = new HashMap<String, Object>(2);
			map.put("result", mr);
			// 转码
			CodeConvertUtils.convert(index);
			map.put("index", index);
			result.add(map);
		}
		return result;
	}

	/**
	 * 查询匹配详情-根据人员id和主索引id列表
	 * 
	 * @param personId
	 * @param idxIds
	 * @return
	 */
	public List<Map<String, Object>> queryMatchDetailPageByIdxIds(String personId, String[] idxIds) {

		// 取得匹配数据
		List<MatchResult> mrs = findMatchResult(personId, idxIds);
		if (mrs == null || mrs.isEmpty()) {
			throw new ValidationException("无法取得相关信息:MatchResult[]=" + mrs);
		}
		List<Map<String, Object>> result = new ArrayList<Map<String, Object>>(mrs.size());
		for (MatchResult mr : mrs) {
			// 取得索引数据
			PersonIndex index = findPersonIndex(mr.getMpiPk());
			if (index == null) {
				throw new ValidationException("无法取得相关信息:PersonIndex=" + index);
			}
			Map<String, Object> map = new HashMap<String, Object>(2);
			map.put("result", mr);
			// 转码
			CodeConvertUtils.convert(index);
			map.put("index", index);
			result.add(map);
		}
		return result;
	}

	/**
	 * 根据人员id查询匹配的主索引列表
	 * 
	 * @param personId
	 * @return
	 */
	public List<Map<String, Object>> queryMatchIndex(String personId) {
		String sql = " select b.mpi_pk,b.name_cn,b.id_no,a.match_degree from mpi_match_result a left join "
				+ " mpi_person_index b on a.mpi_pk = b.mpi_pk where a.field_pk = ? order by a.match_degree desc ";
		return jdbcTemplate.queryForList(sql, new Object[] { personId });
	}

	/**
	 * 根据人员id查询匹配数
	 * 
	 * @param personId
	 * @return
	 */
	public int queryMatchIndexCount(String personId) {
		return matchResultDao.countByFieldPk(personId);
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
		return jdbcTemplate.queryForList(sql, new Object[] { personId });
	}

	/**
	 * 根据人员id查询详情
	 * 
	 * @param personId
	 * @return
	 */
	public Map<String, Object> queryPersonDetail(String personId) {
		Map<String, Object> result = new HashMap<String, Object>();
		// 取得居民数据
		PersonInfo person = findPersonInfo(personId);
		List<MatchDetailForm> fieldList = null;
		if (person != null) {
			// 转码
			CodeConvertUtils.convert(person);
			fieldList = buildField(person);
		}
		result.put("data", person);
		result.put("fields", fieldList);
		return result;
	}

	/**
	 * 查询人员-分页
	 * 
	 * @param t
	 * @param page
	 * @return
	 */
	public List<Map<String, Object>> queryPersonForMapPage(PersonInfo t, PageInfo page) {
		StringBuilder sql = new StringBuilder();
		sql.append(
				" select a.field_pk row_id,1 row_type,'' optype,c.domain_desc,a.name_cn,a.gender_cd,a.birth_date,a.id_no,a.person_tel_no, ")
				.append(" decode(nvl(d.opcount,0),0,'open','closed') \"state\", ")
				.append(" a.medical_insurance_no,a.nh_card,nvl(d.opcount,0) opcount from mpi_person_info a left join MPI_INDEX_IDENTIFIER_REL b ")
				.append(" on a.field_pk = b.field_pk left join mpi_identifier_domain c on b.domain_id = c.domain_id ")
				.append(" left join (select field_pk, count(person_idx_log_id) opcount from mpi_person_idx_log ")
				.append(" group by field_pk) d on d.field_pk = a.field_pk where 1=1 ");
		List<Object> args = buildQueryConditions(sql, t);
		String countSql = page.buildCountSql(sql);
		String querySql = page.buildPageSql(sql);
		int count = jdbcTemplate.queryForObject(countSql, args.toArray(), Integer.class);
		page.setTotal(count);
		return jdbcTemplate.queryForList(querySql, args.toArray());
	}

	/**
	 * 保存
	 * 
	 * @param t
	 */
	public void save(PersonIdxLog t) {
		personIdxLogDao.save(t);
	}

	/**
	 * 更新
	 * 
	 * @param t
	 */
	public void update(PersonIdxLog t) {
		personIdxLogDao.save(t);
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
	public void save(String person, String index, String domainId, String opType, String opStyle, String desc,
			String formermpipk) {
		PersonIdxLog result = new PersonIdxLog();
		result.setOpType(opType);
		result.setOpStyle(opStyle);
		result.setOpTime(DateUtil.getTimeNow(new Date()));
		result.setOpUserId("0");
		result.setOpDesc(desc);
		result.setInfoSour(domainId);
		result.setMpiPk(index);
		result.setFieldPk(person);
		result.setFormermpipk(formermpipk);
		// 自动标志
		save(result);
	}

}
