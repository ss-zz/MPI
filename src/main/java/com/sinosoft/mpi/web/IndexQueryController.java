package com.sinosoft.mpi.web;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.ModelAndView;

import com.sinosoft.mpi.context.Constant;
import com.sinosoft.mpi.form.MatchDetailForm;
import com.sinosoft.mpi.model.PersonIndex;
import com.sinosoft.mpi.model.PersonInfo;
import com.sinosoft.mpi.service.PersonIdxLogService;
import com.sinosoft.mpi.service.PersonIndexService;
import com.sinosoft.mpi.service.PersonInfoService;
import com.sinosoft.mpi.util.CodeConvertUtils;
import com.sinosoft.mpi.util.PageInfo;

/**
 * 索引查询
 */
@Controller
@RequestMapping("/query/query.ac")
public class IndexQueryController {

	@InitBinder
	public void initBinder(WebDataBinder binder, WebRequest request) {
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
		dateFormat.setLenient(false);
		SimpleDateFormat datetimeFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		datetimeFormat.setLenient(false);
		// 自动转换日期类型的字段格式
		binder.registerCustomEditor(Date.class, new CustomDateEditor(dateFormat, true));
	}

	@Resource
	private PersonInfoService personInfoService;
	@Resource
	private PersonIndexService personIndexService;
	@Resource
	private PersonIdxLogService personIdxLogService;

	/**
	 * 查询显示索引记录
	 */
	@RequestMapping
	@ResponseBody
	public Map<String, Object> listIndex(PageInfo page, PersonIndex index) {
		Map<String, Object> datas = new HashMap<>();
		List<Map<String, Object>> list = personIndexService.queryForSplitPage(index, page);
		converDateCode(list);
		// 设置总共有多少条记录
		datas.put(Constant.PAGE_TOTAL, page.getTotal());
		// 设置当前页的数据
		datas.put(Constant.PAGE_ROWS, list);
		return datas;
	}

	/*
	 * 处理日期
	 */
	private void converDateCode(final List<Map<String, Object>> list) {
		// 转换编码数据
		for (Map<String, Object> map : list) {
			for (Iterator<String> iterator = map.keySet().iterator(); iterator.hasNext();) {
				String key = (String) iterator.next();
				if (map.get(key) != null && map.get(key) instanceof Date) {
					if (map.get(key) != null) {
						SimpleDateFormat sdf = new SimpleDateFormat("yyyy年MM月dd日");
						String result = sdf.format((Date) map.get(key));
						map.put(key, result);
					}
				}
			}
		}
	}

	/**
	 * 查询显示索引记录
	 */
	@RequestMapping(params = "method=queryIdx")
	@ResponseBody
	public Map<String, Object> queryIndex(PageInfo page, PersonIndex index, String fromIndexId) {
		Map<String, Object> datas = new HashMap<>();
		List<Map<String, Object>> list = personIndexService.queryForSplitPage(index, fromIndexId, page);
		// 设置总共有多少条记录
		datas.put(Constant.PAGE_TOTAL, page.getTotal());
		// 设置当前页的数据
		datas.put(Constant.PAGE_ROWS, list);
		return datas;
	}

	/**
	 * 转至 索引选择页面
	 */
	@RequestMapping(params = "method=toQueryIdx")
	public ModelAndView toSelectIndexPage(String personId, String indexId) {
		// 取得居民信息
		PersonInfo person = personInfoService.getObjectWithDomainInfo(personId);
		CodeConvertUtils.convert(person);
		ModelAndView result = new ModelAndView("query/page/list");
		result.addObject("person", person);
		result.addObject("indexId", indexId);
		return result;
	}

	/**
	 * 转至 索引居民对比页面
	 */
	@RequestMapping(params = "method=toCompare")
	public ModelAndView toComparePage(String personId, String indexId, String fromIndexId) {
		// 取得居民信息
		List<MatchDetailForm> compareDatas = personIdxLogService.queryCompareData(personId, indexId);
		ModelAndView result = new ModelAndView("query/page/compare");
		result.addObject("comp", compareDatas);
		result.addObject("personId", personId);
		result.addObject("indexId", indexId);
		result.addObject("fromIndexId", fromIndexId);
		return result;
	}

	/**
	 * 查询显示索引关联的居民记录
	 */
	@RequestMapping(params = "method=listPerson")
	@ResponseBody
	public List<Map<String, Object>> listPerson(String indexId) {
		return personInfoService.queryForPersonByIndexId(indexId);
	}

}
