package com.sinosoft.mpi.service;

import java.util.Date;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Service;

import com.sinosoft.match.config.MatchConfig;
import com.sinosoft.mpi.dao.MatchCfgDao;
import com.sinosoft.mpi.dao.MatchFieldCfgDao;
import com.sinosoft.mpi.model.MatchCfg;
import com.sinosoft.mpi.model.MatchFieldCfg;
import com.sinosoft.mpi.util.DateUtil;
import com.sinosoft.mpi.util.PageInfo;

@Service
public class MatchCfgService {

	private Logger logger = Logger.getLogger(MatchCfgService.class);

	@Resource
	private MatchCfgDao matchCfgDao;
	@Resource
	private MatchFieldCfgDao matchFieldCfgDao;

	public void save(MatchCfg t) {
		// 设置必要信息
		t.setCreateDate(DateUtil.getTimeNow(new Date()));
		// 默认无效
		t.setState("0");
		// 保存
		matchCfgDao.add(t);
		logger.debug("Add MatchCfg:" + t);
		// 保存字段配置
		for (MatchFieldCfg mfc : t.getMatchFieldCfgs()) {
			mfc.setConfigId(t.getConfigId());
			matchFieldCfgDao.add(mfc);
			logger.debug("Add MatchFieldCfg:" + mfc);
		}
	}

	public void update(MatchCfg t) {
		matchCfgDao.update(t);
		logger.debug("Update MatchCfg:" + t);
	}

	public void delete(MatchCfg t) {
		matchCfgDao.deleteById(t);
		logger.debug("Del MatchCfg:configId=" + t.getConfigId());
	}

	public MatchCfg getObject(String id) {
		MatchCfg t = new MatchCfg();
		t.setConfigId(id);
		t = matchCfgDao.findById(t);
		if (t != null) {
			// 取得 字段匹配详情
			List<MatchFieldCfg> list = queryFieldCfg(id);
			t.setMatchFieldCfgs(list);
		}
		logger.debug("Load MatchCfg:configId=" + id + ",result=" + t);
		return t;
	}

	/**
	 * 根据匹配配置id 查询关联的字段配置
	 * 
	 * @param configId
	 *            匹配配置id
	 */
	private List<MatchFieldCfg> queryFieldCfg(String configId) {
		String sql = " select * from mpi_match_field_cfg where config_id = ? ";
		List<MatchFieldCfg> list = matchFieldCfgDao.find(sql, new Object[] { configId });
		return list;
	}

	public List<MatchCfg> queryForPage(MatchCfg t, PageInfo page) {
		String sql = " select * from mpi_match_cfg where 1=1 ";
		String countSql = page.buildCountSql(sql);
		page.setTotal(matchCfgDao.getCount(countSql, new Object[] {}));
		logger.debug("Execute sql:[" + countSql + "],params[]");
		String querySql = page.buildPageSql(sql);
		logger.debug("Execute sql:[" + querySql + "],params[]");
		return matchCfgDao.find(querySql, new Object[] {});
	}

	public void updateEffect(String cfgId) {

		// 使id配置 生效
		matchCfgDao.effect(cfgId);
		MatchCfg cfg = getObject(cfgId);
		if (cfg != null) {
			// 重新载入配置文件
			MatchConfig.getInstanse().reloadCfg(cfg);
		}
	}

	/**
	 * 查询生效的配置
	 */
	public MatchCfg queryEffectCfg() {
		String sql = " select * from mpi_match_cfg where state = '1' ";
		MatchCfg result = null;
		List<MatchCfg> list = matchCfgDao.find(sql);
		if (list != null && !list.isEmpty()) {
			result = list.get(0);
		}
		if (result != null) {
			result.setMatchFieldCfgs(queryFieldCfg(result.getConfigId()));
		}

		return result;
	}

	/**
	 * 用于从数据库初始化 匹配配置
	 */
	@PostConstruct
	public void initMatchConfig() {
		MatchCfg cfg = queryEffectCfg();
		if (cfg != null) {
			// 重新载入配置文件
			MatchConfig.getInstanse().reloadCfg(cfg);
		}
	}
}
