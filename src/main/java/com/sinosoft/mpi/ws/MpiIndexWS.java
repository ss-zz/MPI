package com.sinosoft.mpi.ws;

import java.util.List;

import javax.annotation.Resource;
import javax.jws.WebService;

import com.sinosoft.mpi.model.PersonIndex;
import com.sinosoft.mpi.model.register.BizRegister;
import com.sinosoft.mpi.model.register.PersonRegister;
import com.sinosoft.mpi.service.MpiIndexRegisterService;
import com.sinosoft.mpi.ws.domain.DataResult;
import com.sinosoft.mpi.ws.domain.MpiIndexSearchParams;

/**
 * 主索引服务
 */
@WebService(endpointInterface = "com.sinosoft.mpi.ws.IMpiIndexWS", serviceName = "IndexWS")
public class MpiIndexWS implements IMpiIndexWS {

	@Resource
	private MpiIndexRegisterService mpiIndexRegisterService;

	@Override
	public DataResult<Void> registerPerson(PersonRegister personInfo) {
		return mpiIndexRegisterService.registerPerson(personInfo);
	}

	@Override
	public DataResult<Void> registerPersons(PersonRegister[] personInfos) {
		return null;
	}

	@Override
	public DataResult<Void> registerBiz(BizRegister bizInfo) {
		return null;
	}

	@Override
	public DataResult<Void> registerBizs(BizRegister[] bizInfos) {
		return null;
	}

	@Override
	public DataResult<PersonIndex> getPersonIndexByMpiId(String mpiId) {
		return null;
	}

	@Override
	public DataResult<List<PersonIndex>> searchPersonIndex(MpiIndexSearchParams params) {
		return null;
	}

	@Override
	public DataResult<String[][]> searchBizsByMpiId(String mpiId) {
		return null;
	}

	@Override
	public DataResult<String[][]> searchBizsByMpiIdAndSystemId(String mpiId, String systemId) {
		return null;
	}

}
