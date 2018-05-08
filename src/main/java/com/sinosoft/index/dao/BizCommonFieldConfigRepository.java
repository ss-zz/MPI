package com.sinosoft.index.dao;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.sinosoft.index.entity.BizCommonFieldConfig;

/**
 * 通用业务字段配置 dao
 * 
 * @author sinosoft
 *
 */
public interface BizCommonFieldConfigRepository extends MongoRepository<BizCommonFieldConfig, String> {

	long countByFieldName(String fieldName);

}
