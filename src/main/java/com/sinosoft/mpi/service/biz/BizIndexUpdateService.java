package com.sinosoft.mpi.service.biz;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.sinosoft.mpi.dao.biz.BizIndexDao;
import com.sinosoft.mpi.model.biz.BizIndex;
import com.sinosoft.mpi.model.biz.BizInfo;

/**
 * 业务主索引更新服务
 */
@Service
public class BizIndexUpdateService {

	@Resource
	BizIndexDao bizIndexDao;
	List<Map<String, Object>> orgincollevellist = null;

	/**
	 * 更新索引信息
	 * 
	 * @param bizInfo
	 * @param indexId
	 */
	public void updateIndex(BizInfo bizInfo, String indexId) {
		updateIndexDirect(bizInfo, indexId);
	}

	/**
	 * 直接更新 索引信息
	 */
	private void updateIndexDirect(BizInfo bizInfo, String indexId) {
		BizIndex idx = bizInfo.toIndex();
		idx.setId(indexId);
		bizIndexDao.update(idx);
	}

}
