package com.sinosoft.mpi.dao.biz;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import com.sinosoft.mpi.model.biz.MpiBizIndex;

/**
 * 业务主索引dao
 */
public interface MpiBizIndexDao extends JpaRepository<MpiBizIndex, String>, JpaSpecificationExecutor<MpiBizIndex> {

}
