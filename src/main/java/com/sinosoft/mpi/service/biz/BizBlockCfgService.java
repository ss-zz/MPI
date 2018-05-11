package com.sinosoft.mpi.service.biz;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Service;

import com.sinosoft.block.config.BlockConfig;
import com.sinosoft.mpi.cache.CacheManager;
import com.sinosoft.mpi.dao.BlockCfgDao;
import com.sinosoft.mpi.dao.BlockGroupDao;
import com.sinosoft.mpi.model.BlockCfg;
import com.sinosoft.mpi.model.BlockGroup;
import com.sinosoft.mpi.model.PersonPropertiesDesc;
import com.sinosoft.mpi.util.DateUtil;
import com.sinosoft.mpi.util.PageInfo;

/**
 * 初筛配置服务
 */
@Service
public class BizBlockCfgService {

	private Logger logger = Logger.getLogger(BizBlockCfgService.class);

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
		blockCfgDao.add(t);
		for (Integer key : t.getGroups().keySet()) {
			saveBlockGroups(t.getGroups().get(key), t.getBolckId(), key);
		}
		logger.debug("Add BlockCfg:" + t);
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
			blockGroupDao.add(t);
		}
	}

	/**
	 * 更新
	 * 
	 * @param t
	 *            数据对象
	 */
	public void update(BlockCfg t) {
		blockCfgDao.update(t);
	}

	/**
	 * 删除
	 * 
	 * @param t
	 */
	public void delete(BlockCfg t) {
		blockCfgDao.deleteById(t);
	}

	/**
	 * 根据id获取
	 * 
	 * @param id
	 * @return
	 */
	public BlockCfg getObject(String id) {
		BlockCfg t = new BlockCfg();
		t.setBolckId(id);
		t = blockCfgDao.findById(t);
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
	public List<BlockCfg> queryForPage(BlockCfg t, PageInfo page) {
		String sql = " select * from MPI_BLOCK_CFG where 1=1 ";
		String countSql = page.buildCountSql(sql);
		page.setTotal(blockCfgDao.getCount(countSql, new Object[] {}));
		String querySql = page.buildPageSql(sql);
		return blockCfgDao.find(querySql, new Object[] {});
	}

	/**
	 * 使配置生效
	 * 
	 * @param cfgId
	 *            配置id
	 */
	public void updateEffect(String cfgId) {
		// 使id配置 生效
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
		String sql = " select * from MPI_BLOCK_CFG where state = '1' ";
		BlockCfg result = null;
		List<BlockCfg> list = blockCfgDao.find(sql);
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
	 * @param cfg
	 * @return
	 */
	private Map<Integer, List<BlockGroup>> queryFieldCfg(BlockCfg cfg) {
		Map<Integer, List<BlockGroup>> result = new HashMap<Integer, List<BlockGroup>>(cfg.getGroupCount());
		String sql = "select * from mpi_block_group where bolck_id=? and group_sign=?";
		for (int i = 0; i < cfg.getGroupCount(); i++) {
			result.put(Integer.valueOf(i), blockGroupDao.find(sql, new Object[] { cfg.getBolckId(), i }));
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
