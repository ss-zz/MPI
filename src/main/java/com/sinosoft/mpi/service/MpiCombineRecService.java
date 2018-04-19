package com.sinosoft.mpi.service;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.sinosoft.mpi.dao.IMpiCombineRecDao;
import com.sinosoft.mpi.model.MpiCombineRec;
import com.sinosoft.mpi.util.PageInfo;

@Service
public class MpiCombineRecService implements IMpiCombineRecService {

	@Resource
	IMpiCombineRecDao mpiCombineRecDao;

	@Override
	public void save(MpiCombineRec t) {
		mpiCombineRecDao.add(t);
	}

	@Override
	public void update(MpiCombineRec t) {

	}

	@Override
	public void delete(MpiCombineRec t) {

	}

	@Override
	public MpiCombineRec getObject(String id) {
		return null;
	}

	@Override
	public List<MpiCombineRec> queryForPage(MpiCombineRec t, PageInfo page) {
		return null;
	}

	@Override
	public MpiCombineRec queryByCombineNo(Long combineNo) {
		MpiCombineRec t = mpiCombineRecDao.find(combineNo);
		return t;
	}

}
