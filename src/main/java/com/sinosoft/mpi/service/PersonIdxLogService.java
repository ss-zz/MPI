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
import org.springframework.stereotype.Service;

import com.sinosoft.mpi.cache.CacheManager;
import com.sinosoft.mpi.context.QueryConditionType;
import com.sinosoft.mpi.dao.IIdentifierDomainDao;
import com.sinosoft.mpi.dao.IMatchResultDao;
import com.sinosoft.mpi.dao.IPersonIdxLogDao;
import com.sinosoft.mpi.dao.IPersonIndexDao;
import com.sinosoft.mpi.dao.IPersonInfoDao;
import com.sinosoft.mpi.exception.ValidationException;
import com.sinosoft.mpi.form.MatchDetailForm;
import com.sinosoft.mpi.model.IdentifierDomain;
import com.sinosoft.mpi.model.MatchResult;
import com.sinosoft.mpi.model.PerInfoPropertiesDesc;
import com.sinosoft.mpi.model.PersonIdxLog;
import com.sinosoft.mpi.model.PersonIndex;
import com.sinosoft.mpi.model.PersonInfo;
import com.sinosoft.mpi.model.PersonPropertiesDesc;
import com.sinosoft.mpi.util.CodeConvertUtils;
import com.sinosoft.mpi.util.NumberUtils;
import com.sinosoft.mpi.util.PageInfo;
import com.sinosoft.mpi.util.SqlUtils;

@Service("personIdxLogService")
public class PersonIdxLogService implements IPersonIdxLogService {
	private Logger logger = Logger.getLogger(PersonIdxLogService.class);

	@Resource
	private IIdentifierDomainDao identifierDomainDao;
	@Resource
	private IMatchResultDao matchResultDao;
	@Resource
	private IPersonIdxLogDao personIdxLogDao;
	@Resource
	private IPersonIndexDao personIndexDao;
	@Resource
	private IPersonInfoDao personInfoDao;
	@Resource
	private IPersonIndexService personIndexService;

	private void addconditions(final PersonIdxLog t, final StringBuilder sql,
			final List<Object> args) {
		SqlUtils.appendCondition(t.getOpType(), "a.op_type", sql, args,
				QueryConditionType.EQUAL);
		SqlUtils.appendCondition(t.getOpStyle(), "a.op_style", sql, args,
				QueryConditionType.EQUAL);
		SqlUtils.appendCondition(t.getOpTime(), "a.op_time", sql, args,
				QueryConditionType.GREATER_OR_EQUAL);
		SqlUtils.appendCondition(t.getOpTimeEnd(), "a.op_time", sql, args,
				QueryConditionType.LESS_OR_EQUAL);
		SqlUtils.appendCondition(t.getOpUserId(), "d.name", sql, args,
				QueryConditionType.LIKE);
		SqlUtils.appendCondition(t.getInfoSour(), "a.info_sour", sql, args,
				QueryConditionType.EQUAL);
		SqlUtils.appendCondition(t.getPersonName(), "c.name_cn", sql, args,
				QueryConditionType.LIKE);
		SqlUtils.appendCondition(t.getPersonIdcard(), "c.id_no", sql, args,
				QueryConditionType.EQUAL);
		
	}

	private List<MatchDetailForm> buildField(Object obj) {
		List<PerInfoPropertiesDesc> fields = CacheManager
				.getAll(PerInfoPropertiesDesc.class);
		List<MatchDetailForm> result = new ArrayList<MatchDetailForm>(
				fields.size());
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

	private List<MatchDetailForm> buildFieldMatch(MatchResult mr,
			PersonInfo person, PersonIndex index) {
		// 取得字段匹配串
		String fieldMatchStr = mr.getFieldMatDegrees();
		// 验证数据
		if (StringUtils.isBlank(fieldMatchStr))
			return Collections.emptyList();
		// 切分
		String[] fieldMatchs = fieldMatchStr.split(",");
		// 创建返回结果
		List<MatchDetailForm> result = new ArrayList<MatchDetailForm>(
				fieldMatchs.length);
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
			form.setMatchDegree(NumberUtils.toPercentStr(Double
					.parseDouble(degree)));
			// 设置居民表属性值
			form.setPersonValue(getValue(person, fieldName));
			// 设置索引表属性值
			form.setIndexValue(getValue(index, fieldName));
			// 放入list
			result.add(form);
		}

		return result;
	}

	private List<MatchDetailForm> buildFullField(PersonInfo person,
			PersonIndex index) {
		//personindex居民主索引
		List<PersonPropertiesDesc> fields = CacheManager
				.getAll(PersonPropertiesDesc.class);
		//personinfo居民信息
		List<PerInfoPropertiesDesc> infofields = CacheManager
				.getAll(PerInfoPropertiesDesc.class);
		List<MatchDetailForm> result = new ArrayList<MatchDetailForm>(
				fields.size());
		//遍历 居民主索引 字段
		for (PersonPropertiesDesc field : fields) {
			//遍历 居民信息 字段
			for (PerInfoPropertiesDesc infofield : infofields) {
				//如果字段相同显示
				if(infofield.getPropertyName()==field.getPropertyName()){
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

	private List<Object> buildQueryConditions(final StringBuilder sql,
			PersonInfo p) {
		List<Object> args = new ArrayList<Object>();
		SqlUtils.appendCondition(p.getID_NO(), "a.id_no", sql, args,
				QueryConditionType.EQUAL);
		SqlUtils.appendCondition(p.getID_NO_CD(), "a.id_no_cd", sql, args,
				QueryConditionType.EQUAL);
		SqlUtils.appendCondition(p.getID_NO(), "a.id_no", sql, args,
				QueryConditionType.EQUAL);
		SqlUtils.appendCondition(p.getMEDICAL_INSURANCE_NO(),
				"a.medical_insurance_no", sql, args, QueryConditionType.EQUAL);
		SqlUtils.appendCondition(p.getNH_CARD(), "a.nh_card", sql, args,
				QueryConditionType.EQUAL);
		SqlUtils.appendCondition(p.getNAME_CN(), "a.name", sql, args,
				QueryConditionType.LIKE);
		SqlUtils.appendCondition(p.getGENDER_CD(), "a.gender_cd", sql, args,
				QueryConditionType.EQUAL);
		SqlUtils.appendCondition(p.getBIRTH_DATE(), "a.birth_date", sql, args,
				QueryConditionType.LIKE);
		SqlUtils.appendCondition(p.getCARD_NATION_CD(), "a.card_nation_cd",
				sql, args, QueryConditionType.EQUAL);
		SqlUtils.appendCondition(p.getCARD_ED_BG_CD(), "a.card_ed_bg_cd", sql,
				args, QueryConditionType.EQUAL);
		SqlUtils.appendCondition(p.getCARD_MARITAL_ST_CD(),
				"a.card_marital_st_cd", sql, args, QueryConditionType.EQUAL);
		SqlUtils.appendCondition(p.getCARD_OCCU_TYPE_CD(),
				"a.card_occu_type_cd", sql, args, QueryConditionType.EQUAL);
		SqlUtils.appendCondition(p.getPERSON_TEL_NO(), "a.person_tel_no", sql,
				args, QueryConditionType.EQUAL);
		SqlUtils.appendCondition(p.getLIVING_ADDR(), "a.living_addr", sql,
				args, QueryConditionType.EQUAL);
		SqlUtils.appendCondition(p.getUNIQUE_SIGN(), "c.unique_sign", sql,
				args, QueryConditionType.EQUAL);
		SqlUtils.appendCondition(p.getFIELD_PK(), "a.field_pk", sql, args,
				QueryConditionType.EQUAL);
		return args;
	}

	@Override
	public void delete(PersonIdxLog t) {
		personIdxLogDao.deleteById(t);
		logger.debug("delete PersonIdxLog,personIdxLogId="
				+ t.getPersonIdxLogId());
	}

	private List<MatchResult> findMatchResult(String field_pk,
			final PageInfo page) {
		String sql = " select * from mpi_match_result where FIELD_PK=? order by match_degree desc ";
		// 构建分页查询sql
		String pageSql = page.buildPageSql(sql);
		// 构建总数查询sql
		String countSql = page.buildCountSql(sql);
		// 查询总数
		logger.debug("Execute sql:[" + countSql + "]\nparams:[" + field_pk
				+ "]");
		int count = matchResultDao
				.getCount(countSql, new Object[] { field_pk });
		// 总数信息设置到分页信息内
		page.setTotal(count);
		// 分页查询数据
		logger.debug("Execute sql:[" + pageSql + "]\nparams:[" + field_pk + "]");
		List<MatchResult> list = matchResultDao.find(pageSql,
				new Object[] { field_pk });
		return list;
	}

	private MatchResult findMatchResult(String mpi_pk, String field_pk) {
		String sql = " select * from mpi_match_result where MPI_PK=? and FIELD_PK=? order by match_degree desc ";
		List<MatchResult> list = matchResultDao.find(sql, new Object[] {
				mpi_pk, field_pk });
		MatchResult result = null;
		if (list != null && !list.isEmpty()) {
			result = list.get(0);
		}
		return result;
	}

	private List<MatchResult> findMatchResult(String personId, String[] idxIds) {
		StringBuilder sql = new StringBuilder(
				" select * from mpi_match_result where FIELD_PK=? and MPI_PK in ( ");
		for (String mpiPk : idxIds) {
			sql.append('\'').append(mpiPk).append("',");
		}
		sql.deleteCharAt(sql.length() - 1);
		sql.append(" ) order by match_degree desc ");
		// 分页查询数据
		logger.debug("Execute sql:[" + sql.toString() + "]\nparams:["
				+ personId + "]");
		List<MatchResult> list = matchResultDao.find(sql.toString(),
				new Object[] { personId });
		return list;
	}

	private PersonIndex findPersonIndex(String mpiPk) {
		PersonIndex p = new PersonIndex();
		p.setMPI_PK(mpiPk);
		p = personIndexDao.findById(p);
		if(p.getGENDER_CD() != null && !"".equals(p.getGENDER_CD()) && p.getGENDER_CD().length()<2){
			p.setGENDER_CD(p.getGENDER_CD().equals("1")?"男性":"女性");
		}
		return p;
	}

	private PersonInfo findPersonInfo(String personId) {
		PersonInfo p = new PersonInfo();
		p.setFIELD_PK(personId);
		p = personInfoDao.findWithDomainInfoById(p);
		if(p.getGENDER_CD() != null && !"".equals(p.getGENDER_CD()) && p.getGENDER_CD().length()<2){
			p.setGENDER_CD(p.getGENDER_CD().equals("1")?"男性":"女性");
		}
		return p;
	}

	private String getFieldCnName(String fieldName) {
		PersonPropertiesDesc desc = CacheManager.get(
				PersonPropertiesDesc.class, fieldName);
		return desc.getPropertyDesc();
	}

	@Override
	public PersonIdxLog getObject(String id) {
		PersonIdxLog t = new PersonIdxLog();
		t.setPersonIdxLogId(id);
		t = personIdxLogDao.findById(t);
		logger.debug("Load PersonIdxLog : personIdxLogId=" + id + ",result="
				+ t);
		return t;
	}

	private String getValue(Object obj, String fieldName) {
		String result = null;
		Object value = new Object();
		try {
			value = PropertyUtils.getProperty(obj, fieldName);
			if (value!=null&&value instanceof Date) {
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

	public List<MatchDetailForm> queryCompareData(String personId,
			String mpiPk) {
		// TODO
		// 取得 居民
		PersonInfo person = findPersonInfo(personId);
		// 取得 索引
		PersonIndex index = findPersonIndex(mpiPk);
		// 校验
		if (person == null || index == null) {
			logger.debug("无法取得相关信息:PersonInfo=" + person + ",\nPersonIndex="
					+ index);
			throw new ValidationException("无法取得相关信息:PersonInfo=" + person
					+ ",\nPersonIndex=" + index);
		}
		// 转码
		CodeConvertUtils.convert(person);
		CodeConvertUtils.convert(index);
		// 构建对比 why???
		List<MatchDetailForm> list = buildFullField(person, index);

		return list;
	}

	public List<Map<String, Object>> queryForMapPage(PersonIdxLog t,
			PageInfo page) {
		StringBuilder sql = new StringBuilder();
		/*sql.append(
				" select a.person_idx_log_id,a.op_time,a.op_type,a.op_style,a.op_desc,b.domain_desc,d.name from mpi_person_idx_log a ")
				.append(" left join mpi_identifier_domain b on a.info_sour = b.domain_id left join mpi_person_info c on ")
				.append(" a.field_pk = c.field_pk left join mpi_sys_user d on a.op_user_id = d.user_id where 1=1 ");*/
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
		page.setTotal(personIdxLogDao.getCount(countSql, args.toArray()));
		logger.debug("Execute sql:" + countSql);
		// 添加排序sql
		sql.append(" order by a.op_time desc ");
		// 取得分页查询语句
		String querySql = page.buildPageSql(sql);
		logger.debug("Execute sql:" + querySql);
		return personIdxLogDao.findForMap(querySql, args.toArray());
	}

	@Override
	public List<PersonIdxLog> queryForPage(PersonIdxLog t, PageInfo page) {
		// XXX ben 实际应用的时候这里需要添加查询条件
		String sql = " select * from mpi_person_idx_log where 1=1 ";
		String countSql = page.buildCountSql(sql);
		page.setTotal(personIdxLogDao.getCount(countSql));
		logger.debug("Execute sql:" + countSql);
		sql = page.buildPageSql(sql);
		logger.debug("Execute sql:" + sql);
		return personIdxLogDao.find(sql, new Object[] {});
	}

	@Override
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
	
	@Override
	public List<Map<String, Object>> queryIndexDetails(String mpiPk) {
		List<Map<String, Object>> result = new ArrayList<Map<String, Object>>();
		//被合并的主索引集合
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
	 * @date 2012年12月4日
	 * @param obj
	 * @return List<MatchDetailForm>
	 */
	private List<MatchDetailForm> buildFieldByIndex(Object obj) {
		List<PersonPropertiesDesc> fields = CacheManager
				.getAll(PersonPropertiesDesc.class);
		List<MatchDetailForm> result = new ArrayList<MatchDetailForm>(
				fields.size());
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
	@Override
	public Map<String, Object> queryMatchDetail(String logId) {
		// 校验数据
		if (StringUtils.isBlank(logId)) {
			logger.debug("参数为空:logId=" + logId);
			throw new ValidationException("参数为空:logId=" + logId);
		}

		Map<String, Object> result = new HashMap<String, Object>();

		// 取得 索引日志
		PersonIdxLog log = getObject(logId);
		// 取得域信息
		if (StringUtils.isNotBlank(log.getInfoSour())) {
			IdentifierDomain domain = new IdentifierDomain();
			domain.setDOMAIN_ID(log.getInfoSour());
			domain = identifierDomainDao.findById(domain);
			log.setInfoSour(domain.getDOMAIN_DESC());
		}
/*
		// 取得居民数据
		PersonInfo person = findPersonInfo(log.getFieldpk());
		// 取得索引数据
		PersonIndex index = findPersonIndex(log.getMpipk());
		if (log == null || person == null || index == null) {
			logger.debug("无法取得相关信息:PersonIdxLog=" + log + ",\nPersonInfo="
					+ person + ",\nPersonIndex=" + index);
			throw new ValidationException("无法取得相关信息:PersonIdxLog=" + log
					+ ",\nPersonInfo=" + person + ",\nPersonIndex=" + index);
		}*/
		if (log == null ){
			logger.debug("无法取得相关信息:PersonIdxLog=" + log );
			throw new ValidationException("无法取得相关信息:PersonIdxLog=" + log);
		}
		// 取得居民数据
		PersonInfo person;
		// 取得索引数据
		PersonIndex index;
		person= findPersonInfo(log.getFieldpk());
		if(person.getGENDER_CD() != null && !"".equals(person.getGENDER_CD()) && person.getGENDER_CD().length()<2){
			person.setGENDER_CD(person.getGENDER_CD().equals("1")?"男性":"女性");
		}
		index = findPersonIndex(log.getMpipk());
		if(index.getGENDER_CD() != null && !"".equals(index.getGENDER_CD()) && person.getGENDER_CD().length()<2){
			index.setGENDER_CD(index.getGENDER_CD().equals("1")?"男性":"女性");
		}
		
		if ( person == null) {
			person=new PersonInfo();
		}
		if(index==null){
			index =new PersonIndex();
		}
		// 转码
		CodeConvertUtils.convert(index);
		// 转码
		CodeConvertUtils.convert(person);
		// 取得匹配结果
		MatchResult mr = findMatchResult(log.getMpipk(), log.getFieldpk());

		List<MatchDetailForm> matchField = null;
		if (mr != null) {
			// 匹配度转百分数
			mr.setMatchDegree(NumberUtils.toPercentStr(Double.parseDouble(mr
					.getMatchDegree())));
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

	@Override
	public List<Map<String, Object>> queryMatchDetailPage(String personId,
			int start, int end) {

		// 创建分页信息,每次查询出一条匹配记录
		PageInfo page = PageInfo.getByStartAndEnd(start, end);
		// 取得匹配数据
		List<MatchResult> mrs = findMatchResult(personId, page);
		if (mrs == null || mrs.isEmpty()) {
			logger.debug("无法取得相关信息:MatchResult[]=" + mrs);
			throw new ValidationException("无法取得相关信息:MatchResult[]=" + mrs);
		}
		List<Map<String, Object>> result = new ArrayList<Map<String, Object>>(
				mrs.size());
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

	@Override
	public List<Map<String, Object>> queryMatchDetailPageByIdxIds(
			String personId, String[] idxIds) {

		// 取得匹配数据
		List<MatchResult> mrs = findMatchResult(personId, idxIds);
		if (mrs == null || mrs.isEmpty()) {
			logger.debug("无法取得相关信息:MatchResult[]=" + mrs);
			throw new ValidationException("无法取得相关信息:MatchResult[]=" + mrs);
		}
		List<Map<String, Object>> result = new ArrayList<Map<String, Object>>(
				mrs.size());
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

	@Override
	public List<Map<String, Object>> queryMatchIndex(String personId) {
		String sql = " select b.mpi_pk,b.name_cn,b.id_no,a.match_degree from mpi_match_result a left join "
				+ " mpi_person_index b on a.mpi_pk = b.mpi_pk where a.field_pk = ? order by a.match_degree desc ";
		List<Map<String, Object>> result = matchResultDao.findForMap(sql,
				new Object[] { personId });
		logger.debug("Execute sql[" + sql + "],params[" + personId + "]");
		return result;
	}

	@Override
	public int queryMatchIndexCount(String personId) {
		String sql = " select count(*) from mpi_match_result where FIELD_PK=? ";
		return matchResultDao.getCount(sql, new Object[] { personId });
	}

	@Override
	public List<Map<String, Object>> queryOpLogByPersonId(String personId) {
		String sql = "select a.person_idx_log_id row_id,2 row_type,a.index_id,a.op_type optype,a.op_style,a.op_time domain_desc,b.name,b.gender_cd, "
				// +
				// " b.birth_date,b.id_no,b.phone_one,'open' \"state\",b.health_record_num,b.nh_card,'' opcount "
				+ " b.birth_date,b.id_no,b.person_tel_no,'open' \"state\",b.nh_card,'' opcount "
				+ " from mpi_person_idx_log a left join mpi_person_index b on a.index_id = b.index_id where 1=1 and a.mpi_person_id = ? "
				+ " order by a.op_time desc ";
		logger.debug("Execute sql:[" + sql + "],params:[" + personId + "]");
		return personIdxLogDao.findForMap(sql, new Object[] { personId });
	}

	@Override
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

	@Override
	public List<Map<String, Object>> queryPersonForMapPage(PersonInfo t,
			PageInfo page) {
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
		logger.debug("Execute sql:[" + countSql + "],params:["
				+ args.toString() + "]");
		int count = personInfoDao.getCount(countSql, args.toArray());
		page.setTotal(count);
		logger.debug("Execute sql:[" + querySql + "],params:["
				+ args.toString() + "]");
		return personIdxLogDao.findForMap(querySql, args.toArray());
	}

	@Override
	public void save(PersonIdxLog t) {
		personIdxLogDao.add(t);
		logger.debug("Add PersonIdxLog:" + t);
	}

	@Override
	public void update(PersonIdxLog t) {
		personIdxLogDao.update(t);
		logger.debug("update PersonIdxLog:" + t);
	}

	public void setIdentifierDomainDao(IIdentifierDomainDao identifierDomainDao) {
		this.identifierDomainDao = identifierDomainDao;
	}

	public void setMatchResultDao(IMatchResultDao matchResultDao) {
		this.matchResultDao = matchResultDao;
	}

	public void setPersonIdxLogDao(IPersonIdxLogDao personIdxLogDao) {
		this.personIdxLogDao = personIdxLogDao;
	}

	public void setPersonIndexDao(IPersonIndexDao personIndexDao) {
		this.personIndexDao = personIndexDao;
	}

	public void setPersonInfoDao(IPersonInfoDao personInfoDao) {
		this.personInfoDao = personInfoDao;
	}
	
}
