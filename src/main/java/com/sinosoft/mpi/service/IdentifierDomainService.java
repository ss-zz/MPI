package com.sinosoft.mpi.service;

import java.util.List;

import javax.annotation.Resource;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.apache.commons.lang.StringUtils;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

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
		if (testDomain(t.getDomainId(), t.getUniqueSign())) {
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
	public void update(IdentifierDomain t) {
		if (t == null)
			return;
		if (testDomain(t.getDomainId(), t.getUniqueSign())) {
			IdentifierDomain tmp = identifierDomainDao.findOne(t.getDomainId());
			tmp.setDomainDesc(t.getDomainDesc());
			tmp.setDomainType(t.getDomainType());
			tmp.setUniqueSign(t.getUniqueSign());
			tmp.setPushAddr(t.getPushAddr());
			tmp.setBookType(t.getBookType());
			tmp.setDomainLevel(t.getDomainLevel());
			identifierDomainDao.save(tmp);
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
	public List<IdentifierDomain> queryForPage(IdentifierDomain t, PageInfo page) {
		return identifierDomainDao.findAll(new Specification<IdentifierDomain>() {
			@Override
			public Predicate toPredicate(Root<IdentifierDomain> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
				return null;
			}
		}, page).getContent();
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
	 * @param domainId
	 * @param uniqueSign
	 * @return
	 */
	public boolean testDomain(String domainId, String uniqueSign) {
		int count = 0;
		if (StringUtils.isNotBlank(domainId)) {
			count = identifierDomainDao.countByUniqueSignAndDomainId(uniqueSign, domainId);
		} else {
			count = identifierDomainDao.countByUniqueSign(uniqueSign);
		}
		return count == 0;
	}

	/**
	 * 查询待推送的域
	 * 
	 * @return
	 */
	public List<IdentifierDomain> queryPushDomain() {
		return identifierDomainDao.findByBookType("0");
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
