package com.sinosoft.mpi.web;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.sinosoft.block.config.BlockConfig;
import com.sinosoft.mpi.cache.CacheManager;
import com.sinosoft.mpi.context.Constant;
import com.sinosoft.mpi.model.BlockCfg;
import com.sinosoft.mpi.model.PersonPropertiesDesc;
import com.sinosoft.mpi.service.BlockCfgService;
import com.sinosoft.mpi.util.PageInfo;

import net.sf.json.JSONObject;

/**
 * 初筛配置页面控制器
 */
@Controller
@RequestMapping("/cfg/block.ac")
public class BlockCfgController {

	@Resource
	private BlockCfgService blockCfgService;

	/**
	 * 取得配置列表数据
	 */
	@RequestMapping
	@ResponseBody
	public Map<String, Object> list(PageInfo page, BlockCfg t) {
		Map<String, Object> ret = new HashMap<>();
		List<BlockCfg> list = blockCfgService.queryForPage(t, page);
		// 设置总共有多少条记录
		ret.put(Constant.PAGE_TOTAL, page.getTotal());
		// 设置当前页的数据
		ret.put(Constant.PAGE_ROWS, list);
		return ret;
	}

	/**
	 * 配置列表页面入口
	 */
	@RequestMapping(params = "method=toAdd")
	public ModelAndView toMatchCfgPage() {
		// 取得居民信息 字段描述
		List<PersonPropertiesDesc> pList = CacheManager.getAll(PersonPropertiesDesc.class);
		JSONObject datas = new JSONObject();
		// 字段属性
		datas.put("pList", pList);
		ModelAndView mv = new ModelAndView("/cfg/page/block_add");
		mv.addObject("selectJson", datas.toString());
		return mv;
	}

	/**
	 * 添加配置
	 */
	@RequestMapping(params = "method=add")
	@ResponseBody
	public void add(@RequestBody BlockCfg t) {
		blockCfgService.save(t);
	}

	/**
	 * 查看页面入口
	 */
	@RequestMapping(params = "method=view")
	public ModelAndView toViewPage(String cfgId) {
		// 取得配置信息
		BlockCfg cfg = blockCfgService.getObject(cfgId);
		ModelAndView mv = new ModelAndView("/cfg/page/block_view");
		mv.addObject("cfg", cfg);
		return mv;
	}

	/**
	 * 使配置生效
	 */
	@RequestMapping(params = "method=effect")
	@ResponseBody
	public void effectCfg(String cfgId) {
		blockCfgService.updateEffect(cfgId);
	}

	/**
	 * 查看页面入口
	 */
	@RequestMapping(params = "method=current")
	public ModelAndView toCurrentViewPage() {
		ModelAndView mv = new ModelAndView("/cfg/page/current_block");
		mv.addObject("cfg", new BlockCfg(BlockConfig.getInstanse()));
		return mv;
	}

}
