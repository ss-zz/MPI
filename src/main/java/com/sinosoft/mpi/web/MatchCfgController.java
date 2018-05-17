package com.sinosoft.mpi.web;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.sinosoft.match.config.MatchConfig;
import com.sinosoft.mpi.cache.CacheManager;
import com.sinosoft.mpi.context.Constant;
import com.sinosoft.mpi.model.MatchCfg;
import com.sinosoft.mpi.model.MatchFieldCfg;
import com.sinosoft.mpi.model.code.PersonPropertiesDesc;
import com.sinosoft.mpi.service.MatchCfgService;
import com.sinosoft.mpi.util.PageInfo;
import com.sinosoft.stringcomparison.config.StringComparisionConfig;
import com.sinosoft.stringcomparison.model.DistanceMetricType;

import net.sf.json.JSONObject;

/**
 * 匹配配置控制器
 */
@Controller
@RequestMapping("/cfg/match.ac")
public class MatchCfgController {

	@Resource
	private MatchCfgService matchCfgService;

	/**
	 * 取得配置列表数据
	 */
	@RequestMapping
	@ResponseBody
	public Map<String, Object> list(PageInfo page, MatchCfg t) {
		Map<String, Object> datas = new HashMap<>();
		List<MatchCfg> list = matchCfgService.queryForPage(t, page);
		// 设置总共有多少条记录
		datas.put(Constant.PAGE_TOTAL, page.getTotal());
		// 设置当前页的数据
		datas.put(Constant.PAGE_ROWS, list);
		return datas;
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

	/**
	 * 添加配置
	 */
	@RequestMapping(params = "method=add")
	@ResponseBody
	public void add(@RequestBody MatchCfg t) {
		matchCfgService.save(t);
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
	@ResponseBody
	public void effectCfg(String cfgId) {
		matchCfgService.updateEffect(cfgId);
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

}
