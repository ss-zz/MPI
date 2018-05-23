package com.sinosoft.mpi.service;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.apache.commons.lang.StringUtils;
import org.springframework.data.domain.Page;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.sinosoft.block.config.BlockConfig;
import com.sinosoft.mpi.cache.CacheManager;
import com.sinosoft.mpi.dao.mpi.BlockCfgDao;
import com.sinosoft.mpi.dao.mpi.BlockGroupDao;
import com.sinosoft.mpi.exception.ValidationException;
import com.sinosoft.mpi.model.BlockCfg;
import com.sinosoft.mpi.model.BlockGroup;
import com.sinosoft.mpi.model.code.PersonPropertiesDesc;
import com.sinosoft.mpi.util.DateUtil;
import com.sinosoft.mpi.util.PageInfo;

/**
 * 初筛配置服务
 */
@Service
public class BlockCfgService {

	@Resource
	private BlockCfgDao blockCfgDao;
	@Resource
	private BlockGroupDao blockGroupDao;

	/**
	 * 保存配置信息
	 */
	public void save(BlockCfg t) {
		t.setCreateDate(DateUtil.getTimeNow(new Date()));
		t.setState("0");
		blockCfgDao.save(t);
		for (Integer key : t.getGroups().keySet()) {
			saveBlockGroups(t.getGroups().get(key), t.getBolckId(), key);
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
	private void saveBlockGroups(List<BlockGroup> list, String bolckId, Integer groupSign) {
		for (BlockGroup t : list) {
			PersonPropertiesDesc ppd = CacheManager.get(PersonPropertiesDesc.class, t.getPropertyName());
			t.setDbField(ppd.getColumn());
			t.setPropertyCnName(ppd.getPropertyDesc());
			t.setBolckId(bolckId);
			t.setGroupSign(groupSign);
			blockGroupDao.save(t);
		}
	}

	/**
	 * 更新
	 * 
	 * @param t
	 *            数据对象
	 */
	public void update(BlockCfg t) {
		blockCfgDao.save(t);
	}

	/**
	 * 根据id删除
	 * 
	 * @param id
	 */
	@Transactional
	public void deleteById(String id) {
		if (canDelete(id)) {
			blockGroupDao.deleteByBolckId(id);
			blockCfgDao.delete(id);
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
		BlockCfg item = getObject(id);
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
	public BlockCfg getObject(String id) {
		BlockCfg t = blockCfgDao.findOne(id);
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
	public Page<BlockCfg> queryForPage(final BlockCfg t, PageInfo page) {
		return blockCfgDao.findAll(new Specification<BlockCfg>() {
			@Override
			public Predicate toPredicate(Root<BlockCfg> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
				List<Predicate> predicates = new ArrayList<>();
				if (t != null) {
					if (StringUtils.isNotBlank(t.getState())) {
						predicates.add(cb.equal(root.get("state"), t.getState()));
					}
				}
				return cb.and(predicates.toArray(new Predicate[predicates.size()]));
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
		blockCfgDao.uneffectAll();
		blockCfgDao.effect(cfgId);
		BlockCfg cfg = getObject(cfgId);
		if (cfg != null) {
			// 重新载入配置文件
			BlockConfig.getInstanse().reloadCfg(cfg);
		}
	}

	/**
	 * 查询生效的配置
	 */
	public BlockCfg queryEffectCfg() {
		BlockCfg result = null;
		List<BlockCfg> list = blockCfgDao.findAllEffect();
		if (list != null && !list.isEmpty()) {
			result = list.get(0);
		}
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
	private Map<Integer, List<BlockGroup>> queryFieldCfg(BlockCfg cfg) {
		Map<Integer, List<BlockGroup>> result = new HashMap<Integer, List<BlockGroup>>(cfg.getGroupCount());
		for (int i = 0; i < cfg.getGroupCount(); i++) {
			result.put(i, blockGroupDao.findByBolckIdAndGroupSign(cfg.getBolckId(), i));
		}
		return result;
	}

	/**
	 * 用于从数据库初始化 匹配配置
	 */
	@PostConstruct
	public void initMatchConfig() {
		BlockCfg cfg = queryEffectCfg();
		if (cfg != null) {
			// 重新载入配置文件
			BlockConfig.getInstanse().reloadCfg(cfg);
		}
	}
}
