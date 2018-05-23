package com.sinosoft.mpi.ws;

import java.util.List;

import javax.jws.WebService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;

import com.sinosoft.mpi.model.PersonIndex;
import com.sinosoft.mpi.model.register.BizRegister;
import com.sinosoft.mpi.model.register.PersonRegister;
import com.sinosoft.mpi.model.search.BizSearchResult;
import com.sinosoft.mpi.service.MpiIndexRegisterService;
import com.sinosoft.mpi.service.MpiIndexSearchService;
import com.sinosoft.mpi.util.PageInfo;
import com.sinosoft.mpi.ws.domain.DataResult;
import com.sinosoft.mpi.ws.domain.MpiIndexSearchParams;

/**
 * 主索引服务
 */
@WebService(endpointInterface = "com.sinosoft.mpi.ws.IMpiIndexWS", serviceName = "IndexWS")
public class MpiIndexWS implements IMpiIndexWS {

	@Autowired
	MpiIndexRegisterService mpiIndexRegisterService;
	@Autowired
	MpiIndexSearchService mpiIndexSearchService;

	@Override
	public DataResult<Void> registerPerson(PersonRegister personRegister) {
		return mpiIndexRegisterService.registerPerson(personRegister);
	}

	@Override
	public DataResult<Void> registerPersons(PersonRegister[] personRegisters) {
		return mpiIndexRegisterService.registerPersons(personRegisters);
	}

	@Override
	public DataResult<Void> registerBiz(BizRegister bizRegister) {
		return mpiIndexRegisterService.registerBiz(bizRegister);
	}

	@Override
	public DataResult<Void> registerBizs(BizRegister[] bizInfos) {
		return mpiIndexRegisterService.registerBizs(bizInfos);
	}

	@Override
	public DataResult<PersonIndex> getPersonIndexByMpiId(String mpiId) {
		return mpiIndexSearchService.getPersonIndexByMpiId(mpiId);
	}

	@Override
	public DataResult<Page<PersonIndex>> searchPersonIndex(MpiIndexSearchParams params, PageInfo page) {
		return mpiIndexSearchService.searchPersonIndex(params, page);
	}

	@Override
	public DataResult<List<BizSearchResult>> searchBizsByMpiId(String mpiId) {
		return mpiIndexSearchService.searchBizsByMpiId(mpiId);
	}

	@Override
	public DataResult<List<BizSearchResult>> searchBizsByMpiIdAndSystemId(String mpiId, String systemId) {
		return mpiIndexSearchService.searchBizsByMpiIdAndSystemId(mpiId, systemId);
	}

}
