package com.sinosoft.index.service;

import static org.springframework.data.mongodb.core.query.Criteria.where;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Service;

import com.sinosoft.index.dao.MpiIndexConfigRepository;
import com.sinosoft.index.entity.BizMatchServConfig;
import com.sinosoft.index.entity.MpiIndexConfig;

/**
 * mpi配置服务
 */
@Service
public class MpiConfigService {

	private static final String TABLE_NAME = "mpi-config";

	@Autowired
	MpiIndexConfigRepository mpiIndexConfigRepository;
	@Autowired
	MongoTemplate template;

	/**
	 * 获取mpi主索引配置
	 * 
	 * @return 主索引配置
	 */
	public MpiIndexConfig getMpiConfig() {
		List<MpiIndexConfig> configs = mpiIndexConfigRepository.findAll();
		return configs.size() > 0 ? configs.get(0) : null;
	}

	/**
	 * 根据业务唯一标识获取业务匹配具体配置
	 * 
	 * @param bizKey
	 *            业务唯一标识
	 * @return 业务匹配具体配置
	 */
	public BizMatchServConfig getBizMatchServConfigByBizName(String bizKey) {
		return template.findOne(new Query(where("bizMatchConfig.bizMatchServConfigs.servName").is(bizKey)),
				BizMatchServConfig.class, TABLE_NAME);
	}

}
