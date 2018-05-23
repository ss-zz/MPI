package com.sinosoft.mpi.service;

import java.util.List;

import javax.annotation.Resource;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.springframework.data.domain.Page;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.sinosoft.mpi.dao.mpi.IdentifierDomainDao;
import com.sinosoft.mpi.exception.ValidationException;
import com.sinosoft.mpi.model.IdentifierDomain;
import com.sinosoft.mpi.util.PageInfo;

/**
 * 域服务
 */
@Service
public class IdentifierDomainService {

	@Resource
	private IdentifierDomainDao identifierDomainDao;

	/**
	 * 保存
	 * 
	 * @param t
	 */
	public void save(IdentifierDomain t) {
		if (t == null)
			return;
		if (testDomain(t.getUniqueSign())) {
			// 设置主键与唯一标识相同
			t.setDomainId(t.getUniqueSign());
			identifierDomainDao.save(t);
		} else {
			throw new ValidationException("身份域唯一标识:" + t.getUniqueSign() + "已存在");
		}
	}

	/**
	 * 更新
	 * 
	 * @param t
	 */
	@Transactional
	public void update(IdentifierDomain t) {
		if (t == null)
			return;
		if (testDomain(t.getUniqueSign(), t.getDomainId())) {
			identifierDomainDao.updatePart(t.getDomainDesc(), t.getUniqueSign(), t.getDomainLevel(), t.getDomainId());
		} else {
			throw new ValidationException("业务系统唯一标识:" + t.getUniqueSign() + "已存在");
		}
	}

	/**
	 * 删除
	 * 
	 * @param t
	 */
	public void delete(IdentifierDomain t) {
		identifierDomainDao.delete(t);
	}

	/**
	 * 根据id获取
	 * 
	 * @param id
	 * @return
	 */
	public IdentifierDomain getObject(String id) {
		return identifierDomainDao.findOne(id);
	}

	/**
	 * 分页查询
	 * 
	 * @param t
	 * @param page
	 * @return
	 */
	public Page<IdentifierDomain> queryForPage(IdentifierDomain t, PageInfo page) {
		return identifierDomainDao.findAll(new Specification<IdentifierDomain>() {
			@Override
			public Predicate toPredicate(Root<IdentifierDomain> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
				return null;
			}
		}, page);
	}

	/**
	 * 查询所有
	 * 
	 * @return
	 */
	public List<IdentifierDomain> queryAll() {
		return identifierDomainDao.findAll();
	}

	/**
	 * 验证域是否存在
	 * 
	 * @param uniqueSign
	 * @return
	 */
	public boolean testDomain(String uniqueSign) {
		return identifierDomainDao.countByUniqueSign(uniqueSign) == 0;
	}

	/**
	 * 验证域是否存在
	 * 
	 * @param uniqueSign
	 * @param domainId
	 * @return
	 */
	public boolean testDomain(String uniqueSign, String domainId) {
		return identifierDomainDao.countByUniqueSignAndDomainIdNot(uniqueSign, domainId) == 0;
	}

	/**
	 * 根据uniqueSign查询
	 * 
	 * @param uniqueSign
	 * @return
	 */
	public List<IdentifierDomain> queryByUniqueSign(String uniqueSign) {
		return identifierDomainDao.findByUniqueSign(uniqueSign);
	}

	/**
	 * 根据人员id获取数据
	 * 
	 * @param personId
	 * @return
	 */
	public IdentifierDomain getByPersonId(String personId) {
		List<IdentifierDomain> items = identifierDomainDao.getByPersonId(personId);
		return items.size() > 0 ? items.get(0) : null;
	}

	/**
	 * 根据uniqueSign查询
	 * 
	 * @param uniqueSign
	 * @return
	 */
	public IdentifierDomain getByUniqueSign(String uniqueSign) {
		return identifierDomainDao.findFirstByUniqueSign(uniqueSign);
	}

}
