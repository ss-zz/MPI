package com.sinosoft.mpi.web;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.sinosoft.mpi.context.Constant;
import com.sinosoft.mpi.model.IdentifierDomain;
import com.sinosoft.mpi.model.PersonIdxLog;
import com.sinosoft.mpi.service.IdentifierDomainService;
import com.sinosoft.mpi.service.PersonIdxLogService;
import com.sinosoft.mpi.util.PageInfo;

/**
 * 索引操作日志(日志角度)
 */
@Controller
@RequestMapping("/indexlog/il.ac")
public class IdxOpLogController {

	@Resource
	private PersonIdxLogService personIdxLogService;
	@Resource
	private IdentifierDomainService identifierDomainService;

	/**
	 * 显示操作日志列表
	 */
	@RequestMapping(params = "method=query")
	@ResponseBody
	public Map<String, Object> list(PageInfo page, PersonIdxLog t) {
		Map<String, Object> datas = new HashMap<>();
		List<Map<String, Object>> list = personIdxLogService.queryForMapPage(t, page);
		// 设置总共有多少条记录
		datas.put(Constant.PAGE_TOTAL, page.getTotal());
		// 设置当前页的数据
		datas.put(Constant.PAGE_ROWS, list);
		return datas;
	}

	/**
	 * 获取身份域下拉菜单
	 */
	@RequestMapping(params = "method=listDomain")
	@ResponseBody
	public List<IdentifierDomain> listDomain() throws IOException {
		List<IdentifierDomain> list = identifierDomainService.queryAll();
		IdentifierDomain first = new IdentifierDomain();
		first.setDomainId("");
		first.setDomainDesc("--请选择--");
		list.add(0, first);
		return list;
	}

	/**
	 * 查看索引日志详情
	 */
	@RequestMapping(params = "method=view")
	public String view(String logId, ModelMap modelMap) {
		Map<String, Object> map = personIdxLogService.queryMatchDetail(logId);
		modelMap.putAll(map);
		return "indexlog/page/view";
	}

	/**
	 * 查看居民详情
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
	 */
	@RequestMapping(params = "method=showIndex")
	public String showIndex(String indexId, ModelMap modelMap) {
		Map<String, Object> map = personIdxLogService.queryIndexDetail(indexId);
		modelMap.putAll(map);
		modelMap.put("type", "index");
		return "indexlog/page/show";
	}

}
