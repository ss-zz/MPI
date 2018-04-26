package com.sinosoft.index.dao;

import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.sinosoft.index.model.BizConfigModel;

/**
 * 业务配置dao
 * 
 * @author sinosoft
 *
 */
public interface BizConfigRepository extends MongoRepository<BizConfigModel, String> {

	List<BizConfigModel> findByKey(String key);

	long countByKey(String key);

}
