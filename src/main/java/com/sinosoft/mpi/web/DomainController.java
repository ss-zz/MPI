package com.sinosoft.mpi.web;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;

import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.sinosoft.mpi.context.Constant;
import com.sinosoft.mpi.model.IdentifierDomain;
import com.sinosoft.mpi.service.IdentifierDomainService;
import com.sinosoft.mpi.util.PageInfo;

/**
 * 身份域控制器
 */
@Controller
@RequestMapping("/domain/domain.ac")
public class DomainController {

	@Resource
	IdentifierDomainService identifierDomainService;

	/**
	 * 显示身份域列表
	 */
	@RequestMapping(params = "method=query")
	@ResponseBody
	public Map<String, Object> listDomain(IdentifierDomain domain, PageInfo page) {
		Page<IdentifierDomain> ret = identifierDomainService.queryForPage(domain, page);
		Map<String, Object> datas = new HashMap<>();
		// 设置总共有多少条记录
		datas.put(Constant.PAGE_TOTAL, ret.getTotalElements());
		// 设置当前页的数据
		datas.put(Constant.PAGE_ROWS, ret.getContent());
		return datas;
	}

	/**
	 * 检查身份域唯一标识是否存在
	 */
	@RequestMapping(params = "method=test")
	public void testDomainName(IdentifierDomain domain, HttpServletResponse response) throws IOException {
		boolean flag = identifierDomainService.testDomain(domain.getUniqueSign());
		if (flag) {
			response.getWriter().print("true");
		} else {
			response.getWriter().print("false");
		}
	}

	/**
	 * 保存添加的身份域
	 */
	@RequestMapping(params = "method=add")
	@ResponseBody
	public void addDomain(IdentifierDomain domain) {
		identifierDomainService.save(domain);
	}

	/**
	 * 查找要更新的身份域
	 */
	@RequestMapping(params = "method=load")
	@ResponseBody
	public IdentifierDomain loadDomain(String domainId) {
		return identifierDomainService.getObject(domainId);
	}

	/**
	 * 保存编辑的身份域
	 */
	@RequestMapping(params = "method=edit")
	@ResponseBody
	public void editDomain(IdentifierDomain domain) {
		identifierDomainService.update(domain);
	}

	/**
	 * 删除身份域
	 */
	@RequestMapping(params = "method=del")
	@ResponseBody
	public void deleteDomain(IdentifierDomain domain) {
		identifierDomainService.delete(domain);
	}

}
