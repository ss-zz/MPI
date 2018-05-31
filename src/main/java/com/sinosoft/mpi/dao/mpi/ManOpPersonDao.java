package com.sinosoft.mpi.dao.mpi;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import com.sinosoft.mpi.model.ManOpPerson;

/**
 * 需要人工干预的人员列表dao
 */
public interface ManOpPersonDao extends JpaRepository<ManOpPerson, String>, JpaSpecificationExecutor<ManOpPerson> {

	/**
	 * 更新数据状态
	 * @param manOpId
	 * @param state
	 * @param manOpTime
	 * @return
	 */
	@Modifying
	@Query("update #{#entityName} t set t.manOpStatus = ?2, t.manOpTime = ?3  where t.manOpId = ?1")
	int updateState(String manOpId, String state, String manOpTime);

}
