package com.sinosoft.mpi.dao.biz;

import java.util.List;
import java.util.Map;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

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
	
	/**
	 * 查询所有生效的数据
	 * 
	 * @param state
	 * @return
	 */
	@Modifying
	@Query(value ="select l from MpiBizIdxLog l where l.blIdxLogId =?1")
	MpiBizIdxLog findLogById(String logId);

}
