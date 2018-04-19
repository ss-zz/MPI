package com.sinosoft.mpi.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * 登出
 * 
 * @author sinosoft
 *
 */
@Controller
@RequestMapping("/logout")
public class LogoutController {

	@Autowired
	Environment env;

	/**
	 * 登出
	 * 
	 * @return
	 */
	@RequestMapping
	public String logout() {
		// 重定向到cas单点登出地址
		return "redirect:" + env.getProperty("cas.url.logout");
	}

}
