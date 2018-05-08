package com.sinosoft.index.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Service;

import com.sinosoft.index.dao.MpiIndexRelRepository;
import com.sinosoft.index.entity.MpiIndexRel;

/**
 * mpi 主索引与原始数据关联服务
 */
@Service
public class MpiIndexRelService {

	private static final String TABLE_NAME = "mpi-index-rel";

	@Autowired
	MpiIndexRelRepository mpiIndexRelRepository;
	@Autowired
	MongoTemplate mongoTemplate;

	/**
	 * 保存数据
	 * 
	 * @param indexRel
	 *            保存对象
	 */
	public MpiIndexRel save(MpiIndexRel indexRel) {
		return mpiIndexRelRepository.save(indexRel);
	}

	/**
	 * 查询数据
	 * 
	 * @param q
	 *            查询条件
	 * @return 结果集
	 */
	public List<MpiIndexRel> find(Query q) {
		return mongoTemplate.find(q, MpiIndexRel.class, TABLE_NAME);
	}

}
