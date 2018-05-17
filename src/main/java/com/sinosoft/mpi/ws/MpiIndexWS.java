package com.sinosoft.mpi.ws;

import java.util.List;

import javax.annotation.Resource;
import javax.jws.WebService;

import com.sinosoft.mpi.exception.ValidationException;
import com.sinosoft.mpi.model.PersonIndex;
import com.sinosoft.mpi.model.PersonInfo;
import com.sinosoft.mpi.model.register.BizRegister;
import com.sinosoft.mpi.model.register.PersonRegister;
import com.sinosoft.mpi.service.PersonInfoService;
import com.sinosoft.mpi.ws.domain.DataResult;
import com.sinosoft.mpi.ws.domain.MpiIndexSearchParams;

/**
 * 主索引服务
 */
@WebService(endpointInterface = "com.sinosoft.mpi.ws.IMpiIndexWS", serviceName = "IndexWS")
public class MpiIndexWS implements IMpiIndexWS {

	@Resource
	private PersonInfoService personInfoService;

	@Override
	public DataResult<Void> registerPerson(PersonRegister personInfo) {
		DataResult<Void> result = null;
		try {
			PersonInfo person = personInfo.getPersonInfo();
			personInfoService.save(person);
			result = new DataResult<Void>(true,
					"人员添加成功 pname = " + person.getNameCn() + " personId = " + person.getFieldPk());
		} catch (ValidationException e) { // 验证异常

			result = new DataResult<Void>(false, e.getMessage());
		} catch (Throwable e) { // 其他错误
			e.printStackTrace();
			result = new DataResult<Void>(false, "系统错误,无法完成添加居民信息操作");
		}
		return result;
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
