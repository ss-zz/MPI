package com.sinosoft.mpi.web;

import java.io.IOException;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;

import com.sinosoft.mpi.context.Constant;
import com.sinosoft.mpi.model.IdentifierDomain;
import com.sinosoft.mpi.model.PersonIdxLog;
import com.sinosoft.mpi.service.IIdentifierDomainService;
import com.sinosoft.mpi.service.IPersonIdxLogService;
import com.sinosoft.mpi.util.PageInfo;

/**
 * 索引操作日志(日志角度)
 */
@Controller
@RequestMapping("/indexlog/il.ac")
public class IdxOpLogController {
	private Logger logger = Logger.getLogger(IdxOpLogController.class);
	@Resource
	private IPersonIdxLogService personIdxLogService;
	@Resource
	private IIdentifierDomainService identifierDomainService;

	/**
	 * 显示操作日志列表
	 * 
	 * @throws IOException
	 */
	@RequestMapping(params = "method=query")
	public String list(PageInfo page, PersonIdxLog t, HttpServletResponse response) throws IOException {
		List<Map<String, Object>> list = null;
		try {
			list = personIdxLogService.queryForMapPage(t, page);
		} catch (Throwable e) {
			logger.error("查询索引日志时出错", e);
		}
		JSONObject datas = new JSONObject();
		// 设置总共有多少条记录
		datas.put(Constant.PAGE_TOTAL, page.getTotal());
		// 设置当前页的数据
		datas.put(Constant.PAGE_ROWS, list);
		response.setCharacterEncoding(Constant.ENCODING_UTF8);
		response.getWriter().print(datas.toString());
		return null;
	}

	/**
	 * 获取身份域下拉菜单
	 * 
	 * @throws IOException
	 */
	@RequestMapping(params = "method=listDomain")
	public String listDomain(HttpServletResponse response) throws IOException {
		response.setCharacterEncoding(Constant.ENCODING_UTF8);
		try {
			List<IdentifierDomain> list = identifierDomainService.queryAll();
			IdentifierDomain first = new IdentifierDomain();
			first.setDOMAIN_ID("");
			first.setDOMAIN_DESC("--请选择--");
			list.add(0, first);
			JSONArray datas = JSONArray.fromObject(list);
			response.getWriter().print(datas.toString());
		} catch (Throwable e) {
			response.getWriter().print("[]");
		}
		return null;
	}

	/**
	 * 查看索引日志详情
	 * 
	 * @throws IOException
	 */
	@RequestMapping(params = "method=view")
	public String view(String logId, ModelMap modelMap) {
		Map<String, Object> map = personIdxLogService.queryMatchDetail(logId);
		modelMap.putAll(map);
		return "indexlog/page/view";
	}

	/**
	 * 查看居民详情
	 * 
	 * @throws IOException
	 */
	@RequestMapping(params = "method=showPerson")
	public String showPerson(String personId, ModelMap modelMap) {
		Map<String, Object> map = personIdxLogService.queryPersonDetail(personId);
		modelMap.putAll(map);
		modelMap.put("type", "person");
		return "indexlog/page/show";
	}

	/**
	 * 查看索引详情
	 * 
	 * @throws IOException
	 */
	@RequestMapping(params = "method=showIndex")
	public String showIndex(String indexId, ModelMap modelMap) {
		Map<String, Object> map = personIdxLogService.queryIndexDetail(indexId);
		modelMap.putAll(map);
		modelMap.put("type", "index");
		return "indexlog/page/show";
	}

	public void setPersonIdxLogService(IPersonIdxLogService personIdxLogService) {
		this.personIdxLogService = personIdxLogService;
	}

	public void setIdentifierDomainService(IIdentifierDomainService identifierDomainService) {
		this.identifierDomainService = identifierDomainService;
	}

}
