package com.sinosoft.pixpdqv3.iti44;

import com.sinosoft.mpi.exception.MpiException;
import com.sinosoft.mpi.model.PersonInfo;
import com.sinosoft.mpi.model.PersonInfoSimple;
import com.sinosoft.mpi.service.IPersonInfoService;
import com.sinosoft.pixpdqv3.dto.MergeDto;
import com.sinosoft.pixpdqv3.parser.ICdaParser;
import com.sinosoft.pixpdqv3.parser.ResponseParser;
import org.apache.log4j.Logger;

import javax.annotation.Resource;
import java.util.Map;
import java.util.TreeMap;

/**
 * PIX ITI44 接口实现 不是标准实现 in:xml+xpath->bean out:velocity->xml
 * com.sinosoft.pixpdqv3.iti44.Iti44PixPortTypeImpl
 * 
 * @author bysun 2013-04-22 11:53:32
 */
public class Iti44PixPortTypeImpl implements Iti44PixPortType {
	private final static Logger logger = Logger.getLogger(Iti44PixPortTypeImpl.class);
	@Resource
	private IPersonInfoService personInfoService;
	@Resource
	private ICdaParser cdaParser;

	/**
	 * 添加
	 * 
	 * @param request
	 *            xml
	 * @return
	 */
	@Override
	public String recordAdded(String request) {
		Map<String, Object> ctx = new TreeMap<String, Object>();
		String result = null;
		// 解析xml 生成PersonInfo对象
		try {
			PersonInfo p = cdaParser.parser(request, ICdaParser.CdaType.PRPA_IN201301UV02, PersonInfo.class);
			p.setSTATE((short) 0);
			personInfoService.save(p);
			ctx.put("msg", p.getFIELD_PK());
			// 返回正常模板内容
			result = ResponseParser.parse(ResponseParser.ResponseType.SUCCESS, ctx);
		} catch (MpiException e) {
			// 返回异常模板内容
			ctx.put("msg", e.getMessage());
			// 返回正常模板内容
			result = ResponseParser.parse(ResponseParser.ResponseType.ERROR, ctx);
		} catch (Exception e) {
			logger.error("add person error", e);
			// 返回异常模板内容
			ctx.put("msg", "消息无法解析");
			// 返回正常模板内容
			result = ResponseParser.parse(ResponseParser.ResponseType.ERROR, ctx);
		}
		return result;
	}

	/**
	 * 修改
	 * 
	 * @param request
	 *            xml
	 * @return
	 */
	@Override
	public String recordRevised(String request) {
		Map<String, Object> ctx = new TreeMap<String, Object>();
		String result = null;
		// 解析xml 生成PersonInfo对象
		try {
			PersonInfo p = cdaParser.parser(request, ICdaParser.CdaType.PRPA_IN201302UV02, PersonInfo.class);
			// save 方法
			p.setSTATE((short) 1);
			personInfoService.save(p);
			ctx.put("msg", p.getFIELD_PK());
			// 返回正常模板内容
			result = ResponseParser.parse(ResponseParser.ResponseType.SUCCESS, ctx);
		} catch (MpiException e) {
			// 返回异常模板内容
			ctx.put("msg", e.getMessage());
			// 返回正常模板内容
			result = ResponseParser.parse(ResponseParser.ResponseType.ERROR, ctx);
		} catch (Exception e) {
			logger.error("update person error", e);
			// 返回异常模板内容
			ctx.put("msg", "消息无法解析");
			// 返回正常模板内容
			result = ResponseParser.parse(ResponseParser.ResponseType.ERROR, ctx);
		}

		return result;
	}

	/**
	 * 合并
	 * 
	 * @param request
	 *            xml
	 * @return
	 */
	@Override
	public String duplicatesResolved(String request) {
		Map<String, Object> ctx = new TreeMap<String, Object>();
		try {
			MergeDto dto = cdaParser.parser(request, ICdaParser.CdaType.PRPA_IN201304UV02, MergeDto.class);
			PersonInfoSimple r = new PersonInfoSimple(dto.getDomainUniqueSign(), dto.getRetiredId());
			PersonInfoSimple s = new PersonInfoSimple(dto.getDomainUniqueSign(), dto.getSurvivingId());
			personInfoService.mergePerson(r.toPersonInfo(), s.toPersonInfo());
			ctx.put("msg", "合并成功");
		} catch (MpiException e) {
			// 返回异常模板内容
			ctx.put("msg", e.getMessage());
		} catch (Exception e) {
			logger.error("merge person error", e);
			// 返回异常模板内容
			ctx.put("msg", "消息无法解析");
		}

		return ResponseParser.parse(ResponseParser.ResponseType.ERROR, ctx);
	}
}
