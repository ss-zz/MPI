package com.sinosoft.index.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Service;

import com.sinosoft.index.dao.MpiBizRelRepository;
import com.sinosoft.index.entity.MpiBizRel;

/**
 * mpi 业务数据与次数据、主索引数据关联服务
 */
@Service
public class MpiBizRelService {

	private static final String TABLE_NAME = "mpi-biz-rel";

	@Autowired
	MpiBizRelRepository mpiBizRelRepository;
	@Autowired
	MongoTemplate mongoTemplate;

	/**
	 * 保存数据
	 * 
	 * @param bizRel
	 *            保存对象
	 */
	public MpiBizRel save(MpiBizRel bizRel) {
		return mpiBizRelRepository.save(bizRel);
	}

	/**
	 * 查询数据
	 * 
	 * @param q
	 *            查询条件
	 * @return 结果集
	 */
	public List<MpiBizRel> find(Query q) {
		return mongoTemplate.find(q, MpiBizRel.class, TABLE_NAME);
	}

}
