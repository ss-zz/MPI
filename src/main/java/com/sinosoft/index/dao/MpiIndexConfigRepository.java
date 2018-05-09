package com.sinosoft.index.dao;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.sinosoft.index.entity.MpiIndexConfig;

/**
 * 主索引配置dao
 */
public interface MpiIndexConfigRepository extends MongoRepository<MpiIndexConfig, String> {

}
