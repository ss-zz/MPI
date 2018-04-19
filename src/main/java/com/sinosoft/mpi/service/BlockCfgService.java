package com.sinosoft.mpi.service;

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
import com.sinosoft.mpi.dao.IBlockCfgDao;
import com.sinosoft.mpi.dao.IBlockGroupDao;
import com.sinosoft.mpi.model.BlockCfg;
import com.sinosoft.mpi.model.BlockGroup;
import com.sinosoft.mpi.model.PersonPropertiesDesc;
import com.sinosoft.mpi.util.DateUtil;
import com.sinosoft.mpi.util.PageInfo;

@Service("blockCfgService")
public class BlockCfgService implements IBlockCfgService {

	private Logger logger = Logger.getLogger(BlockCfgService.class);

	@Resource
	private IBlockCfgDao blockCfgDao;
	@Resource
	private IBlockGroupDao blockGroupDao;

	@Override
	public void save(BlockCfg t) {
		t.setCreateDate(DateUtil.getTimeNow(new Date()));
		t.setState("0");
		blockCfgDao.add(t);
		for (Integer key : t.getGroups().keySet()) {
			saveBlockGroups(t.getGroups().get(key), t.getBolckId(), key);
		}
		logger.debug("Add BlockCfg:" + t);
	}

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

	@Override
	public void update(BlockCfg t) {
		blockCfgDao.update(t);
		logger.debug("Update BlockCfg:" + t);
	}

	@Override
	public void delete(BlockCfg t) {
		blockCfgDao.deleteById(t);
		logger.debug("Del BlockCfg:bolckId=" + t.getBolckId());
	}

	@Override
	public BlockCfg getObject(String id) {
		BlockCfg t = new BlockCfg();
		t.setBolckId(id);
		t = blockCfgDao.findById(t);
		if (t != null) {
			t.setGroups(queryFieldCfg(t));
		}
		logger.debug("Load BlockCfg:bolckId=" + id + ",result=" + t);
		return t;
	}

	@Override
	public List<BlockCfg> queryForPage(BlockCfg t, PageInfo page) {
		String sql = " select * from MPI_BLOCK_CFG where 1=1 ";
		String countSql = page.buildCountSql(sql);
		page.setTotal(blockCfgDao.getCount(countSql, new Object[] {}));
		String querySql = page.buildPageSql(sql);
		logger.debug("Execute sql:[" + sql + "],params[]");
		return blockCfgDao.find(querySql, new Object[] {});
	}

	public void setBlockCfgDao(IBlockCfgDao blockCfgDao) {
		this.blockCfgDao = blockCfgDao;
	}

	public void setBlockGroupDao(IBlockGroupDao blockGroupDao) {
		this.blockGroupDao = blockGroupDao;
	}

	@Override
	public void updateEffect(String cfgId) {

		// 使id配置 生效
		blockCfgDao.effect(cfgId);
		BlockCfg cfg = getObject(cfgId);
		if (cfg != null) {
			// 重新载入配置文件
			BlockConfig.getInstanse().reloadCfg(cfg);
		}
	}

	@Override
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