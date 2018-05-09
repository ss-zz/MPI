package com.sinosoft.mpi.service;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.sinosoft.mpi.dao.MpiCombineLevelDao;
import com.sinosoft.mpi.model.MpiCombineLevel;
import com.sinosoft.mpi.model.PersonIndex;
import com.sinosoft.mpi.model.PersonInfo;
import com.sinosoft.mpi.util.PageInfo;

@Service
public class MpiCombineLevelService {

	@Resource
	MpiCombineLevelDao mpiCombineLevelDao;

	public void save(MpiCombineLevel t) {

	}

	public void update(MpiCombineLevel t) {

	}

	public void delete(MpiCombineLevel t) {

	}

	public MpiCombineLevel getObject(String id) {
		return null;
	}

	public List<MpiCombineLevel> queryForPage(MpiCombineLevel t, PageInfo page) {
		return null;
	}

	/**
	 * @param args
	 */
	public static void main(String[] args) {

	}

	public void batchAddLevel(PersonIndex personindex, Long combono, Short domainLevel,
			List<Map<String, Object>> srcLevelcolmap) {
		mpiCombineLevelDao.batchAddLevel(personindex, combono, domainLevel, srcLevelcolmap);
	}

	public PersonIndex compareBatchAdd(PersonIndex personindex, PersonInfo personinfo, Long combono, Short domainLevel,
			List<Map<String, Object>> orgincolLevellist, List<Map<String, Object>> srcLevelcollist) {
		return mpiCombineLevelDao.compareBatchAdd(personindex, personinfo, combono, domainLevel, orgincolLevellist,
				srcLevelcollist);
	}

}
