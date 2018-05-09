package com.sinosoft.mpi.web;

import java.io.IOException;
import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.sinosoft.mpi.cache.CacheManager;
import com.sinosoft.mpi.context.Constant;
import com.sinosoft.mpi.model.IBaseCode;
import com.sinosoft.mpi.model.ManOpPerson;
import com.sinosoft.mpi.model.PerInfoPropertiesDesc;
import com.sinosoft.mpi.model.PersonIndex;
import com.sinosoft.mpi.model.PersonInfo;
import com.sinosoft.mpi.model.SexCode;
import com.sinosoft.mpi.service.ManOpPersonService;
import com.sinosoft.mpi.service.PersonIdxLogService;
import com.sinosoft.mpi.service.PersonIndexService;
import com.sinosoft.mpi.service.PersonInfoService;
import com.sinosoft.mpi.util.DateUtil;
import com.sinosoft.mpi.util.JsonDateValueProcessor;
import com.sinosoft.mpi.util.PageInfo;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import net.sf.json.JsonConfig;

/**
 * 手工拆分合并
 */
@Controller
@RequestMapping("/manual/manual.ac")
public class ManualController {

	private Logger logger = Logger.getLogger(ManualController.class);

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
	 * 
	 * @throws IOException
	 */
	@RequestMapping(params = "method=addNewIndex")
	public String addNewIndex(String opId, String personId, HttpServletResponse response) throws IOException {
		response.setCharacterEncoding(Constant.ENCODING_UTF8);
		try {
			personInfoService.addNewIndex(opId, personId);
		} catch (Throwable e) {
			response.getWriter().print("发生错误,请重试!");
			logger.error("新建索引的时候出现错误", e);
		}
		return null;
	}

	/**
	 * 人工干预归属到索引
	 * 
	 * @throws IOException
	 */
	@RequestMapping(params = "method=addToIndex")
	public String addToIndex(String opId, String personId, String indexId, HttpServletResponse response)
			throws IOException {
		response.setCharacterEncoding(Constant.ENCODING_UTF8);
		try {
			personInfoService.addToIndex(opId, personId, indexId);
		} catch (Throwable e) {
			response.getWriter().print("发生错误,请重试!");
			logger.error("合并到索引的时候出现错误", e);
		}
		return null;
	}

	private void converCode(final List<Map<String, Object>> list) {
		// 转换编码数据
		for (Map<String, Object> map : list) {
			String sexCode = (String) map.get("SEX");
			if (StringUtils.isNotBlank(sexCode)) {
				SexCode code = CacheManager.get(SexCode.class, sexCode);
				String sexName = "";
				if (code != null) {
					sexName = code.getCodeName();
				} else {
					sexName = "未知的性别";
				}
				map.put("sexName", sexName);
			}

			java.sql.Timestamp BIRTH_DATE = (java.sql.Timestamp) map.get("BIRTH_DATE");
			if (BIRTH_DATE != null) {
				// 将TIMESTAMP.DATE 转成UTIL.DATE
				java.util.Date date = new java.util.Date(BIRTH_DATE.getTime());
				map.put("BIRTH_DATE", DateUtil.getDate(date));
			}
		}
	}

	/**
	 * 查询显示需要人工干预的居民列表
	 * 
	 * @throws IOException
	 */
	@RequestMapping(params = "method=query")
	public String list(PageInfo page, ManOpPerson t, HttpServletResponse response) throws IOException {
		List<Map<String, Object>> list = null;
		try {
			list = manOpPersonService.queryForMapPage(t, page);
			// 转换编码
			converCode(list);
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
	 * 查询显示需要人工干预的居民列表
	 * 
	 * @throws IOException
	 */
	@SuppressWarnings("unchecked")
	@RequestMapping(params = "method=listCode")
	public String listCode(String codeName, HttpServletResponse response) throws IOException {
		response.setCharacterEncoding(Constant.ENCODING_UTF8);
		String clzStr = "com.sinosoft.mpi.model." + codeName;
		try {
			Class<IBaseCode> clz = (Class<IBaseCode>) Class.forName(clzStr);
			List<IBaseCode> list = CacheManager.getAll(clz);
			IBaseCode def = clz.newInstance();
			def.setCodeId("");
			def.setCodeName("--请选择--");
			list.add(0, def);
			JSONArray datas = JSONArray.fromObject(list);
			response.getWriter().print(datas.toString());
		} catch (Throwable e) {
			response.getWriter().print("[]");
		}
		return null;
	}

	/**
	 * 查询显示可进行拆分的索引记录
	 * 
	 * @throws IOException
	 */
	@RequestMapping(params = "method=list")
	public String listIndex(PageInfo page, PersonIndex index, HttpServletResponse response) throws IOException {
		List<Map<String, Object>> list = null;
		try {
			list = personIndexService.queryForSplitPage(index, page);
			// 转换编码
			converCode(list);
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
	 * 查询显示需要人工干预的居民的匹配明细
	 * 
	 * @throws IOException
	 */
	@RequestMapping(params = "method=matchList")
	public String listMatch(String personId, int start, int end, HttpServletResponse response) throws IOException {
		response.setCharacterEncoding(Constant.ENCODING_UTF8);
		try {
			List<Map<String, Object>> list = personIdxLogService.queryMatchDetailPage(personId, start, end);
			JSONArray datas = JSONArray.fromObject(list);
			response.getWriter().print(datas.toString());
		} catch (Throwable e) {
			logger.error("查询详细匹配记录时出错", e);
			response.getWriter().print("[]");
		}
		return null;
	}

	/**
	 * 查询显示需要人工干预的居民的匹配明细
	 * 
	 * @throws IOException
	 */
	@RequestMapping(params = "method=matchListByIds")
	public String listMatchByIdxIds(String personid, String[] idxIds, HttpServletResponse response) throws IOException {
		response.setCharacterEncoding(Constant.ENCODING_UTF8);
		try {
			List<Map<String, Object>> list = personIdxLogService.queryMatchDetailPageByIdxIds(personid, idxIds);
			JSONArray datas = JSONArray.fromObject(list);
			response.getWriter().print(datas.toString());
		} catch (Throwable e) {
			logger.error("查询详细匹配记录时出错", e);
			response.getWriter().print("[]");
		}
		return null;
	}

	/**
	 * 查询显示所有匹配索引摘要列表
	 * 
	 * @throws IOException
	 */
	@RequestMapping(params = "method=listMatchIndex")
	public String listMatchIndex(String personId, HttpServletResponse response) throws IOException {
		response.setCharacterEncoding(Constant.ENCODING_UTF8);
		try {
			List<Map<String, Object>> list = personIdxLogService.queryMatchIndex(personId);
			JSONArray datas = JSONArray.fromObject(list);
			response.getWriter().print(datas.toString());
		} catch (Throwable e) {
			logger.error("查询详细匹配记录时出错", e);
			response.getWriter().print("[]");
		}
		return null;
	}

	/**
	 * 查询显示可进行拆分的索引记录
	 * 
	 * @throws IOException
	 */
	@RequestMapping(params = "method=listPerson")
	public String listPerson(PageInfo page, String indexId, HttpServletResponse response) throws IOException {
		List<Map<String, Object>> list = null;
		try {
			list = personInfoService.queryForSplitPersonPage(indexId, page);
			// 转换编码
			converCode(list);
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
	 * 将人员从索引中拆分,并建立新索引与之关联
	 */
	@RequestMapping(params = "method=split")
	public String splitPerson(String indexId, String personId, HttpServletResponse response) throws IOException {
		response.setCharacterEncoding(Constant.ENCODING_UTF8);
		try {
			personInfoService.splitPerson(indexId, personId);
		} catch (Throwable e) {
			response.getWriter().print("发生错误,请重试!");
			logger.error("将人员从索引拆分出来的时候出现错误", e);
		}
		return null;
	}

	/**
	 * 将人员从索引中拆分出来,并关联至指定索引
	 */
	@RequestMapping(params = "method=splitToIndex")
	public String splitPersonToIndex(String indexId, String personId, String fromIndexId, HttpServletResponse response)
			throws IOException {
		response.setCharacterEncoding(Constant.ENCODING_UTF8);
		try {
			personInfoService.splitPersonToIndex(indexId, personId, fromIndexId);
		} catch (Throwable e) {
			response.getWriter().print("发生错误,请重试!");
			logger.error("将人员从索引拆分出来的时候出现错误", e);
		}
		return null;
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
