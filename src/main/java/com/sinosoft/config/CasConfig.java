package com.sinosoft.config;

import org.jasig.cas.client.authentication.AuthenticationFilter;
import org.jasig.cas.client.util.HttpServletRequestWrapperFilter;
import org.jasig.cas.client.validation.Cas20ProxyReceivingTicketValidationFilter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;

/**
 * cas 单点登录配置
 */
@Configuration
public class CasConfig {

	// cas 登录地址
	private static final String CAS_URL_LOGIN = "cas.url.login";
	// cas 前缀
	private static final String CAS_URL_PREFIX = "cas.url.prefix";
	// 应用地址
	private static final String APP_SERVICE_HOME = "app.service.home";
	// 忽略的地址
	private static final String CAS_IGNORE_PATH = "cas.url.ignore";

	@Autowired
	Environment env;

	@Bean
	public FilterRegistrationBean casAuthFilter() {
		FilterRegistrationBean filterRegister = new FilterRegistrationBean();
		AuthenticationFilter filter = new AuthenticationFilter();
		filterRegister.setFilter(filter);
		filterRegister.addUrlPatterns("/*");
		filterRegister.addInitParameter("ignorePattern", env.getProperty(CAS_IGNORE_PATH));
		filterRegister.addInitParameter("casServerLoginUrl", env.getProperty(CAS_URL_LOGIN));
		filterRegister.addInitParameter("serverName", env.getProperty(APP_SERVICE_HOME));
		return filterRegister;
	}

	@Bean
	public FilterRegistrationBean cas20Registration() {
		FilterRegistrationBean filterRegister = new FilterRegistrationBean();
		Cas20ProxyReceivingTicketValidationFilter filter = new Cas20ProxyReceivingTicketValidationFilter();
		filterRegister.setFilter(filter);
		filterRegister.addUrlPatterns("/*");
		filterRegister.addInitParameter("ignorePattern", env.getProperty(CAS_IGNORE_PATH));
		filterRegister.addInitParameter("casServerUrlPrefix", env.getProperty(CAS_URL_PREFIX));
		filterRegister.addInitParameter("serverName", env.getProperty(APP_SERVICE_HOME));
		return filterRegister;
	}

	@Bean
	public FilterRegistrationBean casHttpServletRequestFilter() {
		FilterRegistrationBean filterRegister = new FilterRegistrationBean();
		filterRegister.setFilter(new HttpServletRequestWrapperFilter());
		filterRegister.addUrlPatterns("/*");
		filterRegister.addInitParameter("ignorePattern", env.getProperty(CAS_IGNORE_PATH));
		return filterRegister;
	}

}
