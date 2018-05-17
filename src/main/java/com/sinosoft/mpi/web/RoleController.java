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
import com.sinosoft.mpi.model.SysRole;
import com.sinosoft.mpi.service.SysRoleService;
import com.sinosoft.mpi.util.PageInfo;

/**
 * 系统角色控制
 */
@Controller
@RequestMapping("/role/role.ac")
public class RoleController {

	@Resource
	private SysRoleService sysRoleService;

	/**
	 * 系统角色列表
	 */
	@RequestMapping(params = "method=query")
	@ResponseBody
	public Map<String, Object> listRole(SysRole role, PageInfo page) {
		Map<String, Object> datas = new HashMap<>();
		Page<SysRole> ret = sysRoleService.queryForPage(role, page);
		datas.put(Constant.PAGE_TOTAL, ret.getTotalElements());
		datas.put(Constant.PAGE_ROWS, ret.getContent());
		return datas;
	}

	/**
	 * 列出角色下的用户
	 */
	@RequestMapping(params = "method=listUser")
	@ResponseBody
	public Map<String, Object> listRoleUser(SysRole role, PageInfo page) {
		Map<String, Object> datas = new HashMap<>();
		datas.put(Constant.PAGE_TOTAL, page.getTotal());
		datas.put(Constant.PAGE_ROWS, sysRoleService.queryRoleUser(role, page));
		return datas;
	}

	/**
	 * 检查角色名是否可用
	 */
	@RequestMapping(params = "method=test")
	public String testRoleName(SysRole role, HttpServletResponse response) throws IOException {
		boolean flag = sysRoleService.testRoleName(role);
		response.setCharacterEncoding(Constant.ENCODING_UTF8);
		if (flag) {
			response.getWriter().print("true");
		} else {
			response.getWriter().print("false");
		}
		return null;
	}

	/**
	 * 添加系统角色
	 */
	@RequestMapping(params = "method=add")
	@ResponseBody
	public void addRole(SysRole role) {
		sysRoleService.save(role);
	}

	/**
	 * 根据角色id取得角色数据
	 */
	@RequestMapping(params = "method=load")
	@ResponseBody
	public SysRole loadRole(String sysRoleId) {
		return sysRoleService.getObject(sysRoleId);
	}

	/**
	 * 修改系统角色
	 */
	@RequestMapping(params = "method=edit")
	@ResponseBody
	public void editRole(SysRole role) {
		sysRoleService.update(role);
	}

}
