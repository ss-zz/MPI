package com.sinosoft.index.dao;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.sinosoft.index.model.BizCommonFieldConfigModel;

/**
 * 通用业务字段配置 dao
 * 
 * @author sinosoft
 *
 */
public interface BizCommonFieldConfigRepository extends MongoRepository<BizCommonFieldConfigModel, String> {

	long countByKey(String key);

}
