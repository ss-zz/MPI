package com.sinosoft.mpi.service;

import java.util.List;

import javax.annotation.Resource;

import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Service;

import com.sinosoft.mpi.dao.IIdentifierDomainDao;
import com.sinosoft.mpi.exception.ValidationException;
import com.sinosoft.mpi.model.IdentifierDomain;
import com.sinosoft.mpi.model.SysRole;
import com.sinosoft.mpi.util.PageInfo;


@Service("identifierDomainService")
public class IdentifierDomainService implements IIdentifierDomainService {
	private Logger logger = Logger.getLogger(IdentifierDomainService.class);
	@Resource
	private IIdentifierDomainDao identifierDomainDao;

	@Override
	public void save(IdentifierDomain t) {
		if(testDomain(t)){
			identifierDomainDao.add(t);
			logger.debug("Add IdentifierDomain:"+t);
		}else{
			logger.debug("身份域唯一标识:"+t.getUNIQUE_SIGN()+"已存在");
			throw new ValidationException("身份域唯一标识:"+t.getUNIQUE_SIGN()+"已存在");
		}		
	}

	@Override
	public void update(IdentifierDomain t) {
		if(testDomain(t)){
			IdentifierDomain tmp = identifierDomainDao.findById(t);
			tmp.setDOMAIN_DESC(t.getDOMAIN_DESC());
			tmp.setDOMAIN_TYPE(t.getDOMAIN_TYPE());
			tmp.setUNIQUE_SIGN(t.getUNIQUE_SIGN());
			tmp.setPUSH_ADDR(t.getPUSH_ADDR());
			tmp.setBOOK_TYPE(t.getBOOK_TYPE());
			tmp.setDOMAIN_LEVEL(t.getDOMAIN_LEVEL());
			identifierDomainDao.update(tmp);
			logger.debug("Update IdentifierDomain:"+tmp);
		}else{
			logger.debug("身份域唯一标识:"+t.getUNIQUE_SIGN()+"已存在");
			throw new ValidationException("身份域唯一标识:"+t.getUNIQUE_SIGN()+"已存在");
		}		
	}

	@Override
	public void delete(IdentifierDomain t) {
		identifierDomainDao.deleteById(t);
		logger.debug("Del IdentifierDomain:domainId="+t.getDOMAIN_ID());
	}

	@Override
	public IdentifierDomain getObject(String id) {
		IdentifierDomain t = new IdentifierDomain();
		t.setDOMAIN_ID(id);
		t = identifierDomainDao.findById(t);
		logger.debug("Load IdentifierDomain:domainId="+id+",result="+t);
		return t;
	}

	public IIdentifierDomainDao getIdentifierDomainDao() {
		return identifierDomainDao;
	}

	public void setIdentifierDomainDao(IIdentifierDomainDao identifierDomainDao) {
		this.identifierDomainDao = identifierDomainDao;
	}

	@Override
	public List<IdentifierDomain> queryForPage(IdentifierDomain t, PageInfo page) {
		// XXX ben 实际应用的时候这里需要添加查询条件
		String sql = " select * from mpi_identifier_domain where 1=1 ";
		// 取得总条数sql		
		String countSql = page.buildCountSql(sql);
		logger.debug("Execute sql:["+countSql+"],params[]");
		// 设置总条数
		page.setTotal(identifierDomainDao.getCount(countSql, new Object[]{}));
		sql = page.buildPageSql(sql);
		logger.debug("Execute sql:["+sql+"],params[]");
		return identifierDomainDao.find(sql, new Object[]{});
	}
	@Override
	public List<IdentifierDomain> queryAll() {		
		return identifierDomainDao.findAll();
	}

	@Override
	public boolean testDomain(IdentifierDomain domain) {
		StringBuilder sql = new StringBuilder();
		Object[] args = null;
		sql.append(" select count(*) from mpi_identifier_domain where unique_sign = ? ");
		if(StringUtils.isNotBlank(domain.getDOMAIN_ID())){
			sql.append(" and domain_id != ?");
			args = new Object[]{domain.getUNIQUE_SIGN(),domain.getDOMAIN_ID()};
		}else{
			args = new Object[]{domain.getUNIQUE_SIGN()};
		}		
		int count = identifierDomainDao.getCount(sql.toString(), args);
		
		return count==0;
	}

	@Override
	public List<IdentifierDomain> queryPushDomain() {
		String sql = "select * from mpi_identifier_domain where book_type = '0'";
		return identifierDomainDao.find(sql);
	}
	@Override
	public List<IdentifierDomain> queryByDomianId(String  unique_sign) {	
		String sql = " select * from mpi_identifier_domain  where unique_sign='"+unique_sign+"'";
		return identifierDomainDao.find(sql);
		
	}
}
