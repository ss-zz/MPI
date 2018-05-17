package com.sinosoft.mpi.dao.mpi;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import com.sinosoft.mpi.model.IndexIdentifierRel;

/**
 * 合并信息标示关系dao
 */
public interface IndexIdentifierRelDao
		extends JpaRepository<IndexIdentifierRel, String>, JpaSpecificationExecutor<IndexIdentifierRel> {

	/**
	 * 根据mpiPk查询
	 * 
	 * @param mpiPk
	 * @return
	 */
	List<IndexIdentifierRel> findByMpiPk(String mpiPk);

	/**
	 * 递归删除数据
	 * 
	 * @param combineNo
	 */
	@Modifying
	@Query(value = "delete from MPI_INDEX_IDENTIFIER_REL r where r.combine_no in ("
			+ "select combine_no from MPI_INDEX_IDENTIFIER_REL t start with combine_no = ?1 "
			+ "connect by combine_rec = prior combine_no) ", nativeQuery = true)
	void deleteRecurByCombinNo(Long combineNo);

	/**
	 * 根据fieldPk查询匹配的第一条记录
	 * 
	 * @param fieldPk
	 * @return
	 */
	IndexIdentifierRel findFirstByFieldPkOrderByCombineNoDesc(String fieldPk);

	/**
	 * 根据mpiPk查询最新的合并记录
	 * 
	 * @param mpiPk
	 * @return
	 */
	IndexIdentifierRel findFirstByMpiPkOrderByCombineRecDesc(String mpiPk);

	/**
	 * 根据 fieldPk 删除
	 * 
	 * @param fieldPk
	 * @return
	 */
	long deleteByFieldPk(String fieldPk);

	/**
	 * 根据fieldPk查询第一个
	 * 
	 * @param fieldPk
	 * @return
	 */
	IndexIdentifierRel findFirstByFieldPk(String fieldPk);

	/**
	 * 根据mpiPk和fieldpk删除
	 * 
	 * @param mpiPk
	 * @param fieldpk
	 * @return
	 */
	long deleteByMpiPkAndFieldPk(String mpiPk, String fieldpk);

}
