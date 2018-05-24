package com.sinosoft.mpi.service.biz;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.cache.annotation.CacheDefaults;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import com.sinosoft.mpi.context.QueryConditionType;
import com.sinosoft.mpi.dao.biz.MpiBizIndexDao;
import com.sinosoft.mpi.model.biz.MpiBizIndex;
import com.sinosoft.mpi.model.biz.MpiBizInfoRegister;
import com.sinosoft.mpi.model.search.BizSearchResult;
import com.sinosoft.mpi.util.PageInfo;
import com.sinosoft.mpi.util.SqlUtils;

/**
 * 主索引业务服务
 */
@Service
@CacheDefaults(cacheName = "bizCache")
public class BizIndexService {

	@Autowired
	private MpiBizIndexDao bizIndexDao;
	@Autowired
	JdbcTemplate jdbcTemplate;

	/**
	 * 根据id获取
	 * 
	 * @param id
	 * @return
	 */
	public MpiBizIndex getObject(String id) {
		return bizIndexDao.getOne(id);
	}

	/**
	 * 保存
	 * 
	 * @param t
	 */
	public MpiBizIndex save(MpiBizIndex t) {
		t.setCreateDate(new Date());
		return bizIndexDao.save(t);
	}

	/**
	 * 更新
	 * 
	 * @param t
	 */
	public MpiBizIndex update(MpiBizIndex t) {
		return bizIndexDao.save(t);
	}

	/**
	 * 直接更新 索引信息
	 */
	public MpiBizIndex updateIndexDirect(MpiBizInfoRegister person, String id) {
		MpiBizIndex index = person.toIndex();
		index.setId(id);
		return bizIndexDao.save(index);
	}

	/**
	 * 根据sql以及参数查询列表
	 * 
	 * @param sql
	 * @param args
	 * @return
	 */
	public List<MpiBizIndex> find(String sql, Object[] args) {
		return jdbcTemplate.query(sql, args, new BeanPropertyRowMapper<MpiBizIndex>(MpiBizIndex.class));
	}

	/**
	 * 分页查询
	 * 
	 * @param t
	 * @param page
	 * @return
	 */
	public List<Map<String, Object>> queryForMapPage(MpiBizIndex t, PageInfo page) {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		StringBuilder sql = new StringBuilder();
		sql.append(
				"select b.BIZ_ID as bizId,b.BIZ_PATIENT_ID as bizPatientId,b.BIZ_SERIAL_ID as bizSerialId,b.BIZ_INPATIENTNO as bizInpatientno,b.BIZ_INPATIENT_SERIALNO as bizInpatientSerialno, b.BIZ_CLINICNO as bizClinicno,b.BIZ_CLINIC_SERIALNO as bizClinicSerialno,b.CREATE_DATE as create_Date,b.STATE as state,b.REMARK as remark,b.ID as id,b.BIZ_SYSTEM_ID as bizSystemId,d.DOMAIN_DESC as domainName,i.name_cn as uName from mpi_biz_index b")
				.append(" left join mpi_identifier_domain d on b.BIZ_SYSTEM_ID = d.domain_id ")
				.append(" left join mpi_person_info i on i.field_pk = b.biz_patient_id where 1=1 ");

		if (t.getCreateDate() != null) {
			sql.append("and b.CREATE_DATE = TO_DATE('" + sdf.format(t.getCreateDate()).toString() + "','yyyy-mm-dd') ");
		}
		List<Object> args = new ArrayList<Object>();
		// 添加查询条件
		addconditions(t, sql, args);

		// 取得总数查询sql
		String countSql = page.buildCountSql(sql);
		// 查询设置分页记录的总记录数
		page.setTotal(jdbcTemplate.queryForObject(countSql, args.toArray(), Integer.class));
		// 添加排序sql
		sql.append(" order by CREATE_DATE desc ");
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
	private void addconditions(final MpiBizIndex t, final StringBuilder sql, final List<Object> args) {
		// 门诊号
		SqlUtils.appendCondition(t.getBizClinicno(), "b.BIZ_CLINICNO", sql, args, QueryConditionType.EQUAL);
		// 住院号
		SqlUtils.appendCondition(t.getBizInpatientno(), "b.BIZ_INPATIENTNO", sql, args, QueryConditionType.EQUAL);
		// 次id
		SqlUtils.appendCondition(t.getBizSystemId(), "b.BIZ_SYSTEM_ID", sql, args, QueryConditionType.EQUAL);
	}

	/**
	 * 根据人员主索引id查询关联的业务信息
	 * 
	 * @param mpiId
	 *            人员主索引id
	 * @return
	 */
	public List<BizSearchResult> searchBizsByMpiId(String mpiId) {
		return jdbcTemplate.query(
				" select t.biz_id,t.biz_patient_id,t.biz_serial_id,t.biz_system_id"
						+ " from mpi_biz_index t left join mpi_person_info p on t.biz_patient_id = p.patient_id "
						+ " left join mpi_index_identifier_rel r on r.field_pk = p.field_pk where r.mpi_pk = ?",
				new Object[] { mpiId }, new BeanPropertyRowMapper<BizSearchResult>(BizSearchResult.class));
	}

	/**
	 * 根据人员主索引id和业务唯一标示查询关联的业务信息
	 * 
	 * @param mpiId
	 *            人员主索引id
	 * @param systemId
	 *            业务系统唯一标识
	 * @return
	 */
	public List<BizSearchResult> searchBizsByMpiIdAndSystemId(String mpiId, String systemId) {
		return jdbcTemplate.query(" select t.biz_id,t.biz_patient_id,t.biz_serial_id,t.biz_system_id"
				+ " from mpi_biz_index t left join mpi_person_info p on t.biz_patient_id = p.patient_id "
				+ " left join mpi_index_identifier_rel r on r.field_pk = p.field_pk where r.mpi_pk = ? and t.biz_system_id = ? ",
				new Object[] { mpiId, systemId }, new BeanPropertyRowMapper<BizSearchResult>(BizSearchResult.class));
	}

}
