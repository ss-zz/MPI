package com.sinosoft.index.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sinosoft.index.dao.BizConfigRepository;
import com.sinosoft.index.model.BizConfigModel;

/**
 * 主索引业务配置服务
 * 
 * @author sinosoft
 *
 */
@Service
public class BizConfigService {

	@Autowired
	BizConfigRepository bizConfigRepository;

	/**
	 * 查询列表
	 * 
	 * @return
	 */
	public List<BizConfigModel> getAll() {
		return bizConfigRepository.findAll();
	}

	/**
	 * 根据id获取详情
	 * 
	 * @param id
	 * @return
	 */
	public BizConfigModel getById(String id) {
		return bizConfigRepository.findOne(id);
	}

	/**
	 * 根据唯一标识获取列表
	 * 
	 * @param key
	 *            唯一标识
	 * @return
	 */
	public List<BizConfigModel> getByKey(String key) {
		return bizConfigRepository.findByKey(key);
	}

	/**
	 * 业务是否存在
	 * 
	 * @param key
	 *            业务唯一标识
	 * @return 业务是否存在
	 */
	public boolean isExist(String key) {
		return bizConfigRepository.countByKey(key) == 0 ? false : true;
	}

	/**
	 * 保存业务
	 * 
	 * @param bizConfig
	 *            业务配置
	 * @return
	 */
	public String save(BizConfigModel bizConfig) {
		BizConfigModel ret = bizConfigRepository.save(bizConfig);
		return ret.getId();
	}

	/**
	 * 删除
	 * 
	 * @param id
	 *            id
	 */
	public void del(String id) {
		bizConfigRepository.delete(id);
	}

}
