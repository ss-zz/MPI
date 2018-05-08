package com.sinosoft.index.dao;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.sinosoft.index.entity.MpiIndex;

/**
 * mpi index dao 主索引
 * 
 * @author sinosoft
 *
 */
public interface MpiIndexRepository extends MongoRepository<MpiIndex, String> {

}
