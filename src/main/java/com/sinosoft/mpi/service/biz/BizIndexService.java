package com.sinosoft.mpi.service.biz;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.cache.annotation.CacheDefaults;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import com.sinosoft.mpi.context.QueryConditionType;
import com.sinosoft.mpi.dao.biz.MpiBizIndexDao;
import com.sinosoft.mpi.model.biz.MpiBizIdxLog;
import com.sinosoft.mpi.model.biz.MpiBizIndex;
import com.sinosoft.mpi.model.biz.MpiBizInfo;
import com.sinosoft.mpi.util.PageInfo;
import com.sinosoft.mpi.util.SqlUtils;

import javax.persistence.criteria.Path;
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
	 * 删除
	 * 
	 * @param t
	 */
	public void delete(MpiBizIndex t) {
		bizIndexDao.delete(t);
	}

	/**
	 * 删除
	 * 
	 * @param id
	 */
	public void deleteById(String id) {
		bizIndexDao.delete(id);
	}

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
	 * 分页查询
	 * 
	 * @param t
	 * @param page
	 * @return
	 */
	public Page<MpiBizIndex> queryForPage(final MpiBizIndex t,PageInfo page) {
		return bizIndexDao.findAll(new Specification<MpiBizIndex>() {
			@Override
			public Predicate toPredicate(Root<MpiBizIndex> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
				 List<Predicate> predicates = new ArrayList<>();
	                if(null != t.getBizInpatientSerialno()){
	                    predicates.add(cb.equal(root.get("bizInpatientSerialno"), t.getBizInpatientSerialno()));
	                }
	                if(null != t.getBizSystemId()){
	                    predicates.add(cb.equal(root.get("bizSystemId"), t.getBizSystemId()));
	                }
	                if(null != t.getCreate_Date()){
	                    predicates.add(cb.equal(root.get("create_Date"), t.getCreate_Date()));
	                }
	                return cb.and(predicates.toArray(new Predicate[predicates.size()]));
			}
		}, page);
	}
	
	

	/**
	 * 保存
	 * 
	 * @param t
	 */
	public MpiBizIndex save(MpiBizIndex t) {
		return bizIndexDao.save(t);
	}

	/**
	 * 直接更新 索引信息
	 */
	public MpiBizIndex updateIndexDirect(MpiBizInfo person, String id) {
		person.setId(id);
		return bizIndexDao.save(person.toIndex());
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
	 * 根据sql以及参数查询列表
	 * 
	 * @param sql
	 * @param args
	 * @return
	 */
	public List<MpiBizIndex> find(String sql, Object[] args) {
		return jdbcTemplate.queryForList(sql, args, MpiBizIndex.class);
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
		sql.append("select b.BIZ_ID as bizId,b.BIZ_PATIENT_ID as bizPatientId,b.BIZ_SERIAL_ID as bizSerialId,b.BIZ_INPATIENTNO as bizInpatientno,b.BIZ_INPATIENT_SERIALNO as bizInpatientSerialno, b.BIZ_CLINICNO as bizClinicno,b.BIZ_CLINIC_SERIALNO as bizClinicSerialno,b.CREATE_DATE as create_Date,b.STATE as state,b.REMARK as remark,b.ID as id,b.BIZ_SYSTEM_ID as bizSystemId,d.DOMAIN_DESC as domainName,i.name_cn as uName from mpi_biz_index b")
				.append(" left join mpi_identifier_domain d on b.BIZ_SYSTEM_ID = d.domain_id ")
				.append(" left join mpi_person_info i on i.field_pk = b.biz_patient_id where 1=1 ");
		
			if(t.getCreate_Date() != null){
				sql.append("and b.CREATE_DATE = TO_DATE('"+sdf.format(t.getCreate_Date()).toString()+"','yyyy-mm-dd') ");
			}
			/*if(t.getBlTime_end() != null && t.getBlTime_begin() == null){
				sql.append("and l.bl_time <= TO_DATE('"+sdf.format(sdf.parse(t.getBlTime_end())).toString()+"','yyyy-mm-dd') ");
			}
			if(t.getBlTime_begin() != null && t.getBlTime_end() !=null){
				sql.append("and l.bl_time >= TO_DATE('"+sdf.format(sdf.parse(t.getBlTime_begin())).toString()+"','yyyy-mm-dd') and l.bl_time <= TO_DATE('"+sdf.format(sdf.parse(t.getBlTime_end())).toString()+"','yyyy-mm-dd') ");
			}*/
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
	private void addconditions(final MpiBizIndex t, final StringBuilder sql, final List<Object> args){
		SqlUtils.appendCondition(t.getBizInpatientSerialno(), "b.BIZ_INPATIENT_SERIALNO", sql, args, QueryConditionType.EQUAL);
		SqlUtils.appendCondition(t.getBizSystemId(), "b.BIZ_SYSTEM_ID", sql, args, QueryConditionType.EQUAL);
		SqlUtils.appendCondition(t.getBizClinicSerialno(), "b.BIZ_CLINIC_SERIALNO", sql, args, QueryConditionType.EQUAL);
	}

}
