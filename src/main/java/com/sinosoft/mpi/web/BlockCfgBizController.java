package com.sinosoft.mpi.web;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.sinosoft.block.config.BlockConfig;
import com.sinosoft.mpi.cache.CacheManager;
import com.sinosoft.mpi.model.BlockCfg;
import com.sinosoft.mpi.model.biz.MpiBizBlockCfg;
import com.sinosoft.mpi.model.code.PersonPropertiesDesc;
import com.sinosoft.mpi.util.PageInfo;

/**
 * 主索引业务控制器
 * @author admin
 *
 */
@Controller
@RequestMapping("/blockCfgbiz")
public class BlockCfgBizController {
	
	/**
	 * 获取主索引列表数据
	 */
	@RequestMapping("/query")
	@ResponseBody
	public Map<String, Object> listIndex(PageInfo page,MpiBizBlockCfg bizBlockCfg) {
		Map<String, Object> datas = new HashMap<>();
		return datas;
	}
	
	/**
	 * 查看页面入口
	 */
	@RequestMapping("/view")
	public ModelAndView toViewPage(String cfgId) {
		ModelAndView mv = new ModelAndView("/biz/page/block_view");
		// 取得配置信息
		/*BlockCfg cfg = blockCfgService.getObject(cfgId);
		
		mv.addObject("cfg", cfg);*/
		return mv;
	}
	
	/**
	 * 配置列表页面入口
	 */
	@RequestMapping("/toAdd")
	public ModelAndView toMatchCfgPage() {
		Map<String, Object> datas = new HashMap<>();
		// 取得居民信息 字段描述
		List<PersonPropertiesDesc> pList = CacheManager.getAll(PersonPropertiesDesc.class);
		// 字段属性
		datas.put("pList", pList);
		ModelAndView mv = new ModelAndView("/biz/page/block_add");
		mv.addObject("selectJson", datas);
		return mv;
	}
	
	/**
	 * 添加配置
	 */
	@RequestMapping("/method=add")
	@ResponseBody
	public void add(@RequestBody MpiBizBlockCfg t) {
		/*blockCfgService.save(t);*/
	}
	
	/**
	 * 查看页面入口
	 */
	@RequestMapping("/current")
	public ModelAndView toCurrentViewPage() {
		ModelAndView mv = new ModelAndView("/biz/page/current_block");
		mv.addObject("cfg", new BlockCfg(BlockConfig.getInstanse()));
		return mv;
	}
}
