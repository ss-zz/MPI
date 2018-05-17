package com.sinosoft.mpi.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.springframework.data.domain.Page;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import com.sinosoft.mpi.dao.mpi.DomainSrcLevelDao;
import com.sinosoft.mpi.model.DomainSrcLevel;
import com.sinosoft.mpi.util.PageInfo;

/**
 * 域源级别服务
 */
@Service
public class DomainSrcLevelService {

	@Resource
	private DomainSrcLevelDao domainSrcLevelDao;

	/**
	 * 对象缓存-根据域id获取数据
	 */
	private Map<String, List<DomainSrcLevel>> TMP_DOMAINID_DATA = new HashMap<String, List<DomainSrcLevel>>();

	/**
	 * 保存
	 * 
	 * @param t
	 */
	public void save(DomainSrcLevel t) {
		domainSrcLevelDao.save(t);
	}

	/**
	 * 更新
	 * 
	 * @param t
	 */
	public void update(DomainSrcLevel t) {
		domainSrcLevelDao.save(t);
	}

	/**
	 * 删除
	 * 
	 * @param t
	 */
	public void delete(DomainSrcLevel t) {
		domainSrcLevelDao.delete(t);
	}

	/**
	 * 根据id获取
	 * 
	 * @param id
	 * @return
	 */
	public DomainSrcLevel getObject(String id) {
		return domainSrcLevelDao.findOne(id);
	}

	/**
	 * 分页查询
	 * 
	 * @param t
	 * @param page
	 * @return
	 */
	public Page<DomainSrcLevel> queryForPage(DomainSrcLevel t, PageInfo page) {
		return domainSrcLevelDao.findAll(new Specification<DomainSrcLevel>() {
			@Override
			public Predicate toPredicate(Root<DomainSrcLevel> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
				return null;
			}
		}, page);
	}

	/**
	 * 根据id和字段名查询
	 * 
	 * @param srclevel
	 * @return
	 */
	public DomainSrcLevel queryByDomainIdAndFieldName(String domainId, String fieldName) {
		List<DomainSrcLevel> datas = domainSrcLevelDao.findByDomainIdAndFieldName(domainId, fieldName);
		return datas.size() > 0 ? datas.get(0) : null;
	}

	/**
	 * 根据域id查询
	 * 
	 * @param domainid
	 * @return
	 */
	public List<DomainSrcLevel> queryByDomainID(String domainid) {
		if (domainid == null) {
			return new ArrayList<DomainSrcLevel>();
		}
		if (TMP_DOMAINID_DATA.containsKey(domainid)) {
			return TMP_DOMAINID_DATA.get(domainid);
		} else {
			List<DomainSrcLevel> datas = domainSrcLevelDao.findByDomainId(domainid);
			TMP_DOMAINID_DATA.put(domainid, datas);
			return datas;
		}

	}

	/**
	 * 根据域id分页查询
	 * 
	 * @param t
	 * @param page
	 * @return
	 */
	public Page<DomainSrcLevel> queryPageByID(final String domainid, PageInfo page) {
		return domainSrcLevelDao.findAll(new Specification<DomainSrcLevel>() {
			@Override
			public Predicate toPredicate(Root<DomainSrcLevel> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
				return cb.and(cb.equal(root.get("domainId"), domainid));
			}
		}, page);
	}

	/**
	 * 查询所有
	 * 
	 * @return
	 */
	public List<DomainSrcLevel> queryAll() {
		return domainSrcLevelDao.findAll();
	}

}
