package com.sinosoft.index.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sinosoft.index.dao.BizCommonFieldConfigRepository;
import com.sinosoft.index.model.BizCommonFieldConfigModel;

/**
 * 主索引通用业务字段配置服务
 * 
 * @author sinosoft
 *
 */
@Service
public class BizCommonFieldConfigService {

	@Autowired
	BizCommonFieldConfigRepository bizCommonFieldConfigRepository;

	/**
	 * 查询列表
	 * 
	 * @return
	 */
	public List<BizCommonFieldConfigModel> getAll() {
		return bizCommonFieldConfigRepository.findAll();
	}

	/**
	 * 根据id获取详情
	 * 
	 * @param id
	 * @return
	 */
	public BizCommonFieldConfigModel getById(String id) {
		return bizCommonFieldConfigRepository.findOne(id);
	}

	/**
	 * 字段是否存在
	 * 
	 * @param key
	 *            字段唯一标识
	 * @return 字段是否存在
	 */
	public boolean isExist(String key) {
		return bizCommonFieldConfigRepository.countByKey(key) == 0 ? false : true;
	}

	/**
	 * 保存业务字段配置
	 * 
	 * @param bizFieldConfig
	 *            业务字段配置
	 * @return
	 */
	public String save(BizCommonFieldConfigModel bizFieldConfig) {
		BizCommonFieldConfigModel ret = bizCommonFieldConfigRepository.save(bizFieldConfig);
		return ret.getId();
	}

	/**
	 * 删除
	 * 
	 * @param id
	 *            id
	 */
	public void del(String id) {
		bizCommonFieldConfigRepository.delete(id);
	}

}
