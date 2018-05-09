package com.sinosoft.mpi.web;

import java.io.IOException;
import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.sinosoft.mpi.cache.CacheManager;
import com.sinosoft.mpi.context.Constant;
import com.sinosoft.mpi.exception.ValidationException;
import com.sinosoft.mpi.model.PerInfoPropertiesDesc;
import com.sinosoft.mpi.model.PersonInfo;
import com.sinosoft.mpi.service.PersonInfoService;
import com.sinosoft.mpi.util.JsonDateValueProcessor;
import com.sinosoft.mpi.util.PageInfo;

import net.sf.json.JSONObject;
import net.sf.json.JsonConfig;

/**
 * 居民信息合并
 */
@Controller
@RequestMapping("/merge/merge.ac")
public class MergePersonController {

	private Logger logger = Logger.getLogger(MergePersonController.class);

	@Resource
	private PersonInfoService personInfoService;

	/**
	 * 列表显示居民信息
	 */
	@RequestMapping
	public String listPersonInfo(PersonInfo person, boolean isSurvive, PageInfo page, HttpServletResponse response)
			throws IOException {
		List<Map<String, Object>> list = null;
		try {
			list = personInfoService.queryForMapPage(person, isSurvive, page);
		} catch (Throwable e) {
			logger.error("查询居民信息时出错", e);
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
	 * 合并居民详情比较页面
	 */
	@RequestMapping(params = "method=comp")
	public ModelAndView toComparePersonPage(String survivePersonId, String retiredPersonId) {
		Map<String, Object> map = personInfoService.queryComparePersonData(survivePersonId, retiredPersonId);
		List<PerInfoPropertiesDesc> fields = CacheManager.getAll(PerInfoPropertiesDesc.class);
		JsonConfig jsonConfig = new JsonConfig(); // JsonConfig是net.sf.json.JsonConfig中的这个，为固定写法
		jsonConfig.registerJsonValueProcessor(Date.class, new JsonDateValueProcessor());
		JSONObject datas = JSONObject.fromObject(map, jsonConfig);
		// JSONObject datas = new JSONObject();
		// datas.putAll(map);
		datas.put("fields", fields);
		ModelAndView mv = new ModelAndView("/merge/page/comp");
		mv.addObject("datas", datas.toString());
		return mv;
	}

	/*
	 * 最终合并操作
	 */
	@RequestMapping(params = "method=merge")
	public String mergePerson(String survivePersonId, String retiredPersonId, HttpServletResponse response)
			throws IOException {
		response.setCharacterEncoding(Constant.ENCODING_UTF8);
		try {
			personInfoService.mergePersonFromPage(retiredPersonId, survivePersonId);
		} catch (ValidationException e) {
			response.getWriter().print(e.getMessage());
			logger.error("合并用户时发生错误", e);
		} catch (Throwable e) {
			response.getWriter().print("发生错误,请重试!");
			logger.error("合并用户时发生错误", e);
		}
		return null;
	}

}
