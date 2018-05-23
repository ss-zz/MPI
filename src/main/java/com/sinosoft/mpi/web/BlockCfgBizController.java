package com.sinosoft.mpi.web;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.sinosoft.bizblock.config.BizBlockConfig;
import com.sinosoft.mpi.cache.CacheManager;
import com.sinosoft.mpi.context.Constant;
import com.sinosoft.mpi.model.biz.MpiBizPropertiesDesc;
import com.sinosoft.mpi.model.biz.MpiBizBlockCfg;
import com.sinosoft.mpi.service.biz.BizBlockCfgService;
import com.sinosoft.mpi.util.PageInfo;

import net.sf.json.JSONObject;

/**
 * 主索引业务控制器
 * 
 * @author admin
 *
 */
@Controller
@RequestMapping("/blockCfgbiz")
public class BlockCfgBizController {

	@Resource
	BizBlockCfgService bizBlockCfgService;

	/**
	 * 获取主索引列表数据
	 */
	@RequestMapping("/query")
	@ResponseBody
	public Map<String, Object> listIndex(PageInfo page, MpiBizBlockCfg bizBlockCfg) {
		Map<String, Object> datas = new HashMap<>();
		page.setPage(page.getPage() - 1);
		Page<MpiBizBlockCfg> ret = bizBlockCfgService.queryForPage(bizBlockCfg, page);
		// 设置总共有多少条记录
		datas.put(Constant.PAGE_TOTAL, ret.getTotalElements());
		// 设置当前页的数据
		datas.put(Constant.PAGE_ROWS, ret.getContent());
		return datas;
	}

	/**
	 * 查看页面入口
	 */
	@RequestMapping("/view")
	public ModelAndView toViewPage(String cfgId) {
		ModelAndView mv = new ModelAndView("/biz/page/block_view");
		// 取得配置信息
		MpiBizBlockCfg cfg = bizBlockCfgService.getObject(cfgId);
		mv.addObject("cfg", cfg);
		return mv;
	}

	/**
	 * 配置列表页面入口
	 */
	@RequestMapping("/toAdd")
	public ModelAndView toMatchCfgPage() {
		JSONObject datas = new JSONObject();
		// 取得居民信息 字段描述
		List<MpiBizPropertiesDesc> pList = CacheManager.getAll(MpiBizPropertiesDesc.class);
		// 字段属性
		datas.put("pList", pList);
		ModelAndView mv = new ModelAndView("/biz/page/block_add");
		mv.addObject("selectJson", datas.toString());
		return mv;
	}

	/**
	 * 添加配置
	 */
	@RequestMapping("/add")
	@ResponseBody
	public void add(@RequestBody MpiBizBlockCfg t) {
		bizBlockCfgService.save(t);
	}

	/**
	 * 查看页面入口
	 */
	@RequestMapping("/current")
	public ModelAndView toCurrentViewPage() {
		ModelAndView mv = new ModelAndView("/biz/page/current_block");
		
		BizBlockConfig bizBlockConfig = BizBlockConfig.getInstanse();
		
		MpiBizBlockCfg cfg = new MpiBizBlockCfg(bizBlockConfig);
		
		mv.addObject("cfg", cfg);
		return mv;
	}

	/**
	 * 使配置生效
	 */
	@RequestMapping("/effect")
	@ResponseBody
	public void effectCfg(String cfgId) {
		bizBlockCfgService.updateEffect(cfgId);
	}
	
	/**
	 * 删除
	 */
	@RequestMapping(params = "method=del")
	@ResponseBody
	public void del(String id) {
		bizBlockCfgService.deleteById(id);
	}
	
}
