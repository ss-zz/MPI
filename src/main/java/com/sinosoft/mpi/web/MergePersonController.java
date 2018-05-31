package com.sinosoft.mpi.web;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.sinosoft.mpi.cache.CacheManager;
import com.sinosoft.mpi.context.Constant;
import com.sinosoft.mpi.dics.LogOpStyle;
import com.sinosoft.mpi.dics.LogOpType;
import com.sinosoft.mpi.model.PersonInfo;
import com.sinosoft.mpi.model.code.PerInfoPropertiesDesc;
import com.sinosoft.mpi.service.PersonInfoService;
import com.sinosoft.mpi.util.JsonDateValueProcessor;
import com.sinosoft.mpi.util.PageInfo;

import net.sf.json.JSONObject;
import net.sf.json.JsonConfig;

/**
 * 居民信息合并-合并原始居民
 */
@Controller
@RequestMapping("/merge/merge.ac")
public class MergePersonController {

	@Resource
	private PersonInfoService personInfoService;

	/**
	 * 列表显示居民信息
	 */
	@RequestMapping
	@ResponseBody
	public Map<String, Object> listPersonInfo(PersonInfo person, boolean isSurvive, PageInfo page) {
		Map<String, Object> datas = new HashMap<>();
		List<Map<String, Object>> list = personInfoService.queryForMapPage(person, isSurvive, page);
		// 设置总共有多少条记录
		datas.put(Constant.PAGE_TOTAL, page.getTotal());
		// 设置当前页的数据
		datas.put(Constant.PAGE_ROWS, list);
		return datas;
	}

	/**
	 * 合并居民详情比较页面
	 */
	@RequestMapping(params = "method=comp")
	public ModelAndView toComparePersonPage(String survivePersonId, String retiredPersonId) {
		Map<String, Object> map = personInfoService.queryComparePersonData(survivePersonId, retiredPersonId);
		List<PerInfoPropertiesDesc> fields = CacheManager.getAll(PerInfoPropertiesDesc.class);
		JsonConfig jsonConfig = new JsonConfig();
		jsonConfig.registerJsonValueProcessor(Date.class, new JsonDateValueProcessor());
		JSONObject datas = JSONObject.fromObject(map, jsonConfig);
		datas.put("fields", fields);
		ModelAndView mv = new ModelAndView("/merge/page/comp");
		mv.addObject("datas", datas.toString());
		return mv;
	}

	/*
	 * 最终合并操作
	 */
	@RequestMapping(params = "method=merge")
	@ResponseBody
	public void mergePerson(String survivePersonId, String retiredPersonId) {
		personInfoService.mergePersonFromPage(retiredPersonId, survivePersonId, LogOpType.MODIFY, LogOpStyle.MAN_MERGE);
	}

}
