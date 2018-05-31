package com.sinosoft.mpi.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

import com.sinosoft.mpi.model.PersonIndex;
import com.sinosoft.mpi.model.search.BizSearchResult;
import com.sinosoft.mpi.service.biz.BizIndexService;
import com.sinosoft.mpi.util.PageInfo;
import com.sinosoft.mpi.ws.domain.DataResult;
import com.sinosoft.mpi.ws.domain.MpiIndexSearchParams;

/**
 * mpi 主索引查询服务
 */
@Service
public class MpiIndexSearchService {

	@Autowired
	PersonIndexService personIndexService;

	@Autowired
	BizIndexService bizIndexService;

	/**
	 * 根据主索引id查询主索引详情
	 * 
	 * @param mpiId
	 * @return
	 */
	public DataResult<PersonIndex> getPersonIndexByMpiId(String mpiId) {
		return new DataResult<PersonIndex>(true, "成功", personIndexService.get(mpiId));
	}

	/**
	 * 分页查询主索引信息
	 * 
	 * @param params
	 * @param page
	 * @return
	 */
	public DataResult<Page<PersonIndex>> searchPersonIndex(MpiIndexSearchParams params, PageInfo page) {
		Page<PersonIndex> ret = personIndexService.queryForPage(params, page);
		return new DataResult<Page<PersonIndex>>(true, "成功", ret);
	}

	/**
	 * 根据主索引id查询关联的业务数据信息
	 * 
	 * @param mpiId
	 * @return
	 */
	public DataResult<List<BizSearchResult>> searchBizsByMpiId(String mpiId) {
		List<BizSearchResult> ret = bizIndexService.searchBizsByMpiId(mpiId);
		return new DataResult<List<BizSearchResult>>(true, "成功", ret);
	}

	/**
	 * 根据主索引id和业务系统唯一标识查询关联的业务数据信息
	 * 
	 * @param mpiId
	 * @return
	 */
	public DataResult<List<BizSearchResult>> searchBizsByMpiIdAndSystemId(String mpiId, String systemId) {
		List<BizSearchResult> ret = bizIndexService.searchBizsByMpiIdAndSystemId(mpiId, systemId);
		return new DataResult<List<BizSearchResult>>(true, "成功", ret);
	}

}
