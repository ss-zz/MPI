package com.sinosoft.mpi.dao;

import java.util.List;
import java.util.Map;

import com.sinosoft.mpi.model.PersonInfo;

/**
 * @Description: 居民信息数据操作类接口
 * 
 * @Package com.sinosoft.mpi.dao
 * @author bysun
 * @version v1.0,2012-3-12
 * @see
 * @since v1.0
 */
public interface IPersonInfoDao extends IBaseDao<PersonInfo> {

	/**
	 * 查询用户信息,并带出域相关信息(域id,域唯一标识)
	 * 
	 * @param sql
	 *            查询语句(连表查询请将域业务Id字段命名为:person_identifier,域唯一标识命名为:unique_sign)
	 * @param args 查询参数
	 * @return
	 */
	List<PersonInfo> findWithDomainInfo(String sql, Object[] args);

	/**
	 * 取得居民信息 包含域信息
	 * @param entity
	 * @return
	 */
	PersonInfo findWithDomainInfoById(PersonInfo entity);
	
	public PersonInfo findByPK(String fieldpk);
	public Map<String,Object> findById(String person_id,String org_code);

	Map<String, Object> findById(String person_id, String org_code, String type);

	PersonInfo findByOrgId(PersonInfo entity);

	void addHistory(PersonInfo entity);
	
}
