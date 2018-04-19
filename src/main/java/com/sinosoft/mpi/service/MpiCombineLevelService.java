package com.sinosoft.mpi.service;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.sinosoft.mpi.dao.IMpiCombineLevelDao;
import com.sinosoft.mpi.model.MpiCombineLevel;
import com.sinosoft.mpi.model.PersonIndex;
import com.sinosoft.mpi.model.PersonInfo;
import com.sinosoft.mpi.util.PageInfo;

@Service
public class MpiCombineLevelService implements IMpiCombineLevelService {

	
	@Resource
	IMpiCombineLevelDao mpiCombineLevelDao;
	@Override
	public void save(MpiCombineLevel t) {
		// TODO Auto-generated method stub

	}

	@Override
	public void update(MpiCombineLevel t) {
		// TODO Auto-generated method stub

	}

	@Override
	public void delete(MpiCombineLevel t) {
		// TODO Auto-generated method stub

	}

	@Override
	public MpiCombineLevel getObject(String id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<MpiCombineLevel> queryForPage(MpiCombineLevel t, PageInfo page) {
		// TODO Auto-generated method stub
		return null;
	}

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

	@Override
	public void batchAddLevel(PersonIndex personindex, Long combono,
			Short domainLevel, List<Map<String, Object>> srcLevelcolmap) {
		
		
		mpiCombineLevelDao.batchAddLevel(personindex,combono,domainLevel,srcLevelcolmap);
	}

	@Override
	public PersonIndex compareBatchAdd(PersonIndex personindex,
			PersonInfo personinfo, Long combono, Short domainLevel,
			List<Map<String, Object>> orgincolLevellist,
			List<Map<String, Object>> srcLevelcollist) {
		return mpiCombineLevelDao.compareBatchAdd(personindex, personinfo, combono, domainLevel,orgincolLevellist,srcLevelcollist);
	}

}