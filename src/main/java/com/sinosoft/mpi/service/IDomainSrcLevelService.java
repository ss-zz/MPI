package com.sinosoft.mpi.service;

import java.util.List;
import java.util.Map;

import com.sinosoft.mpi.model.DomainSrcLevel;
import com.sinosoft.mpi.model.IdentifierDomain;
import com.sinosoft.mpi.model.IndexIdentifierRel;
import com.sinosoft.mpi.model.MatchResult;
import com.sinosoft.mpi.util.PageInfo;


/**   
*    
* @Description 数据源级别操作
* 
* 
*
* 
* @Package com.sinosoft.mpi.service 
* @author lpk
* @version v1.0,2012-3-19
* @see	
* @since	（可选）	
*   
*/ 
public interface IDomainSrcLevelService extends IService<DomainSrcLevel> {
	
	/**
	 * 删除
	 * @param id
	 */
	public void deleteByID(String id);
	/**
	 * 保存
	 * @param id
	 */
	public void saveSrcLevel(DomainSrcLevel srclevel);
	/**
	 * 删除
	 * @param DomainSrcLevel
	 */
	public void deleteSrcLevel(DomainSrcLevel srclevel);
	/**
	 * 保存
	 * @param id
	 */
	public void updateSrcLevel(DomainSrcLevel srclevel);
	public List<DomainSrcLevel> queryByMpiPK(String domainid);
	List<DomainSrcLevel> queryAll();
	public DomainSrcLevel queryByID(DomainSrcLevel srclevel);
	void updateByDomainID(DomainSrcLevel t);
	List<Map<String, Object>> queryByDomainID(String domainid);
	List<DomainSrcLevel> queryForPagebyDomainID(DomainSrcLevel t, PageInfo page);
	List<DomainSrcLevel> queryByID(String domainid);
	List<DomainSrcLevel> queryPageByID(DomainSrcLevel t, PageInfo page);
}