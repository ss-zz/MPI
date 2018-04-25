package com.sinosoft.index.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Service;

import com.sinosoft.index.dao.MpiIndexRepository;
import com.sinosoft.index.model.MpiIndexModel;

/**
 * mpi 主索引服务-index
 * 
 * @author sinosoft
 *
 */
@Service
public class MpiIndexService {

	@Autowired
	MpiIndexRepository mpiIndexRepository;

	/**
	 * 保存数据
	 * 
	 * @param o
	 *            保存对象
	 */
	public void saveIndex(MpiIndexModel index) {
		mpiIndexRepository.save(index);
	}

	/**
	 * 查询数据
	 * 
	 * @param q
	 *            查询条件
	 * @return 结果集
	 */
	public List<MpiIndexModel> findIndex(Query q) {
		return null;
	}

}
