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
import com.sinosoft.mpi.model.IBaseCode;
import com.sinosoft.mpi.model.ManOpPerson;
import com.sinosoft.mpi.model.PerInfoPropertiesDesc;
import com.sinosoft.mpi.model.PersonIndex;
import com.sinosoft.mpi.model.PersonInfo;
import com.sinosoft.mpi.service.ManOpPersonService;
import com.sinosoft.mpi.service.PersonIdxLogService;
import com.sinosoft.mpi.service.PersonIndexService;
import com.sinosoft.mpi.service.PersonInfoService;
import com.sinosoft.mpi.util.JsonDateValueProcessor;
import com.sinosoft.mpi.util.PageInfo;

import net.sf.json.JSONObject;
import net.sf.json.JsonConfig;

/**
 * 手工拆分合并
 */
@Controller
@RequestMapping("/manual/manual.ac")
public class ManualController {

	@Resource
	private ManOpPersonService manOpPersonService;
	@Resource
	private PersonIdxLogService personIdxLogService;
	@Resource
	private PersonIndexService personIndexService;
	@Resource
	private PersonInfoService personInfoService;

	/**
	 * 人工干预新建索引
	 */
	@RequestMapping(params = "method=addNewIndex")
	@ResponseBody
	public void addNewIndex(String opId, String personId) {
		personInfoService.addNewIndex(opId, personId);
	}

	/**
	 * 人工干预归属到索引
	 */
	@RequestMapping(params = "method=addToIndex")
	@ResponseBody
	public void addToIndex(String opId, String personId, String indexId) {
		personInfoService.addToIndex(opId, personId, indexId);
	}

	/**
	 * 查询显示需要人工干预的居民列表
	 */
	@RequestMapping(params = "method=query")
	@ResponseBody
	public Map<String, Object> list(PageInfo page, ManOpPerson t) {
		Map<String, Object> datas = new HashMap<>();
		List<Map<String, Object>> list = manOpPersonService.queryForMapPage(t, page);
		// 设置总共有多少条记录
		datas.put(Constant.PAGE_TOTAL, page.getTotal());
		// 设置当前页的数据
		datas.put(Constant.PAGE_ROWS, list);
		return datas;
	}

	/**
	 * 查询显示需要人工干预的居民列表
	 */
	@RequestMapping(params = "method=listCode")
	@ResponseBody
	public List<IBaseCode> listCode(String codeName) {
		String clzStr = "com.sinosoft.mpi.model." + codeName;
		try {
			@SuppressWarnings("unchecked")
			Class<IBaseCode> clz = (Class<IBaseCode>) Class.forName(clzStr);
			List<IBaseCode> list = CacheManager.getAll(clz);
			IBaseCode def = clz.newInstance();
			def.setCodeId("");
			def.setCodeName("--请选择--");
			list.add(0, def);
			return list;
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (InstantiationException e) {
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		}
		return null;
	}

	/**
	 * 查询显示可进行拆分的索引记录
	 */
	@RequestMapping(params = "method=list")
	@ResponseBody
	public Map<String, Object> listIndex(PageInfo page, PersonIndex index) {
		Map<String, Object> datas = new HashMap<>();
		List<Map<String, Object>> list = personIndexService.queryForSplitPage(index, page);
		// 设置总共有多少条记录
		datas.put(Constant.PAGE_TOTAL, page.getTotal());
		// 设置当前页的数据
		datas.put(Constant.PAGE_ROWS, list);
		return datas;
	}

	/**
	 * 查询显示需要人工干预的居民的匹配明细
	 */
	@RequestMapping(params = "method=matchList")
	@ResponseBody
	public List<Map<String, Object>> listMatch(String personId, int start, int end) {
		return personIdxLogService.queryMatchDetailPage(personId, start, end);
	}

	/**
	 * 查询显示需要人工干预的居民的匹配明细
	 */
	@RequestMapping(params = "method=matchListByIds")
	@ResponseBody
	public List<Map<String, Object>> listMatchByIdxIds(String personid, String[] idxIds) {
		return personIdxLogService.queryMatchDetailPageByIdxIds(personid, idxIds);
	}

	/**
	 * 查询显示所有匹配索引摘要列表
	 */
	@RequestMapping(params = "method=listMatchIndex")
	@ResponseBody
	public List<Map<String, Object>> listMatchIndex(String personId) {
		return personIdxLogService.queryMatchIndex(personId);
	}

	/**
	 * 查询显示可进行拆分的索引记录
	 */
	@RequestMapping(params = "method=listPerson")
	@ResponseBody
	public Map<String, Object> listPerson(PageInfo page, String indexId) {
		Map<String, Object> datas = new HashMap<>();
		List<Map<String, Object>> list = personInfoService.queryForSplitPersonPage(indexId, page);
		// 设置总共有多少条记录
		datas.put(Constant.PAGE_TOTAL, page.getTotal());
		// 设置当前页的数据
		datas.put(Constant.PAGE_ROWS, list);
		return datas;
	}

	/**
	 * 将人员从索引中拆分,并建立新索引与之关联
	 */
	@RequestMapping(params = "method=split")
	@ResponseBody
	public void splitPerson(String indexId, String personId) {
		personInfoService.splitPerson(indexId, personId);
	}

	/**
	 * 将人员从索引中拆分出来,并关联至指定索引
	 */
	@RequestMapping(params = "method=splitToIndex")
	@ResponseBody
	public void splitPersonToIndex(String indexId, String personId, String fromIndexId) {
		personInfoService.splitPersonToIndex(indexId, personId, fromIndexId);
	}

	/**
	 * 前往匹配列表页面
	 */
	@RequestMapping(params = "method=toMatch")
	public ModelAndView toMatchPage(String personId, String opId) {
		ModelAndView mv = new ModelAndView("/manual/page/match");
		PersonInfo person = personInfoService.getObject(personId);
		List<PerInfoPropertiesDesc> fields = CacheManager.getAll(PerInfoPropertiesDesc.class);
		int total = personIdxLogService.queryMatchIndexCount(personId);
		JsonConfig jsonConfig = new JsonConfig(); // JsonConfig是net.sf.json.JsonConfig中的这个，为固定写法
		jsonConfig.registerJsonValueProcessor(Date.class, new JsonDateValueProcessor());
		JSONObject datas = JSONObject.fromObject(person, jsonConfig);
		datas.put("fields", fields);
		datas.put("total", total);
		datas.put("opId", opId);
		mv.addObject("datas", datas.toString());
		return mv;
	}

}
