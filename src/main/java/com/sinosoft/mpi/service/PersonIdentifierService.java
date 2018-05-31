package com.sinosoft.mpi.service;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.sinosoft.mpi.dao.mpi.PersonIdentifierDao;
import com.sinosoft.mpi.model.PersonIdentifier;
import com.sinosoft.mpi.model.PersonInfo;

/**
 * 居民标志服务
 */
@Service
public class PersonIdentifierService {

	@Resource
	private PersonIdentifierDao personIdentifierDao;

	/**
	 * 保存
	 */
	public void save(PersonIdentifier t) {
		personIdentifierDao.save(t);
	}

	/**
	 * 更新
	 */
	public void update(PersonIdentifier t) {
		personIdentifierDao.save(t);
	}

	/**
	 * 删除
	 * 
	 * @param t
	 */
	public void delete(PersonIdentifier t) {
		personIdentifierDao.delete(t);
	}

	/**
	 * 获取详情
	 * 
	 * @param id
	 * @return
	 */
	public PersonIdentifier getObject(String id) {
		return personIdentifierDao.findOne(id);
	}

	/**
	 * 根据personId查询PersonIdentifier
	 * 
	 * @param personId
	 * @return
	 */
	public PersonIdentifier getByPersonId(String personId) {
		List<PersonIdentifier> datas = personIdentifierDao.findByPersonId(personId);
		return datas.size() > 0 ? datas.get(0) : null;
	}

	/**
	 * 根据居民信息保存居民标志
	 * 
	 * @param t
	 */
	public void save(PersonInfo t) {
		if (t == null)
			return;
		PersonIdentifier identifier = new PersonIdentifier();
		identifier.setDomainId(t.getUniqueSign());
		identifier.setPersonId(t.getFieldPk());
		identifier.setPersonIdentifier(t.getIdentifier());
		save(identifier);
	}

}
