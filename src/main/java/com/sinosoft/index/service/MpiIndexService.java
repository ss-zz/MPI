package com.sinosoft.index.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Service;

import com.sinosoft.index.dao.MpiIndexRepository;
import com.sinosoft.index.entity.MpiIndex;

/**
 * mpi 主索引服务-index
 * 
 * @author sinosoft
 *
 */
@Service
public class MpiIndexService {

	private static final String TABLE_NAME = "mpi-index";

	@Autowired
	MpiIndexRepository mpiIndexRepository;
	@Autowired
	MongoTemplate mongoTemplate;

	/**
	 * 保存数据
	 * 
	 * @param o
	 *            保存对象
	 */
	public MpiIndex saveIndex(MpiIndex index) {
		return mpiIndexRepository.save(index);
	}

	/**
	 * 查询数据
	 * 
	 * @param q
	 *            查询条件
	 * @return 结果集
	 */
	public List<MpiIndex> findIndex(Query q) {
		return mongoTemplate.find(q, MpiIndex.class, TABLE_NAME);
	}

}
