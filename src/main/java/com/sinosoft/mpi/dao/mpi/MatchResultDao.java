package com.sinosoft.mpi.dao.mpi;

import java.util.List;

import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import com.sinosoft.mpi.model.MatchResult;

/**
 * 匹配结果dao
 */
public interface MatchResultDao extends JpaRepository<MatchResult, String>, JpaSpecificationExecutor<MatchResult> {

	/**
	 * 根据fieldPk删除
	 * 
	 * @param fieldPk
	 */
	long deleteByFieldPk(String fieldPk);

	/**
	 * 根据fieldPk分页查询
	 * 
	 * @param fieldPk
	 * @param pageable
	 * @return
	 */
	List<MatchResult> findByFieldPkOrderByMatchDegreeDesc(String fieldPk, Pageable pageable);

	/**
	 * 根据mpiPk和fieldPk查询
	 * 
	 * @param mpiPk
	 * @param fieldPk
	 * @return
	 */
	List<MatchResult> findByMpiPkAndFieldPkOrderByMatchDegreeDesc(String mpiPk, String fieldPk);

	/**
	 * 根据personId和idxIds查询
	 * 
	 * @param personId
	 * @param idxIds
	 * @return
	 */
	List<MatchResult> findByFieldPkAndMpiPkInOrderByMatchDegreeDesc(String personId, String[] idxIds);

	/**
	 * 根据personId统计数量
	 * 
	 * @param personId
	 * @return
	 */
	int countByFieldPk(String personId);

	/**
	 * 根据mpiPK和fieldPK查询
	 * 
	 * @param mpiPK
	 * @param fieldPK
	 * @return
	 */
	MatchResult findFirstByMpiPkAndFieldPk(String mpiPK, String fieldPK);

}
