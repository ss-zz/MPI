package com.sinosoft.mpi.dao.biz;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import com.sinosoft.mpi.model.biz.MpiBizIdxLog;

/**
 * 业务日志主索引dao
 */
public interface MpiBizIdxLogDao extends JpaRepository<MpiBizIdxLog, String>, JpaSpecificationExecutor<MpiBizIdxLog> {

	/**
	 * 根据人员查询操作日志列表
	 * 
	 * @param blUserId
	 *            操作人员id
	 * @return
	 */
	List<MpiBizIdxLog> findByBlUserId(String blUserId);

}
