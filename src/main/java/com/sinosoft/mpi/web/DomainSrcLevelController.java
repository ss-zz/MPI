package com.sinosoft.mpi.web;

import java.io.IOException;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONObject;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.sinosoft.mpi.cache.CacheManager;
import com.sinosoft.mpi.context.Constant;
import com.sinosoft.mpi.exception.ValidationException;
import com.sinosoft.mpi.model.DomainSrcLevel;
import com.sinosoft.mpi.model.IdentifierDomain;
import com.sinosoft.mpi.model.PersonPropertiesDesc;
import com.sinosoft.mpi.service.IDomainSrcLevelService;
import com.sinosoft.mpi.service.IIdentifierDomainService;
import com.sinosoft.mpi.util.PageInfo;

/**
 * 数据源级别控制器
 */
@Controller
@RequestMapping("/domainsrclevel/srclevel.ac")
public class DomainSrcLevelController {
	private Logger logger = Logger.getLogger(DomainSrcLevelController.class);

	@Resource
	private IDomainSrcLevelService domainSrcLevelService;
	@Resource
	private IIdentifierDomainService identifierDomainService;

	public IIdentifierDomainService getIdentifierDomainService() {
		return identifierDomainService;
	}

	public void setIdentifierDomainService(IIdentifierDomainService identifierDomainService) {
		this.identifierDomainService = identifierDomainService;
	}

	public IDomainSrcLevelService getDomainSrcLevelService() {
		return domainSrcLevelService;
	}

	public void setDomainSrcLevelService(IDomainSrcLevelService domainSrcLevelService) {
		this.domainSrcLevelService = domainSrcLevelService;
	}

	/**
	 * 取得配置列表数据
	 */
	@RequestMapping(params = "method=query")
	public String list(PageInfo page, DomainSrcLevel t, HttpServletResponse response) throws IOException {
		List<DomainSrcLevel> list = null;
		try {
			list = domainSrcLevelService.queryForPage(t, page);
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
	 * 取得配置列表数据
	 */
	@RequestMapping(params = "method=queryByID")
	public String fieldlevellist(PageInfo page, DomainSrcLevel t, HttpServletResponse response) throws IOException {
		List<DomainSrcLevel> list = null;
		try {
			list = domainSrcLevelService.queryPageByID(t, page);
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
	 * 保存添加的身份域
	 * 
	 * @throws IOException
	 */
	@RequestMapping(params = "method=add")
	public String addSrcLevel(DomainSrcLevel srclevel, HttpServletResponse response) throws IOException {
		response.setCharacterEncoding(Constant.ENCODING_UTF8);
		try {
			DomainSrcLevel entity = domainSrcLevelService.queryByID(srclevel);
			if (entity == null) {
				domainSrcLevelService.save(srclevel);
			} else {
				domainSrcLevelService.updateByDomainID(srclevel);
				// return null;
				// return
				// "域："+srclevel.getDOMAIN_ID()+",字段："+srclevel.getFIELD_NAME()+"数据源级别已设置！";
				// response.getWriter().print("域："+srclevel.getDOMAIN_ID()+",字段："+srclevel.getFIELD_NAME()+"数据源级别已设置！");
			}
		} catch (ValidationException e) {
			response.getWriter().print(e.getMessage());
		} catch (Throwable e) {
			logger.error("添加数据源级别时出现错误!", e);
			response.getWriter().print("添加数据源级别时出现错误!");
		}
		return null;
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
		// ModelAndView mv = new ModelAndView("/srclevel/page/srclevel_add");
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
	public String deleteSrclevel(DomainSrcLevel t, HttpServletResponse response) throws IOException {

		response.setCharacterEncoding(Constant.ENCODING_UTF8);
		try {
			domainSrcLevelService.delete(t);
		} catch (Throwable e) {
			logger.error("删除系统用户时出现错误!", e);
			response.getWriter().print("删除系统用户时出现错误!");
		}
		return null;
	}

	/**
	 * 配置列表页面入口
	 */
	@RequestMapping(params = "method=toEdit")
	public ModelAndView toEdit(DomainSrcLevel t, HttpServletResponse response) {
		// 取得居民信息 字段描述
		List<IdentifierDomain> domainlist = identifierDomainService.queryAll();
		List<PersonPropertiesDesc> pList = CacheManager.getAll(PersonPropertiesDesc.class);
		JSONObject datas = new JSONObject();
		// 字段属性
		datas.put("pList", pList);
		datas.put("domainList", domainlist);
		ModelAndView mv = new ModelAndView("/srclevel/page/srclevel_edit");
		mv.addObject("selectJson", datas.toString());
		return mv;
	}

	@RequestMapping(params = "method=toView")
	public ModelAndView fieldLevellist(PageInfo page, DomainSrcLevel t, HttpServletResponse response)
			throws IOException {
		List<DomainSrcLevel> list = null;
		try {
			list = domainSrcLevelService.queryByID(t.getDOMAIN_ID());
		} catch (Throwable e) {
			logger.error("查询匹配配置的时候出现错误", e);
		}
		ModelAndView mv = new ModelAndView("/domain/page/fieldlevel");
		mv.addObject("list", list);
		return mv;
	}
}
