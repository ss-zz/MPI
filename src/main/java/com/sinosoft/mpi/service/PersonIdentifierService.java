package com.sinosoft.mpi.service;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.sinosoft.mpi.dao.PersonIdentifierDao;
import com.sinosoft.mpi.model.PersonIdentifier;
import com.sinosoft.mpi.util.PageInfo;

@Service
public class PersonIdentifierService {

	@Resource
	private PersonIdentifierDao personIdentifierDao;

	public void save(PersonIdentifier t) {
		personIdentifierDao.add(t);
	}

	public void update(PersonIdentifier t) {
		personIdentifierDao.update(t);
	}

	public void delete(PersonIdentifier t) {
		personIdentifierDao.deleteById(t);
	}

	public PersonIdentifier getObject(String id) {
		PersonIdentifier t = new PersonIdentifier();
		t.setIdentifierId(id);
		t = personIdentifierDao.findById(t);
		return t;
	}

	public List<PersonIdentifier> queryForPage(PersonIdentifier t, PageInfo page) {
		String sql = " select * from mpi_person_identifier where 1=1 ";
		String countSql = page.buildCountSql(sql);
		page.setTotal(personIdentifierDao.getCount(countSql, new Object[] {}));
		String querySql = page.buildPageSql(sql);
		return personIdentifierDao.find(querySql, new Object[] {});
	}

}
