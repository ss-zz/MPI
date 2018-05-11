package com.sinosoft.mpi.service;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.sinosoft.mpi.dao.MpiCombineLevelDao;
import com.sinosoft.mpi.model.PersonIndex;
import com.sinosoft.mpi.model.PersonInfo;

/**
 * 人员合并级别服务
 *
 */
@Service
public class MpiCombineLevelService {

	@Resource
	MpiCombineLevelDao mpiCombineLevelDao;

	/**
	 * 新数据来源字段级别
	 * 
	 * @param personindex
	 * @param combono
	 * @param domainLevel
	 * @param srcLevelcolmap
	 */
	public void batchAddLevel(PersonIndex personindex, Long combono, Short domainLevel,
			List<Map<String, Object>> srcLevelcolmap) {
		mpiCombineLevelDao.batchAddLevel(personindex, combono, domainLevel, srcLevelcolmap);
	}

	/**
	 * 合并字段级别信息
	 * 
	 * @param personindex
	 * @param personinfo
	 * @param combono
	 * @param domainLevel
	 * @param orgincolLevellist
	 * @param srcLevelcollist
	 * @return
	 */
	public PersonIndex compareBatchAdd(PersonIndex personindex, PersonInfo personinfo, Long combono, Short domainLevel,
			List<Map<String, Object>> orgincolLevellist, List<Map<String, Object>> srcLevelcollist) {
		return mpiCombineLevelDao.compareBatchAdd(personindex, personinfo, combono, domainLevel, orgincolLevellist,
				srcLevelcollist);
	}

}
