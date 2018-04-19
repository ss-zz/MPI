package com.sinosoft.mpi.web;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * 首页控制器
 * 
 * @author sinosoft
 *
 */
@Controller
public class IndexController {

	/**
	 * 进入首页
	 */
	@RequestMapping({ "/", "/index", "index.html", "index.htm" })
	public String toIndexPage() {
		return "common/page/main";
	}

}
