package com.sinosoft.index.dao;

import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.sinosoft.index.entity.BizMatchServConfig;

/**
 * 业务配置
 *
 */
public interface BizMatchServConfigRepository extends MongoRepository<BizMatchServConfig, String> {

	List<BizMatchServConfig> findByServName(String key);

}
