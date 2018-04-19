package com.sinosoft.mpi.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.sinosoft.mpi.dao.IDomainSrcLevelDao;
import com.sinosoft.mpi.model.DomainSrcLevel;
import com.sinosoft.mpi.util.PageInfo;

@Service("domainSrcLevelService")
public class DomainSrcLevelService implements IDomainSrcLevelService {

	@Resource
	private IDomainSrcLevelDao domainSrcLevelDao;

	/**
	 * 对象缓存-根据域id获取数据
	 */
	private Map<String, List<Map<String, Object>>> TMP_DOMAINID_DATA = new HashMap<String, List<Map<String, Object>>>();

	@Override
	public void save(DomainSrcLevel t) {
		domainSrcLevelDao.add(t);
	}

	@Override
	public void update(DomainSrcLevel t) {
		domainSrcLevelDao.update(t);
	}
	@Override
	public void updateByDomainID(DomainSrcLevel t) {
		domainSrcLevelDao.updateByDomainID(t);
	}
	@Override
	public void delete(DomainSrcLevel t) {
		domainSrcLevelDao.deleteById(t);
	}

	@Override
	public DomainSrcLevel getObject(String id) {
		return null;
	}

	@Override
	public List<DomainSrcLevel> queryForPage(DomainSrcLevel t, PageInfo page) {
		String sql = " select * from MPI_DOMAIN_SRCLEVEL where 1=1 ";
		String countSql = page.buildCountSql(sql);
		page.setTotal(domainSrcLevelDao.getCount(countSql, new Object[]{}));
		String querySql = page.buildPageSql(sql);
		return domainSrcLevelDao.find(querySql, new Object[]{});
	}

	@Override
	public DomainSrcLevel queryByID(DomainSrcLevel srclevel) {
		String sql = " select * from MPI_DOMAIN_SRCLEVEL where DOMAIN_ID='" + srclevel.getDOMAIN_ID()
				+ "' and FIELD_NAME='" + srclevel.getFIELD_NAME() + "'";
		List<DomainSrcLevel> datas = domainSrcLevelDao.find(sql, new Object[]{});
		if (datas != null && datas.size() > 0) {
			return datas.get(0);
		}
		return null;
	}

	@Override
	public List<Map<String, Object>> queryByDomainID(String domainid) {
		if (domainid == null) {
			return new ArrayList<Map<String, Object>>();
		}
		if (TMP_DOMAINID_DATA.containsKey(domainid)) {
			return TMP_DOMAINID_DATA.get(domainid);
		} else {
			String sql = " select * from MPI_DOMAIN_SRCLEVEL where DOMAIN_ID='" + domainid + "'";
			List<Map<String, Object>> datas = domainSrcLevelDao.findForMap(sql);
			TMP_DOMAINID_DATA.put(domainid, datas);
			return datas;
		}

	}

	@Override
	public List<DomainSrcLevel> queryByID(String domainid) {
		String sql = " select * from MPI_DOMAIN_SRCLEVEL where DOMAIN_ID='" + domainid + "'";
		List<DomainSrcLevel> datas = domainSrcLevelDao.find(sql);
		return datas;
	}

	@Override
	public List<DomainSrcLevel> queryForPagebyDomainID(DomainSrcLevel t, PageInfo page) {
		String sql = " select * from MPI_DOMAIN_SRCLEVEL where DOMAIN_ID='" + t.getDOMAIN_ID() + "'";
		String countSql = page.buildCountSql(sql);
		page.setTotal(domainSrcLevelDao.getCount(countSql, new Object[]{}));
		String querySql = page.buildPageSql(sql);
		return domainSrcLevelDao.find(querySql, new Object[]{});
	}

	@Override
	public List<DomainSrcLevel> queryPageByID(DomainSrcLevel t, PageInfo page) {
		String sql = " select * from MPI_DOMAIN_SRCLEVEL where DOMAIN_ID='" + t.getDOMAIN_ID() + "'";
		String countSql = page.buildCountSql(sql);
		page.setTotal(domainSrcLevelDao.getCount(countSql, new Object[]{}));
		String querySql = page.buildPageSql(sql);
		return domainSrcLevelDao.find(querySql, new Object[]{});
	}

	@Override
	public void deleteByID(String id) {

	}

	@Override
	public void saveSrcLevel(DomainSrcLevel srclevel) {

	}

	@Override
	public void deleteSrcLevel(DomainSrcLevel srclevel) {

	}

	@Override
	public void updateSrcLevel(DomainSrcLevel srclevel) {

	}

	@Override
	public List<DomainSrcLevel> queryByMpiPK(String domainid) {
		return null;
	}

	@Override
	public List<DomainSrcLevel> queryAll() {
		return domainSrcLevelDao.findAll();
	}

}
