package com.sinosoft.mpi.web;

import java.io.IOException;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONObject;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.sinosoft.mpi.context.Constant;
import com.sinosoft.mpi.exception.ValidationException;
import com.sinosoft.mpi.model.SysRole;
import com.sinosoft.mpi.model.SysUser;
import com.sinosoft.mpi.service.ISysUserService;

/**
 * 系统用户控制器
 */
@Controller
@RequestMapping("/sysuser/su.ac")
public class UserController {
	private Logger logger = Logger.getLogger(UserController.class);
	@Resource
	private ISysUserService sysUserService;

	/**
	 * 前往列表页面
	 */
	@RequestMapping
	public ModelAndView toListPage() {
		List<SysRole> roles = sysUserService.findRoles();
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
	public String listUsers(HttpServletResponse response) throws IOException {
		List<Map<String, Object>> list = sysUserService.findForList();
		JSONObject datas = new JSONObject();
		// 设置总共有多少条记录
		datas.put(Constant.PAGE_TOTAL, list.size());
		// 设置当前页的数据
		datas.put(Constant.PAGE_ROWS, list);
		response.setCharacterEncoding(Constant.ENCODING_UTF8);
		response.getWriter().print(datas.toString());
		return null;
	}

	/**
	 * 保存添加的系统用户
	 * 
	 * @throws IOException
	 */
	@RequestMapping(params = "method=add")
	public String addUser(SysUser user, HttpServletResponse response) throws IOException {
		response.setCharacterEncoding(Constant.ENCODING_UTF8);
		try {
			sysUserService.save(user);
		} catch (ValidationException e) {
			response.getWriter().print(e.getMessage());
		} catch (Throwable e) {
			logger.error("添加系统用户时出现错误!", e);
			response.getWriter().print("添加系统用户时出现错误!");
		}
		return null;
	}

	/**
	 * 检查用户名是否存在
	 * 
	 * @throws IOException
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
	 * 
	 * @throws IOException
	 */
	@RequestMapping(params = "method=load")
	public String loadUser(String userId, HttpServletResponse response) throws IOException {
		SysUser user = sysUserService.getObject(userId);
		JSONObject datas = JSONObject.fromObject(user);
		response.setCharacterEncoding(Constant.ENCODING_UTF8);
		response.getWriter().print(datas.toString());
		return null;
	}

	/**
	 * 保存编辑的系统用户
	 * 
	 * @throws IOException
	 */
	@RequestMapping(params = "method=edit")
	public String editUser(SysUser user, HttpServletResponse response) throws IOException {
		response.setCharacterEncoding(Constant.ENCODING_UTF8);
		try {
			sysUserService.update(user);
		} catch (Throwable e) {
			logger.error("修改系统用户时出现错误!", e);
			response.getWriter().print("修改系统用户时出现错误!");
		}
		return null;
	}

	/**
	 * 删除系统用户
	 * 
	 * @throws IOException
	 */
	@RequestMapping(params = "method=del")
	public String deleteUser(SysUser user, HttpServletResponse response) throws IOException {

		response.setCharacterEncoding(Constant.ENCODING_UTF8);
		try {
			sysUserService.delete(user);
		} catch (Throwable e) {
			logger.error("删除系统用户时出现错误!", e);
			response.getWriter().print("删除系统用户时出现错误!");
		}
		return null;
	}

	public void setSysUserService(ISysUserService sysUserService) {
		this.sysUserService = sysUserService;
	}

}
