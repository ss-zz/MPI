package com.sinosoft.mpi.service.biz;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

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

import com.sinosoft.bizblock.config.BizBlockConfig;
import com.sinosoft.mpi.cache.CacheManager;
import com.sinosoft.mpi.dao.biz.MpiBizBlockCfgDao;
import com.sinosoft.mpi.dao.biz.MpiBizBlockGroupDao;
import com.sinosoft.mpi.exception.ValidationException;
import com.sinosoft.mpi.model.biz.MpiBizBlockCfg;
import com.sinosoft.mpi.model.biz.MpiBizBlockGroup;
import com.sinosoft.mpi.model.biz.MpiBizPropertiesDesc;
import com.sinosoft.mpi.util.PageInfo;

/**
 * 初筛配置服务
 */
@Service
public class BizBlockCfgService {

	@Resource
	MpiBizBlockCfgDao mpiBizBlockCfgDao;
	@Resource
	MpiBizBlockGroupDao mpiBizBlockGroupDao;

	/**
	 * 保存配置信息
	 */
	public void save(MpiBizBlockCfg t) {
		t.setState("0");
		mpiBizBlockCfgDao.save(t);
		for (Integer key : t.getGroups().keySet()) {
			saveBlockGroups(t.getGroups().get(key), t.getBlockId(), key);
		}
	}

	/**
	 * 保存组信息
	 * 
	 * @param list
	 *            组
	 * @param bolckId
	 *            初筛配置id
	 * @param groupSign
	 *            组唯一标识
	 */
	private void saveBlockGroups(List<MpiBizBlockGroup> list, String bolckId, Integer groupSign) {
		for (MpiBizBlockGroup t : list) {
			MpiBizPropertiesDesc ppd = CacheManager.get(MpiBizPropertiesDesc.class, t.getPropertyName());
			t.setDbField(ppd.getColumn());
			t.setPropertyCnName(ppd.getPropertyDesc());
			t.setBolckId(bolckId);
			t.setGroupSign(groupSign);
			mpiBizBlockGroupDao.save(t);
		}
	}

	/**
	 * 更新
	 * 
	 * @param t
	 *            数据对象
	 */
	public MpiBizBlockCfg update(MpiBizBlockCfg t) {
		return mpiBizBlockCfgDao.save(t);
	}

	/**
	 * 根据id删除
	 * 
	 * @param id
	 */
	@Transactional
	public void deleteById(String id) {
		if (canDelete(id)) {
			mpiBizBlockGroupDao.deleteByBolckId(id);
			mpiBizBlockCfgDao.delete(id);
		} else {
			throw new ValidationException("不能删除生效中的配置");
		}
	}

	/**
	 * 是否允许删除
	 * 
	 * @param id
	 * @return
	 */
	private boolean canDelete(String id) {
		MpiBizBlockCfg item = getObject(id);
		if (item != null && item.getState() == "1") {
			return false;
		}
		return true;
	}

	/**
	 * 根据id获取
	 * 
	 * @param id
	 * @return
	 */
	public MpiBizBlockCfg getObject(String id) {
		MpiBizBlockCfg t = mpiBizBlockCfgDao.findOne(id);
		if (t != null) {
			t.setGroups(queryFieldCfg(t));
		}
		return t;
	}

	/**
	 * 分页查询
	 * 
	 * @param t
	 * @param page
	 * @return
	 */
	public Page<MpiBizBlockCfg> queryForPage(final MpiBizBlockCfg t, PageInfo page) {
		return mpiBizBlockCfgDao.findAll(new Specification<MpiBizBlockCfg>() {
			@Override
			public Predicate toPredicate(Root<MpiBizBlockCfg> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
				if (t != null) {
					if (t.getState() != null) {
						return cb.and(cb.equal(root.get("state"), t.getState()));
					}
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
		mpiBizBlockCfgDao.uneffectAll();
		// 使目标生效
		mpiBizBlockCfgDao.effect(cfgId);
		MpiBizBlockCfg cfg = getObject(cfgId);
		if (cfg != null) {
			// 重新载入配置文件
			BizBlockConfig.getInstanse().reloadCfg(cfg);
		}
	}

	/**
	 * 查询生效的配置
	 */
	public MpiBizBlockCfg queryEffectCfg() {
		List<MpiBizBlockCfg> list = mpiBizBlockCfgDao.findAllEffect();
		MpiBizBlockCfg result = list.size() > 0 ? list.get(0) : null;
		if (result != null) {
			result.setGroups(queryFieldCfg(result));
		}
		return result;
	}

	/**
	 * 查询字段配置
	 * 
	 * @param cfg
	 * @return
	 */
	private Map<Integer, List<MpiBizBlockGroup>> queryFieldCfg(MpiBizBlockCfg cfg) {
		Map<Integer, List<MpiBizBlockGroup>> result = new HashMap<Integer, List<MpiBizBlockGroup>>(cfg.getGroupCount());
		for (int i = 0; i < cfg.getGroupCount(); i++) {
			result.put(i, mpiBizBlockGroupDao.findByBolckIdAndGroupSign(cfg.getBlockId(), i));
		}
		return result;
	}

	/**
	 * 用于从数据库初始化 匹配配置
	 */
	@PostConstruct
	public void initMatchConfig() {
		MpiBizBlockCfg cfg = queryEffectCfg();
		if (cfg != null) {
			// 重新载入配置文件
			BizBlockConfig.getInstanse().reloadCfg(cfg);
		}
	}
}
