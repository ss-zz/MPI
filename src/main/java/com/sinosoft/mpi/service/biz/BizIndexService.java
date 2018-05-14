package com.sinosoft.mpi.service.biz;

import java.util.List;

import javax.cache.annotation.CacheDefaults;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import com.sinosoft.mpi.dao.biz.MpiBizIndexDao;
import com.sinosoft.mpi.model.biz.MpiBizIndex;
import com.sinosoft.mpi.model.biz.MpiBizInfo;
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
	public List<MpiBizIndex> queryForPage(final MpiBizIndex t, String fromIndexId, PageInfo page) {
		return bizIndexDao.findAll(new Specification<MpiBizIndex>() {
			@Override
			public Predicate toPredicate(Root<MpiBizIndex> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
				if (t != null) {
					return cb.and(cb.equal(root.get("bizSerialId"), t.getBizSerialId()));
				}
				return null;
			}
		}, page).getContent();
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

}
