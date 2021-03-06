package com.sinosoft.mpi.web;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.sinosoft.mpi.cache.CacheManager;
import com.sinosoft.mpi.context.Constant;
import com.sinosoft.mpi.model.DomainSrcLevel;
import com.sinosoft.mpi.model.IdentifierDomain;
import com.sinosoft.mpi.model.code.PersonPropertiesDesc;
import com.sinosoft.mpi.service.DomainSrcLevelService;
import com.sinosoft.mpi.service.IdentifierDomainService;
import com.sinosoft.mpi.util.PageInfo;

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

	ObjectMapper om = new ObjectMapper();

	/**
	 * 取得配置列表数据
	 */
	@RequestMapping(params = "method=query")
	@ResponseBody
	public Map<String, Object> list(PageInfo page, DomainSrcLevel t) throws IOException {
		Map<String, Object> datas = new HashMap<>();
		Page<DomainSrcLevel> ret = domainSrcLevelService.queryForPage(t, page);
		// 设置总共有多少条记录
		datas.put(Constant.PAGE_TOTAL, ret.getTotalElements());
		// 设置当前页的数据
		datas.put(Constant.PAGE_ROWS, ret.getContent());
		return datas;
	}

	/**
	 * 取得配置列表数据
	 */
	@RequestMapping(params = "method=queryByID")
	@ResponseBody
	public Map<String, Object> fieldlevellist(PageInfo page, DomainSrcLevel t) {
		Map<String, Object> datas = new HashMap<>();
		Page<DomainSrcLevel> data = domainSrcLevelService.queryPageByID(t.getDomainId(), page);
		// 设置总共有多少条记录
		datas.put(Constant.PAGE_TOTAL, data.getTotalElements());
		// 设置当前页的数据
		datas.put(Constant.PAGE_ROWS, data.getContent());
		return datas;
	}

	/**
	 * 保存添加的身份域
	 */
	@RequestMapping(params = "method=add")
	@ResponseBody
	public void addSrcLevel(DomainSrcLevel srclevel) {
		DomainSrcLevel entity = domainSrcLevelService.queryByDomainIdAndFieldName(srclevel.getDomainId(),
				srclevel.getFieldName());
		if (entity == null) {
			domainSrcLevelService.save(srclevel);
		} else {
			domainSrcLevelService.update(srclevel);
		}
	}

	/**
	 * 配置列表页面入口
	 */
	@RequestMapping(params = "method=toAdd")
	public ModelAndView toSrcLevelPage(String domainId) {
		// 取得居民信息 字段描述
		List<IdentifierDomain> domainlist = new ArrayList<IdentifierDomain>();
		IdentifierDomain domain = identifierDomainService.getObject(domainId);
		domainlist.add(domain);
		List<PersonPropertiesDesc> pList = CacheManager.getAll(PersonPropertiesDesc.class);
		Map<String, Object> datas = new HashMap<>();
		// 字段属性
		datas.put("pList", pList);
		datas.put("domainList", domainlist);
		ModelAndView mv = new ModelAndView("/srclevel/page/srclevel");
		mv.addObject("domainId", domainId);
		mv.addObject("uniqueSign", domain.getUniqueSign());
		mv.addObject("domain", domain);
		try {
			mv.addObject("selectJson", om.writeValueAsString(datas));
		} catch (JsonProcessingException e) {
			e.printStackTrace();
		}
		return mv;
	}

	/**
	 * 删除系统用户
	 * 
	 * @throws IOException
	 */
	@RequestMapping(params = "method=del")
	@ResponseBody
	public Object deleteSrclevel(DomainSrcLevel t) {
		domainSrcLevelService.delete(t);
		return new HashMap<>();
	}

	/**
	 * 查询级别列表
	 */
	@RequestMapping(params = "method=toView")
	public ModelAndView fieldLevellist(PageInfo page, DomainSrcLevel t) {
		List<DomainSrcLevel> list = domainSrcLevelService.queryByDomainID(t.getDomainId());
		ModelAndView mv = new ModelAndView("/domain/page/fieldlevel");
		mv.addObject("list", list);
		return mv;
	}

}
