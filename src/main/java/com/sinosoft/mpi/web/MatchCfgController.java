package com.sinosoft.mpi.web;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONObject;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.sinosoft.match.config.MatchConfig;
import com.sinosoft.mpi.cache.CacheManager;
import com.sinosoft.mpi.context.Constant;
import com.sinosoft.mpi.exception.BaseBussinessException;
import com.sinosoft.mpi.exception.ValidationException;
import com.sinosoft.mpi.model.MatchCfg;
import com.sinosoft.mpi.model.MatchFieldCfg;
import com.sinosoft.mpi.model.PersonPropertiesDesc;
import com.sinosoft.mpi.service.IMatchCfgService;
import com.sinosoft.mpi.util.PageInfo;
import com.sinosoft.stringcomparison.config.StringComparisionConfig;
import com.sinosoft.stringcomparison.model.DistanceMetricType;

/**
 * 匹配配置控制器
 */
@Controller
@RequestMapping("/cfg/match.ac")
public class MatchCfgController {
	private Logger logger = Logger.getLogger(MatchCfgController.class);

	@Resource
	private IMatchCfgService matchCfgService;

	/**
	 * 取得配置列表数据
	 */
	@RequestMapping
	public String list(PageInfo page, MatchCfg t, HttpServletResponse response) throws IOException {
		List<MatchCfg> list = null;
		try {
			list = matchCfgService.queryForPage(t, page);
		} catch (Throwable e) {
			logger.error("查询匹配配置的时候出现错误", e);
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
	 * 配置列表页面入口
	 */
	@RequestMapping(params = "method=toAdd")
	public ModelAndView toMatchCfgPage() {
		// 取得居民信息 字段描述
		List<PersonPropertiesDesc> pList = CacheManager.getAll(PersonPropertiesDesc.class);
		// 取得匹配函数
		Map<String, DistanceMetricType> metrices = StringComparisionConfig.getInstanse().getDistanceMetricTypes();
		List<DistanceMetricType> mList = new ArrayList<DistanceMetricType>(metrices.values());
		JSONObject datas = new JSONObject();
		// 字段属性
		datas.put("pList", pList);
		// 匹配函数
		datas.put("mList", mList);
		ModelAndView mv = new ModelAndView("/cfg/page/match_add");
		mv.addObject("selectJson", datas.toString());
		return mv;
	}

	@RequestMapping(params = "method=add")
	public String add(@RequestBody MatchCfg t, HttpServletResponse response) throws IOException {
		response.setCharacterEncoding(Constant.ENCODING_UTF8);
		try {
			matchCfgService.save(t);
		} catch (ValidationException e) {
			response.getWriter().print(e.getMessage());
		} catch (Throwable e) {
			logger.error("添加匹配配置信息的时候出错!", e);
			response.getWriter().print("添加匹配配置信息的时候出错!");
		}
		return null;
	}

	/**
	 * 查看页面入口
	 */
	@RequestMapping(params = "method=view")
	public ModelAndView toViewPage(String cfgId) {
		// 取得配置信息
		MatchCfg cfg = matchCfgService.getObject(cfgId);
		// 取得匹配函数
		Map<String, DistanceMetricType> metrices = StringComparisionConfig.getInstanse().getDistanceMetricTypes();
		// 转码
		for (MatchFieldCfg mfc : cfg.getMatchFieldCfgs()) {
			String fn = mfc.getMatchFunction();
			DistanceMetricType dmt = metrices.get(fn);
			if (dmt != null) {
				mfc.setMatchFunction(dmt.getNameCn());
			}
		}
		ModelAndView mv = new ModelAndView("/cfg/page/match_view");
		mv.addObject("cfg", cfg);
		return mv;
	}

	/**
	 * 使配置生效
	 */
	@RequestMapping(params = "method=effect")
	public String effectCfg(String cfgId, HttpServletResponse response) throws IOException {
		response.setCharacterEncoding(Constant.ENCODING_UTF8);
		try {
			matchCfgService.updateEffect(cfgId);
		} catch (BaseBussinessException e) {
			response.getWriter().print(e.getMessage());
		} catch (Throwable e) {
			logger.error("激活匹配配置时发生错误!", e);
			response.getWriter().print("激活匹配配置时发生错误!");
		}
		return null;
	}

	/**
	 * 查看页面入口
	 */
	@RequestMapping(params = "method=current")
	public ModelAndView toCurrentViewPage() {
		// 取得配置信息
		MatchConfig matchConfig = MatchConfig.getInstanse();
		MatchCfg cfg = new MatchCfg(matchConfig);
		// 取得匹配函数
		Map<String, DistanceMetricType> metrices = StringComparisionConfig.getInstanse().getDistanceMetricTypes();
		// 转码
		for (MatchFieldCfg mfc : cfg.getMatchFieldCfgs()) {
			String fn = mfc.getMatchFunction();
			DistanceMetricType dmt = metrices.get(fn);
			if (dmt != null) {
				mfc.setMatchFunction(dmt.getNameCn());
			}
		}
		ModelAndView mv = new ModelAndView("/cfg/page/current_match");
		mv.addObject("cfg", cfg);
		return mv;
	}

	public void setMatchCfgService(IMatchCfgService matchCfgService) {
		this.matchCfgService = matchCfgService;
	}
}
