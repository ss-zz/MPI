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

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.sinosoft.block.config.BlockConfig;
import com.sinosoft.mpi.cache.CacheManager;
import com.sinosoft.mpi.context.Constant;
import com.sinosoft.mpi.model.BlockCfg;
import com.sinosoft.mpi.model.code.PersonPropertiesDesc;
import com.sinosoft.mpi.service.BlockCfgService;
import com.sinosoft.mpi.util.PageInfo;

/**
 * 初筛配置页面控制器
 */
@Controller
@RequestMapping("/cfg/block.ac")
public class BlockCfgController {

	@Resource
	private BlockCfgService blockCfgService;

	ObjectMapper om = new ObjectMapper();

	/**
	 * 取得配置列表数据
	 */
	@RequestMapping
	@ResponseBody
	public Map<String, Object> list(PageInfo page, BlockCfg t) {
		Map<String, Object> ret = new HashMap<>();
		Page<BlockCfg> data = blockCfgService.queryForPage(t, page);
		// 设置总共有多少条记录
		ret.put(Constant.PAGE_TOTAL, data.getTotalElements());
		// 设置当前页的数据
		ret.put(Constant.PAGE_ROWS, data.getContent());
		return ret;
	}

	/**
	 * 配置列表页面入口
	 */
	@RequestMapping(params = "method=toAdd")
	public ModelAndView toMatchCfgPage() {
		Map<String, Object> datas = new HashMap<>();
		// 取得居民信息 字段描述
		List<PersonPropertiesDesc> pList = CacheManager.getAll(PersonPropertiesDesc.class);
		// 字段属性
		datas.put("pList", pList);
		ModelAndView mv = new ModelAndView("/cfg/page/block_add");
		try {
			mv.addObject("selectJson", om.writeValueAsString(datas));
		} catch (JsonProcessingException e) {
			e.printStackTrace();
		}
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
	 * 删除
	 */
	@RequestMapping(params = "method=del")
	@ResponseBody
	public void del(String id) {
		blockCfgService.deleteById(id);
	}

	/**
	 * 查看页面入口
	 */
	@RequestMapping(params = "method=current")
	public ModelAndView toCurrentViewPage() {
		ModelAndView mv = new ModelAndView("/biz/page/current_block");
		mv.addObject("cfg", new BlockCfg(BlockConfig.getInstanse()));
		return mv;
	}

}
