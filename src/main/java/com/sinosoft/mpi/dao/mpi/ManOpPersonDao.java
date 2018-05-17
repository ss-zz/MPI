package com.sinosoft.mpi.dao.mpi;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import com.sinosoft.mpi.model.ManOpPerson;

/**
 * 需要人工干预的人员列表dao
 */
public interface ManOpPersonDao extends JpaRepository<ManOpPerson, String>, JpaSpecificationExecutor<ManOpPerson> {

}
