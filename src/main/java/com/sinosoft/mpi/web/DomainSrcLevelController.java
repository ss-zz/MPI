package com.sinosoft.mpi.web;

import java.io.IOException;
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
import com.sinosoft.mpi.model.DomainSrcLevel;
import com.sinosoft.mpi.model.IdentifierDomain;
import com.sinosoft.mpi.model.PersonPropertiesDesc;
import com.sinosoft.mpi.service.DomainSrcLevelService;
import com.sinosoft.mpi.service.IdentifierDomainService;
import com.sinosoft.mpi.util.PageInfo;

import net.sf.json.JSONObject;

/**
 * 数据源级别控制器
 */
@Controller
@RequestMapping("/domainsrclevel/srclevel.ac")
public class DomainSrcLevelController {

	@Resource
	private DomainSrcLevelService domainSrcLevelService;
	@Resource
	private IdentifierDomainService identifierDomainService;

	/**
	 * 取得配置列表数据
	 */
	@RequestMapping(params = "method=query")
	@ResponseBody
	public Map<String, Object> list(PageInfo page, DomainSrcLevel t) throws IOException {
		Map<String, Object> datas = new HashMap<>();
		List<DomainSrcLevel> list = domainSrcLevelService.queryForPage(t, page);
		// 设置总共有多少条记录
		datas.put(Constant.PAGE_TOTAL, page.getTotal());
		// 设置当前页的数据
		datas.put(Constant.PAGE_ROWS, list);
		return datas;
	}

	/**
	 * 取得配置列表数据
	 */
	@RequestMapping(params = "method=queryByID")
	@ResponseBody
	public Map<String, Object> fieldlevellist(PageInfo page, DomainSrcLevel t) {
		Map<String, Object> datas = new HashMap<>();
		List<DomainSrcLevel> list = domainSrcLevelService.queryPageByID(t.getDOMAIN_ID(), page);
		// 设置总共有多少条记录
		datas.put(Constant.PAGE_TOTAL, page.getTotal());
		// 设置当前页的数据
		datas.put(Constant.PAGE_ROWS, list);
		return datas;
	}

	/**
	 * 保存添加的身份域
	 */
	@RequestMapping(params = "method=add")
	@ResponseBody
	public void addSrcLevel(DomainSrcLevel srclevel) {
		DomainSrcLevel entity = domainSrcLevelService.queryByDomainIdAndFieldName(srclevel.getDOMAIN_ID(),
				srclevel.getFIELD_NAME());
		if (entity == null) {
			domainSrcLevelService.save(srclevel);
		} else {
			domainSrcLevelService.updateByDomainID(srclevel);
		}
	}

	/**
	 * 配置列表页面入口
	 */
	@RequestMapping(params = "method=toAdd")
	public ModelAndView toSrcLevelPage(String domainId) {
		// 取得居民信息 字段描述
		List<IdentifierDomain> domainlist = identifierDomainService.queryByDomianId(domainId);
		List<PersonPropertiesDesc> pList = CacheManager.getAll(PersonPropertiesDesc.class);
		JSONObject datas = new JSONObject();
		// 字段属性
		datas.put("pList", pList);
		datas.put("domainList", domainlist);
		datas.put("domainid", domainId);
		ModelAndView mv = new ModelAndView("/srclevel/page/srclevel");
		mv.addObject("selectJson", datas.toString());
		return mv;
	}

	/**
	 * 删除系统用户
	 * 
	 * @throws IOException
	 */
	@RequestMapping(params = "method=del")
	@ResponseBody
	public void deleteSrclevel(DomainSrcLevel t) {
		domainSrcLevelService.delete(t);
	}

	/**
	 * 配置列表页面入口
	 */
	@RequestMapping(params = "method=toEdit")
	public ModelAndView toEdit(DomainSrcLevel t) {
		Map<String, Object> datas = new HashMap<>();
		// 取得居民信息 字段描述
		List<IdentifierDomain> domainlist = identifierDomainService.queryAll();
		List<PersonPropertiesDesc> pList = CacheManager.getAll(PersonPropertiesDesc.class);
		// 字段属性
		datas.put("pList", pList);
		datas.put("domainList", domainlist);
		ModelAndView mv = new ModelAndView("/srclevel/page/srclevel_edit");
		mv.addObject("selectJson", datas);
		return mv;
	}

	/**
	 * 查询级别列表
	 */
	@RequestMapping(params = "method=toView")
	public ModelAndView fieldLevellist(PageInfo page, DomainSrcLevel t) {
		List<DomainSrcLevel> list = domainSrcLevelService.queryByID(t.getDOMAIN_ID());
		ModelAndView mv = new ModelAndView("/domain/page/fieldlevel");
		mv.addObject("list", list);
		return mv;
	}

}
