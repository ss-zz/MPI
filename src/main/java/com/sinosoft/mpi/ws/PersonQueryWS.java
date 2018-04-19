package com.sinosoft.mpi.ws;

import java.util.List;

import javax.annotation.Resource;
import javax.jws.WebService;

import org.apache.log4j.Logger;

import com.sinosoft.mpi.exception.BaseBussinessException;
import com.sinosoft.mpi.exception.ValidationException;
import com.sinosoft.mpi.model.PersonIndex;
import com.sinosoft.mpi.model.PersonInfo;
import com.sinosoft.mpi.model.PersonInfoSimple;
import com.sinosoft.mpi.service.IPersonInfoService;

@WebService(endpointInterface = "com.sinosoft.mpi.ws.IPersonQueryWS", serviceName = "PersonQueryWS")
public class PersonQueryWS implements IPersonQueryWS {
	private static Logger logger = Logger.getLogger(PersonQueryWS.class);
	
	@Resource
	private IPersonInfoService personInfoService;
	
	@Override
	public QueryResult queryIndexByIdentifier(PersonInfoSimple simplePerson) {
		QueryResult result = new QueryResult();
		try {
			PersonIndex index = personInfoService.queryPersonIndexByPersonInfo(simplePerson.toPersonInfo());
			result.setIndex(index);
		} catch (ValidationException e) {
			result.setSuccess(false);
			result.setMsg(e.getMessage());
		} catch (Throwable e) {
			logger.debug("查询索引信息时出现错误", e);
			result.setSuccess(false);
			result.setMsg("查询索引信息时出现错误");
		}
		
		return result;
	}

	@Override
	public QueryResult queryPersonByAttributes(PersonInfo person) {
		QueryResult result = new QueryResult();
		try {
			List<PersonInfo> list = personInfoService.queryPersonByAttributes(person);
			result.setPersons(list.toArray(new PersonInfo[list.size()]));
		} catch (BaseBussinessException e) {
			logger.debug("查询结果过多,请增加条件缩小查询范围!", e);
			result.setSuccess(false);
			result.setMsg("查询结果过多,请增加条件缩小查询范围!");
		} catch (Throwable e) {
			logger.debug("根据居民信息查询居民数据时出现错误,params[PersonInfo="+person+"]", e);
			result.setSuccess(false);
			result.setMsg("根据居民信息查询居民数据时出现错误,params[PersonInfo="+person+"]");
		}		
		return result;
	}

	@Override
	public QueryResult queryPersonByIndex(String indexId) {

		QueryResult result = new QueryResult();
		try {
			List<PersonInfo> list = personInfoService.queryPersonsByIndex(indexId);
			result.setPersons(list.toArray(new PersonInfo[list.size()]));
		} catch (Throwable e) {
			logger.debug("根据索引信息查询居民数据时出现错误,params[indexId="+indexId+"]", e);
			result.setSuccess(false);
			result.setMsg("根据索引信息查询居民数据时出现错误,params[indexId="+indexId+"]");
		}
		
		return result;
	}

	@Override
	public QueryResult queryPersonByIndexAndUniqueSign(String indexId, String domainUniqueSign) {
		QueryResult result = new QueryResult();
		try {
			List<PersonInfo> list = personInfoService.queryPersonsByIndex(indexId,domainUniqueSign);
			result.setPersons(list.toArray(new PersonInfo[list.size()]));
		} catch (Throwable e) {
			logger.debug("根据索引信息查询居民数据时出现错误,params[indexId="+indexId+",domainUniqueSign="+domainUniqueSign+"]", e);
			result.setSuccess(false);
			result.setMsg("根据索引信息查询居民数据时出现错误,params[indexId="+indexId+",domainUniqueSign="+domainUniqueSign+"]");
		}
		
		return result;
	}

	public void setPersonInfoService(IPersonInfoService personInfoService) {
		this.personInfoService = personInfoService;
	}
}
