package com.sinosoft.index.dao;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.sinosoft.index.model.MpiIndexModel;

/**
 * mpi index dao
 * 
 * @author sinosoft
 *
 */
public interface MpiIndexRepository extends MongoRepository<MpiIndexModel, String> {

}
