package com.sinosoft.mpi.service;

import com.sinosoft.mpi.model.MpiCombineRec;

public interface IMpiCombineRecService extends IService<MpiCombineRec> {

	public MpiCombineRec queryByCombineNo(Long combineNo);
}
