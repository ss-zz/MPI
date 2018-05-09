package com.sinosoft.mpi.service;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.sinosoft.mpi.dao.MpiCombineRecDao;
import com.sinosoft.mpi.model.MpiCombineRec;
import com.sinosoft.mpi.util.PageInfo;

@Service
public class MpiCombineRecService {

	@Resource
	MpiCombineRecDao mpiCombineRecDao;

	public void save(MpiCombineRec t) {
		mpiCombineRecDao.add(t);
	}

	public void update(MpiCombineRec t) {

	}

	public void delete(MpiCombineRec t) {

	}

	public MpiCombineRec getObject(String id) {
		return null;
	}

	public List<MpiCombineRec> queryForPage(MpiCombineRec t, PageInfo page) {
		return null;
	}

	public MpiCombineRec queryByCombineNo(Long combineNo) {
		MpiCombineRec t = mpiCombineRecDao.find(combineNo);
		return t;
	}

}
