package com.sinosoft.mpi.web;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.sinosoft.mpi.context.Constant;
import com.sinosoft.mpi.model.SysRole;
import com.sinosoft.mpi.model.SysUser;
import com.sinosoft.mpi.service.SysRoleService;
import com.sinosoft.mpi.service.SysUserService;

/**
 * 系统用户控制器
 */
@Controller
@RequestMapping("/sysuser/su.ac")
public class UserController {

	@Resource
	private SysUserService sysUserService;
	@Resource
	private SysRoleService sysRoleService;

	/**
	 * 前往列表页面
	 */
	@RequestMapping
	public ModelAndView toListPage() {
		List<SysRole> roles = sysRoleService.findAll();
		ModelAndView mv = new ModelAndView("sysuser/page/su");
		mv.addObject("roles", roles);
		return mv;
	}

	/**
	 * 显示用户列表
	 * 
	 * @throws IOException
	 */
	@RequestMapping(params = "method=query")
	@ResponseBody
	public Map<String, Object> listUsers() {
		Map<String, Object> datas = new HashMap<>();
		List<Map<String, Object>> list = sysUserService.findForList();
		datas.put(Constant.PAGE_TOTAL, list.size());
		datas.put(Constant.PAGE_ROWS, list);
		return datas;
	}

	/**
	 * 保存添加的系统用户
	 */
	@RequestMapping(params = "method=add")
	@ResponseBody
	public void addUser(SysUser user) {
		sysUserService.save(user);
	}

	/**
	 * 检查用户名是否存在
	 */
	@RequestMapping(params = "method=test")
	public String testUserName(String userName, HttpServletResponse response) throws IOException {
		boolean flag = sysUserService.testUserName(userName);
		response.setCharacterEncoding(Constant.ENCODING_UTF8);
		if (flag) {
			response.getWriter().print("true");
		} else {
			response.getWriter().print("false");
		}
		return null;
	}

	/**
	 * 查找要更新的用户数据
	 */
	@RequestMapping(params = "method=load")
	@ResponseBody
	public SysUser loadUser(String userId) {
		return sysUserService.getObject(userId);
	}

	/**
	 * 保存编辑的系统用户
	 */
	@RequestMapping(params = "method=edit")
	@ResponseBody
	public void editUser(SysUser user) {
		sysUserService.update(user);
	}

	/**
	 * 删除系统用户
	 */
	@RequestMapping(params = "method=del")
	@ResponseBody
	public void deleteUser(SysUser user) {
		sysUserService.delete(user);
	}

}
