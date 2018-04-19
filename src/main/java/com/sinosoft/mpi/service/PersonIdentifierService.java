package com.sinosoft.mpi.service;

import java.util.List;

import javax.annotation.Resource;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Service;

import com.sinosoft.mpi.dao.IPersonIdentifierDao;
import com.sinosoft.mpi.model.PersonIdentifier;
import com.sinosoft.mpi.util.PageInfo;

@Service("personIdentifierService")
public class PersonIdentifierService implements IPersonIdentifierService {

	private Logger logger = Logger.getLogger(PersonIdentifierService.class);

	@Resource
	private IPersonIdentifierDao personIdentifierDao;

	@Override
	public void save(PersonIdentifier t) {
		personIdentifierDao.add(t);
		logger.debug("Add PersonIdentifier:" + t);
	}

	@Override
	public void update(PersonIdentifier t) {
		personIdentifierDao.update(t);
		logger.debug("Update PersonIdentifier:" + t);
	}

	@Override
	public void delete(PersonIdentifier t) {
		personIdentifierDao.deleteById(t);
		logger.debug("Del PersonIdentifier:identifierId=" + t.getIdentifierId());
	}

	@Override
	public PersonIdentifier getObject(String id) {
		PersonIdentifier t = new PersonIdentifier();
		t.setIdentifierId(id);
		t = personIdentifierDao.findById(t);
		logger.debug("Load PersonIdentifier:identifierId=" + id + ",result=" + t);
		return t;
	}

	@Override
	public List<PersonIdentifier> queryForPage(PersonIdentifier t, PageInfo page) {
		// XXX ben 实际应用的时候这里需要添加查询条件
		String sql = " select * from mpi_person_identifier where 1=1 ";
		String countSql = page.buildCountSql(sql);
		page.setTotal(personIdentifierDao.getCount(countSql, new Object[] {}));
		String querySql = page.buildPageSql(sql);
		logger.debug("Execute sql:[" + sql + "],params[]");
		return personIdentifierDao.find(querySql, new Object[] {});
	}

	public IPersonIdentifierDao getPersonIdentifierDao() {
		return personIdentifierDao;
	}

	public void setPersonIdentifierDao(IPersonIdentifierDao personIdentifierDao) {
		this.personIdentifierDao = personIdentifierDao;
	}

}
