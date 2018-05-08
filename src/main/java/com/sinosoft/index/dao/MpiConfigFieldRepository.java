package com.sinosoft.index.dao;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.sinosoft.index.entity.MpiConfigField;

/**
 * 主索引字段配置dao
 */
public interface MpiConfigFieldRepository extends MongoRepository<MpiConfigField, String> {

	int countByPropertyName(String propertyName);

}
