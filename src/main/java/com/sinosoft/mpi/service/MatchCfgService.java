package com.sinosoft.mpi.service;

import java.util.Date;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.sinosoft.match.config.MatchConfig;
import com.sinosoft.mpi.dao.mpi.MatchCfgDao;
import com.sinosoft.mpi.dao.mpi.MatchFieldCfgDao;
import com.sinosoft.mpi.model.MatchCfg;
import com.sinosoft.mpi.model.MatchFieldCfg;
import com.sinosoft.mpi.util.DateUtil;
import com.sinosoft.mpi.util.PageInfo;

/**
 * 人员匹配配置服务
 */
@Service
public class MatchCfgService {

	@Resource
	private MatchCfgDao matchCfgDao;
	@Resource
	private MatchFieldCfgDao matchFieldCfgDao;

	/**
	 * 保存
	 */
	public void save(MatchCfg t) {
		// 设置必要信息
		t.setCreateDate(DateUtil.getTimeNow(new Date()));
		// 默认无效
		t.setState("0");
		// 保存
		matchCfgDao.save(t);
		// 保存字段配置
		for (MatchFieldCfg mfc : t.getMatchFieldCfgs()) {
			mfc.setConfigId(t.getConfigId());
			matchFieldCfgDao.save(mfc);
		}
	}

	/**
	 * 更新
	 * 
	 * @param t
	 */
	public void update(MatchCfg t) {
		matchCfgDao.save(t);
	}

	/**
	 * 删除
	 */
	public void delete(MatchCfg t) {
		matchCfgDao.delete(t);
	}

	/**
	 * 根据id获取
	 * 
	 * @param id
	 * @return
	 */
	public MatchCfg getObject(String id) {
		MatchCfg t = matchCfgDao.findOne(id);
		if (t != null) {
			// 取得 字段匹配详情
			List<MatchFieldCfg> list = queryFieldCfg(id);
			t.setMatchFieldCfgs(list);
		}
		return t;
	}

	/**
	 * 根据匹配配置id 查询关联的字段配置
	 * 
	 * @param configId
	 *            匹配配置id
	 */
	private List<MatchFieldCfg> queryFieldCfg(String configId) {
		return matchFieldCfgDao.findByConfigId(configId);
	}

	/**
	 * 分页查询
	 * 
	 * @param t
	 * @param page
	 * @return
	 */
	public List<MatchCfg> queryForPage(MatchCfg t, PageInfo page) {
		return matchCfgDao.findAll(page).getContent();
	}

	/**
	 * 使配置生效
	 * 
	 * @param cfgId
	 */
	public void updateEffect(String cfgId) {
		matchCfgDao.uneffectAll();
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
		List<MatchCfg> list = matchCfgDao.findAllEffect();
		MatchCfg result = list.size() > 0 ? list.get(0) : null;
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
