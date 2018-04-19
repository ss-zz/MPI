package com.sinosoft.mpi.dao;

import com.sinosoft.mpi.model.MpiCombineRec;

public interface IMpiCombineRecDao extends IBaseDao<MpiCombineRec> {
	public MpiCombineRec find(Long sql);
}
