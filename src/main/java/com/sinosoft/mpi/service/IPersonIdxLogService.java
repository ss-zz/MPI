package com.sinosoft.mpi.service;

import java.util.List;
import java.util.Map;

import com.sinosoft.mpi.form.MatchDetailForm;
import com.sinosoft.mpi.model.PersonIdxLog;
import com.sinosoft.mpi.model.PersonInfo;
import com.sinosoft.mpi.util.PageInfo;


/**   
*    
* @Description 主索引操作日志操作
* 
* 
*
* 
* @Package com.sinosoft.mpi.service 
* @author Bysun
* @version v1.0,2012-3-19
* @see	
* @since	（可选）	
*   
*/ 
public interface IPersonIdxLogService extends IService<PersonIdxLog> {
	
	/**
	 * 查询 索引 居民 对比数据
	 * @param personId 居民id
	 * @param indexId 索引id
	 */
	List<MatchDetailForm> queryCompareData(String personId, String indexId);

	/**
	 * 查询结果以map形式返回
	 * @param t
	 * @param page
	 * @return
	 */
	List<Map<String,Object>> queryForMapPage(PersonIdxLog t, PageInfo page);

	/**
	 * 查询索引信息详情
	 * @param indexId
	 * @return
	 */
	Map<String, Object> queryIndexDetail(String indexId);

	/**
	 * 查询展示匹配结果详情
	 * @param indexId 居民索引id
	 * @param personId 居民id
	 * @return
	 */
	Map<String, Object> queryMatchDetail(String logId);

	/**
	 * 分页查询匹配记录
	 * @param personId 居民id
	 * @param matchPage 显示第几个匹配结果
	 * @return
	 */
	List<Map<String, Object>> queryMatchDetailPage(String personId,int start,int end);

	/**
	 * 根据 传来的索引id查询 匹配明细
	 * @param personId 居民id
	 * @param idxIds 索引ids
	 * @return
	 */
	List<Map<String, Object>> queryMatchDetailPageByIdxIds(String personId ,String[] idxIds);

	/**
	 * 列表显示匹配的索引
	 * @param personId
	 * @return
	 */
	List<Map<String, Object>> queryMatchIndex(String personId);

	/**
	 * 根据人员 查询与索引的匹配数量
	 * @param personId
	 * @return
	 */
	int queryMatchIndexCount(String personId);

	/**
	 * 根据居民id查询其所有索引操作日期记录
	 * @param personId
	 * @return
	 */
	List<Map<String, Object>> queryOpLogByPersonId(String personId);

	/**
	 * 查询居民信息详情
	 * @param personId
	 * @return
	 */
	Map<String, Object> queryPersonDetail(String personId);

	/**
	 * 居民角度查询日志
	 * @param t 居民信息
	 * @param PageInfo 分页信息
	 * @return
	 */
	List<Map<String, Object>> queryPersonForMapPage(PersonInfo t, PageInfo page);
	
	/**
	 * 根据原主索引主键获取所有曾合并过的主索引
	 * @param mpiPk
	 * @return
	 */
	List<Map<String, Object>> queryIndexDetails(String mpiPk);
}
