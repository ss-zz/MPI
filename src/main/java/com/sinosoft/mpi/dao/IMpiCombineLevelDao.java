package com.sinosoft.mpi.dao;

import java.util.List;
import java.util.Map;

import com.sinosoft.mpi.model.MpiCombineLevel;
import com.sinosoft.mpi.model.PersonIndex;
import com.sinosoft.mpi.model.PersonInfo;

public interface IMpiCombineLevelDao extends IBaseDao<MpiCombineLevel> {

	public void batchAddLevel(PersonIndex personindex, Long combono, Short domainLevel,
			List<Map<String, Object>> srcLevelcolmap);

	public PersonIndex compareBatchAdd(PersonIndex personindex, PersonInfo personinfo, Long combono, Short domainLevel,
			List<Map<String, Object>> orgincolLevellist, List<Map<String, Object>> srcLevelcollist);

}
