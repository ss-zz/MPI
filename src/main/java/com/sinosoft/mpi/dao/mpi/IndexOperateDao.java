package com.sinosoft.mpi.dao.mpi;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import com.sinosoft.mpi.model.IndexOperate;

/**
 * 主索引操作关系dao
 */
public interface IndexOperateDao extends JpaRepository<IndexOperate, String>, JpaSpecificationExecutor<IndexOperate> {

}
