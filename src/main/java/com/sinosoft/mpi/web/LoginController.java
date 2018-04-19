package com.sinosoft.mpi.web;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;

import com.sinosoft.mpi.service.ISysUserService;

import logon.PrvManager;

/**
 * 登录
 */
@Controller
@RequestMapping("/login/login.ac")
public class LoginController {
	protected final Logger log = LoggerFactory.getLogger(this.getClass());
	@Resource
	private ISysUserService sysUserService;
	// 用户相关权限
	private PrvManager prvmgr = new PrvManager();

	/**
	 * 登录系统
	 */
	@RequestMapping
	public String login(String userName, String password, String yzm, ModelMap modelMap, HttpServletRequest request,
			HttpServletResponse response) {

		// 判断验证码是否正确 20151215-wmc(添加首页验证码)
		Object securityCodeObj = request.getSession().getAttribute("securityCode");
		String yzmstr = securityCodeObj == null ? null : securityCodeObj.toString();
		if (!yzmstr.equals(yzm)) {
			modelMap.put("error", "验证码错误!");
			return "login/page/login";
		}

		if (userName != null && !userName.trim().equals("") && password != null && !password.trim().equals("")) {
			try {
				if (!prvmgr.getPrvMgr("EHR", userName, password, request)) {// 验证身份
					modelMap.put("error", "用户名或密码错误!");
					return "login/page/login";
				}
			} catch (Exception e) {
				log.error(e.getMessage());
				e.printStackTrace();
				modelMap.put("error", "权限验证异常,请联系统管理员!");
				return "login/page/login";
			}
		} else {
			modelMap.put("error", "用户名|密码为空!");
			return "login/page/login";
		}

		return "common/page/main";

	}

	public void setSysUserService(ISysUserService sysUserService) {
		this.sysUserService = sysUserService;
	}
}
