package com.sinosoft.index.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sinosoft.index.dao.BizFieldConfigRepository;
import com.sinosoft.index.model.BizFieldConfigModel;

/**
 * 主索引业务字段配置服务
 * 
 * @author sinosoft
 *
 */
@Service
public class BizFieldConfigService {

	@Autowired
	BizFieldConfigRepository bizFieldConfigRepository;

	/**
	 * 查询列表
	 * 
	 * @return
	 */
	public List<BizFieldConfigModel> getAll() {
		return bizFieldConfigRepository.findAll();
	}

	/**
	 * 根据id获取详情
	 * 
	 * @param id
	 * @return
	 */
	public BizFieldConfigModel getById(String id) {
		return bizFieldConfigRepository.findOne(id);
	}

	/**
	 * 根据业务配置id获取列表
	 * 
	 * @param bizConfigId
	 *            业务配置id
	 * @return
	 */
	public List<BizFieldConfigModel> getByBizConfigId(String bizConfigId) {
		return bizFieldConfigRepository.findByBizConfigId(bizConfigId);
	}

	/**
	 * 字段是否存在
	 * 
	 * @param key
	 *            字段唯一标识
	 * @return 字段是否存在
	 */
	public boolean isExist(String key) {
		return bizFieldConfigRepository.countByKey(key) == 0 ? false : true;
	}

	/**
	 * 保存业务字段配置
	 * 
	 * @param bizFieldConfig
	 *            业务字段配置
	 * @return
	 */
	public String save(BizFieldConfigModel bizFieldConfig) {
		return bizFieldConfigRepository.save(bizFieldConfig).getId();
	}

	/**
	 * 删除
	 * 
	 * @param id
	 *            id
	 */
	public void del(String id) {
		bizFieldConfigRepository.delete(id);
	}

}
