package com.sinosoft.mpi.service.biz;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.cache.annotation.CacheDefaults;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import com.sinosoft.mpi.dao.biz.MpiBizIndexDao;
import com.sinosoft.mpi.model.biz.MpiBizIndex;
import com.sinosoft.mpi.model.biz.MpiBizInfoRegister;
import com.sinosoft.mpi.model.search.BizSearchResult;
import com.sinosoft.mpi.util.PageInfo;

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
	public Page<MpiBizIndex> queryForPage(final MpiBizIndex t, PageInfo page) {
		return bizIndexDao.findAll(new Specification<MpiBizIndex>() {
			@Override
			public Predicate toPredicate(Root<MpiBizIndex> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
				List<Predicate> predicates = new ArrayList<>();
				if (null != t.getBizInpatientSerialno()) {
					predicates.add(cb.equal(root.get("bizInpatientSerialno"), t.getBizInpatientSerialno()));
				}
				if (null != t.getBizSystemId()) {
					predicates.add(cb.equal(root.get("bizSystemId"), t.getBizSystemId()));
				}
				if (null != t.getCreateDate()) {
					predicates.add(cb.equal(root.get("createDate"), t.getCreateDate()));
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
		t.setCreateDate(new Date());
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
		return jdbcTemplate.query(sql, args, new BeanPropertyRowMapper<MpiBizIndex>(MpiBizIndex.class));
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
