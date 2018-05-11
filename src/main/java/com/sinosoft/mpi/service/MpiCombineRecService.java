package com.sinosoft.mpi.service;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.sinosoft.mpi.dao.MpiCombineRecDao;
import com.sinosoft.mpi.model.MpiCombineRec;

/**
 * 人员合并源服务
 */
@Service
public class MpiCombineRecService {

	@Resource
	MpiCombineRecDao mpiCombineRecDao;

	/**
	 * 保存
	 */
	public void save(MpiCombineRec t) {
		mpiCombineRecDao.add(t);
	}

	/**
	 * 根据合并号查询
	 * 
	 * @param combineNo
	 * @return
	 */
	public MpiCombineRec queryByCombineNo(Long combineNo) {
		return mpiCombineRecDao.find(combineNo);
	}

}
