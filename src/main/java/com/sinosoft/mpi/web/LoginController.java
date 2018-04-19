package com.sinosoft.mpi.web;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * 登录
 */
@Controller
public class LoginController {

	/**
	 * 进入登录页面
	 *
	 * @return
	 */
	@RequestMapping("/login")
	public String toLogin() {
		return "login/page/login";
	}

}
