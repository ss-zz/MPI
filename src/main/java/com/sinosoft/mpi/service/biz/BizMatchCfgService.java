package com.sinosoft.mpi.service.biz;

import java.util.Date;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.springframework.data.domain.Page;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.sinosoft.bizmatch.config.BizMatchConfig;
import com.sinosoft.mpi.dao.biz.MpiBizMatchCfgDao;
import com.sinosoft.mpi.dao.biz.MpiBizMatchFieldCfgDao;
import com.sinosoft.mpi.model.biz.MpiBizMatchCfg;
import com.sinosoft.mpi.model.biz.MpiBizMatchFieldCfg;
import com.sinosoft.mpi.util.PageInfo;

/**
 * 人员匹配配置服务
 */
@Service
public class BizMatchCfgService {

	@Resource
	MpiBizMatchCfgDao mpiBizMatchCfgDao;
	@Resource
	MpiBizMatchFieldCfgDao mpiBizMatchFieldCfgDao;

	/**
	 * 保存
	 */
	public void save(MpiBizMatchCfg t) {
		// 设置必要信息
		t.setCreateDate(new Date());
		// 默认无效
		t.setState("0");
		// 保存
		mpiBizMatchCfgDao.save(t);
		// 保存字段配置
		for (MpiBizMatchFieldCfg mfc : t.getMatchFieldCfgs()) {
			mfc.setConfigId(t.getConfigId());
			mpiBizMatchFieldCfgDao.save(mfc);
		}
	}

	/**
	 * 更新
	 * 
	 * @param t
	 */
	public MpiBizMatchCfg update(MpiBizMatchCfg t) {
		return mpiBizMatchCfgDao.save(t);
	}

	/**
	 * 删除
	 */
	public void delete(MpiBizMatchCfg t) {
		mpiBizMatchCfgDao.delete(t);
	}

	/**
	 * 删除
	 */
	public void deleteById(String id) {
		mpiBizMatchCfgDao.delete(id);
	}

	/**
	 * 根据id获取
	 * 
	 * @param id
	 * @return
	 */
	public MpiBizMatchCfg getObject(String id) {
		MpiBizMatchCfg t = mpiBizMatchCfgDao.getOne(id);
		if (t != null) {
			// 取得 字段匹配详情
			List<MpiBizMatchFieldCfg> list = queryFieldCfg(id);
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
	private List<MpiBizMatchFieldCfg> queryFieldCfg(String configId) {
		return mpiBizMatchFieldCfgDao.findByConfigId(configId);
	}

	/**
	 * 分页查询
	 * 
	 * @param t
	 * @param page
	 * @return
	 */
	public Page<MpiBizMatchCfg> queryForPage(final MpiBizMatchCfg t, PageInfo page) {
		return mpiBizMatchCfgDao.findAll(new Specification<MpiBizMatchCfg>() {
			@Override
			public Predicate toPredicate(Root<MpiBizMatchCfg> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
				if (t.getState() != null) {
					return cb.and(cb.equal(root.get("state"), t.getState()));
				}
				return null;
			}
		}, page);
	}

	/**
	 * 使配置生效
	 * 
	 * @param cfgId
	 *            配置id
	 */
	@Transactional
	public void updateEffect(String cfgId) {
		// 使所有失效
		mpiBizMatchCfgDao.uneffectAll();
		// 使目标生效
		mpiBizMatchCfgDao.effect(cfgId);
		MpiBizMatchCfg cfg = getObject(cfgId);
		if (cfg != null) {
			// 重新载入配置文件
			BizMatchConfig.getInstanse().reloadCfg(cfg);
		}
	}

	/**
	 * 查询生效的配置
	 */
	public MpiBizMatchCfg queryEffectCfg() {
		List<MpiBizMatchCfg> list = mpiBizMatchCfgDao.findAllEffect();
		MpiBizMatchCfg result = list.size() > 0 ? list.get(0) : null;
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
		MpiBizMatchCfg cfg = queryEffectCfg();
		if (cfg != null) {
			// 重新载入配置文件
			BizMatchConfig.getInstanse().reloadCfg(cfg);
		}
	}
}
