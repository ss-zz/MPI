package com.sinosoft.mpi.dao.mpi;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import com.sinosoft.mpi.model.PinyinKind;

/**
 * 拼音dao
 */
public interface PinyinDicDao extends JpaRepository<PinyinKind, String>, JpaSpecificationExecutor<PinyinKind> {

}
