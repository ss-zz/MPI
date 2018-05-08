package com.sinosoft.mpi.web;

import java.io.IOException;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.sinosoft.block.config.BlockConfig;
import com.sinosoft.index.entity.MpiConfigField;
import com.sinosoft.index.service.MpiConfigFieldService;
import com.sinosoft.mpi.context.Constant;
import com.sinosoft.mpi.exception.BaseBussinessException;
import com.sinosoft.mpi.exception.ValidationException;
import com.sinosoft.mpi.model.BlockCfg;
import com.sinosoft.mpi.service.IBlockCfgService;
import com.sinosoft.mpi.util.PageInfo;

import net.sf.json.JSONObject;

/**
 * 初筛配置页面控制器
 */
@Controller
@RequestMapping("/cfg/block.ac")
public class BlockCfgController {
	private Logger logger = Logger.getLogger(BlockCfgController.class);

	@Resource
	private IBlockCfgService blockCfgService;
	@Autowired
	MpiConfigFieldService mpiConfigFieldService;

	/**
	 * 取得配置列表数据
	 */
	@RequestMapping
	public String list(PageInfo page, BlockCfg t, HttpServletResponse response) throws IOException {
		List<BlockCfg> list = null;
		try {
			list = blockCfgService.queryForPage(t, page);
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
		// List<PersonPropertiesDesc> pList =
		// CacheManager.getAll(PersonPropertiesDesc.class);
		List<MpiConfigField> pList = mpiConfigFieldService.getAll();
		JSONObject datas = new JSONObject();
		// 字段属性
		datas.put("pList", pList);

		ModelAndView mv = new ModelAndView("/cfg/page/block_add");
		mv.addObject("selectJson", datas.toString());
		return mv;
	}

	@RequestMapping(params = "method=add")
	public String add(@RequestBody BlockCfg t, HttpServletResponse response) throws IOException {
		response.setCharacterEncoding(Constant.ENCODING_UTF8);
		try {
			blockCfgService.save(t);
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
		BlockCfg cfg = blockCfgService.getObject(cfgId);

		ModelAndView mv = new ModelAndView("/cfg/page/block_view");
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
			blockCfgService.updateEffect(cfgId);
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

		BlockConfig blockConfig = BlockConfig.getInstanse();
		BlockCfg cfg = new BlockCfg(blockConfig);

		ModelAndView mv = new ModelAndView("/cfg/page/current_block");
		mv.addObject("cfg", cfg);
		return mv;
	}

	public void setBlockCfgService(IBlockCfgService blockCfgService) {
		this.blockCfgService = blockCfgService;
	}

}
