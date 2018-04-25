package com.sinosoft.index.dao;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.sinosoft.index.model.MpiInfoModel;

/**
 * mpi info dao
 * 
 * @author sinosoft
 *
 */
public interface MpiInfoRepository extends MongoRepository<MpiInfoModel, String> {

}
