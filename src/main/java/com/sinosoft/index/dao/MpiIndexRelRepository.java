package com.sinosoft.index.dao;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.sinosoft.index.entity.MpiIndexRel;

/**
 * mpi主索引与原始数据关联关系dao
 */
public interface MpiIndexRelRepository extends MongoRepository<MpiIndexRel, String> {

}
