package com.sinosoft.mpi.web;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.ModelAndView;

import com.sinosoft.bizmatch.config.BizMatchConfig;
import com.sinosoft.mpi.cache.CacheManager;
import com.sinosoft.mpi.context.Constant;
import com.sinosoft.mpi.model.biz.MpiBizPropertiesDesc;
import com.sinosoft.mpi.model.biz.MpiBizMatchCfg;
import com.sinosoft.mpi.model.biz.MpiBizMatchFieldCfg;
import com.sinosoft.mpi.service.biz.BizMatchCfgService;
import com.sinosoft.mpi.util.PageInfo;
import com.sinosoft.stringcomparison.config.StringComparisionConfig;
import com.sinosoft.stringcomparison.model.DistanceMetricType;

import net.sf.json.JSONObject;

/**
 * 匹配配置控制器
 */
@Controller
@RequestMapping("/matchCfgbiz")
public class MatchCfgBizController {

	@Resource
	private BizMatchCfgService bizMatchCfgService;

	@InitBinder
	public void initBinder(WebDataBinder binder, WebRequest request) {
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
		dateFormat.setLenient(false);
		SimpleDateFormat datetimeFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		datetimeFormat.setLenient(false);
		// 自动转换日期类型的字段格式
		binder.registerCustomEditor(Date.class, new CustomDateEditor(dateFormat, true));
	}

	/**
	 * 取得配置列表数据
	 */
	@RequestMapping("/match")
	@ResponseBody
	public Map<String, Object> list(PageInfo page, MpiBizMatchCfg t) {
		Map<String, Object> datas = new HashMap<>();
		Page<MpiBizMatchCfg> data = bizMatchCfgService.queryForPage(t, page);
		// 设置总共有多少条记录
		datas.put(Constant.PAGE_TOTAL, data.getTotalElements());
		// 设置当前页的数据
		datas.put(Constant.PAGE_ROWS, data.getContent());
		return datas;
	}

	/**
	 * 查看页面入口
	 */
	@RequestMapping("/view")
	public ModelAndView toViewPage(String cfgId) {
		// 取得配置信息
		MpiBizMatchCfg cfg = bizMatchCfgService.getObject(cfgId);
		// 取得匹配函数
		Map<String, DistanceMetricType> metrices = StringComparisionConfig.getInstanse().getDistanceMetricTypes();
		// 转码
		for (MpiBizMatchFieldCfg mfc : cfg.getMatchFieldCfgs()) {
			String fn = mfc.getMatchFunction();
			DistanceMetricType dmt = metrices.get(fn);
			if (dmt != null) {
				mfc.setMatchFunction(dmt.getNameCn());
			}
		}
		ModelAndView mv = new ModelAndView("/biz/page/match_view");
		mv.addObject("cfg", cfg);
		return mv;
	}

	/**
	 * 使配置生效
	 */
	@RequestMapping("/effect")
	@ResponseBody
	public void effectCfg(String cfgId) {
		bizMatchCfgService.updateEffect(cfgId);
	}

	/**
	 * 查看页面入口
	 */
	@RequestMapping("/current")
	public ModelAndView toCurrentViewPage() {
		// 取得配置信息
		// MatchConfig matchConfig = MatchConfig.getInstanse();

		BizMatchConfig bizMatchConfig = BizMatchConfig.getInstanse();

		MpiBizMatchCfg cfg = new MpiBizMatchCfg(bizMatchConfig);
		// 取得匹配函数
		Map<String, DistanceMetricType> metrices = StringComparisionConfig.getInstanse().getDistanceMetricTypes();
		// 转码
		for (MpiBizMatchFieldCfg mfc : cfg.getMatchFieldCfgs()) {
			String fn = mfc.getMatchFunction();
			DistanceMetricType dmt = metrices.get(fn);
			if (dmt != null) {
				mfc.setMatchFunction(dmt.getNameCn());
			}
		}
		ModelAndView mv = new ModelAndView("/biz/page/current_match");
		mv.addObject("cfg", cfg);
		return mv;
	}

	/**
	 * 配置列表页面入口
	 */
	@RequestMapping("/toAdd")
	public ModelAndView toMatchCfgPage() {
		// 取得居民信息 字段描述
		List<MpiBizPropertiesDesc> pList = CacheManager.getAll(MpiBizPropertiesDesc.class);
		// 取得匹配函数
		Map<String, DistanceMetricType> metrices = StringComparisionConfig.getInstanse().getDistanceMetricTypes();
		List<DistanceMetricType> mList = new ArrayList<DistanceMetricType>(metrices.values());
		JSONObject datas = new JSONObject();
		// 字段属性
		datas.put("pList", pList);
		// 匹配函数
		datas.put("mList", mList);
		ModelAndView mv = new ModelAndView("/biz/page/match_add");
		mv.addObject("selectJson", datas.toString());
		return mv;
	}

	/**
	 * 添加配置
	 */
	@RequestMapping("/add")
	@ResponseBody
	public void add(@RequestBody MpiBizMatchCfg t) {
		bizMatchCfgService.save(t);
	}
	
	/**
	 * 删除
	 */
	@RequestMapping("/del")
	@ResponseBody
	public void del(String id) {
		bizMatchCfgService.deleteById(id);
	}
	
}
