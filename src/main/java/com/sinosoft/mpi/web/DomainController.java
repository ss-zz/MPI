package com.sinosoft.mpi.web;

import java.io.IOException;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;
import net.sf.json.JSONObject;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.sinosoft.mpi.context.Constant;
import com.sinosoft.mpi.exception.ValidationException;
import com.sinosoft.mpi.model.IdentifierDomain;
import com.sinosoft.mpi.service.IIdentifierDomainService;
import com.sinosoft.mpi.util.PageInfo;

/**
 * 身份域控制器
 */
@Controller
@RequestMapping("/domain/domain.ac")
public class DomainController {
	private Logger logger = Logger.getLogger(DomainController.class);
	@Resource
	private IIdentifierDomainService identifierDomainService;

	/**
	 * 显示身份域列表
	 * 
	 * @throws IOException
	 */
	@RequestMapping(params = "method=query")
	public String listDomain(IdentifierDomain domain, PageInfo page, HttpServletResponse response) throws IOException {
		List<IdentifierDomain> list = identifierDomainService.queryForPage(domain, page);
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
	 * 检查身份域唯一标识是否存在
	 * 
	 * @throws IOException
	 */
	@RequestMapping(params = "method=test")
	public String testDomainName(IdentifierDomain domain, HttpServletResponse response) throws IOException {
		boolean flag = identifierDomainService.testDomain(domain);
		response.setCharacterEncoding(Constant.ENCODING_UTF8);
		if (flag) {
			response.getWriter().print("true");
		} else {
			response.getWriter().print("false");
		}
		return null;
	}

	/**
	 * 保存添加的身份域
	 * 
	 * @throws IOException
	 */
	@RequestMapping(params = "method=add")
	public String addDomain(IdentifierDomain domain, HttpServletResponse response) throws IOException {
		response.setCharacterEncoding(Constant.ENCODING_UTF8);
		try {
			identifierDomainService.save(domain);
		} catch (ValidationException e) {
			response.getWriter().print(e.getMessage());
		} catch (Throwable e) {
			logger.error("添加身份域时出现错误!", e);
			response.getWriter().print("添加身份域时出现错误!");
		}
		return null;
	}

	/**
	 * 查找要更新的身份域
	 * 
	 * @throws IOException
	 */
	@RequestMapping(params = "method=load")
	public String loadDomain(String domainId, HttpServletResponse response) throws IOException {
		IdentifierDomain domain = identifierDomainService.getObject(domainId);
		JSONObject datas = JSONObject.fromObject(domain);
		response.setCharacterEncoding(Constant.ENCODING_UTF8);
		response.getWriter().print(datas.toString());
		return null;
	}

	/**
	 * 保存编辑的身份域
	 * 
	 * @throws IOException
	 */
	@RequestMapping(params = "method=edit")
	public String editDomain(IdentifierDomain domain, HttpServletResponse response) throws IOException {
		response.setCharacterEncoding(Constant.ENCODING_UTF8);
		try {
			identifierDomainService.update(domain);
		} catch (ValidationException e) {
			response.getWriter().print(e.getMessage());
		} catch (Throwable e) {
			logger.error("修改身份域时出现错误!", e);
			response.getWriter().print("修改身份域时出现错误!");
		}
		return null;
	}

	/**
	 * 删除身份域
	 * 
	 * @throws IOException
	 */
	@RequestMapping(params = "method=del")
	public String deleteDomain(IdentifierDomain domain, HttpServletResponse response) throws IOException {

		response.setCharacterEncoding(Constant.ENCODING_UTF8);
		try {
			identifierDomainService.delete(domain);
		} catch (Throwable e) {
			logger.error("删除身份域时出现错误!", e);
			response.getWriter().print("删除身份域时出现错误!");
		}
		return null;
	}

	public void setIdentifierDomainService(IIdentifierDomainService identifierDomainService) {
		this.identifierDomainService = identifierDomainService;
	}
}
