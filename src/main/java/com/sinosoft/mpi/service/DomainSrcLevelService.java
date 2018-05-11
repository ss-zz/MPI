package com.sinosoft.mpi.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.sinosoft.mpi.dao.DomainSrcLevelDao;
import com.sinosoft.mpi.model.DomainSrcLevel;
import com.sinosoft.mpi.util.PageInfo;

/**
 * 域源级别服务
 */
@Service
public class DomainSrcLevelService {

	@Resource
	private DomainSrcLevelDao domainSrcLevelDao;

	/**
	 * 对象缓存-根据域id获取数据
	 */
	private Map<String, List<Map<String, Object>>> TMP_DOMAINID_DATA = new HashMap<String, List<Map<String, Object>>>();

	/**
	 * 保存
	 * 
	 * @param t
	 */
	public void save(DomainSrcLevel t) {
		domainSrcLevelDao.add(t);
	}

	/**
	 * 更新
	 * 
	 * @param t
	 */
	public void update(DomainSrcLevel t) {
		domainSrcLevelDao.update(t);
	}

	/**
	 * 根据id更新
	 * 
	 * @param t
	 */
	public void updateByDomainID(DomainSrcLevel t) {
		domainSrcLevelDao.updateByDomainID(t);
	}

	/**
	 * 删除
	 * 
	 * @param t
	 */
	public void delete(DomainSrcLevel t) {
		domainSrcLevelDao.deleteById(t);
	}

	/**
	 * 根据id获取
	 * 
	 * @param id
	 * @return
	 */
	public DomainSrcLevel getObject(String id) {
		return null;
	}

	/**
	 * 分页查询
	 * 
	 * @param t
	 * @param page
	 * @return
	 */
	public List<DomainSrcLevel> queryForPage(DomainSrcLevel t, PageInfo page) {
		String sql = " select * from MPI_DOMAIN_SRCLEVEL where 1=1 ";
		String countSql = page.buildCountSql(sql);
		page.setTotal(domainSrcLevelDao.getCount(countSql, new Object[] {}));
		String querySql = page.buildPageSql(sql);
		return domainSrcLevelDao.find(querySql, new Object[] {});
	}

	/**
	 * 根据id和字段名查询
	 * 
	 * @param srclevel
	 * @return
	 */
	public DomainSrcLevel queryByDomainIdAndFieldName(String domainId, String fieldName) {
		String sql = " select * from MPI_DOMAIN_SRCLEVEL where DOMAIN_ID= ? and FIELD_NAME= ? ";
		List<DomainSrcLevel> datas = domainSrcLevelDao.find(sql, new Object[] { domainId, fieldName });
		if (datas != null && datas.size() > 0) {
			return datas.get(0);
		}
		return null;
	}

	/**
	 * 根据域id查询
	 * 
	 * @param domainid
	 * @return
	 */
	public List<Map<String, Object>> queryByDomainID(String domainid) {
		if (domainid == null) {
			return new ArrayList<Map<String, Object>>();
		}
		if (TMP_DOMAINID_DATA.containsKey(domainid)) {
			return TMP_DOMAINID_DATA.get(domainid);
		} else {
			String sql = " select * from MPI_DOMAIN_SRCLEVEL where DOMAIN_ID=? ";
			List<Map<String, Object>> datas = domainSrcLevelDao.findForMap(sql, new Object[] { domainid });
			TMP_DOMAINID_DATA.put(domainid, datas);
			return datas;
		}

	}

	/**
	 * 根据域id查询对象列表
	 * 
	 * @param domainid
	 * @return
	 */
	public List<DomainSrcLevel> queryByID(String domainid) {
		String sql = " select * from MPI_DOMAIN_SRCLEVEL where DOMAIN_ID=?";
		return domainSrcLevelDao.find(sql, new Object[] { domainid });
	}

	/**
	 * 根据域id分页查询
	 * 
	 * @param t
	 * @param page
	 * @return
	 */
	public List<DomainSrcLevel> queryPageByID(String domainid, PageInfo page) {
		String sql = " select * from MPI_DOMAIN_SRCLEVEL where DOMAIN_ID='" + domainid + "'";
		String countSql = page.buildCountSql(sql);
		page.setTotal(domainSrcLevelDao.getCount(countSql, new Object[] {}));
		String querySql = page.buildPageSql(sql);
		return domainSrcLevelDao.find(querySql, new Object[] {});
	}

	/**
	 * 查询所有
	 * 
	 * @return
	 */
	public List<DomainSrcLevel> queryAll() {
		return domainSrcLevelDao.findAll();
	}

}
