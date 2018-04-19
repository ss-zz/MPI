package com.sinosoft.config;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * jsp页面直接访问适配器，springboot不支持jsp直接访问，故添加此适配器用于统一跳转
 */
@Controller
public class JspViewController {

	@RequestMapping("/{p1}-jsp")
	public String toJspPageL1(@PathVariable String p1) {
		return p1;
	}

	@RequestMapping("/{p1}/{p2}-jsp")
	public String toJspPageL2(@PathVariable String p1, @PathVariable String p2) {
		return p1 + "/" + p2;
	}

	@RequestMapping("/{p1}/{p2}/{p3}-jsp")
	public String toJspPageL3(@PathVariable String p1, @PathVariable String p2, @PathVariable String p3) {
		return p1 + "/" + p2 + "/" + p3;
	}

	@RequestMapping("/{p1}/{p2}/{p3}/{p4}-jsp")
	public String toJspPageL4(@PathVariable String p1, @PathVariable String p2, @PathVariable String p3,
			@PathVariable String p4) {
		return p1 + "/" + p2 + "/" + p3 + "/" + p4;
	}

	@RequestMapping("/{p1}/{p2}/{p3}/{p4}/{p5}-jsp")
	public String toJspPageL4(@PathVariable String p1, @PathVariable String p2, @PathVariable String p3,
			@PathVariable String p4, @PathVariable String p5) {
		return p1 + "/" + p2 + "/" + p3 + "/" + p4 + "/" + p5;
	}

}
