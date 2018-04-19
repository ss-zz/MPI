package com.sinosoft.pixpdqv3.iti47;

import java.util.List;
import java.util.Map;
import java.util.TreeMap;

import javax.annotation.Resource;
import javax.jws.WebParam;

import org.apache.log4j.Logger;

import com.sinosoft.mpi.exception.MpiException;
import com.sinosoft.mpi.model.PersonInfo;
import com.sinosoft.mpi.service.IPersonInfoService;
import com.sinosoft.pixpdqv3.parser.ICdaParser;
import com.sinosoft.pixpdqv3.parser.ResponseParser;

/**
 * 只实现 operation 方法 continuation 和 cancel 涉及到有状态查询 暂不实现
 */
public class Iti47PortTypeImpl implements Iti47PortType {
	
	protected static Logger log = Logger.getLogger(Iti47PortType.class);
	@Resource
	private IPersonInfoService personInfoService;
	@Resource
	private ICdaParser cdaParser;

	@Override
	public String operation(
			@WebParam(partName = "Body", name = "PRPA_IN201305UV02", targetNamespace = "urn:hl7-org:v3") String request) {
		Map<String, Object> ctx = new TreeMap<String, Object>();
		String result = null;
		// 解析xml 生成PersonInfo对象
		try {
			PersonInfo p = cdaParser.parser(request, ICdaParser.CdaType.PRPA_IN201305UV02, PersonInfo.class);
			// 取得索引人员
			List<PersonInfo> list = personInfoService.queryPersonByAttributes(p);
			// 生成返回信息
			ctx.put("ps", p);
			if (list != null && !list.isEmpty()) {
				ctx.put("pl", list);
				result = ResponseParser.parse(ResponseParser.ResponseType.PRPA_IN201306UV02, ctx);
			} else {
				result = ResponseParser.parse(ResponseParser.ResponseType.PRPA_IN201306UV02, ctx);
			}
		} catch (MpiException e) {
			ctx.put("err", "err");
			result = ResponseParser.parse(ResponseParser.ResponseType.PRPA_IN201306UV02, ctx);
		} catch (Exception e) {
			log.warn("出错了", e);
			ctx.put("err", "err");
			result = ResponseParser.parse(ResponseParser.ResponseType.PRPA_IN201306UV02, ctx);
		}
		return result;
	}

	@Override
	public String continuation(
			@WebParam(partName = "Body", name = "QUQI_IN000003UV01", targetNamespace = "urn:hl7-org:v3") String request) {
		return null; // To change body of implemented methods use File | Settings | File Templates.
	}

	@Override
	public String cancel(
			@WebParam(partName = "Body", name = "QUQI_IN000003UV01_Cancel", targetNamespace = "urn:hl7-org:v3") String request) {
		return null; // To change body of implemented methods use File | Settings | File Templates.
	}
}
