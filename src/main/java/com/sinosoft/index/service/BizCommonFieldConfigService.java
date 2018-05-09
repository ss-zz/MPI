package com.sinosoft.index.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sinosoft.index.dao.BizCommonFieldConfigRepository;
import com.sinosoft.index.entity.BizCommonFieldConfig;
import com.sinosoft.index.exception.ServiceException;

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
	public List<BizCommonFieldConfig> getAll() {
		return bizCommonFieldConfigRepository.findAll();
	}

	/**
	 * 根据id获取详情
	 * 
	 * @param id
	 * @return
	 */
	public BizCommonFieldConfig getById(String id) {
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
		return bizCommonFieldConfigRepository.countByFieldName(key) == 0 ? false : true;
	}

	/**
	 * 保存通用业务字段配置
	 * 
	 * @param bizCommonFieldConfig
	 *            通用业务字段配置
	 * @return
	 * @throws ServiceException
	 *             业务异常
	 */
	public String save(BizCommonFieldConfig bizCommonFieldConfig) throws ServiceException {
		if (bizCommonFieldConfig == null) {
			return null;
		}
		if (bizCommonFieldConfig.getId() == null) {// 新增
			if (isExist(bizCommonFieldConfig.getFieldName())) {
				throw new ServiceException("通用业务字段唯一标识已存在");
			}
		}
		return bizCommonFieldConfigRepository.save(bizCommonFieldConfig).getId();
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
