package com.sinosoft.mpi.service;

import java.util.List;

import javax.annotation.Resource;

import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Service;

import com.sinosoft.mpi.dao.IdentifierDomainDao;
import com.sinosoft.mpi.exception.ValidationException;
import com.sinosoft.mpi.model.IdentifierDomain;
import com.sinosoft.mpi.util.PageInfo;

/**
 * 域服务
 */
@Service
public class IdentifierDomainService {

	private Logger logger = Logger.getLogger(IdentifierDomainService.class);

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
		if (testDomain(t.getDOMAIN_ID(), t.getUNIQUE_SIGN())) {
			identifierDomainDao.add(t);
		} else {
			throw new ValidationException("身份域唯一标识:" + t.getUNIQUE_SIGN() + "已存在");
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
		if (testDomain(t.getDOMAIN_ID(), t.getUNIQUE_SIGN())) {
			IdentifierDomain tmp = identifierDomainDao.findById(t);
			tmp.setDOMAIN_DESC(t.getDOMAIN_DESC());
			tmp.setDOMAIN_TYPE(t.getDOMAIN_TYPE());
			tmp.setUNIQUE_SIGN(t.getUNIQUE_SIGN());
			tmp.setPUSH_ADDR(t.getPUSH_ADDR());
			tmp.setBOOK_TYPE(t.getBOOK_TYPE());
			tmp.setDOMAIN_LEVEL(t.getDOMAIN_LEVEL());
			identifierDomainDao.update(tmp);
		} else {
			throw new ValidationException("身份域唯一标识:" + t.getUNIQUE_SIGN() + "已存在");
		}
	}

	/**
	 * 删除
	 * 
	 * @param t
	 */
	public void delete(IdentifierDomain t) {
		identifierDomainDao.deleteById(t);
	}

	/**
	 * 根据id获取
	 * 
	 * @param id
	 * @return
	 */
	public IdentifierDomain getObject(String id) {
		IdentifierDomain t = new IdentifierDomain();
		t.setDOMAIN_ID(id);
		return identifierDomainDao.findById(t);
	}

	/**
	 * 分页查询
	 * 
	 * @param t
	 * @param page
	 * @return
	 */
	public List<IdentifierDomain> queryForPage(IdentifierDomain t, PageInfo page) {
		String sql = " select * from mpi_identifier_domain where 1=1 ";
		// 取得总条数sql
		String countSql = page.buildCountSql(sql);
		logger.debug("Execute sql:[" + countSql + "],params[]");
		// 设置总条数
		page.setTotal(identifierDomainDao.getCount(countSql, new Object[] {}));
		sql = page.buildPageSql(sql);
		logger.debug("Execute sql:[" + sql + "],params[]");
		return identifierDomainDao.find(sql, new Object[] {});
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
		StringBuilder sql = new StringBuilder();
		Object[] args = null;
		sql.append(" select count(*) from mpi_identifier_domain where unique_sign = ? ");
		if (StringUtils.isNotBlank(domainId)) {
			sql.append(" and domain_id != ?");
			args = new Object[] { uniqueSign, domainId };
		} else {
			args = new Object[] { uniqueSign };
		}
		int count = identifierDomainDao.getCount(sql.toString(), args);

		return count == 0;
	}

	/**
	 * 查询待推送的域
	 * 
	 * @return
	 */
	public List<IdentifierDomain> queryPushDomain() {
		String sql = "select * from mpi_identifier_domain where book_type = '0'";
		return identifierDomainDao.find(sql);
	}

	/**
	 * 根据唯一标识查询
	 * 
	 * @param unique_sign
	 * @return
	 */
	public List<IdentifierDomain> queryByDomianId(String unique_sign) {
		String sql = " select * from mpi_identifier_domain  where unique_sign=?";
		return identifierDomainDao.find(sql, new Object[] { unique_sign });
	}

}
