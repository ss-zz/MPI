package com.sinosoft.mpi.service;

import java.util.List;
import java.util.Map;

import com.sinosoft.mpi.model.PersonIndex;
import com.sinosoft.mpi.model.PersonInfo;
import com.sinosoft.mpi.model.PersonInfoSimple;
import com.sinosoft.mpi.util.PageInfo;

/**
 * 居民信息操作
 */
public interface IPersonInfoService extends IService<PersonInfo> {

	/**
	 * 人工干预生成新索引
	 * 
	 * @param opId
	 *            操作id
	 * @param personId
	 *            居民id
	 */
	void addNewIndex(String opId, String personId);

	/**
	 * 批量添加 居民信息
	 * 
	 * @param persons
	 */
	void addPersonBatch(PersonInfo[] persons);

	/**
	 * 手工添加居民信息到已存在索引
	 * 
	 * @param opId
	 *            人工干预主键
	 * @param personId
	 *            居民主键
	 * @param indexId
	 *            索引主键
	 */
	void addToIndex(String opId, String personId, String indexId);

	/**
	 * 根据域唯一标识和居民在该域的标识查找用户
	 * 
	 * @param domainUnique
	 *            域唯一标识
	 * @param identifier
	 *            居民在该域的主键
	 * @return nullable
	 */
	PersonInfo getByPersonIdentifier(String domainUnique, String identifier);

	/**
	 * 根据id 查询居民信息(并附带域相关信息)
	 * 
	 * @param id
	 * @return
	 */
	PersonInfo getObjectWithDomainInfo(String id);

	/**
	 * 合并两个居民信息
	 * 
	 * @param retired
	 *            要删除的居民信息
	 * @param surviving
	 *            要保留的居民信息
	 */
	void mergePerson(PersonInfo retired, PersonInfo surviving);

	/**
	 * 批量合并 居民信息
	 * 
	 * @param retireds
	 * @param surviving
	 */
	void mergePersonBatch(PersonInfoSimple[] retireds, PersonInfoSimple surviving);

	/**
	 * 页面来源合并居民
	 * 
	 * @param retiredPersonId
	 *            被合并的居民
	 * @param survivePersonId
	 *            目标居民
	 */
	void mergePersonFromPage(String retiredPersonId, String survivePersonId);

	/**
	 * 取得居民信息对比数据
	 * 
	 * @param survivePersonId
	 *            目标居民id
	 * @param retiredPersonId
	 *            被合并居民id
	 */
	Map<String, Object> queryComparePersonData(String survivePersonId, String retiredPersonId);

	/**
	 * 分页查询居民信息
	 * 
	 * @param t
	 *            居民查询模板
	 * @param isSurvive
	 *            是否是选择目标居民
	 * @param page
	 *            分页信息
	 * @return
	 */
	List<Map<String, Object>> queryForMapPage(PersonInfo t, boolean isSurvive, PageInfo page);

	/**
	 * 根据索引id查询其关联的居民数据
	 * 
	 * @param indexId
	 * @return
	 */
	List<Map<String, Object>> queryForPersonByIndexId(String indexId);

	/**
	 * 查找某索引下所有关联的居民数据
	 * 
	 * @param indexId
	 *            索引id
	 * @param page
	 *            分页信息
	 * @return
	 */
	List<Map<String, Object>> queryForSplitPersonPage(String indexId, PageInfo page);

	/**
	 * 根据居民属性查询居民信息
	 * 
	 * @param p
	 *            居民信息
	 * @return
	 */
	List<PersonInfo> queryPersonByAttributes(PersonInfo p);

	/**
	 * 根据 域唯一标识和域主键取得索引信息
	 * 
	 * @param p
	 *            居民信息(需要包含域唯一标识和域主键)
	 * @return
	 */
	PersonIndex queryPersonIndexByPersonInfo(PersonInfo p);

	/**
	 * 根据索引id查询 所绑定的居民信息
	 * 
	 * @param indexId
	 * @return
	 */
	List<PersonInfo> queryPersonsByIndex(String indexId);

	/**
	 * 根据索引id和居民所在域唯一标识查询居民信息
	 * 
	 * @param indexId
	 * @return
	 */
	List<PersonInfo> queryPersonsByIndex(String indexId, String domainUniqueSign);

	/**
	 * 根据居民唯一标识查询居民信息
	 * 
	 * @param fieldpk
	 * @return
	 */
	PersonInfo queryPersonsByFieldPK(String fieldpk);

	/**
	 * 拆分居民信息
	 * 
	 * @param indexId
	 *            索引id
	 * @param personId
	 *            居民id
	 */
	void splitPerson(String indexId, String personId);

	/**
	 * 拆分索引至指定索引
	 * 
	 * @param indexId
	 *            目标索引id
	 * @param personId
	 *            居民id
	 * @param fromIndexId
	 *            原索引id
	 */
	void splitPersonToIndex(String indexId, String personId, String fromIndexId);

	/**
	 * 批量更新 居民信息
	 * 
	 * @param persons
	 */
	void updatePersonBatch(PersonInfo[] persons);

	public Map<String, Object> findByRelationPK(String relationpk, String org_code);

	public PersonInfo getByDomainid(String domain, String identifier);
}
