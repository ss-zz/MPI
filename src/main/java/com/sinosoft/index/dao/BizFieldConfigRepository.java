package com.sinosoft.index.dao;

import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.sinosoft.index.entity.BizFieldConfigModel;

/**
 * 业务字段配置 dao
 * 
 * @author sinosoft
 *
 */
public interface BizFieldConfigRepository extends MongoRepository<BizFieldConfigModel, String> {

	List<BizFieldConfigModel> findByBizConfigId(String bizConfigId);

	List<BizFieldConfigModel> findByKey(String key);

	long countByKey(String key);

	long countByKeyAndBizConfigId(String key, String bizConfigId);

}
