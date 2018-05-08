package com.sinosoft.index.dao;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.sinosoft.index.entity.MpiBizRel;

/**
 * mpi业务数据与次数据、主索引数据关联关系dao
 */
public interface MpiBizRelRepository extends MongoRepository<MpiBizRel, String> {

}
