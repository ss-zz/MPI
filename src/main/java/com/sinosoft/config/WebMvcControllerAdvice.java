package com.sinosoft.config;

import org.springframework.beans.propertyeditors.StringTrimmerEditor;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.InitBinder;

@ControllerAdvice
public class WebMvcControllerAdvice {

	@InitBinder
	public void initBinder(WebDataBinder binder) {
		// 空字符串转换为null
		binder.registerCustomEditor(String.class, new StringTrimmerEditor(true));
	}

}
